package com.zy.sso.base.util;

public class StringUtil {
	/**
	 * 判断字符串是否为非空
	 * 
	 * @param str
	 *            字符串
	 * @return 非空返回true，空串返回false
	 */
	public static boolean isNotNull(final String str) {
		if ((null == str) || (str.trim().equals(""))) {
			return false;
		}
		return true;
	}
	public static boolean isEmpty(final String str){
        if(str == null || str.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
   	}
	
	/**
     * 将JSON数据中的 /r/n 替换 为 /n
     */
    public static String JsonFilter(String jsonstr) {
        jsonstr = jsonstr.replace("\r\n", "\n");
        return jsonstr;
    }

}
