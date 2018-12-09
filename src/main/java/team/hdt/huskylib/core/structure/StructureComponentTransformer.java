package team.hdt.huskylib.core.structure;

import org.apache.commons.lang3.tuple.Pair;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import team.hdt.huskylib.HuskyLib;
import team.hdt.huskylib.core.ASMHooks;
import team.hdt.huskylib.core.ClassTransformer;
import team.hdt.huskylib.core.MethodSignature;
import team.hdt.huskylib.core.Transformer;

public class StructureComponentTransformer implements Transformer {

    @Override
    public byte[] transform(byte[] basicClass) {
        HuskyLib.log("Transforming StructureComponent.setBlockState");
        MethodSignature sig = new MethodSignature(HuskyLib.STRUCTURECOMPONENT_SETBLOCKSTATE);

        return transform(basicClass, (Pair<MethodSignature, ClassTransformer.MethodAction>) Pair.of(sig, ClassTransformer.combine(
                (AbstractInsnNode node) -> { // Filter
                    return node.getOpcode() == Opcodes.ASTORE;
                },
                (MethodNode method, AbstractInsnNode node) -> { // Action
                    InsnList newInstructions = new InsnList();

                    newInstructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); //this
                    newInstructions.add(new VarInsnNode(Opcodes.ALOAD, 1)); //world
                    newInstructions.add(new VarInsnNode(Opcodes.ALOAD, 7)); //pos

                    newInstructions.add(new VarInsnNode(Opcodes.ILOAD, 3)); //x
                    newInstructions.add(new VarInsnNode(Opcodes.ILOAD, 4)); //y
                    newInstructions.add(new VarInsnNode(Opcodes.ILOAD, 5)); //z

                    newInstructions.add(new VarInsnNode(Opcodes.ALOAD, 2)); //state
                    newInstructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, ASMHooks.HOOKS, "onStructureSetBlock", "(Lnet/minecraft/world/gen/structure/StructureComponent;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;IIILnet/minecraft/block/state/IBlockState;)Lnet/minecraft/block/state/IBlockState;", false));
                    newInstructions.add(new VarInsnNode(Opcodes.ASTORE, 2)); //state

                    method.instructions.insert(node, newInstructions);
                    return true;
                })));
    }

    @Override
    public String[] getClasses() {
        return new String[]{
                "net.minecraft.world.gen.structure.StructureComponent"
        };
    }
}
