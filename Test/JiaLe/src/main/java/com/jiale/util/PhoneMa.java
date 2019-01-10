package com.jiale.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author JiaLe 手机短信验证码
 *
 */
public class PhoneMa {

	private static final String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
	private static final String ACCOUNT_SID = "0fe3c5d3af08412da53b794afc3c7ff5";
	private static final String AUTH_TOKEN = "f1348df47f7047abaa72eccac805487c";
	private static String phonename = "";
	private static String keyname = "";
	private static String templeid = "";

	public static String getPhoneMa(String key, String phone) {
		StringBuffer bu = new StringBuffer();
		try {
			phonename = URLEncoder.encode(phone, "utf-8");
			keyname = URLEncoder.encode(key, "utf-8");
			templeid = URLEncoder.encode("802520068", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String str = "accountSid=" + ACCOUNT_SID + "&to=" + phonename + "&param=" + keyname + "&templateid=" + templeid
				+ createCommonParam();
		System.out.println(str);
		try {
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(2000);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
			out.write(str);
			out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String s = "";
			boolean firstLine = true; // ����һ�в��ӻ��з�
			while ((s = in.readLine()) != null) {
				if (firstLine) {
					firstLine = false;
				} else {
					bu.append(System.lineSeparator());
				}
				bu.append(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(bu.toString());
		return bu.toString();
	}

	public static String createCommonParam() {
		// 时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		System.out.println(timestamp);
		// 加密
		System.out.println(ACCOUNT_SID + "   " + AUTH_TOKEN);
		String sig = DigestUtils.md5Hex(ACCOUNT_SID + AUTH_TOKEN + timestamp);
		return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=JSON";
	}
}
