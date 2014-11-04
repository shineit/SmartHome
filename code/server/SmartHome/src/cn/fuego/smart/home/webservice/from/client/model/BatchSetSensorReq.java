/**   
* @Title: BatchSetSensorReq.java 
* @Package cn.fuego.smart.home.webservice.from.client.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-4 下午9:32:13 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.model;

import java.util.List;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.from.client.model.base.HomeSensorJson;

 /** 
 * @ClassName: BatchSetSensorReq 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-4 下午9:32:13 
 *  
 */
public class BatchSetSensorReq extends BaseJsonReq
{
	private List<HomeSensorJson> sensorList;

	public List<HomeSensorJson> getSensorList()
	{
		return sensorList;
	}

	public void setSensorList(List<HomeSensorJson> sensorList)
	{
		this.sensorList = sensorList;
	}
	

}
