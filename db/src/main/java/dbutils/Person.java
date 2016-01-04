package dbutils;

/**

CREATE TABLE person ( 
     id bigint(20) NOT NULL AUTO_INCREMENT, 
     name varchar(24) DEFAULT NULL, 
     age int(11) DEFAULT NULL, 
     address varchar(120) DEFAULT NULL, 
     PRIMARY KEY (id) 
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=gbk;

通过getter和setter获得和设置数据
 * @author pugwoo
 * @date 2011-11-13
 * 
 * 一般来说，建议bean的成员都不要用基本类型
 * 例如用Integer代替int，这样可以区分数据库里面的null和0
 * 但是java程序里的基本类型和引用类型比较时要小心，null == 0会报错，此外大数的Integer不能用==比较
 * 
 * 也有一种建议是：如果某字段必定有值，例如PK，那么就用基本类型；否则用引用类型
 */
public class Person {

	private Long id;
	private String name;
	private Integer age;
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
