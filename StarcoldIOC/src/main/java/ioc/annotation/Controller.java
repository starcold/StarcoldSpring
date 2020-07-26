/**
 * author: starcold
 * createTime: 2020/07/10
 * context: Controller注解
 * updateTime:
 * updateContext:
 */
package ioc.annotation;
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Target(ElementType.TYPE)//作用在类上
@Documented
public @interface Controller {
    String value() default "";
}
