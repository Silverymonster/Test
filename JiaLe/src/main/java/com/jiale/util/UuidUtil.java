package com.jiale.util;

import java.util.UUID;

/**
 * 
 * @author JiaLe UUID通用唯一识别码,是一种软件建构的标准
 *         UUID的标准型式包含32个16进制数字，以连字号分为五段，形式为8-4-4-4-12的32个字符
 *
 */

public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
}
