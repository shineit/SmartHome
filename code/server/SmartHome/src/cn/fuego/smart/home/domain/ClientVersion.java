/**   
* @Title: ClientVersion.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-1-16 下午7:26:20 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

/** 
 * @ClassName: ClientVersion 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-16 下午7:26:20 
 *  
 */
public class ClientVersion
{
	private int versionID;
	private int companyID;
	private String appName;
	private String apkName;
	private String versionName;
	private int versionCode;
	private int clientType;	//WEB_CLIENT(0,"WEB"),ANDRIOD_CLIENT(3,"ANDRIOD"),IOS_CLIENT(4,"IOS");
	private String apkURL;
	private String qrCode;
	private int versionStatus; // 0-old;1-new;
	public int getVersionID()
	{
		return versionID;
	}
	public void setVersionID(int versionID)
	{
		this.versionID = versionID;
	}
	public int getCompanyID()
	{
		return companyID;
	}
	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}
	public String getAppName()
	{
		return appName;
	}
	public void setAppName(String appName)
	{
		this.appName = appName;
	}
	public String getApkName()
	{
		return apkName;
	}
	public void setApkName(String apkName)
	{
		this.apkName = apkName;
	}
	public String getVersionName()
	{
		return versionName;
	}
	public void setVersionName(String versionName)
	{
		this.versionName = versionName;
	}
	public int getVersionCode()
	{
		return versionCode;
	}
	public void setVersionCode(int versionCode)
	{
		this.versionCode = versionCode;
	}

	public int getClientType()
	{
		return clientType;
	}
	public void setClientType(int clientType)
	{
		this.clientType = clientType;
	}
	public String getApkURL()
	{
		return apkURL;
	}
	public void setApkURL(String apkURL)
	{
		this.apkURL = apkURL;
	}
	public String getQrCode()
	{
		return qrCode;
	}
	public void setQrCode(String qrCode)
	{
		this.qrCode = qrCode;
	}
	public int getVersionStatus()
	{
		return versionStatus;
	}
	public void setVersionStatus(int versionStatus)
	{
		this.versionStatus = versionStatus;
	}
	
	
}
