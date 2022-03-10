package ioc_impl.annotation;

import ioc_impl.constant.ScopeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Scope注解 标识bean的作用域
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface MyScope {

    public String value() default ScopeType.SINGLETON;

}
