package com.cabletech.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 字符串处理类
 * 
 * @author wangt
 * 
 */
public class StringUtil {

	/**
	 * 计算字符串的长度
	 * 
	 * @param strExp
	 *            字符串
	 * @return
	 */
	public static synchronized int length(String strExp) {
		return strExp.getBytes().length;
	}

	/**
	 * 获取字符串左边指定个数字符组成的字符串
	 * 
	 * @param strExp
	 *            String
	 * @param intLen
	 *            int
	 */
	public static synchronized String left(String strExp, int intLen) {
		if (intLen <= 0)
			return "";

		if (length(strExp) <= intLen)
			return strExp;

		if (length(strExp) == strExp.length())
			return strExp.substring(0, intLen);

		int intCLoop = 0;
		int intBLoop = 0;
		byte abytTemp[] = strExp.getBytes();

		while (true) {
			if (abytTemp[intBLoop] > 127 || abytTemp[intBLoop] < 0) {
				if (intBLoop + 2 > intLen) {
					break;
				} else {
					intBLoop++;
					intBLoop++;
				}
			} else {
				if (intBLoop + 1 > intLen) {
					break;
				} else {
					intBLoop++;
				}
			}
			intCLoop++;
		}

		return strExp.substring(0, intCLoop);
	}

	/**
	 * 根据月份重组str
	 * 
	 * @param month
	 *            String
	 * @return
	 */
	public static String getMonth(String month) {
		if (Integer.parseInt(month) < 10) {
			return "0" + month;

		} else
			return month;
	}

	/**
	 * 获取给定字符串指定位数的右边的字符串
	 * 
	 * @param strExp
	 *            String
	 * @param intLen
	 *            int
	 * @return
	 */
	public static synchronized String right(String strExp, int intLen) {
		if (intLen <= 0)
			return "";

		if (length(strExp) <= intLen)
			return strExp;

		if (length(strExp) == strExp.length())
			return strExp.substring(strExp.length() - intLen);

		int intCLoop = 0;
		int intBLoop = 0;
		byte abytTemp[] = strExp.getBytes();

		while (intBLoop < length(strExp) - intLen) {
			if (abytTemp[intBLoop] > 127 || abytTemp[intBLoop] < 0) {
				intBLoop++;
				intBLoop++;
			} else {
				intBLoop++;
			}
			intCLoop++;

		}
		return strExp.substring(intCLoop);
	}

	/**
	 * 获取子串
	 * 
	 * @param strExp
	 *            String
	 * @param intFrom
	 *            int
	 * @return
	 */
	public static synchronized String substring(String strExp, int intFrom) {
		if (intFrom <= 0)
			return strExp;

		if (length(strExp) <= intFrom)
			return "";

		int intCLoop = 0;
		int intBLoop = 0;
		byte abytTemp[] = strExp.getBytes();

		while (true) {
			if (abytTemp[intBLoop] > 127 || abytTemp[intBLoop] < 0) {
				if (intBLoop + 2 > intFrom) {
					break;
				} else {
					intBLoop++;
					intBLoop++;
				}
			} else {
				if (intBLoop + 1 > intFrom) {
					break;
				} else {
					intBLoop++;
				}
			}
			intCLoop++;
		}

		return strExp.substring(intCLoop);
	}

	/**
	 * 获取子串
	 * 
	 * @param strExp
	 *            String
	 * @param intFrom
	 *            int
	 * @param intLen
	 *            int
	 * @return
	 */
	public static synchronized String substring(String strExp, int intFrom,
			int intLen) {
		if (intLen <= 0)
			return "";

		strExp = substring(strExp, intFrom);
		if (strExp.length() > 0) {
			strExp = left(strExp, intLen);
		}
		return strExp;
	}

	/**
	 * indexOf
	 * 
	 * @param strExp
	 *            String
	 * @param strSub
	 *            String
	 * @return
	 */
	public static synchronized int indexOf(String strExp, String strSub) {

		int intTemp = strExp.indexOf(strSub);
		if (intTemp <= 0 || length(strExp) == strExp.length())
			return intTemp;

		int intCLoop = 0;
		int intBLoop = 0;
		byte abytTemp[] = strExp.getBytes();

		while (intCLoop < intTemp) {
			if (abytTemp[intBLoop] > 127 || abytTemp[intBLoop] < 0) {
				intBLoop++;
				intBLoop++;
			} else {
				intBLoop++;
			}
			intCLoop++;
		}

		return intBLoop;
	}

