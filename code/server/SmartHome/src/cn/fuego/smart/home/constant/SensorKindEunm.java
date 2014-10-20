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
	
	DISCRETE_SENSOR(0),
	CONTIUOUS_SENSOR(1),
	CTRL_SENSOR(2);
	private int statusInt;
	private SensorKindEunm(int statusInt)
	{
		this.statusInt = statusInt;
	}
	public int getStatusInt()
	{
		return statusInt;
	}

	
}
