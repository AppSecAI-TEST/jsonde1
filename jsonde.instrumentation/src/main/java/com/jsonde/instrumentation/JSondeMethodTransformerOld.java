package com.jsonde.instrumentation;

import com.jsonde.profiler.Profiler;
import com.jsonde.util.ClassUtils;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class JSondeMethodTransformerOld extends AdviceAdapter {

    private final long methodId;
    private final Label startFinallyLabel = new Label();

    private final boolean isConstructor;
    private final boolean isStaticConstructor;
    private final String className;
    private final String parentClassName;

    private static final String PROFILER_CLASS_INTERNAL_NAME =
            ClassUtils.getInternalClassName(Profiler.CLASS_CANONICAL_NAME);

    public JSondeMethodTransformerOld(long methodId, MethodVisitor mv, int access, String name, String desc, String className, String parentClassName) {
        super(Opcodes.ASM4, mv, access, name, desc);
        this.methodId = methodId;
        this.isConstructor = name.equals(ClassUtils.CONSTRUCTOR_METHOD_NAME);
        this.isStaticConstructor = name.equals(ClassUtils.STATIC_CONSTRUCTOR_METHOD_NAME);
        this.className = className;
        this.parentClassName = parentClassName;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitLabel(startFinallyLabel);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        Label endFinallyLabel = new Label();
        mv.visitTryCatchBlock(startFinallyLabel, endFinallyLabel, endFinallyLabel, null);
        mv.visitLabel(endFinallyLabel);
        onFinally(ATHROW);
        mv.visitInsn(ATHROW);
        mv.visitMaxs(maxStack, maxLocals);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc) {

        if (isConstructor && name.equals(ClassUtils.CONSTRUCTOR_METHOD_NAME) && (owner.equals(parentClassName) || ClassUtils.getFullyQualifiedName(owner).equals(className))) {

            super.visitLdcInsn(methodId);

            super.visitMethodInsn(
                    INVOKESTATIC,
                    PROFILER_CLASS_INTERNAL_NAME,
                    Profiler.PRE_ENTER_CONSTRUCTOR_METHOD_NAME,
                    Profiler.PRE_ENTER_CONSTRUCTOR_METHOD_DESCRIPTOR
            );

        }

        super.visitMethodInsn(opcode, owner, name, desc);

    }


    private void onFinally(int opcode) {

        if (opcode == ATHROW) {

            super.visitInsn(DUP);

            super.visitMethodInsn(
                    INVOKESTATIC,
                    PROFILER_CLASS_INTERNAL_NAME,
                    Profiler.LEAVE_METHOD_THROW_EXCEPTION_METHOD_NAME,
                    Profiler.LEAVE_METHOD_THROW_EXCEPTION_METHOD_DESCRIPTOR
            );

        } else if (opcode == RETURN) {

            super.visitMethodInsn(
                    INVOKESTATIC,
                    PROFILER_CLASS_INTERNAL_NAME,
                    Profiler.LEAVE_METHOD_METHOD_NAME,
                    Profiler.LEAVE_METHOD_METHOD_DESCRIPTOR
            );

        } else {

            if (opcode == LRETURN || opcode == DRETURN) {
                super.visitInsn(DUP2);
            } else {
                super.visitInsn(DUP);
            }

            box(Type.getReturnType(methodDesc));

            super.visitMethodInsn(
                    INVOKESTATIC,
                    PROFILER_CLASS_INTERNAL_NAME,
                    Profiler.LEAVE_METHOD_RETURN_VALUE_METHOD_NAME,
                    Profiler.LEAVE_METHOD_RETURN_VALUE_METHOD_DESCRIPTOR
            );

        }

    }

    @Override
    protected void onMethodExit(int opcode) {
        if (opcode != ATHROW) {
            onFinally(opcode);
        }
    }

    @Override
    protected void onMethodEnter() {

        try {

            if ((ACC_STATIC & methodAccess) == 0) {

                int argIndex = generateArgumentsArray(1);

                super.visitLdcInsn(methodId);
                super.visitVarInsn(ALOAD, 0);
                super.visitVarInsn(ALOAD, argIndex);

                if (isConstructor) {
                    super.visitMethodInsn(
                            INVOKESTATIC,
                            PROFILER_CLASS_INTERNAL_NAME,
                            Profiler.ENTER_CONSTRUCTOR_METHOD_NAME,
                            Profiler.ENTER_CONSTRUCTOR_METHOD_DESCRIPTOR
                    );
                } else {
                    super.visitMethodInsn(
                            INVOKESTATIC,
                            PROFILER_CLASS_INTERNAL_NAME,
                            Profiler.ENTER_METHOD_METHOD_NAME,
                            Profiler.ENTER_METHOD_METHOD_DESCRIPTOR
                    );
                }

            } else {

                if (isStaticConstructor) {

                    super.visitLdcInsn(methodId);

                    super.visitMethodInsn(
                            INVOKESTATIC,
                            PROFILER_CLASS_INTERNAL_NAME,
                            Profiler.DESCRIBE_CLASS_METHOD_NAME,
                            Profiler.DESCRIBE_CLASS_METHOD_DESCRIPTOR
                    );
                }

                int argIndex = generateArgumentsArray(0);

                super.visitLdcInsn(methodId);
                super.visitInsn(ACONST_NULL);
                super.visitVarInsn(ALOAD, argIndex);

                super.visitMethodInsn(
                        INVOKESTATIC,
                        PROFILER_CLASS_INTERNAL_NAME,
                        Profiler.ENTER_METHOD_METHOD_NAME,
                        Profiler.ENTER_METHOD_METHOD_DESCRIPTOR
                );

            }
        } catch (Throwable e) {
        	JOptionPane.showMessageDialog (
					null , "Eccezione lanciata"
			);
        }

    }

}
