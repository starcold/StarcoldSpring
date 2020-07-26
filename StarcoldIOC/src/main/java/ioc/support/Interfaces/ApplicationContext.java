/**
 * author: starcold
 * createTime: 2020/07/10
 * context: 定义获取bean的方法，由根据id的，也由获取所有的
 * updateTime:
 * updateContext:
 */
package ioc.support.Interfaces;

import java.util.Map;

public interface ApplicationContext {
    /**
     * @name getBean
     * @param id String
     * @return Object
     * @description 根据id获取bean
     * */
    Object getBean(String id);

    /**
     * @name getBean
     * @param id String
     * @param clazz Class<T>
     * @return T
     * @description：根据id获取特定类型的bean
     * */
    <T>T getBean(String id, Class<T> clazz);

    /**
     * @name getBeans
     * @param null
     * @return Map<String, Object>
     * @description：获取工厂所有的bean
     * */
    Map<String, Object> getBeans();

}
