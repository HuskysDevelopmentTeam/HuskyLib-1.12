package net.hdt.huskylib2.core;

import net.hdt.huskylib2.HuskyLib;
import org.apache.commons.lang3.tuple.Pair;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public interface Transformer {
    byte[] transform(byte[] basicClass);

    default byte[] transform(byte[] basicClass, Pair<MethodSignature, ClassTransformer.MethodAction>... methods) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);

        boolean didAnything = false;

        for (Pair<MethodSignature, ClassTransformer.MethodAction> pair : methods) {
            HuskyLib.log("Applying Transformation to method (" + pair.getLeft() + ")");
            didAnything |= ClassTransformer.findMethodAndTransform(node, pair.getLeft(), pair.getRight());
        }

        if (didAnything) {
            ClassWriter writer = new SafeClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            node.accept(writer);
            return writer.toByteArray();
        }

        return basicClass;
    }


    String[] getClasses();
}
