package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//aop는 aspect를 적어야한다.
//aop는 공통 관심사항을 모은 것
//중간에 인터셉팅해서 풀수 있는 기술
//aop는 프록시 기술이 있어야 가능하다.
@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 패키지에서 하위에 다 적용해
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START= " + joinPoint.toString());
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END= " + joinPoint.toString() + " " + timeMs + "ms");

        }

    }

}
