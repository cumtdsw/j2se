package guava.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 2015年3月3日 13:41:03
 */
public class Basic {
	
	/**
	 * 测试实用情况下的cache
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		Cache<String, Object> cache = CacheBuilder.newBuilder()
				.maximumSize(10000) // 最大元素个数，超过时会按规则一个淘汰，通常最久没使用的被淘汰
				.expireAfterWrite(10, TimeUnit.MINUTES) // 元素过期时间，实用
				// 还有另外一种过期时间是靠访问时间，这个也实用，例如网站session
				// 还有一种是弱引用的方式，类似与GC
				.build();
		
		for(int i = 0; i < 1000000; i++) {
			cache.put(String.valueOf(i), i);
		}
		
		System.out.println("size:" + cache.size());
		
		/**
		 * 删除数据方式: 【支持removeListener，异步removeListener:RemovalListeners.asynchronous(RemovalListener, Executor)】
　　1.单独移除用 Cache.invalidate(key)
　　2.批量移除用 Cache.invalidateAll(keys)
　　3.移除所有用 Cache.invalidateAll()
		 */
		
	}

	@Test
	public void TestLoadingCache() throws Exception {
		LoadingCache<String, String> cahceBuilder = CacheBuilder.newBuilder()
				.build(new CacheLoader<String, String>() {
					// 支持这种函数式的key -> value
					@Override
					public String load(String key) throws Exception {
						String strProValue = "hello " + key + "!";
						return strProValue;
					}
				});

		System.out.println("jerry value:" + cahceBuilder.get("jerry"));
		System.out.println("peida value:" + cahceBuilder.get("peida"));
		System.out.println("lisa value:" + cahceBuilder.get("lisa"));

		cahceBuilder.put("harry", "ssdded"); // 也支持直接给值，就等同于map了
		System.out.println("harry value:" + cahceBuilder.get("harry"));
	}

	/**
	 * 参数：
　　1. 大小的设置：CacheBuilder.maximumSize(long)  CacheBuilder.weigher(Weigher)  CacheBuilder.maxumumWeigher(long)
　　2. 时间：expireAfterAccess(long, TimeUnit) expireAfterWrite(long, TimeUnit)
　　3. 引用：CacheBuilder.weakKeys() CacheBuilder.weakValues()  CacheBuilder.softValues()
　　4. 明确的删除：invalidate(key)  invalidateAll(keys)  invalidateAll()
　　5. 删除监听器：CacheBuilder.removalListener(RemovalListener)
	 */
	@Test
	public void testcallableCache() throws Exception {
		Cache<String, String> cache = CacheBuilder.newBuilder()
				.maximumSize(1000).build();
		String resultVal = cache.get("jerry", new Callable<String>() {
			public String call() {
				System.out.println("---- jerry call run ----"); // run once
				String strProValue = "hello " + "jerry" + "!";
				return strProValue;
			}
		});
		System.out.println("jerry value : " + resultVal);
		System.out.println("jerry value : " + cache.getIfPresent("jerry"));
		
		resultVal = cache.get("peida", new Callable<String>() {
			public String call() {
				String strProValue = "hello " + "peida" + "!";
				return strProValue;
			}
		});
		System.out.println("peida value : " + resultVal);
	}
	
}
