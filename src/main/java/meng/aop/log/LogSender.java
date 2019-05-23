package meng.aop.log;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogSender {

    String value() default "";

    int time() default 1;
}
