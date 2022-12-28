/**
 * Copyright(C) 2020  LUvina Software
 * ConfigProperties.java, 10 Mar 2020 DuongTV
 *
 */
package utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description 
 * @author DuongTV
 *
 */
public class ConfigProperties {
	// lưu các cặp <key, value> trong file properties
			private static Map<String, String> mapCFProperties = new HashMap<String, String>();
			static {
				try {
					// tạo đối tượng kiểu Properties
					Properties properties = new Properties(); 
					// đọc file properties
					properties.load(new InputStreamReader(DatabasePropertiesUtils.class.getClassLoader()
							.getResourceAsStream(ConstantUtils.PROPERTIES_CONFIG_PATH), "UTF-8"));
					// lưu các giá trị key trong file properties					
					Enumeration<?> enumeration = properties.propertyNames(); 
					// true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
					while (enumeration.hasMoreElements()) { 
						// key = key tiếp theo
						String key = (String) enumeration.nextElement(); 
						// lấy value tương ứng với key
						String value = properties.getProperty(key); 
						// thêm vào list
						mapCFProperties.put(key, value); 
					}
				} catch (IOException e) {
					System.out.println("ConfigProperties + " + e.getMessage());
				}
			}

			/**
			 * lấy value tương ứng với key trong file properties
			 * @param key tên key trong file properties
			 * @return trả về value tương ứng với key
			 */
			public static int getValueByKey(String key) {
				int value = 0;
				try {
					 value = Integer.parseInt(mapCFProperties.get(key));
				} catch (NumberFormatException e) {
					System.out.println("ConfigProperties + getValueByKey + "+ e.getMessage());
					throw e;
				}
				return value;
			}
			
}
