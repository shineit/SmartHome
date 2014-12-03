package cn.fuego.smart.home.webservice.down.model;


/**
 * 
* @ClassName: SendAlarmReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:54:09 
*
 */
public class PushMessageJson
{
	private String title;
	private String description;
	private int objType;
	private Object obj;
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public int getObjType()
	{
		return objType;
	}
	public void setObjType(int objType)
	{
		this.objType = objType;
	}
	public Object getObj()
	{
		return obj;
	}
	public void setObj(Object obj)
	{
		this.obj = obj;
	}
	
	

}
