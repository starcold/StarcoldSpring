package aop;

import aop.Impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

public class SpringAopTest {

    //代理对象的创建
    @Test
    public void createProxyTest(){
        Object target = new UserServiceImpl();
        ProxyFactory pf = new ProxyFactory(target);
        //方法执行次数计数器
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        pf.addAdvice(counting);
        UserService service = (UserService)pf.getProxy();
        service.getUser();
    }


    //添加多个的情况
    @Test
    public void createProxyTest2(){
        Object target = new UserServiceImpl();
        ProxyFactory pf = new ProxyFactory(target);
        //方法执行次数计数器
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        //添加两个advice
        pf.addAdvice(counting);
        pf.addAdvice(counting);
        UserService service = (UserService)pf.getProxy();
        service.getUser();
    }

    //动态的移除和添加
    @Test
    public void createProxyTest3(){
        Object target = new UserServiceImpl();
        ProxyFactory pf = new ProxyFactory(target);
        UserService service = (UserService)pf.getProxy();
        service.getUser();

        Advised advised = (Advised) service;
        //方法执行次数计数器
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        //动态添加
        advised.addAdvice(counting);
        service.getUser();
        //移除
        advised.removeAdvice(counting);
    }
    //AspectJProxyFactory
    @Test
    public void createProxyAspectJByTest(){
        Object target = new UserServiceImpl();
        AspectJProxyFactory pf = new AspectJProxyFactory(target);
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        //设置表达式
        advisor.setExpression("execution(* *.getUser(..))");//只有getUser方法才会起作用；
        //计数器
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        //设置目标
        advisor.setAdvice(counting);
        pf.addAdvisor(advisor);
        //获取代理对象
        pf.getProxy();

        UserService service = (UserService)pf.getProxy();
        service.getUser();
        service.setUser();
    }

    //jdk proxy和CGLib
    @Test
    public void createProxyFactoryTest(){
        Object target = new UserServiceImpl();
        CountingBeforeAdvice counting = new CountingBeforeAdvice();

        DefaultAopProxyFactory factory = new DefaultAopProxyFactory();
        AdvisedSupport config = new AdvisedSupport();

        //这里的三行和后面的两行执行效果完全相同，因为Pointcut为true
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(counting);
        config.addAdvisor(advisor);

        //设置切入的目标
        config.setTarget(target);
        //添加通知——计数功能
        config.addAdvice(counting);
        //创建aop代理
        factory.createAopProxy(config);//有接口，走JDK；源码里有个IsInterface()方法；
    }

    public class CountingBeforeAdvice implements MethodBeforeAdvice {

        private int count;

        public void before(Method m, Object[] args, Object target) throws Throwable {
            ++count;
        }
    }
}
