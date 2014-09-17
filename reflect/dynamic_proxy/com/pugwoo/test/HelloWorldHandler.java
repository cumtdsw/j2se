package com.pugwoo.test;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 2010年12月6日 下午08:46:06
 * 
 * 实际上，这是一个通用的Handler，由client使用方来指定使用
 */
public class HelloWorldHandler implements InvocationHandler {
	
	private Object target;

	/**
	 * 绑定委托对象并返回一个代理类 
	 * @param target
	 * @return
	 */
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
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
		Object result = method.invoke(target, args);

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
