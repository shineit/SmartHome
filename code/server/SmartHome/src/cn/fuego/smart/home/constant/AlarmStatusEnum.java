package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: AlarmStatusEnum 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:34 
*
 */
public enum AlarmStatusEnum
{
	NO_ALARM(0,"正常"),
	WARN_ALARM(1,"预警"),
	ERROR_ALARM(2,"火警");
	private int statusInt;
	private String statusStr;
	private AlarmStatusEnum(int statusInt,String statusStr)
	{
		this.statusInt = statusInt;
		this.statusStr = statusStr;
	}
	public int getStatusInt()
	{
		return statusInt;
	}
	public String getStatusStr()
	{
		return statusStr;
	}
	
	

}
