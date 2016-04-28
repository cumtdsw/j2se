package com.pugwoo.j2se.apache_commons.beanutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 2014年11月5日 16:02:22<br>
 * 这里演示一个如果目标obj没值则同步，有值则不变的工具<br>
 * 
 * 示例详见test代码
 */
public class PropertySyncTool {
	
	public static class InnerClass {
		private InnerInnerClass innerInnerClass = new InnerInnerClass();
		public InnerInnerClass getInnerInnerClass() {
			return innerInnerClass;
		}
		public void setInnerInnerClass(InnerInnerClass innerInnerClass) {
			this.innerInnerClass = innerInnerClass;
		}
	}
	
	public static class InnerInnerClass {
		private String name;
		private List<Integer> scores = new ArrayList<Integer>();
		private Map<String, Object> map = new HashMap<String, Object>();
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<Integer> getScores() {
			return scores;
		}
		public void setScores(List<Integer> scores) {
			this.scores = scores;
		}
		public Map<String, Object> getMap() {
			return map;
		}
		public void setMap(Map<String, Object> map) {
			this.map = map;
		}
	}

	// 如果目标obj没值则同步，有值则不变的方法
	public static void setPropertyIfTargetNull(Object orgin, Object target,
			String propertyName) throws Exception {
		Object value = PropertyUtils.getProperty(target, propertyName);
		if (value == null) {
			Object orginValue = PropertyUtils.getProperty(orgin, propertyName);
			if (orginValue != null) {
				PropertyUtils.setProperty(target, propertyName, orginValue);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		// 2016年3月30日 17:54:50 看了PropertyUtils的实现，它支持NESTED的获取方式，支持数组获取方式[]，支持map的获取
		InnerClass innerClass = new InnerClass();
		innerClass.getInnerInnerClass().setName("nick");
		innerClass.getInnerInnerClass().getScores().add(3);
		innerClass.getInnerInnerClass().getMap().put("name", "karen");
		System.out.println(PropertyUtils.getProperty(innerClass, "innerInnerClass.name"));
		System.out.println(PropertyUtils.getProperty(innerClass, "innerInnerClass.scores[0]"));
		System.out.println(PropertyUtils.getProperty(innerClass, "innerInnerClass.map.name"));
		System.out.println(PropertyUtils.getProperty(innerClass, "innerInnerClass.map(name)")); // map写法也可以这样
		/**
		 * 看PropertyUtils的实现原理：
		 * 它没有语法树，就是约定这些简单的层次.(NESTED)结构之后，一层一层的取。
		 */
		
		// 复制对象，整个复制
		InnerClass copied = new InnerClass();
		PropertyUtils.copyProperties(copied, innerClass);
		System.out.println("copied name:" + copied.getInnerInnerClass().getName());
	}

}
