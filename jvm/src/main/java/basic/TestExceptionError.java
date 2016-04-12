package basic;

/**
 * 2016年4月12日 16:31:42
 * 异常分为不需要显式检查的Error和RuntimeException和需要显式catch的Exception
 * 
 * 所有的异常都实现了Throwable类
 * 
 * Error/RuntimeException和Exception，只要没捕获，都会导致当前线程终止
 * 
 */
public class TestExceptionError {
	
	public static void testError() {
		System.out.println("before throw error");
		throw new Error();
	}

	public static void testRuntimeException() {
		System.out.println("before throw runtime exception");
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
		testError();
		System.out.println("after testRuntimeException");
	}
	
	
}
