package com.pugwoo.test;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 2010年12月6日 下午08:46:06
 * 
 * 实际上，这是一个通用的Handler，由client使用方来指定使用
 */
public class HelloWorldHandler implements InvocationHandler {

	/**
	 * 要代理的原始对象
	 */
	private Object objOriginal;

	public HelloWorldHandler(Object obj) {
		this.objOriginal = obj;
	}

	/**
	 * 实现接口，当调用方法是将来到这个函数
	 * 参数中proxy指代理类，method指被代理的方法，args是参数列表
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		//System.out.println(proxy.getClass()); // com.sun.proxy.$Proxy0

		// 方法调用之前
		doBefore();

		// 调用原始对象的方法
		Object result = method.invoke(this.objOriginal, args);

		// 方法调用之后
		doAfter();

		return result;
	}

	private void doBefore() {
		System.out.println("HelloWorldHandler before method invoke!");
	}

	private void doAfter() {
		System.out.println("HelloWorldHandler after method invoke!");
	}

}
