package ioc_impl.annotation;

import ioc_impl.constant.ScopeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface MyComponent {

    String name() default "";

    String scope() default ScopeType.SINGLETON;

}
