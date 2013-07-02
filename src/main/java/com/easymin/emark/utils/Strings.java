/**
 * 
 */
package com.easymin.emark.utils;

/**
 * @author Shine
 *
 */
public class Strings {

	/**
	 * 判断字符串是否为空白
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {

		if (null == str) {
			return true;
		}

		char[] chars = str.toCharArray();

		for (char ch : chars) {
			if (!Character.isWhitespace(ch)) {
				return false;
			}
		}

		return true;
	}
}
