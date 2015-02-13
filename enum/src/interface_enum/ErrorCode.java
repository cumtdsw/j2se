package interface_enum;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过interface成员变量来定义枚举，适合于基本类型
 */
public interface ErrorCode {

	String NOT_FOUND = "404";
	int INTERNAL_ERROR = 500;
	
	// 不适合做枚举，因为虽然LIST是final修饰，但是其引用的Object不是immutable
	List<String> LIST = new ArrayList<String>();
}
