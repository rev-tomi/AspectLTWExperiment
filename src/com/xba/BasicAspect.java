package com.xba;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.example.ChildStringHolder;
import com.example.ConstructorAspectTest;
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
	
	@Around("execution(com.example.ConstructorAspectTest.new(java.lang.String))")
	public void beforeCreation(ProceedingJoinPoint joinPoint) throws Throwable {
		joinPoint.proceed();
		ConstructorAspectTest cat = (ConstructorAspectTest) joinPoint.getThis();
		cat.addData("Nirvana");
	}
	
//	@Around("execution(com.example.StringHolder.new(java.lang.String))")
	public void beforeCreatingHolder(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("--> before creating holder...");
		System.out.println(joinPoint.getTarget().getClass());
		System.out.println(joinPoint.getThis().getClass());
		System.out.println(Arrays.toString(joinPoint.getArgs()));
		System.out.println(joinPoint.getClass());
		joinPoint.proceed(new Object[] {"ghij"});
		//return new ChildStringHolder(sh.getStr(), "asdf");
	}
	@Around("call(com.example.StringHolder.new(java.lang.String))")
	public StringHolder beforeCallingHolder(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("--> before creating holder...");
		System.out.println(joinPoint.getTarget());
		System.out.println(joinPoint.getThis());
		System.out.println(Arrays.toString(joinPoint.getArgs()));
		Object[] args = joinPoint.getArgs();
//		joinPoint.proceed(new Object[] {"ghij"});
		return new ChildStringHolder((String) args[0], "ghij");
		//return new ChildStringHolder(sh.getStr(), "asdf");
	}
	
//	@Around("after(com.example.StringHolder.new(java.lang.String))")
	public StringHolder afterCreatingHolder(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("--> after creating holder...");
		System.out.println(joinPoint.getTarget());
		System.out.println(joinPoint.getThis());
		System.out.println(Arrays.toString(joinPoint.getArgs()));
		joinPoint.proceed(new Object[] {"ghij"});
		return null;
		//return new ChildStringHolder(sh.getStr(), "asdf");
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
