package guava.container;

import java.util.Comparator;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

/**
 * 2015年9月7日 16:45:50
 */
public class TestSets {

	@Test
	public void testNewTreeSet() {
//        Set<Student> sets = Sets.newTreeSet(new Comparator<Student>() {
//        	/**
//        	 * 发现一个很【严重】的问题，关于Sets.newTreeSet
//        	 * 如果compare返回值相同，那么这两个对象会被【合并】成一个！！！根本就不是排序
//        	 * 这个非常不合理，虽说Set只允许一个object，但是Comparator是用于排序的，而不是equals！！
//        	 * 
//        	 * 使用 newHashSet就可以了
//        	 * @param o1
//        	 * @param o2
//        	 * @return
//        	 */
//            @Override
//            public int compare(Student o1, Student o2) {
////            	if(o1.getName() == null || o2.getName() == null) {
////            		return 0;
////            	}
////                return o2.getName().compareTo(o1.getName());
//            	return 0;
//            }
//        });
		
		Set<Student> sets = Sets.newHashSet();
        
        Student student3 = new Student(1, null);
        Student student1 = new Student(3, "nick");
        Student student2 = new Student(9, "karen");
        Student student4 = new Student(2, "dom");
        
        sets.add(student1);
        sets.add(student2);
        sets.add(student3);
        sets.add(student4);
        
        System.out.println(sets.size());
	}
	
}

class Student {
	private int id;
	private String name;
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "id:" + id + ",name:" + name;
	}
}