/**   
 * @Title: ShartMessageServiceImpl.java 
 * @Package cn.fuego.misp.service.impl 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-10-30 上午10:50:46 
 * @version V1.0   
 */
package cn.fuego.misp.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MISPShortMessageService;

/**
 * @ClassName: ShartMessageServiceImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-30 上午10:50:46
 * 
 */
public class MXShortMessageServiceImpl implements MISPShortMessageService
{
	private static String MX_MESSAGE_URL = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend?";
	private Log log = LogFactory.getLog(MXShortMessageServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.fuego.misp.service.ShortMessageService#sendMessage(java.util.List,
	 * java.lang.String)
	 */
	@Override
	public boolean sendMessage(List<String> phoneNumList, String content)
	{
		String url = MX_MESSAGE_URL + "UserID="
				+ SystemConfigInfo.getMessageUserID();
		url += "&Account=" + SystemConfigInfo.getMessageAccount();
		url += "&Password=" + SystemConfigInfo.getMessagePassword();

		String phoneStr = "";
		for (String e : phoneNumList)
		{	if(!ValidatorUtil.isEmpty(e))
			{
				e.trim();
				phoneStr += e + ";";
			}
			else
			{
				log.warn("Exist empty phoneNumber");
			}
			
		}
		url += "&Phones=" + phoneStr;
		url += "&Content=" + content;
		url += "&SendTime=";
		url += "&SendType=1";
		url += "&PostFixNumber=";
		HttpClient httpClient = new DefaultHttpClient();  
		HttpGet httpget = new HttpGet(url);
		
		
		HttpResponse response = null;
		try
		{
 			response = httpClient.execute(httpget);

			HttpEntity entity = response.getEntity();
			if (entity != null)
			{
				String result = EntityUtils.toString(entity);
				log.info(result);  
			    EntityUtils.consume(entity);  
			}
		}
		catch(Exception e)
		{
			log.error("send message error",e);
			return false;
		}
		return true;
 
	}

}
