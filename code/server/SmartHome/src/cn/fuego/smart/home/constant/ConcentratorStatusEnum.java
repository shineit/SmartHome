package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: ConcentratorStatusEnum 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:39 
*
 */
public enum ConcentratorStatusEnum
{
	OFFLINE(0,"offline"),
	ONLINE(1,"online");
	private int intValue;
	private String strValue;
	
	/**
	 * @param intValue
	 * @param strValue
	 */
	private ConcentratorStatusEnum(int intValue, String strValue)
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
