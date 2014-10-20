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

	DISABLE(0),
	ENABLE(1);
	private int statusInt;

	private SensorStatusEnum(int statusInt)
	{
		this.statusInt = statusInt;

	}
	public int getStatusInt()
	{
		return statusInt;
	}

}
