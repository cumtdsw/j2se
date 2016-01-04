package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表优于数组
 */
public class UseGenericThanArray {

	public static void main(String[] args) {

		/**
		 * 下面这个例子说明：数组的变量类型是由它当前的类型决定的，可以向父类cast，但使用时不检查子类
		 */
		Object[] objArr = new Long[1];
		// objArr[0] = "test"; // 编译没有错，但是运行时出错
		
		Long[] longArr = new Long[1];
		// longArr[0] = "test"; // 编译时就可以检查出错误
		
		/**
		 * 使用List泛型则没有这个问题，泛型是什么就只能转成什么（当然统配例外）
		 */
		// List<Object> list = new ArrayList<Long>(); // 编译不通过
		
		// new ArrayList<String>[]; // 无法生成泛型数组，泛型和数组经常不兼容，因此为了更安全的使用，java禁用泛型数组。
		// 泛型数组不等同于：不能将泛型List转化成数组。
		
		// 处理泛型数组的方法：两种：
		// 1. (E[]) new Object[SIZE]; 【常用】
		// 2. 使用Obejct[]来代替E[]泛型数组，但要自己保证类型的正确。
	}

}
