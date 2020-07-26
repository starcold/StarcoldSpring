/**
 * author: starcold
 * createTime: 2020/07/10
 * context: 定义向工厂内注册bean和beanDefinition的方法
 * updateTime:
 * updateContext:
 */
package ioc.support.Interfaces;

import ioc.support.BeanDefinition;

import java.util.List;

public interface BeanRegister {
    /**
     * @name regiestBeanDefinition
     * @param bds List<BeanDefinition>
     * @return null
     * @description 向工厂内注册BeanDefinition
     */
    void registBeanDefinition(List<BeanDefinition> bds);

    /**
     * @name registInstanceMapping
     * @param id String
     * @param instance Object
     * @return null
     * @description 向工厂内注册Bean实例对象
     */
    void registInstanceMapping(String id, Object instance);
}
