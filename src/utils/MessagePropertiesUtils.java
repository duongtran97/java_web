/**
 * Copyright(C) 2020  Luvina Software
 *MessagePropertiesUtils.java, Feb 26, 2020 DuongTV
 */
package utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Đọc các thông tin về các câu thông báo của hệ thống
 * @author DuongTV
 * 
 */
public class MessagePropertiesUtils {
	// lư các cặp <key,value> trong file properties
		private static Map<String, String> mapMessageProperties = new HashMap<String, String>();
		static {
			try {
				// Tạo đối tượng kiểu Properties
				Properties properties = new Properties();
				// đọc file properties
				properties.load(new InputStreamReader(MessagePropertiesUtils.class.getClassLoader()
						.getResourceAsStream(ConstantUtils.PROPERTIES_MESSAGE_PATH), "UTF-8"));
				// lưu các giá trị key trong file properties
				Enumeration<?> enumeration = properties.propertyNames();
				// true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
				while (enumeration.hasMoreElements()) {
					String key = (String) enumeration.nextElement();
					// lấy value tương ứng với key
					String value = properties.getProperty(key);
					// thêm vào list
					mapMessageProperties.put(key, value);
				}
			} catch (IOException e) {
				System.out.println("MessagePropertiesUtils + " + e.getMessage());
			}
		}
		/**
		 * lấy value tương ứng với key trong file properties
		 * @param key tên key trong file properties
		 * @return trả về value tương ứng với key
		 */
		public static String getValueByKey(String key) {
			String value = mapMessageProperties.get(key);
			return value;
		}
}
