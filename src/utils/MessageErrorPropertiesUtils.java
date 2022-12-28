/**
 * Copyright(C) 2020  Luvina Software
 * MessageErrorPropertiesUtils.java, Feb 26, 2020 DuongTV
 */
package utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * đọc các thông tin về các câu thông báo lỗi của hệ thống
 * @author DuongTV
 */
public class MessageErrorPropertiesUtils {
// lưu các cặp <key,value> trong file properties
	private static Map<String, String> mapMessageErrProperties = new HashMap<String, String>();
	static {
		try {
			// Tạo đối tượng kiểu Properties
			Properties properties = new Properties();
			// đọc file properties
			properties.load(new InputStreamReader(MessageErrorPropertiesUtils.class.getClassLoader()
					.getResourceAsStream(ConstantUtils.PROPERTIES_MESSAGE_ERR_PATH), "UTF-8"));
			// lưu các giá trị key trong file properties
			Enumeration<?> enumeration = properties.propertyNames();
			// true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				// lấy value tương ứng với key
				String value = properties.getProperty(key);
				// thêm vào list
				mapMessageErrProperties.put(key, value);
			}
		} catch (IOException e) {
			System.out.println("MessageErrorPropertiesUtils + " + e.getMessage());
		}
	}
	/**
	 * lấy value tương ứng với key trong file properties
	 * @param key tên key trong file properties
	 * @return trả về value tương ứng với key
	 */
	public static String getValueByKey(String key) {
		String value = mapMessageErrProperties.get(key);
		return value;
	}
}
