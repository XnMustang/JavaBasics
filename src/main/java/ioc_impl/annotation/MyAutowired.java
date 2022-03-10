package ioc_impl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动填充注解
 */
@Retention(value = RetentionPolicy.RUNTIME) // 注解信息会保留在源文件、类文件中，在执行的时也加载到Java的JVM中，因此可以反射性的读取
@Target(value = ElementType.TYPE) // @Target：注解的作用目标  TYPE：可作用于类、接口、注解、枚举
public @interface MyAutowired {

    String value() default "";

}
