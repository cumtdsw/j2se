package com.pugwoo.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;

public class GetStarted {

	public static void main(String[] args) {
		/**
		 * 之所以返回数组是可能为多音字
		 */
		String[] pinyinArray = PinyinHelper.toTongyongPinyinStringArray('李');
		// String[] pinyinArray = PinyinHelper.toYalePinyinStringArray('李');
		
		for (int i = 0; i < pinyinArray.length; i++) {
			System.out.println(pinyinArray[i]);
		}
	}

}
