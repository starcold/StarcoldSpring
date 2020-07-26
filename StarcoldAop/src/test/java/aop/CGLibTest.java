package aop;

import aop.Impl.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibTest {
    public static void main(String[] args) {
        final UserServiceImpl serviceImpl = new UserServiceImpl();
        //定义一个增强器
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        //设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("前置方法");
                Object result = null;
                try{
                    result = methodProxy.invoke(serviceImpl, objects);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println("后置方法");
                return result;
            }
        });
        UserService userService = (UserService)enhancer.create();
        userService.getUser();
    }
}
