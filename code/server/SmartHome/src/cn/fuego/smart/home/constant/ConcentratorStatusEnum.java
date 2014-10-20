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
	OFFLINE(0),
	ONLINE(1);
	private int statusInt;

	private ConcentratorStatusEnum(int statusInt)
	{
		this.statusInt = statusInt;

	}
	public int getStatusInt()
	{
		return statusInt;
	}

}
