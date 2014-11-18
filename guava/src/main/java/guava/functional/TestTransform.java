package guava.functional;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

/**
 * 2014年11月18日 16:11:26
 * 
 * 这个也类似java8里面的数组变换
 */
public class TestTransform {

	public static void main(String[] args) {
		List<Integer> ages = Lists.newArrayList(32, 89, 12);
		List<String> transformedList = FluentIterable.from(ages)
				.transform(new Function<Integer, String>() {
					@Override
					public String apply(Integer input) {
						return "aaa" + input.toString();
					}
				}).toList();
		
		System.out.println(transformedList);
	}

}
