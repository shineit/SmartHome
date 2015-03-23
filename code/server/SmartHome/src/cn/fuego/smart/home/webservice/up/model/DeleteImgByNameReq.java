package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;


/** 
* @ClassName: DeleteImgByNameReq 
* @Description: TODO
* @author Aether
* @date 2015-3-21 下午5:12:08 
*  
*/
public class DeleteImgByNameReq extends MispBaseReqJson
{
	private int userID;
	private String imgName;
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public String getImgName()
	{
		return imgName;
	}
	public void setImgName(String imgName)
	{
		this.imgName = imgName;
	}
	@Override
	public String toString()
	{
		return "DeleteImgByNameReq [userID=" + userID + ", imgName=" + imgName
				+ ", app_id=" + app_id + ", token=" + token + "]";
	}

	
}