	/**
	 * 获取子串的位置
	 * 
	 * @param strExp
	 *            String
	 * @param strSub
	 *            String
	 * @param intFrom
	 *            int
	 */
	public static synchronized int indexOf(String strExp, String strSub,
			int intFrom) {
		intFrom = intFrom < 0 ? 0 : intFrom;

		if (length(strExp) < intFrom)
			return -1;

		if (length(strExp) == strExp.length())
			return strExp.indexOf(strSub, intFrom);

		int intCLoop = 0;
		int intBLoop = 0;
		byte abytTemp[] = strExp.getBytes();

		while (true) {
			if (abytTemp[intBLoop] > 127 || abytTemp[intBLoop] < 0) {
				if (intBLoop + 2 > intFrom) {
					break;
				} else {
					intBLoop++;
					intBLoop++;
				}
			} else {
				if (intBLoop + 1 > intFrom) {
					break;
				} else {
					intBLoop++;
				}
			}
			intCLoop++;
		}

		intFrom = intCLoop;

		int intTemp = strExp.indexOf(strSub, intFrom);
		if (intTemp <= 0)
			return intTemp;

		intCLoop = 0;
		intBLoop = 0;

		while (intCLoop < intTemp) {
			if (abytTemp[intBLoop] > 127 || abytTemp[intBLoop] < 0) {
				intBLoop++;
				intBLoop++;
			} else {
				intBLoop++;
			}
			intCLoop++;
		}
		return intBLoop;
	}

	/**
	 * 获取子串的最后一个位置
	 * 
	 * @param strExp
	 *            String
	 * @param strSub
	 *            String
	 * @return
	 */
	public static synchronized int lastIndexOf(String strExp, String strSub) {
		int intTemp = strExp.lastIndexOf(strSub);
		if (intTemp <= 0 || length(strExp) == strExp.length())
			return intTemp;

		int intCLoop = 0;
		int intBLoop = 0;
		byte abytTemp[] = strExp.getBytes();

		while (intCLoop < intTemp) {
			if (abytTemp[intBLoop] > 127 || abytTemp[intBLoop] < 0) {
				intBLoop++;
				intBLoop++;
			} else {
				intBLoop++;
			}
			intCLoop++;
		}

		return intBLoop;
	}

	/**
	 * 用指定字符串替换原来的字符串
	 * 
	 * @param strExp
	 *            String
	 * @param strFind
	 *            String
	 * @param strRep
	 *            String
	 * @return String
	 */
	public static synchronized String replace(String strExp, String strFind,
			String strRep) {
		int intFrom, intTo;
		String strHead, strTail;

		if (strFind == null || strFind.length() == 0)
			return strExp;

		intFrom = strExp.indexOf(strFind, 0);
		while (intFrom >= 0) {
			intTo = intFrom + strFind.length();
			strHead = strExp.substring(0, intFrom);
			strTail = strExp.substring(intTo);
			strExp = strHead + strRep + strTail;
			intTo = intFrom + strRep.length();
			intFrom = strExp.indexOf(strFind, intTo);
		}

		return strExp;
	}

	/**
	 * 去除字串左侧开始的空格
	 * 
	 * @param strExp
	 *            String
	 * @param strTrim
	 *            String
	 * @return String
	 */
	public static synchronized String ltrim(String strExp, String strTrim) {
		int i;

		if (strExp == null || strExp.length() == 0) {
			strExp = "";
		}

		if (strTrim == null || strTrim.length() == 0)
			return strExp;

		char chrTrim = strTrim.charAt(0);

		for (i = 0; i < strExp.length(); i++) {
			if (strExp.charAt(i) != chrTrim) {
				break;
			}
		}

		return strExp.substring(i);
	}

	/**
	 * 去除字串右侧结尾处的空格
	 * 
	 * @param strExp
	 *            String
	 * @param strTrim
	 *            String
	 * @return String
	 */
	public static synchronized String rtrim(String strExp, String strTrim) {
		int i;

		if (strExp == null || strExp.length() == 0) {
			strExp = "";
		}

		int intLen = strExp.length();

		if (strTrim == null || strTrim.length() == 0)
			return strExp;

		char chrTrim = strTrim.charAt(0);

		for (i = 0; i < strExp.length(); i++) {
			if (strExp.charAt(intLen - i - 1) != chrTrim) {
				break;
			}
		}

		return strExp.substring(0, intLen - i);
	}

