/**   
* @Title: PublicController.java 
* @Package com.hikvision 
* @Description: TODO
* @author Aether
* @date 2015-1-12 下午6:19:32 
* @version V1.0   
*/ 
package com.hikvision;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.format.JsonConvert;

import com.alibaba.fastjson.JSON;

/** 
 * @ClassName: PublicController 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-12 下午6:19:32 
 *  
 */
public class PublicController
{
	private Log log = LogFactory.getLog(PublicController.class);
	protected Map<String, Object> paramsInit(String method,	Map<String, Object> paramsMap) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		long time = System.currentTimeMillis() / 1000;

		/** 换成自己的 */
/*		String key = "c6c01c86768c44b883575bce6fb4a0d6";

		String secret = "245c37d9e5201a7a16b161bdf3053c29";*/
        /** 测试用(开发者需换成自己的appkey和secret) */
        //String key = "c279ded87d3f4fdca7658f95fb5f1d9e";
        String key = "9a39449992d048439b4cef7d62a3c997";
        //String secret = "b097e53bb9627e7e32b7a8001c701151";
        String secret = "2e49fa81764d370c2693a5f1ed0d8048";

		StringBuilder paramString = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		for (Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext();) {
			String key1 = it.next();
			String param = key1 + ":" + paramsMap.get(key1);
			paramList.add(param);
		}
		String[] params = paramList.toArray(new String[paramList.size()]);
		Arrays.sort(params);
		for (String param : params) {
			paramString.append(param).append(",");
		}
		paramString.append("method").append(":").append(method).append(",");
		paramString.append("time").append(":").append(time).append(",");
		paramString.append("secret").append(":").append(secret);
		System.out.println(paramString);
		System.out.println("未加密的字符串:" + paramString.toString().trim());
		// 加密的类
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			// 对接后的字符串进行MD5加密
			byte[] digest = md.digest(paramString.toString().getBytes());
			String sign = byteToStr(digest);
			System.out.println("加密的字符串:" + sign);
			Map<String, Object> systemMap = new HashMap<String, Object>();
			systemMap.put("ver", "1.0");
			systemMap.put("sign", sign);
			systemMap.put("key", key);
			systemMap.put("time", time);
			map.put("system", systemMap);
			map.put("method", method);
			map.put("params", paramsMap);
			map.put("id", "123456");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f' };
		char[] tempArr = new char[2];
		// 位移 ，把2进制的字节位移 4位就是16进制了
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

	 protected TokenResultModel doPost(Map<String, Object> map) 
	 {
		 	TokenResultRsp result= new TokenResultRsp();
		 	String json = JSON.toJSONString(map);
	        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
	        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
	        HttpClient client = new HttpClient();

	        PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
	        try {
	            RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
	            method.setRequestEntity(entity);
	            client.executeMethod(method);

	            InputStream inputStream = method.getResponseBodyAsStream();
	            String jsonResult = IOUtils.toString(inputStream);
	            result=(TokenResultRsp) JsonConvert.jsonToObject(jsonResult, TokenResultRsp.class);
	            //System.out.println(result);
	            log.info("get token result is:"+jsonResult);
	            
	            
	        } catch (Exception e)
	        {
	           // e.printStackTrace();
	            log.error("get Token failed,the error is",e);
	            
	        } finally {
	            // 释放连接
	            method.releaseConnection();
	        }
	        return result.getResult();
	    }
	 
		public TokenResultModel getAccessToken(String userId,String phone) 
		{
			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId);
			paramsMap.put("phone", phone);
			Map<String, Object> map = paramsInit("token/accessToken/get", paramsMap);
			TokenResultModel result =doPost(map);
			log.info("getAccessToken result is:"+result);
			return result;
		}

}
