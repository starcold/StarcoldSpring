/**
 * author: starcold
 * createTime: 2020/07/13
 * context: 完成扫描包下bean信息的解析注册
 * updateTime:
 * updateContext:
 */
package ioc.support;

import ioc.support.Interfaces.BeanRegister;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class BeanDefinitionParser {
    //配置扫描包的key
    public static final String SCAN_PACKAGE = "scanPackage";
    //容器注册对象
    private BeanRegister register;

    /**
     * @name BeanDefinitionParser
     * @param register BeanRegister
     * @return null
     * @description：构造函数
     * */
    public BeanDefinitionParser(BeanRegister register){
        this.register = register;
    }

    /**
     * @name parse
     * @param properties Properties
     * @return null
     * @description：解析
     * */
    public void parse(Properties properties){
        //获取要扫描的包
        String packageName = properties.getProperty(SCAN_PACKAGE);
        //执行注册
        doRegister(packageName);
    }

    /**
     * @name doRegister
     * @param packageName Properties
     * @return null
     * @description：执行注册的函数
     * */
    public void doRegister(String packageName){
        //获取此包名下绝对路径
        URL url = getClass().getClassLoader().getResource("./" + packageName.replace(".","/"));
        File dir = new File(url.getFile());
        //循环遍历，获取所有java文件
        for(File file:dir.listFiles()){//listFiles()：返回某个目录下的所有文件和目录的绝对路径，返回的是File数组。list()返回的是String数组。
            //如果是文件夹
            if (file.isDirectory()){
                //文件夹->继续执行
                doRegister(packageName + "." + file.getName());
            }
            else{
                //处理文件名来获取类名 运行时获取到的是Class文件
                String className = packageName + "." + file.getName().replaceAll(".class", "").trim();
                //调用BeanDefinitionGenerator.generate(ClassName)方法来处理;
                //1、类带有容器注解要处理，解析id生成beanDefinition集合返回
                //2、不带有需要处理的注解 直接返回null
                List<BeanDefinition> definitions = BeanDefinitionGenerator.generte(className);
                //definitions 不存在，跳过
                if(definitions == null)continue;
                this.register.registBeanDefinition(definitions);
            }
        }
    }
}
