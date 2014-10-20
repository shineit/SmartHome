package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: ServiceOrderStatusEnum 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:56 
*
 */
public enum ServiceOrderStatusEnum
{
	APPLYED(0),
	HANDLED(1);
	private int statusInt;

	private ServiceOrderStatusEnum(int statusInt)
	{
		this.statusInt = statusInt;

	}
	public int getStatusInt()
	{
		return statusInt;
	}

}
