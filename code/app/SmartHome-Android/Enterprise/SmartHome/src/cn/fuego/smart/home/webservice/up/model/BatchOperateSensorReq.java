/**   
* @Title: BatchSetSensorReq.java 
* @Package cn.fuego.smart.home.webservice.from.client.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-4 下午9:32:13 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.model;

import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;

 /** 
 * @ClassName: BatchSetSensorReq 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-4 下午9:32:13 
 *  
 */
public class BatchOperateSensorReq extends BaseJsonReq
{
	private List<String> sensorList;
    private int userID;


	public int getUserID()
	{
		return userID;
	}

	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	public List<String> getSensorList()
	{
		return sensorList;
	}

	public void setSensorList(List<String> sensorList)
	{
		this.sensorList = sensorList;
	}

	@Override
	public String toString()
	{
		return "BatchOperateSensorReq [sensorList=" + sensorList + ", userID="
				+ userID + ", token=" + token + "]";
	}

}
