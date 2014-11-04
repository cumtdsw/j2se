package guava.container;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 一些简单的创建List Map的例子
 */
public class CreateCollectionUtils {

	public static void main(String[] args) {

		List<Long> list = Lists.newArrayList(1L, 2L, 3L);
		System.out.println(list);
		
		Map<String, Long> map = Maps.newHashMap();
		map.put("one", 1L);
		map.put("two", 2L);
		
		System.out.println(map);
	}

}
