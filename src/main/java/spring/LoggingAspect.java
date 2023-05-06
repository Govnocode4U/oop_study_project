package spring;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    public LoggingAspect() {
        System.out.println("logging creaye");
    }
    @Before("execution(* *(..))")
    public void beforeBark() {
        System.out.println("Aspect: org.utils.interacrions.Dog is about to bark!");
    }
}