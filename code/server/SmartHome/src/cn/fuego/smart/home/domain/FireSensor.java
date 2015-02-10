/**   
* @Title: FireSensor.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-30 下午11:29:20 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

 /** 
 * @ClassName: FireSensor 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-30 下午11:29:20 
 *  
 */
public class FireSensor
{
	private long id; //
	private int planNodeID; //平面图节点ID
	private long concentratorID;  //集中器ID
	private int machineID; //机号
	private int loopID;    //回路号
	private int codeID;    //编号
	private String locationDesp;  //位置描述
	private float locationX;  //X 偏移，相对当前图片尺寸宽度
	private float locationY;  //Y 偏移，相当当前图片尺寸高度
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}

	public int getPlanNodeID()
	{
		return planNodeID;
	}
	public void setPlanNodeID(int planNodeID)
	{
		this.planNodeID = planNodeID;
	}
	public long getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(long concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public int getMachineID()
	{
		return machineID;
	}
	public void setMachineID(int machineID)
	{
		this.machineID = machineID;
	}
	public int getLoopID()
	{
		return loopID;
	}
	public void setLoopID(int loopID)
	{
		this.loopID = loopID;
	}
	public int getCodeID()
	{
		return codeID;
	}
	public void setCodeID(int codeID)
	{
		this.codeID = codeID;
	}

	public String getLocationDesp()
	{
		return locationDesp;
	}
	public void setLocationDesp(String locationDesp)
	{
		this.locationDesp = locationDesp;
	}
	public float getLocationX()
	{
		return locationX;
	}
	public void setLocationX(float locationX)
	{
		this.locationX = locationX;
	}
	public float getLocationY()
	{
		return locationY;
	}
	public void setLocationY(float locationY)
	{
		this.locationY = locationY;
	}


}
