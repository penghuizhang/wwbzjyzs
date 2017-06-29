
package cn.wechat.oa.wx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;
/**
 * @author kylin
 * @description:
 * @date 2017-5-4
 */
public class wxUtils {

	public static final String APPID = "wx57048fccc7bd7900";
	public static final String SECRET = "aaafdca602abaee72c89a9f75944dada";

	public static final String codeGetsecret_key = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
    

	public static String replaces(String APPID, String SECRET) {
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET
				+ "&js_code=JSCODE&grant_type=authorization_code";
		System.out.println("url = " + url);
		return url;
	}

	public static JSONObject HttpRequest(String request, String RequestMethod, String output) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
		
			URL url = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod(RequestMethod);
			if (output != null) {
				OutputStream out = connection.getOutputStream();
				out.write(output.getBytes("UTF-8"));
				out.close();
			}
	
			InputStream input = connection.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(input, "UTF-8");
			BufferedReader reader = new BufferedReader(inputReader);
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		
			reader.close();
			inputReader.close();
			input.close();
			input = null;
			connection.disconnect();

		} catch (Exception e) {
		}
		return jsonObject;
	}

	public static OpenId getOpenId(String code) {
		OpenId openId = new OpenId();
		String requestUrl = codeGetsecret_key.replace("APPID", APPID).replace("SECRET", SECRET).replace("JSCODE", code);
		JSONObject jsonObject = HttpRequest(requestUrl, "GET", null);
		
		System.out.println(requestUrl);
		System.out.println(jsonObject);
		if (null != jsonObject) {
			try {
				openId.setOpenid(jsonObject.getString("openid"));
				openId.setExpires_in(jsonObject.getInt("expires_in"));
				openId.setSession_key(jsonObject.getString("session_key"));
				System.out.println(openId.getOpenid());
				System.out.println(openId.getExpires_in());
				System.out.println(openId.getSession_key());
				
			} catch (Exception e) {
				openId = null;
			}
		}
		return openId;
	}

	public static String URLEncoder(String str) {
		String result = str;
		try {
			result = java.net.URLEncoder.encode(result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
