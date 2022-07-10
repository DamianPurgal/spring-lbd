package lbd.fissst.springlbd.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceAspect.class);

    @Around("execution(* lbd.fissst.springlbd.service..*.*(..))")
    public Object aroundServiceMethods(ProceedingJoinPoint joinPoint){
        LOG.info("Execute method");
        Object[] values = joinPoint.getArgs();
        Object value = null;
        try{
            LOG.info("Passed arguments:");
            for(Object val : values){
                LOG.info("Argument: {}", val);
            }
            value = joinPoint.proceed();
            LOG.info("Method executed successfully");
        }catch(Throwable e){
            LOG.error("error while executing method");
        }
        LOG.info("Returned value: {}", value);

        return value;
    }
}
