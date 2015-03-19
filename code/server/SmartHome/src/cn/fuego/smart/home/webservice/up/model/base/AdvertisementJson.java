/**   
* @Title: AD.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-3-3 下午3:43:00 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: AD 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-3 下午3:43:00 
 *  
 */
public class AdvertisementJson implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PRI_KEY = "adID";
	
	private int adID;
	private String adName;
	private String adInfo;
	private String adURL;
	private String adImg;
	public int getAdID()
	{
		return adID;
	}
	public void setAdID(int adID)
	{
		this.adID = adID;
	}
	public String getAdName()
	{
		return adName;
	}
	public void setAdName(String adName)
	{
		this.adName = adName;
	}
	public String getAdInfo()
	{
		return adInfo;
	}
	public void setAdInfo(String adInfo)
	{
		this.adInfo = adInfo;
	}
	public String getAdURL()
	{
		return adURL;
	}
	public void setAdURL(String adURL)
	{
		this.adURL = adURL;
	}
	public String getAdImg()
	{
		return adImg;
	}
	public void setAdImg(String adImg)
	{
		this.adImg = adImg;
	}

	
	
}
