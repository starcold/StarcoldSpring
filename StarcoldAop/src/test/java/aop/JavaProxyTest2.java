package aop;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class JavaProxyTest2 {
    public JavaProxyTest2() {
    }

    public static void main(String[] args) throws IOException {
        int accessFlags = 17;
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass("proxy0", new Class[]{UserService.class}, accessFlags);
        Path path = (new File(System.getProperty("user.dir") + "/target/proxy0.class")).toPath();
        Files.write(path, proxyClassFile, new OpenOption[0]);
        //class
        //使用classLoader.define(ProxyCLassFile)加载字节码到classLoader里去
        //classLoader.getClass()拿到class;
    }
}
