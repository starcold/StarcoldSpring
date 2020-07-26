package aop;

import aop.Impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JavaProxyTest {
    public JavaProxyTest() {
    }

    public static void main(final String[] args) {
        final UserServiceImpl serviceImpl = new UserServiceImpl();
        UserService userService = (UserService)Proxy.newProxyInstance(JavaProxyTest.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("前置通知");

                try {
                    method.invoke(serviceImpl, args);
                } catch (Exception var5) {
                    System.out.println("异常通知");
                    var5.printStackTrace();
                }

                System.out.println("后置通知");
                return null;
            }
        });
        userService.getUser();
    }
}