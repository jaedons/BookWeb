package com.example.demo.util;

import java.util.UUID;

/**UUID操作*/
public class UUIDUtil {
	/**获取UUID*/
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString();
		uuidStr =  uuidStr.replace("-", "");
		return uuidStr;
	}
}
