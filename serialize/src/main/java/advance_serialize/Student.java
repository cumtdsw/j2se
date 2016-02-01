package advance_serialize;

import java.io.Serializable;

/**
 * 2014年5月21日 11:45:51 支持： 1) 模糊化序列化数据，可以对数据进行变换，可用于加密
 */
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * 年龄，年龄这个字段会被简单的加密处理
	 */
	private int age;
	
	@Override
	public String toString() {
		return "name:" + name + ",age:" + age;
	}
	
	// 这个接口是约定的，可以是private
	private void writeObject(java.io.ObjectOutputStream stream)
			throws java.io.IOException {
		// "Encrypt"/obscure the sensitive data
		age = age << 2;
		stream.defaultWriteObject();
	}

	// 这个接口是约定的，可以是private
	private void readObject(java.io.ObjectInputStream stream)
			throws java.io.IOException, ClassNotFoundException {
		stream.defaultReadObject();
		// "Decrypt"/de-obscure the sensitive data
		age = age >> 2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
