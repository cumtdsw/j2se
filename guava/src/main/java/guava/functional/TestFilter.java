package guava.functional;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

/**
 * 类似于java8里面的filter
 *
 * 2014年11月18日 16:06:34
 */
public class TestFilter {

	public static void main(String[] args) {
		List<Integer> ages = Lists.newArrayList(23, 25, 38, 10);

		Iterable<Integer> agesAudit = FluentIterable.from(ages).filter(
				new Predicate<Integer>() {
					@Override
					public boolean apply(Integer input) {
						return input > 18;
					}
				});
		
		System.out.println(agesAudit);
	}

}
