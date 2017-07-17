package com.xba;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.example.StringHolder; 

@Aspect
public class BasicAspect {

	@Around("execution(java.util.List com.example.AspectExperiment.foo(com.example.StringHolder))")
	public List<Integer> aroundFoo(ProceedingJoinPoint joinPoint) throws Throwable {
		StringHolder input = (StringHolder) joinPoint.getArgs()[0];
		System.out.println("--> around foo(" + input + ")");
		List<Integer> result = (List<Integer>) joinPoint.proceed(joinPoint.getArgs());
		System.out.println("--> original result: " + Arrays.toString(result.toArray()));
		result.set(0, 42);
		return result.subList(0, 3);
	}
	/*
	@AfterReturning(
//			pointcut = "execution(java.util.List com.example.AbstractAspectExperiment.foo(com.example.StringHolder))",
			pointcut = "execution(public * com.example.AbstractAspectExperiment.*(..))",
			returning= "result")
	public void afterFoo(JoinPoint joinPoint, List<Object> result) {
		StringHolder input = (StringHolder) joinPoint.getArgs()[0];
		System.out.println("--> input: " + input);
		result.clear();
		// here we can mess with the input / output
	}
	*/
}
