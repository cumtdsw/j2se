package guava.functional;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.base.Function;

/**
 * 2014年11月18日 15:45:09
 *
 * Function最重要的就是定义了输入和输出的接口
 */
public class TestFunction {

	public static void main(String[] args) {
		final SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		
		Function<Date, String> function = new Function<Date, String>() {
			@Override
			public String apply(Date input) {
				/**
				 * 函数化的一个大特点是闭包，java中只能用匿名实现来做到闭包
				 * 所以这里的df实际上是外部提供的，这是function话的一大特点.
				 * 
				 * 本质上来看，闭包和接口实现也比较接近
				 */
				return df.format(input);
			}
		};
		
		System.out.println(function.apply(new Date()));
	}
}
