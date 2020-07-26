/**
 * author: starcold
 * createTime: 2020/07/13
 * context: 完成bean之间的依赖注入
 * updateTime:
 * updateContext:
 */

package ioc.support;

import ioc.annotation.Autowire;

import java.lang.reflect.Field;
import java.util.Map;

public class Populator {
    /**
     * @name Populator
     * @return null
     * @description：构造函数
     * */
    public Populator(){

    }

    /**
     * @name populate
     * @param instanceMapping Map<String, Object>
     * @return null
     * @description：依赖注入
     * */
    public void populator(Map<String, Object> instanceMapping) {
        //首先要判断容器里有没有东西,没有就返回
        if (instanceMapping.isEmpty()) return;
        //循环遍历容器中每一个对象
        for (Map.Entry<String, Object> entry : instanceMapping.entrySet()) {
            //获取对象字段
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            //遍历字段
            for (Field field : fields) {
                //判断字段有没有Autowire注解
                if (!field.isAnnotationPresent(Autowire.class)) continue;
                //获取Autowire对象
                Autowire autowire = field.getAnnotation(Autowire.class);
                //获取字段要注入的id value, 为空则按类名, 接口名自动注入
                String id = autowire.value();
                if ("".equals(id)) id = field.getType().getName();
                //类中的成员变量为private,就必须进行此setAccessible操作
                field.setAccessible(true);
                try {
                    //反射注入
                    field.set(entry.getValue(), instanceMapping.get(id));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
