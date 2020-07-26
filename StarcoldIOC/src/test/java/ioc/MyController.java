/**
 * author: starcold
 * createTime: 2020/07/13
 * context: 测试Controllor
 * updateTime:
 * updateContext:
 */
package ioc;

import ioc.annotation.Autowire;
import ioc.annotation.Controller;
import ioc.support.Impl.AnnotationApplicationContext;
import ioc.support.Interfaces.ApplicationContext;

@Controller
public class MyController {
    @Autowire("myservice")
    private MyService service;

    /**
     * @name test
     * @return null
     * @description：测试函数
     * */
    public void test(){
        service.print("test success!");
    }

    public static void main(String[] args){
        ApplicationContext context = new AnnotationApplicationContext("applicationContext.properties");
        MyController controller = context.getBean("ioc.MyController", MyController.class);
        controller.test();
    }
}
