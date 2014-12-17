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
	private String alarmID="alarmID";
	private String icon="icon";
	private String title="title"; //对应告警类型
	private String content="concentDesp";
	private String status="status";
	private String time="time";

	//消除告警页面显示具体内容
    private String terminDesp="terminDesp";
    private String terminType="terminType";
    
    
	public String getAlarmID()
	{
		return alarmID;
	}
	public void setAlarmID(String alarmID)
	{
		this.alarmID = alarmID;
	}
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
	public String getTerminDesp()
	{
		return terminDesp;
	}
	public void setTerminDesp(String terminDesp)
	{
		this.terminDesp = terminDesp;
	}
	public String getTerminType()
	{
		return terminType;
	}
	public void setTerminType(String terminType)
	{
		this.terminType = terminType;
	}

	
	
}
