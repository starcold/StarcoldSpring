/**
 * author: starcold
 * createTime: 2020/07/10
 * context: RequsetMapping注解
 * updateTime:
 * updateContext:
 */
package ioc.annotation;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Target({ElementType.TYPE, ElementType.METHOD})//作用在类和方法，参考springmvc
@Documented
public @interface RequsetMapping {
    String value() default "";
}