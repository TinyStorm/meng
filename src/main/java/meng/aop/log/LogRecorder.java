package meng.aop.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
    五个方法的执行顺序
    异常情况
    1.around proceed前的内容
    2.before
    3.真正的方法
    4.after
    5.afterThrowing

    五个方法的执行顺序
    正常情况
    1.around proceed前的内容
    2.before
    3.真正的方法
    4.around proceed后的内容
    5.after
    6.after returning
 */

@Component
@Aspect
public class LogRecorder {


    @Pointcut("within(meng.aop..*)&&@annotation(LogSender)")
    public void pointcut(){
    }

    /*
        一定执行 在around中的proceed后
     */
    @Before("pointcut()")
    public void beforeRecord(JoinPoint joinPoint) {
        System.out.println("beforeRecord");
        Object[] objects = joinPoint.getArgs();
        System.out.println("params:");
        for (Object o : objects) {
            System.out.println(o);
        }
    }

    /*
        方法异常才会执行,异常被around吞掉不会执行
        异常发生后最后执行
     */
    @AfterThrowing(value = "within(meng.aop..*)&&@annotation(logSender)", throwing = "tx")
    public void afterThrowing(Exception tx, LogSender logSender) {

        System.out.println("afterThrowing");
        System.out.println("record exp****");
        for (int i = 0; i < logSender.time(); i++) {
            System.out.println(tx.getMessage());
        }
        System.out.println("record finish****");
    }
    /*
        正常返回才会执行
        最后执行

     */

    @AfterReturning("within(meng.aop..*)&&@annotation(logSender)")
    public void afterReturning(JoinPoint joinPoint, LogSender logSender) {
        System.out.println("afterReturning");
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + " return ");
        System.out.println("recording*********");
        for (int i = 0; i < logSender.time(); i++) {
            System.out.println(logSender.value());
        }
        System.out.println("record finish****");
    }

    /*
        after 无论如何都会执行
        在around方法返回之后执行
        在afterReturn前执行
        不能修改返回值
     */
    @After("within(meng.aop..*)&&@annotation(LogSender)")
    public Object afterRecord() {
        System.out.println("afterRecord");
        return "mod by after";//看样子不能修改
    }

    /*
        可修改参数和返回值
     */

    @Around("within(meng.aop..*)&&@annotation(LogSender)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before around");
        Object[] args = pjp.getArgs();
        args[0] = args[0] + "123";
        String rtv;
        try {
            rtv = (String) pjp.proceed(args);//此处捕获了异常会吞掉方法本身的异常
        } catch (Throwable e) {
            throw e;
        }

        System.out.println("after around and return");
        return rtv + " modify by around";
    }
}
