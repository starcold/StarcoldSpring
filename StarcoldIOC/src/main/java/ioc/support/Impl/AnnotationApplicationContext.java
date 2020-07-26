/**
 * author: starcold
 * createTime: 2020/07/10
 * context：AnnotationApplicationContext容器的具体实现类
 * updateTime:
 * updateContext:
 */
package ioc.support.Impl;

import ioc.support.BeanCreater;
import ioc.support.BeanDefinition;
import ioc.support.BeanDefinitionParser;
import ioc.support.Interfaces.ApplicationContext;
import ioc.support.Interfaces.BeanRegister;
import ioc.support.Populator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class AnnotationApplicationContext implements ApplicationContext, BeanRegister {
    private Map<String, Object> instanceMapping = new ConcurrentHashMap<String, Object>();

    //保存所有bean的信息，主要包含bean的类型 id等信息
    private List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();
    //配置文件的config, 先使用properties文件，以后再改
    private Properties config = new Properties();

    /**
     * @name getBean
     * @param location String
     * @return null
     * @description 构造方法,实例化的过程中完成资源定位、载入，注册Beandefinition、实例化、注入的。
     * */
    public AnnotationApplicationContext(String location){
        //输入流
        InputStream is = null;
        try{
            //1、定位, Class.getClassLoader().getResourceAsStream(String path)从ClassPath根下获取，path不能以’/'开头，最终是由ClassLoader获取资源
            is = this.getClass().getClassLoader().getResourceAsStream(location);

            //2、载入
            config.load(is);

            //3、注册
            register();

            //4、实例化
            createBean();

            //5、注入
            populate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                is.close();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

    }

    /**
     * @name populate
     * @param null
     * @return null
     * @description 调用具体委派的注入类进行注入
     * */
    private void populate(){
        Populator populator = new Populator();
        populator.populator(instanceMapping);
    }

    /**
     * @name createBean
     * @param null
     * @return null
     * @description 调用具体的创建对象创建bean
     * */
    private void createBean(){
        BeanCreater create = new BeanCreater(this);
        create.create(beanDefinitions);

    }

    /**
     * @name rigister
     * @param null
     * @return null
     * @description 调用具体的注册对象注册bean信息
     * */
    private void register(){
        BeanDefinitionParser parser = new BeanDefinitionParser(this);
        parser.parse(config);
    }

    /**
     * @name getBean
     * @param id String
     * @return Object
     * @description 根据id获取bean
     * */
    public Object getBean(String id) {
        return instanceMapping.get(id);
    }

    /**
     * @name getConfig
     * @param null
     * @return Properties
     * @description 获取config
     * */
    public Properties getConfig() {
        return this.config;
    }


    /**
     * @name getBean
     * @param id String
     * @param clazz Class<T>
     * @return T
     * @description：根据id获取特定类型的bean
     * */
    public <T> T getBean(String id, Class<T> clazz) {
        return (T)instanceMapping.get(id);
    }

    /**
     * @name getBeans
     * @return Map<String, Object>
     * @description：获取工厂所有的bean
     * */
    public Map<String, Object> getBeans() {
        return instanceMapping;
    }

    /**
     * @name registerBeanDefinition
     * @param bds List<BeanDefinition>
     * @return null
     * @description：注册BeanDefinition
     * */
    public void registBeanDefinition(List<BeanDefinition> bds){
        beanDefinitions.addAll(bds);
    }

    /**
     * @name registerInstanceMapping
     * @param id String
     * @param instance Object
     * @return null
     * @description： 注册InstanceMapping
     * */
    public void registInstanceMapping(String id, Object instance){
        instanceMapping.put(id, instance);
    }
}
