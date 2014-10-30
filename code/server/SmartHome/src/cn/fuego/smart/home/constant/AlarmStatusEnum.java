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
	OFFLINE_ALARM(1,"掉线"),
	FAULT_ALARM(2,"故障"),
	SUBPRESSURE_ALARM(3,"欠压"),
	WARN_ALARM(4,"预警"),
	ERROR_ALARM(5,"火警"),
	FEEDBACK_ALARM(6,"反馈"),
	ACTION_ALARM(7,"动作"),
	RESET_ALARM(8,"复位"),
	SETUP_ALARM(9,"设防"),
	REMOVE_ALARM(10,"撤防");
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
