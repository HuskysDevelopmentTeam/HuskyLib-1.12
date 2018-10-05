package net.hdt.huskylib2.core;

import com.google.common.collect.Sets;
import net.hdt.huskylib2.HuskyLib;
import net.hdt.huskylib2.core.structure.StructureComponentTransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ClassTransformer implements IClassTransformer {

    private static final Set<Transformer> transformers = Sets.newHashSet();

    static {
        transformers.add(new StructureComponentTransformer());
    }

    public static boolean findMethodAndTransform(ClassNode node, MethodSignature sig, MethodAction pred) {
        String funcName = sig.funcName;

        for (MethodNode method : node.methods) {
            if ((method.name.equals(funcName) || method.name.equals(sig.obfName) || method.name.equals(sig.srgName)) && (method.desc.equals(sig.funcDesc))) {
                HuskyLib.log("Located Method, patching...");

                boolean finish = pred.test(method);
                HuskyLib.log("Patch result: " + finish);

                return finish;
            }
        }

        HuskyLib.log("Failed to locate the method!");
        return false;
    }

    public static MethodAction combine(NodeFilter filter, NodeAction action) {
        return (MethodNode mnode) -> applyOnNode(mnode, filter, action);
    }

    public static boolean applyOnNode(MethodNode method, NodeFilter filter, NodeAction action) {
        Iterator<AbstractInsnNode> iterator = method.instructions.iterator();

        boolean didAny = false;
        while (iterator.hasNext()) {
            AbstractInsnNode anode = iterator.next();
            if (filter.test(anode)) {
                HuskyLib.log("Located patch target node " + getNodeString(anode));
                didAny = true;
                if (action.test(method, anode))
                    break;
            }
        }

        return didAny;
    }


    private static String getNodeString(AbstractInsnNode node) {
        Printer printer = new Textifier();

        TraceMethodVisitor visitor = new TraceMethodVisitor(printer);
        node.accept(visitor);

        StringWriter sw = new StringWriter();
        printer.print(new PrintWriter(sw));
        printer.getText().clear();

        return sw.toString().replaceAll("\n", "").trim();
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        for (Transformer transformer : transformers) {
            for (String clazz : transformer.getClasses()) {

                if (clazz.equals(transformedName)) {
                    return transformer.transform(basicClass);
                }
            }
        }
        return basicClass;
    }


    public interface MethodAction extends Predicate<MethodNode> {
    }

    public interface NodeFilter extends Predicate<AbstractInsnNode> {
    }

    public interface NodeAction extends BiPredicate<MethodNode, AbstractInsnNode> {
    }

}
