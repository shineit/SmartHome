/**   
* @Title: LogFilterModel.java 
* @Package cn.fuego.misp.web.model.log 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-2 下午04:31:03 
* @version V1.0   
*/ 
package cn.fuego.misp.web.model.log;

/** 
 * @ClassName: LogFilterModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-2 下午04:31:03 
 *  
 */

public class LogFilterModel
{
	private int id;
	private String user;
	private String name;
	private String object;
	private String result;
	private String startTime;
	private String endTime;
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
	public String getStartTime()
	{
		return startTime;
	}
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	public String getEndTime()
	{
		return endTime;
	}
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	
	
}
