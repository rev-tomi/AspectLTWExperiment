package com.example;

import javassist.ClassMap;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.util.proxy.ProxyFactory;

public class ProxyTest {

	public static void main(String[] args) throws Exception {
		/*
		Proxy.getProxyClass(ClassToProxize.class.getClassLoader(), new Class[] {ClassToProxize.class});
		
		ClassToProxize clt = (ClassToProxize) Proxy.newProxyInstance(ClassToProxize.class.getClassLoader(), new Class[] {
				//ClassToProxize.class
		}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				final String methodName = method.getName();
				if ("bar".equals(methodName)) {
					return "Babar";
				} else {
					return method.invoke(proxy, args);
				}
			}
		});
		System.out.println(clt.foo());
		System.out.println(clt.bar());
		*/
		
		ClassPool pool = ClassPool.getDefault();
		CtClass subClass = pool.makeClass("com.example.SubclassForProxy");
		final CtClass superClass = pool.get(ClassToProxize.class.getName());
		subClass.setSuperclass( superClass );
		CtMethod barMethod = superClass.getMethod("bar", "()Ljava/lang/String;");
		ClassMap classMap = new ClassMap();
		CtMethod otherBarMethod = new CtMethod(barMethod, subClass, classMap);
		classMap.put(subClass.getName(), superClass.getName());
		otherBarMethod.setBody("return \"Babar\";");
		subClass.addMethod(otherBarMethod);
		
//		for (CtMethod m : superClass.getMethods()) {
//			System.out.println("--> method: " + m);
//		}
		
		ClassToProxize clt = new ClassToProxize();
		ProxyFactory proxyFactory = new ProxyFactory();
		
		ClassToProxize ctp2 = (ClassToProxize) subClass.toClass().newInstance();
		System.out.println(ctp2.bar());

	}

}
