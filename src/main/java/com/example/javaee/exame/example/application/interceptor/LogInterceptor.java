package com.example.javaee.exame.example.application.interceptor;

import org.jboss.logging.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.HttpMethod;
import java.util.Arrays;
import java.util.Optional;

@Log
@Interceptor
@Priority(99)
public class LogInterceptor {

  @AroundInvoke
  public Object aroundInvoke(InvocationContext ctx) throws Exception {
    Logger log = Logger.getLogger(ctx.getMethod().getDeclaringClass());

    String methodName = ctx.getMethod().getName();

    Optional<String> httpMethod = Arrays.stream(ctx.getMethod().getDeclaredAnnotations())
        .flatMap(annotation -> Arrays.stream(annotation.annotationType().getDeclaredAnnotations()))
        .filter(HttpMethod.class::isInstance)
        .map(annotation -> ((HttpMethod) annotation).value())
        .findFirst();

    StringBuilder logValue = new StringBuilder();
    httpMethod.ifPresent(httpValue -> logValue.append(httpValue).append(" "));
    logValue.append(methodName).append(" ");
    Arrays.stream(ctx.getParameters()).forEach(param -> logValue.append(param.toString()).append(" "));

    log.debug(logValue.toString());
    return ctx.proceed();
  }
}
