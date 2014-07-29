package jvm;

public class TestException {
	
	public TestException() throws Exception{
		throw new Exception("hi");
	}

	public static void main(String[] args) {
		Exception ex = new Exception();
		ex.printStackTrace();
	}
	
}
