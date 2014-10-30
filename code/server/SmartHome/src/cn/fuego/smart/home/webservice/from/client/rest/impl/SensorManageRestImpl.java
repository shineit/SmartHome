/**   
* @Title: SensorManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:24:29 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.rest.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.smart.home.domain.Sensor;
import cn.fuego.smart.home.service.SensorManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.from.client.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.from.client.model.SetSensorReq;
import cn.fuego.smart.home.webservice.from.client.model.SetSensorRsp;
import cn.fuego.smart.home.webservice.from.client.model.base.SensorJson;
import cn.fuego.smart.home.webservice.from.client.rest.SensorManageRest;

 /** 
 * @ClassName: SensorManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:24:29 
 *  
 */
public class SensorManageRestImpl implements SensorManageRest
{
	
	private Log log = LogFactory.getLog(SensorManageRestImpl.class);

	private SensorManageService sensorService = ServiceContext.getInstance().getSensorManageService();

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
		
		try
		{
			List<Sensor> sensorList = sensorService.getSensorDataSource().getAllPageData();
	 		
			for(Sensor sensor :sensorList)
			{	
				SensorJson json = new SensorJson();
			
				json.loadWithSensor(sensor);
				rsp.getSensorList().add(json);
			}
			
		}
		catch(Exception e)
		{
			log.error("get sensor list error",e);
		}


 		
		return rsp;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.SensorManageService#setSensor(cn.fuego.smart.home.webservice.from.client.model.SetSensorReq)
	 */
	@Override
	public SetSensorRsp setSensor(SetSensorReq req)
	{
		SetSensorRsp rsp = new SetSensorRsp();
		return rsp;
	}

}
