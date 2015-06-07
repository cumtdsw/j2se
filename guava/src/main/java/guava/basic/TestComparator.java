package guava.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

/**
 * 2015年6月7日 15:29:41
 * http://ifeve.com/google-guava-ordering/
 */
public class TestComparator {

	public static void main(String[] args) throws ParseException {
		// 通过Ordering构建一个根据字符串的长度排序的比较器
		Ordering<String> byLengthOrdering = new Ordering<String>() {
			public int compare(String left, String right) {
				return Ints.compare(left.length(), right.length());
			}
		};
		
		List<String> strs = Lists.newArrayList("aaa", "bb", "cccc");
		Collections.sort(strs, byLengthOrdering);
		System.out.println(strs);
		
		// natural() 对可排序类型做自然排序，如数字按大小，日期按先后排序
		Ordering<Date> orderByDate = Ordering.natural();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		List<Date> dates = Lists.newArrayList(df.parse("20100101"),
				df.parse("20090101"), df.parse("20110101"));
		Collections.sort(dates, orderByDate);
		System.out.println(dates);
		
		// 重要，当构建完一个Ordering对象，就可以用这个对象简单修改得到另一个排序器
		// 常用的：reverse() 倒序
		// nullsFirst()  null排在最前面
		// nullsLast()   null排在最后面
		// compound(Comparator) 合成另一个比较器，以处理当前排序器中的相等情况。
		// onResultOf(Function) 对集合中元素调用Function，再按返回值用当前排序器排序。
		
		// 另外，Ordering也提供一系列的方法，用于检查序列是否排序号，或者copySorted复制一个排序的list等
		// 还能取得最大的k个值这样的实现
	}
}
