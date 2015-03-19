package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;


/**
 * 
* @ClassName: GetUserMarkListReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:10 
*
 */
public class GetCaTokenByIDReq extends MispBaseReqJson
{

	private int userID;
	private String phone;
	public int getUserID()
	{
		return userID;
	}

	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	@Override
	public String toString()
	{
		return "GetCustomerByIDReq [userID=" + userID + ", phone=" + phone
				+ ", token=" + token + "]";
	}

	
 
}