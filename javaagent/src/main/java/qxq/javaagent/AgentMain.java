package qxq.javaagent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/28 15:20
 **/
public class AgentMain {
    public static class ClassLoggerTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] classfileBuffer) {
            System.out.println("transform: " + className);
            return classfileBuffer;
        }
    }

    public static void premain(String agentArgument, Instrumentation instrumentation) {
        ClassFileTransformer classFileTransformer = new ClassLoggerTransformer();
        instrumentation.addTransformer(classFileTransformer, true);
    }
}
