package cn.fuego.smart.home.service;

import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;

public class SystemUser
{
	private int userID;
	private String userName;
	private int role;
	private List<AttributeJson> listAttr;
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public int getRole()
	{
		return role;
	}
	public void setRole(int role)
	{
		this.role = role;
	}
	public List<AttributeJson> getListAttr()
	{
		return listAttr;
	}
	public void setListAttr(List<AttributeJson> listAttr)
	{
		this.listAttr = listAttr;
	}
	@Override
	public String toString()
	{
		return "SystemUser [userID=" + userID + ", userName=" + userName
				+ ", role=" + role + ", listAttr=" + listAttr + "]";
	}
	
	

}
