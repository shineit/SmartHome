package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: SensorKindEunm 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:47 
*
 */
public enum SensorKindEunm
{
	
	DISCRETE_SENSOR(0,"告警类"),
	CONTIUOUS_SENSOR(1,"模拟类"),
	CTRL_SENSOR(2,"控制类");
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private SensorKindEunm(int intValue, String strValue)
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
