package map;
import java.util.HashMap;
import java.util.Map;

/**
 * 2012年2月7日 12:43:10
 * 
 * hash算法：就2种，“分桶单链表”或“N次探测法”
 */
public class TestHashMap {
	
	public static void main(String[] args) {
		Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		
		for(Integer i = 0; i< 1000000; i++) {
			hashMap.put(i, i);
		}
		//hashMap.put("name", "nick");
		System.out.println(hashMap.size());
		
	}
}
