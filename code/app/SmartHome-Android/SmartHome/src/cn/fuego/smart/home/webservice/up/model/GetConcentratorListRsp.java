package cn.fuego.smart.home.webservice.up.model;

import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.ConcentratorJson;


/**
 * 
* @ClassName: GetConcentratorListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:28 
*
 */
public class GetConcentratorListRsp extends BaseJsonRsp
{
	private List<ConcentratorJson> sensorList;

	public List<ConcentratorJson> getSensorList()
	{
		return sensorList;
	}

	public void setSensorList(List<ConcentratorJson> sensorList)
	{
		this.sensorList = sensorList;
	}

	@Override
	public String toString()
	{
		return "GetConcentratorListRsp [sensorList=" + sensorList + "]";
	}
	
	

}
