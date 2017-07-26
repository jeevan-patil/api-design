package xyz.jeevan.api.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class PerformanceProfiler {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(PerformanceProfiler.class);
  
  @Around("@annotation(xyz.jeevan.api.annotation.LogExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object retVal = joinPoint.proceed();
    stopWatch.stop();
    LOG.info("{} took {} milliseconds.", joinPoint.toString(), stopWatch.getLastTaskTimeMillis());
    return retVal;
  }

}
