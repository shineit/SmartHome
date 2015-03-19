package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.AdvertisementJson;


/**
 * 
* @ClassName: GetSensorListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:58 
*
 */
public class GetAdListRsp extends MispBaseRspJson
{
	private List<AdvertisementJson> adList = new ArrayList<AdvertisementJson>();

	public List<AdvertisementJson> getAdList()
	{
		return adList;
	}

	public void setAdList(List<AdvertisementJson> adList)
	{
		this.adList = adList;
	}

 

	 
 



}
