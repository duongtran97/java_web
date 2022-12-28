/**
 * Copyright(C) 2020  LUvina Software
 * StringUtil.java, 12 Mar 2020 DuongTV
 *
 */
package utils;

import java.lang.Character.UnicodeBlock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Chứa các hàm common của dự án về kiểu String
 * @author DuongTV
 */
public class StringUtils {
	/**
	 * Kiểm tra chuỗi null và rỗng 
	 * @param strCheck
	 * @return true - chuỗi rỗng và null
	 */
	public static boolean checkNullAndNotType(String strCheck) {
		//nếu chuỗi là null 
		if(strCheck == null) {
			//trả về true
			return true;
		}
		//nếu chuỗi rỗng
		if(strCheck.trim().isEmpty()) {
			//trả về true
			return true;
		}
		return false;
	}
	/**
	 * checkKanakata - Kiểm tra chuỗi có phải là chuỗi katakana hay không
	 * @param strCheck - Chuỗi cần kiểm tra
	 * @return True - Nếu là chuỗi katakana False - Nếu không phải là chuỗi katakana
	 */
	public static boolean checkKanakata(String strCheck) {
		for (char c : strCheck.toCharArray()) {
			if ((UnicodeBlock.of(c) != UnicodeBlock.KATAKANA) || (Character.isDigit(c))
					|| (Character.isWhitespace(c))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * isEnglish - Kiểm tra chuỗi có phải là ký tự tiếng anh hay không
	 * @param strCheck - chuỗi cần kiểm tra
	 * @return true - Nếu là ký tự 1 byte false - Nếu không phải là ký tự
	 *         1 byte - Tiếng anh
	 */
	public static boolean is1Byte(String strCheck) {
		char[] c = strCheck.toCharArray();
		for (int i = 0; i < c.length; i++) {
			// Lấy số Byte cần để biểu diễn chuỗi
			if ((c[i] + "").getBytes().length > 1 || Character.isWhitespace(c[i])) {
				return false;
			}
		}
		return true;
	}
	/**
	 * isDigit - Hàm kiểm tra ký tự có phải là chữ số hay không
	 * @param c - ký tự cần kiểm tra
	 * @return true - Nếu là chữ số false - Nếu không phải là chữ số
	 */
	public static boolean isDigit(char c) {
		int x = (int) c;
		if ((x >= 48) && (x <= 57)) {
			return true;
		}
		return false;
	}
	/**
	 * kiểm tra một chuỗi có phải là số không
	 * @param input chuỗi cần kiểm tra
	 * @return true - là một chuỗi số, false - không
	 */
	public static boolean checkIsStringNumber(String input) {
		boolean check = true;
		if(!checkNullAndNotType(input)) {
			for (int i = 0; i < input.length(); i++) {
				if(!isDigit(input.charAt(i))) {
					check = false;
					break;
				}
			}
		}else {
			check = false;
		}
		return check;
		
	}
	/**
	 * checkMatcherPattern - Kiểm tra chuỗi có khớp với biểu thức hay không
	 * @param strCheck
	 * @param patternCheck
	 * @return true - Nếu khớp
	 * @return false - Nếu không khớp
	 */
	public static boolean checkMatcherPattern(String strCheck, String patternCheck) {
		// đối tượng mẫu, tạo đối tượng thông qua method tĩnh
		Pattern pattern = Pattern.compile(patternCheck);
		// phương tiện để so khớp chuỗi dữ liệu đầu vào
		Matcher matcher = pattern.matcher(strCheck);
		// trả về kết quả so
		return matcher.matches();
	}
	/**
	 * Kiểm tra độ dài của chuỗi nằm trong khoảng
	 * @param from số kí tự nhỏ nhất cho phép
	 * @param to số kí tự lớn nhất cho phép
	 * @param strCheck chuỗi cần kiểm tra
	 * @return true khi thỏa mãn độ dài , false khi không thỏa mãn
	 */
	public static boolean checkLengthString(int from,int to, String strCheck) {
		if(strCheck.length()<from || strCheck.length()>to) {
			return false;
		}
		return true;
	}
}