	/**
	 * 去掉空格
	 * 
	 * @param strExp
	 *            String
	 * @return
	 */
	public static synchronized String trim(String strExp) {
		return trim(strExp, " ");
	}

	/**
	 * 去掉空格
	 * 
	 * @param strExp
	 *            String
	 * @param strTrim
	 *            String
	 * @return String
	 */
	public static synchronized String trim(String strExp, String strTrim) {
		return ltrim(rtrim(strExp, strTrim), strTrim);
	}

	/**
	 * 进行左填充
	 * 
	 * @param strExp
	 *            String
	 * @param intLen
	 *            int
	 * @param strPad
	 *            String
	 * @return String
	 */
	public static synchronized String lpad(String strExp, int intLen,
			String strPad) {
		if (strExp == null) {
			strExp = "";
		}

		while (strExp.length() < intLen) {
			strExp = strPad + strExp;
		}
		return right(strExp, intLen);
	}

	/**
	 * 进行右填充
	 * 
	 * @param strExp
	 *            String
	 * @param intLen
	 *            int
	 * @param strPad
	 *            String
	 * @return String
	 */
	public static synchronized String rpad(String strExp, int intLen,
			String strPad) {
		if (strExp == null) {
			strExp = "";
		}

		while (strExp.length() < intLen) {
			strExp = strExp + strPad;
		}
		return left(strExp, intLen);
	}

	/**
	 * 在HTML中显示文本信息（替换& " < > 四种字符为HTML中的字符显示格式）
	 * 
	 * @param strExp
	 *            String
	 * @return String
	 */
	public static synchronized String HtmlString(String strExp) {
		if (strExp == null || strExp.length() == 0)
			return "";

		strExp = replace(strExp, "&", "&amp;");
		strExp = replace(strExp, "<", "&lt;");
		strExp = replace(strExp, ">", "&gt;");
		strExp = replace(strExp, "\"", "&quot;");
		strExp = replace(strExp, " ", "&nbsp;");
		return strExp;
	}

	/**
	 * 转换文本字串为HTML字串
	 * 
	 * @param strExp
	 *            String
	 */
	public static synchronized String HtmlStringFromText(String strExp) {
		return HtmlStringFromText(strExp, "");
	}

	// Transfer text string into html format

	/**
	 * 转换文本字串为HTML字串
	 * 
	 * @param strExp
	 *            String
	 * @param strPad
	 *            String
	 */
	public static synchronized String HtmlStringFromText(String strExp,
			String strPad) {
		if (strExp == null || strExp.length() == 0)
			return strPad;

		strExp = replace(strExp, "&", "&amp;");
		strExp = replace(strExp, "<", "&lt;");
		strExp = replace(strExp, ">", "&gt;");
		strExp = replace(strExp, "\"", "&quot;");
		strExp = replace(strExp, "\r\n", "<br>\r\n");
		return strExp;
	}

	// Get data from database, and put it into JSP file as a display string

	/**
	 * 转换文本字串为HTML字串
	 * 
	 * @param strExp
	 *            String
	 * @param strPad
	 *            String
	 */
	public static synchronized String HtmlString(String strExp, String strPad) {

		if (strExp == null || strExp.trim().length() == 0)
			return strPad;
		strExp = strExp.trim();

		strExp = HtmlString(strExp);
		return strExp;
	}

	/**
	 * 在HTML中显示文本信息（替换& " < >四种字符为HTML中的字符显示格式）
	 * 
	 * @param strExp
	 *            String
	 * @return String
	 */
	public static synchronized String ShowTextArea(String strExp) {
		if (strExp == null || strExp.length() == 0) {
			return "";
		}
		strExp = replace(strExp, "&", "&amp;");
		strExp = replace(strExp, "<", "&lt;");
		strExp = replace(strExp, ">", "&gt;");
		strExp = replace(strExp, "\"", "&quot;");
		return strExp;
	}

	/**
	 * 在HTML中显示文本信息（替换\ "两种字符为HTML中的字符显示格式）
	 * 
	 * @param strExp
	 *            String
	 * @return String
	 */
	public static synchronized String SpecString(String strExp) {
		if (strExp == null || strExp.length() == 0) {
			return "";
		}
		strExp = replace(strExp, "\\", "\\\\");
		strExp = replace(strExp, "\"", "\\\"");
		return strExp;
	}

