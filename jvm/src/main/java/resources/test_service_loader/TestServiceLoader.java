package resources.test_service_loader;

import java.util.ServiceLoader;

import com.test.IHello;

/**
 * 2014年8月28日 13:46:42 http://huangyunbin.iteye.com/blog/1883124
 * ServiceLoader有点像Spring的容器，它可以指定接口，然后让jvm去加载另一个jar包里的指定的所有实现。
 * 
 * 另一个jar包的META-INF/services/接口全称  这个文件来指定实现，多个实现换行表示。
 */
public class TestServiceLoader {

	public static void main(String[] args) {
		ServiceLoader<IHello> loader = ServiceLoader.load(IHello.class);
		for(IHello hello : loader) {
			System.out.println("===class===" + hello.getClass());
			hello.sayHello();
		}
	}
}
