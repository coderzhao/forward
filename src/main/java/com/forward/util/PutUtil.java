package com.forward.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class PutUtil {
	
	private static Logger logger = Logger.getLogger(PutUtil.class);

	public static String requestForword(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		logger.info("METHOD:"+request.getMethod());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject jsonObject = null;
		InputStream inputStream = request.getInputStream();
		StringBuffer stringBuffer = new StringBuffer();
		Map<String,String> param = new HashMap<String,String>();
		Map<String,Object> body = new HashMap<String,Object>();
		//读取报文参数
		String meta = "no";
		if(inputStream.available()!=0) {
			logger.info("HAS PARAM");
			meta = "yes";
			int b;
			while(( b=inputStream.read())!=-1) {
				char word = (char)b;
				stringBuffer.append(word);
			}
			JSONParser jsonParser = new JSONParser();
			try {
				jsonObject = (JSONObject) jsonParser.parse(stringBuffer.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("ERROR:ParseException");
			}
			//转发报文参数
			@SuppressWarnings("unchecked")
			Iterator<Entry<String,Object>> iterator = jsonObject.entrySet().iterator();
			while(iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				logger.info("PARAM---"+entry.getKey()+":"+entry.getValue());
				body.put(entry.getKey(), entry.getValue());
			}
		}
		//转发和设定报头信息
		param.put("Method",request.getMethod());
		param.put("Authorization","Hhhzp023j1nckca9OsauXr-T4Onmf7Bp");
		/*param.put("Authorization","iqwpYL6jTEnnebA2WIYeluFZCtBV4kx3");*/
		/*param.put("Authorization","kXa75ctpbTqoS6vo1QtYe-Gae9gNLnGR");*/
		param.put("Content-Type", "application/json");
		param.put("API",request.getRequestURI().split("n-tech")[1]);
		
		String SDKreply =  ConnectionSDK.httpURLConnectionPOST(param, body,meta);
		
		return SDKreply;
	}
}