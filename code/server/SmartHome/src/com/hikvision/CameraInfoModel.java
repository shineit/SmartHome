/**   
* @Title: CameraInfoModel.java 
* @Package com.hikvision 
* @Description: TODO
* @author Aether
* @date 2015-1-23 下午12:25:16 
* @version V1.0   
*/ 
package com.hikvision;

/** 
 * @ClassName: CameraInfoModel 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-23 下午12:25:16 
 *  
 */
public class CameraInfoModel
{
	private String deviceId;
	private String deviceSerial;
	private String cameraId;
	private String cameraNo;
	private String cameraName;
	private String status;
	private String isShared;
	private String picUrl;
	private String isEncrypt;
	public String getDeviceId()
	{
		return deviceId;
	}
	public void setDeviceId(String deviceId)
	{
		this.deviceId = deviceId;
	}
	public String getDeviceSerial()
	{
		return deviceSerial;
	}
	public void setDeviceSerial(String deviceSerial)
	{
		this.deviceSerial = deviceSerial;
	}
	public String getCameraId()
	{
		return cameraId;
	}
	public void setCameraId(String cameraId)
	{
		this.cameraId = cameraId;
	}
	public String getCameraNo()
	{
		return cameraNo;
	}
	public void setCameraNo(String cameraNo)
	{
		this.cameraNo = cameraNo;
	}
	public String getCameraName()
	{
		return cameraName;
	}
	public void setCameraName(String cameraName)
	{
		this.cameraName = cameraName;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getIsShared()
	{
		return isShared;
	}
	public void setIsShared(String isShared)
	{
		this.isShared = isShared;
	}
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}
	public String getIsEncrypt()
	{
		return isEncrypt;
	}
	public void setIsEncrypt(String isEncrypt)
	{
		this.isEncrypt = isEncrypt;
	}
	@Override
	public String toString()
	{
		return "CameraInfoModel [deviceId=" + deviceId + ", deviceSerial="
				+ deviceSerial + ", cameraId=" + cameraId + ", cameraNo="
				+ cameraNo + ", cameraName=" + cameraName + ", status="
				+ status + ", isShared=" + isShared + ", picUrl=" + picUrl
				+ ", isEncrypt=" + isEncrypt + "]";
	}
	
	
}
