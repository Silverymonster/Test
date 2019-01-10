package myx.Util;

import net.sf.json.JSONArray;

public class ToJson {
     public static String toJsonArray(Object obj) {
		return JSONArray.fromObject(obj).toString();
     }
}
