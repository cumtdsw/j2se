package com.pugwoo.j2se.apache_commons.beanutils;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 2014年11月5日 16:02:22<br>
 * 这里演示一个如果目标obj没值则同步，有值则不变的工具<br>
 * 
 * 示例详见test代码
 */
public class PropertySyncTool {

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

}
