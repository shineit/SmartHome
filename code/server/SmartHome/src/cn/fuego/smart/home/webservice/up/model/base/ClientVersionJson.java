package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;

public class ClientVersionJson implements Serializable
{
	private int versionID;
	private int companyID;
	private String appName;
	private String apkName;
	private String versionName;
	private int versionCode;
	private int clientType;
	private String apkURL;
	private String qrCode;
	private int versionStatus; // 1-new, 0-old;
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
	@Override
	public String toString()
	{
		return "ClientVersionJson [versionID=" + versionID + ", companyID="
				+ companyID + ", appName=" + appName + ", apkName=" + apkName
				+ ", versionName=" + versionName + ", versionCode="
				+ versionCode + ", clientType=" + clientType + ", apkURL="
				+ apkURL + ", qrCode=" + qrCode + ", versionStatus="
				+ versionStatus + "]";
	}

	
	
 }
