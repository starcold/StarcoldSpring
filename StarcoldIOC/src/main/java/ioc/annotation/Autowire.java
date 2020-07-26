/**
 * author: starcold
 * createTime: 2020/07/10
 * context: Autowire注解
 * updateTime:
 * updateContext:
 */
package ioc.annotation;
import java.lang.annotation.*;

//https://blog.csdn.net/fengcai0123/article/details/90544338,几个注解的作用
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Target(ElementType.FIELD)//作用在字段上
@Documented
public @interface Autowire {
    //https://blog.csdn.net/qq_35835624/article/details/80196932 default: Virtual extension methods——虚拟扩展方法。
    //注解有一个名为 value 的可选参数。不设置的话默认为“”。如果没有后面的 default ""，则表示这是一个必须的参数。不指定的话会报错。
    String value() default "";
}
