/**
 * author: starcold
 * createTime: 2020/07/10
 * context: 创建bean并且天骄到容器工厂
 * updateTime:
 * updateContext:
 */
package ioc.support;

import ioc.support.Interfaces.BeanRegister;

import java.util.List;

public class BeanCreater{
    //注册器
    private BeanRegister register;

    /**
     * @name BeanCreater
     * @param register BeanRegister
     * @return null
     * @description：构造函数
     * */
    public BeanCreater(BeanRegister register){
        this.register = register;
    }

    /**
     * @name create
     * @param bds List<BeanDefinition>
     * @return null
     * @description：根据BeanDefinition创建bean对象
     * */
    public void create(List<BeanDefinition> bds){
        for(BeanDefinition bd : bds){
            doCreate(bd);
        }
    }

    /**
     * @name doCreate
     * @param bd BeanDefinition
     * @return null
     * @description：创建bean对象的具体实现
     * */
    public void doCreate(BeanDefinition bd){
        //生成实例
        Object instance = bd.getInstance();
        //注册实例路由
        this.register.registInstanceMapping(bd.getId(), instance);
    }
}
