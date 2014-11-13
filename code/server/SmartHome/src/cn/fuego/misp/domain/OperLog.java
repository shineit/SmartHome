/**   
* @Title: OperLog.java 
* @Package cn.fuego.remote.medical.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-27 下午07:18:29 
* @version V1.0   
*/ 
package cn.fuego.misp.domain;

import java.util.Date;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: OperLog 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-27 下午07:18:29 
 *  
 */

public class OperLog implements PersistenceObject
{
	private int id;
	private String user;
	private String name;
	private String object;
	private String result;
	private String desp;
	private Date operTime;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getObject()
	{
		return object;
	}
	public void setObject(String object)
	{
		this.object = object;
	}
	public String getResult()
	{
		return result;
	}
	public void setResult(String result)
	{
		this.result = result;
	}
	public String getDesp()
	{
		return desp;
	}
	public void setDesp(String desp)
	{
		this.desp = desp;
	}
	public Date getOperTime()
	{
		return operTime;
	}
	public void setOperTime(Date operTime)
	{
		this.operTime = operTime;
	}
	@Override
	public String toString()
	{
		return "OperLog [id=" + id + ", user=" + user + ", name=" + name
				+ ", object=" + object + ", result=" + result + ", desp="
				+ desp + ", operTime=" + operTime + "]";
	}
	 
	

}
