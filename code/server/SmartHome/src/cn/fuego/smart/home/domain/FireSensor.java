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
	private int concentratorID;  //集中器ID

	private int machineID; //机号
	private int loopID;    //回路号
	private int codeID;    //编号
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
	
	

}
