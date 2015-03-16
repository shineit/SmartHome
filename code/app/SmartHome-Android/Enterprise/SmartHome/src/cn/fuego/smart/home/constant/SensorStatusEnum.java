package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: SensorStatusEnum 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:51 
*
 */
public enum SensorStatusEnum
{

	DISABLE(0,"禁止"),
	ENABLE(1,"使能");
	private int intValue;
	private String strValue;
	
	/**
	 * @param intValue
	 * @param strValue
	 */
	private SensorStatusEnum(int intValue, String strValue)
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
