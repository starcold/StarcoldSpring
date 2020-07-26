/**
 * author: starcold
 * createTime: 2020/07/10
 * context: RequestParam注解
 * updateTime:
 * updateContext:
 */
package ioc.annotation;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Target(ElementType.PARAMETER)//作用在方法参数上面
@Documented
public @interface RequestParam {
    String value() default "";
}