	/**
	 * 将用;号分隔的字符串转换为字符串数组
	 * 
	 * @param strExp
	 *            String
	 * @return String[]
	 */
	public static synchronized String[] getAccountInfoString(String strExp) {
		if (strExp == null || strExp.length() == 0) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(strExp, ";");
		String[] strTemp = new String[2];
		strTemp[1] = "";
		int i = 0;
		while (st.hasMoreTokens()) {
			strTemp[i++] = st.nextToken();
		}
		return strTemp;
	}

	/**
	 * List 转换为 String <br>
	 * 转换的string 类型主要用于sql中。
	 * 
	 * @param strList
	 *            List<String>
	 * @return String
	 */
	public static String listStr4Sql(List<String> strList) {
		String listString = strList.toString();
		listString = listString.replace('[', '\'');
		listString = listString.replaceAll(", ", "','");
		listString = listString.replace(']', '\'');
		return listString;
	}

	/**
	 * 将map的字符串显示换为,号分隔的字符串
	 * 
	 * @param strmap
	 *            Map<String, String>
	 * @return String
	 */
	public static String map2StrSql(Map<String, String> strmap) {
		String mapstring = strmap.toString();
		mapstring = mapstring.replace('{', '\'');
		mapstring = mapstring.replaceAll(", ", "','");
		mapstring = mapstring.replaceAll("=", "','");
		mapstring = mapstring.replace('}', '\'');
		return mapstring + ",''";
	}

	/**
	 * 将字符串数组转换成,号分隔的字符串
	 * 
	 * @param array
	 *            String[]
	 * @return String
	 */
	public static String array2String(String[] array) {
		return list2StringComma(Arrays.asList(array));

	}

	/**
	 * 将数值型字符串转换成Integer型
	 * 
	 * @since 1.0
	 * @param str
	 *            String 需要转换的字符型字符串
	 * @param ret
	 *            Integer 转换失败时返回的值
	 * @return 成功则返回转换后的Integer型值；失败则返回ret
	 */
	public static Integer String2Integer(String str, Integer ret) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return ret;
		}
	}

	/**
	 * 将list数组转换为以逗号分隔的字符串 例如：sohu,sina,google,baidu,
	 * 
	 * @param list
	 *            <String>
	 * @return
	 */
	public static String list2StringComma(List<String> list) {
		return list2String(list, ",");
	}

	/**
	 * 将list以#（pound）进行分隔 例如：sohu#sina#google#baidu#
	 * 
	 * @param list
	 *            List<String>
	 * @return String
	 */
	public static String list2StringPound(List<String> list) {
		return list2String(list, "#");
	}

	/**
	 * 将数组切割成以指定分隔符分隔的字符串
	 * 
	 * @param list
	 *            数组
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static String list2String(List<String> list, String separator) {
		StringBuilder strs = new StringBuilder();
		int i = 1;
		int size = list.size();
		for (String str : list) {
			strs.append(str);
			if (i < size) {
				strs.append(separator);
			}
			i++;
		}
		return strs.toString();
	}

	/**
	 * 将以指定分隔符分隔的字符串，重组为数组
	 * 
	 * @param str
	 *            以指定分隔符分隔的字符串
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static List<String> string2List(String str, String separator) {
		List<String> list = new ArrayList<String>();
		if (str != null) {
			StringTokenizer st = new StringTokenizer(str, separator);
			while (st.hasMoreTokens()) {
				list.add(st.nextToken());
			}
		}
		return list;

	}

	/**
	 * 将以#号分隔的字符串，重组成数组
	 * 
	 * @param str
	 *            String
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List string2ListPound(String str) {
		return StringUtil.string2List(str, "#");
	}

	/**
	 * 获得合适于select_options的字符串
	 * 
	 * @param ls
	 *            List 转换的list
	 * @return String
	 */
	public static String selectedOtionsAjaxStr(List<Map<String, Object>> ls) {
		StringBuffer buf = new StringBuffer();
		Map<String, Object> map = null;
		for (int i = 0; i < ls.size(); i++) {
			map = ls.get(i);
			buf.append(map.get("ID"));
			buf.append(",");
			buf.append(map.get("NAME"));
			if (i < ls.size() - 1) {
				buf.append(";");
			}
		}
		return buf.toString();
	}
}
