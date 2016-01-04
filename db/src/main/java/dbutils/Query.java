package dbutils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * 查询实际上是各种ResultSetHandler的应用
 * 
 * DBUtils可以将结果集封装为各种类型，主要有：
 * Bean/List<Bean>，Map/List<Map>/Map<Map>，数组/List<数组>，列/List<列>
 * 
 * 2011年11月13日
 * 
 * @author pugwoo
 */
public class Query extends TestCase {

	/**
	 * ArrayHandler 将ResultSet的第一行的数据转换成对象数组
	 * 
	 * 注：Object[]里的元素已经被转换成数据库对应的格式，如字符串、数字、日期等
	 */
	public static void testArrayHandler() throws SQLException {
		Object[] result = (Object[]) new QueryRunner(DB.getDataSource()).query(
				"select * from person where id=?", new ArrayHandler(), 1);
		for (Object obj : result) {
			System.out.println(obj + "," + obj.getClass());
		}
	}

	/**
	 * ArrayListHandler 将ResultSet中所有的数据转化成List，List里存放的是Object[]
	 */
	public static void testArrayListHandler() throws SQLException {
		List<Object[]> result = (List<Object[]>) new QueryRunner(DB
				.getDataSource()).query("select * from person",
				new ArrayListHandler());
		for (Object[] objs : result) {
			for (Object obj : objs) {
				System.out.print(obj + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * BeanHandler，自动装载Bean（一个） 使用getter和setter来赋值
	 */
	@SuppressWarnings("unchecked")
	public static void testBeanHanler() throws SQLException {
		@SuppressWarnings("rawtypes")
		Person person = (Person) new QueryRunner(DB.getDataSource()).query(
				"select * from person where id=?",
				new BeanHandler(Person.class), 1);
		System.out.println("name:" + person.getName() + ",age:"
				+ person.getAge());
	}

	/**
	 * BeanHandler，自动装载Bean（多个） 使用getter和setter来赋值
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void testBeanListHanler() throws SQLException {
		List<Person> list = (List<Person>) new QueryRunner(DB.getDataSource()).query(
				"select * from person", new BeanListHandler(Person.class));
		System.out.println(list.size());
		for (Person person : list)
			System.out.println("name:" + person.getName() + ",age:"
					+ person.getAge());
	}

	/**
	 * 只查询一个值，数字或字符串等
	 * 
	 * @throws SQLException
	 */
	public static void testScalaHandler() throws SQLException {
		Long count = (Long) new QueryRunner(DB.getDataSource()).query(
				"select count(*) from person", new ScalarHandler());
		System.out.println("count: " + count);
	}

	/**
	 * Map（一个） 此外还有MapListHandler，返回的是List<Map>
	 * 
	 * @throws SQLException
	 */
	public static void testMapHandler() throws SQLException {
		Map<String, Object> map = new QueryRunner(DB.getDataSource()).query(
				"select * from person where id=?", new MapHandler(), 1L);
		Person person = new Person();
		person.setId((Long) map.get("id"));
		person.setName((String) map.get("name"));
		person.setAge((Integer) map.get("age"));
		person.setAddress((String) map.get("address"));
		System.out.println("name:" + person.getName() + ",age:"
				+ person.getAge());
	}

	/**
	 * （多个） Map<Map>的类型使用KeyedHandler作为结果集处理器
	 * 内层的Map是“列名-值"对，外层的Map是“主键-内层Map的引用”， 但此处的主键不一定就是数据库的主键，可以随意指定
	 */
	@SuppressWarnings("unchecked")
	public static void testKeyedHandler() throws SQLException {
		ResultSetHandler<?> h = new KeyedHandler("id");
		Map<Long, Map<?,?>> result = (Map<Long, Map<?,?>>) new QueryRunner(DB
				.getDataSource()).query(
				"select id,name,age,address from person", h);
		System.out.println("result: " + result.size());

		Map<?,?> person = result.get(new Long(1));
		String name = (String) person.get("name");
		Integer age = (Integer) person.get("age");
		System.out.println("name:" + name + ",age:" + age);
	}

}
