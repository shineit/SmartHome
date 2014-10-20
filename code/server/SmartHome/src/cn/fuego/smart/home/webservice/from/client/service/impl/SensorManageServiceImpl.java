/**   
* @Title: SensorManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:24:29 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.from.client.model.GetSensorListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.from.client.model.SetSensorReq;
import cn.fuego.smart.home.webservice.from.client.model.SetSensorRsp;
import cn.fuego.smart.home.webservice.from.client.model.base.SensorJson;
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
	public String getHello()
	{
		// TODO Auto-generated method stub
		return "helllo rest easy aaa";
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.SensorManageService#getSensorList(cn.fuego.smart.home.webservice.from.client.model.GetSensorListReq)
	 */
	@Override
	public GetSensorListRsp getSensorList()
	{
		// TODO Auto-generated method stub
		GetSensorListRsp rsp = new GetSensorListRsp();
		
		List<SensorJson> sensorList = new ArrayList<SensorJson>();
		
		SensorJson sensorJson = new SensorJson();
		sensorJson.setChannelID(1);
		sensorJson.setErrorValue(2.2);
		
		rsp.setSensorList(sensorList);
		
		return rsp;
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
