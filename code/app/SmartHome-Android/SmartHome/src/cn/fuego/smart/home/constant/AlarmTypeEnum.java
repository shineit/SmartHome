package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: AlarmStatusEnum 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:34 
*
 */
public enum AlarmTypeEnum
{
	OFFLINE_ALARM(1,"掉线"),
	OFFLINE_RECOVER(2,"掉线恢复"),
	SUBPRESSURE_ALARM(3,"欠压"),
	SUBPRESSURE_RECOVER(4,"欠压恢复"),
	SETUP_ALARM(5,"设防"),
	REMOVE_ALARM(6,"撤防"),
	ERROR_ALARM(7,"故障"),
	ERROR_RECOVER(8,"故障恢复"),
	WARN_ALARM(9,"预警"),
	FIRE_ALARM(10,"火警"),	
	FEEDBACK_ALARM(11,"反馈"),
	RESET_ALARM(12,"复位"),
	ACTION_ALARM(13,"动作"),
	ACTION_RESET(14,"动作复位");

	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private AlarmTypeEnum(int intValue, String strValue)
	{
		this.intValue = intValue;
		this.strValue = strValue;
	}
	public int getIntValue()
	{
		return intValue;
	}
	public String getStrValue()
	{
		return strValue;
	}
	
	public static AlarmTypeEnum getEnumByInt(int intValue)
	{
		for (AlarmTypeEnum c : AlarmTypeEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}	
	public static AlarmTypeEnum getEnumByStr(String strValue)
	{
		for (AlarmTypeEnum c : AlarmTypeEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	} 

		
	

}
