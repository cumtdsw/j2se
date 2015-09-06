package guava.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * 2014年11月17日 17:01:14
 */
public class TestJoiner {

	public static void main(String[] args) {
		List<String> names = Lists.newArrayList("nick", "karen", "shaji");
		System.out.println(Joiner.on(",").join(names));
		
		// 去掉null值的join
		List<Integer> numbers = Lists.newArrayList(3, 4, 5, null, 9);
		System.out.println(Joiner.on(",").skipNulls().join(numbers));
		
		// 为null设定默认值
	    System.out.println(Joiner.on(",").useForNull("0").join(numbers));
	    
	    // 
	    System.out.println(Joiner.on(",").join("first", "second"));
	    
	    // 也可以追加到StringBuilder后面
	    StringBuilder sb = new StringBuilder("name:");
	    Joiner.on(",").appendTo(sb, "first", "second");
	    System.out.println(sb.toString());
	    
	    // 处理map
	    Map<String, Long> map = new HashMap<String, Long>();
	    map.put("one", 1L);
	    map.put("two", 2L);
	    String str = Joiner.on(",").withKeyValueSeparator(":").join(map);
	    System.out.println(str);
	}
}
