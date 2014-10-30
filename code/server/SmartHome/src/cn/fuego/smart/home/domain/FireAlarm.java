/**   
* @Title: FireAlarm.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-30 下午11:29:33 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

import java.util.Date;

 /** 
 * @ClassName: FireAlarm 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-30 下午11:29:33 
 *  
 */
public class FireAlarm
{
	private int concentratorID;  //集中器ID

	private int machineID;
	private int loopID;
	private int codeID;
	private int alarmStatus;		//告警状态
	private float alarmValue;		//告警值
	private Date alarmTime;			//告警产生的时间
	
	
	public int getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(int concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public int getMachineID()
	{
		return machineID;
	}
	public void setMachineID(int machineID)
	{
		this.machineID = machineID;
	}
	public int getLoopID()
	{
		return loopID;
	}
	public void setLoopID(int loopID)
	{
		this.loopID = loopID;
	}
	public int getCodeID()
	{
		return codeID;
	}
	public void setCodeID(int codeID)
	{
		this.codeID = codeID;
	}
	public int getAlarmStatus()
	{
		return alarmStatus;
	}
	public void setAlarmStatus(int alarmStatus)
	{
		this.alarmStatus = alarmStatus;
	}
	public float getAlarmValue()
	{
		return alarmValue;
	}
	public void setAlarmValue(float alarmValue)
	{
		this.alarmValue = alarmValue;
	}
	public Date getAlarmTime()
	{
		return alarmTime;
	}
	public void setAlarmTime(Date alarmTime)
	{
		this.alarmTime = alarmTime;
	}
	
	
}
