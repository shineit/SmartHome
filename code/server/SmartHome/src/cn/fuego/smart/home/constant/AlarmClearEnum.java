package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: AlarmStatusEnum 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:34 
*
 */
public enum AlarmClearEnum
{
	NONE_CLEAR(0,"未清除"),
	MANUAL_CLEAR(1,"手动清除"),
	AUTO_CLEAR(2,"自动清除");
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private AlarmClearEnum(int intValue, String strValue)
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
	
	
	

}
