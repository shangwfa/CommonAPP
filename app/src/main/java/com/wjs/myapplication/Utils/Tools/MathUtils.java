package com.wjs.myapplication.Utils.Tools;

import java.text.DecimalFormat;

/**
 * @author zju_wjf
 *
 */
public final class MathUtils {
	
	/**
	 * @param value
	 * @param format like 0.00
	 * @return
	 */
	public static String getNumberString(final double value, final String format) {
		final DecimalFormat df = new DecimalFormat(format);
        return df.format(value);
	}
	
//	/**
//	 * @param value
//	 * @return
//	 * 自动截取合适的小数点
//	 */
//	public static String getAutoNumberString(final double value) {
//		//最多 二位
//		final String strValue = getNumberString(value, "#.##");
//		return strValue;
//	}
	
	public static String getNumberString(final double value) {
		return getNumberString(value, "0.00");
	}
	
}
