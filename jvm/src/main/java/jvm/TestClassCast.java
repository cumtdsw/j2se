package jvm;

import java.util.Map;

/**
 * 2014年11月13日 14:05:15 演示类型转换
 */
public class TestClassCast {

	protected static <T> T getOption(Map<String, Object> options, String key,
			Class<T> targetClass) {
		return getOption(options, key, targetClass, null);
	}

	/**
	 * 泛型方式反射拿到值
	 * @param options
	 * @param key
	 * @param targetClass
	 * @param defaultValue
	 * @return
	 */
	protected static <T> T getOption(Map<String, Object> options, String key,
			Class<T> targetClass, T defaultValue) {
		if (options != null) {
			Object value = options.get(key);
			if (value != null && targetClass != null
					&& targetClass.isAssignableFrom(value.getClass())) {
				return targetClass.cast(value);
			}
		}
		return defaultValue;
	}

	public static void main(String[] args) {

		Integer a = 3;
		System.out.println(a);

		Class<Number> clzNumber = Number.class;

		System.out.println(clzNumber.isAssignableFrom(a.getClass()));
		if(clzNumber.isAssignableFrom(a.getClass())) { // 接下来就可以安全cast
			Number aN = clzNumber.cast(a);
			System.out.println(aN);
		}
	}

}
