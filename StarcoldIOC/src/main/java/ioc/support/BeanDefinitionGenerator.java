/**
 * author: starcold
 * createTime: 2020/07/13
 * context: 根据具体类名完成BeanDefinition的生成
 * updateTime:
 * updateContext:
 */
package ioc.support;

import ioc.annotation.Component;
import ioc.annotation.Controller;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinitionGenerator {

    /**
     * @name generte
     * @param classname String
     * @return null
     * @description：生成BeanDefinition列表
     * */
    public static List<BeanDefinition> generte(String classname){
        try{
            //获取类型
            Class clazz = Class.forName(classname);
            //生成id数组
            String[] ids = generateIds(clazz);
            //如果id数组为空，返回null
            if(ids==null){
                return null;
            }
            //BeanDefinition列表
            List<BeanDefinition> list = new ArrayList<BeanDefinition>();
            //遍历id数组
            for(String id:ids){
                //将BeanDefinition添加到列表中
                list.add(new BeanDefinition(id, clazz));
            }
            return list;
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @name generateIds
     * @param clazz Class
     * @return null
     * @description：生成Id数组
     * */
    private static String[] generateIds(Class clazz){
        String[] ids = null;
        //Controller注解是否在clazz类上,@Controller注解一般没有value
        if(clazz.isAnnotationPresent(Controller.class)){
            //用类的全名作为id返回;ids长度为1;
            ids = new String[]{clazz.getName()};
        }
        //Component注解是否在clazz类上
        else if(clazz.isAnnotationPresent(Component.class)){
            Component component = (Component) clazz.getAnnotation(Component.class);
            //获取Compoment注解的value;
            String value = component.value();
            //有value，返回value;
            if(!"".equals(value)){
                ids = new String[]{value};
            }
            //没有value;获取其所有实现的接口, 接口名为id, 返回Ids数组;
            else{
                Class<?>[]  interfaces = clazz.getInterfaces();
                ids = new String[interfaces.length];
                //如果实现了接口，就用接口类型作为id
                for(int i = 0; i < interfaces.length; i++){
                    ids[i] = interfaces[i].getName();
                }
                return ids;
            }
        }
        return ids;
    }
}
