package com.pugwoo.j2se.apache_commons.beanutils;

import com.pugwoo.j2se.apache_commons.model.StudentDO;

/**
 * 2014年11月5日 16:22:30
 */
public class TestPropertySyncTool {

	public static void main(String[] args) throws Exception {
		StudentDO orgin = new StudentDO();
		orgin.setName("nick");

		StudentDO target = new StudentDO();

		PropertySyncTool.setPropertyIfTargetNull(orgin, target, "name");

		System.out.println(target.getName());
	}

}
