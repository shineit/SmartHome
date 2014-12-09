/**   
* @Title: AlarmViewModel.java 
* @Package cn.fuego.smart.home.ui.model 
* @Description: TODO
* @author Aether
* @date 2014-11-26 上午10:11:59 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.model;


/** 
 * @ClassName: AlarmViewModel 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-26 上午10:11:59 
 *  
 */
public class AlarmViewModel
{
	private String icon="icon";
	private String title="title";
	private String content="content";
	private String status="status";
	private String time="time";
	private String eventID="eventID";
	private String objID="objID";
	private String obj="obj";
	private String alarmValue="alarmValue";
	//消除告警页面显示具体内容
	private String description="desp";
	private String warnValue="warnValue";
	private String errorValue="errorValue";
	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public String getEventID()
	{
		return eventID;
	}
	public void setEventID(String eventID)
	{
		this.eventID = eventID;
	}
	public String getObjID()
	{
		return objID;
	}
	public void setObjID(String objID)
	{
		this.objID = objID;
	}
	public String getObj()
	{
		return obj;
	}
	public void setObj(String obj)
	{
		this.obj = obj;
	}
	public String getAlarmValue()
	{
		return alarmValue;
	}
	public void setAlarmValue(String alarmValue)
	{
		this.alarmValue = alarmValue;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getWarnValue()
	{
		return warnValue;
	}
	public void setWarnValue(String warnValue)
	{
		this.warnValue = warnValue;
	}
	public String getErrorValue()
	{
		return errorValue;
	}
	public void setErrorValue(String errorValue)
	{
		this.errorValue = errorValue;
	}
	
	
}
