package dynamic_proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 2010年12月6日 下午08:46:06
 * 
 * 
 */
public class Client {

	public static void main(String args[]){
		
		HelloWorldHandler proxy = new HelloWorldHandler();
		
		HelloWorld hello = (HelloWorld) proxy.bind(new HelloWorldImpl());
		
		//通过动态代理对象调用sayHelloWorld()方法
		hello.sayHello();
		
		System.out.println("hello class:" + hello.getClass());
	}
}
