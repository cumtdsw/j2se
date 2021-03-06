package guava.basic;

import com.google.common.base.Throwables;

/**
 * 2014年10月30日 15:57:28 <br>
 * 刚刚学习到，如果一个接口没有声明异常，但是实现中有异常需要带出去，<br>
 * 那么就包装成RuntimeException再抛出去
 */
public class TestThrowRuntimeException {

	public static void a() throws Exception {
		throw new Exception("exception in a");
	}

	// 包装成
	public static void b() {
		try {
			a();
		} catch (Exception e) {
			Throwables.propagate(e);
			// 上面这一行等价于：
			// throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		try {
			b();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
