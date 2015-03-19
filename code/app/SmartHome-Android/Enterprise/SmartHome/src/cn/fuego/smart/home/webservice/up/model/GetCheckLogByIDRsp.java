package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;

/** 
* @ClassName: GetCompanyListRsp 
* @Description: TODO
* @author Aether
* @date 2015-3-13 上午10:17:00 
*  
*/
public class GetCheckLogByIDRsp extends MispBaseRspJson
{
	
	private List<CheckLogJson> checkLogList = new ArrayList<CheckLogJson>();

	public List<CheckLogJson> getCheckLogList()
	{
		return checkLogList;
	}

	public void setCheckLogList(List<CheckLogJson> checkLogList)
	{
		this.checkLogList = checkLogList;
	}

	@Override
	public String toString()
	{
		return "GetCheckLogByIDRsp [checkLogList=" + checkLogList
				+ ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", obj=" + obj + "]";
	}

 

}
