
public class A<T> {

	public String getString(T t){
		return t.toString();
	}
	
	public int nothingAboutT() {
		return 123;
	}
	
	public static void main(String args[]){
		A<String> a = new A<String>(); // 指定类的通配符
		System.out.println(a.getString("ff"));
		
		A<?> aa = new A<String>(); // 通用类型，适合于不需要关心是什么类型的使用，例如List的size()
		System.out.println(aa.nothingAboutT());
	}
}

/**
编译成class之后再反编译：可以看到代码部分的泛型没有了。
import java.io.PrintStream;

public class A<T>
{
  public String getString(T t)
  {
    return t.toString();
  }

  public static void main(String[] args) {
    A a = new A();
    System.out.println(a.getString("ff"));
  }
}
 */
