package guava.container;

/**
 * 2015年6月7日 16:43:19
 * 每个有经验的Java程序员都在某处实现过Map<K, List<V>>或Map<K, Set<V>>，
 * 并且要忍受这个结构的笨拙。例如，Map<K, Set<V>>通常用来表示非标定有向图。
 * Guava的 Multimap可以很容易地把一个键映射到多个值。
 * 换句话说，Multimap是把键映射到任意多个值的一般方式。
 * 
 * 处于这样的理解，就有：
 * 1. ArrayListMultimap  键的行为为HashMap，值为ArrayList
 * 2. HashMultimap       键的行为为HashMap，值为ArrayList
 */
public class TestMultimap {

	public static void main(String[] args) {
		
	}
}
