package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: NewsStatusEnum 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:43 
*
 */
public enum NewsStatusEnum
{
	NO_RELEASED(0),
	RELEASED(1);
	private int statusInt;

	private NewsStatusEnum(int statusInt)
	{
		this.statusInt = statusInt;

	}
	public int getStatusInt()
	{
		return statusInt;
	}

}
