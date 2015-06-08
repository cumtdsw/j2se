package guava.container;

import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * 2015年6月7日 16:58:05
 * Guava提供的工具方法更偏向于接受Iterable而不是Collection类型。
 * 在Google，对于不存放在主存的集合——比如从数据库或其他数据中心收集的结果集，
 * 因为实际上还没有攫取全部数据，这类结果集都不能支持类似size()的操作 
 * ——通常都不会用Collection类型来表示。
 * 
 * Iterator特别适合于遍历时增删改查，应当优先使用
 */
public class TestIterables {

	public static void main(String[] args) {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3);
		List<Integer> list2 = Lists.newArrayList(4, 5, 6);
		
		// 合并两个迭代器
		Iterable<Integer> it = Iterables.concat(list1, list2);
		System.out.println(it.getClass() + ":" + it);
		
		// frequency(Iterable, Object) 返回对象在iterable中出现的次数
		// getFirst(Iterable, T default) 返回iterable的第一个元素，若iterable为空则返回默认值
		
		// elementsEqual(Iterable, Iterable) 比较两个序列
		
	}
	
}
