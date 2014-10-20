/**   
* @Title: SensorManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:24:29 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.service.impl;

import cn.fuego.smart.home.webservice.from.client.model.GetSensorListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.from.client.model.SetSensorReq;
import cn.fuego.smart.home.webservice.from.client.model.SetSensorRsp;
import cn.fuego.smart.home.webservice.from.client.service.SensorManageService;

 /** 
 * @ClassName: SensorManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:24:29 
 *  
 */
public class SensorManageServiceImpl implements SensorManageService
{

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.SensorManageService#getWild()
	 */
	@Override
	public String getWild()
	{
		// TODO Auto-generated method stub
		return "helllo  ";
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.SensorManageService#getSensorList(cn.fuego.smart.home.webservice.from.client.model.GetSensorListReq)
	 */
	@Override
	public GetSensorListRsp getSensorList(GetSensorListReq sensorListReq)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.SensorManageService#setSensor(cn.fuego.smart.home.webservice.from.client.model.SetSensorReq)
	 */
	@Override
	public SetSensorRsp setSensor(SetSensorReq sensorListReq)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
