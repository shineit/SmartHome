package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.CheckItemJson;

/** 
* @ClassName: GetCompanyListRsp 
* @Description: TODO
* @author Aether
* @date 2015-3-13 上午10:17:00 
*  
*/
public class GetCheckItemByIDRsp extends MispBaseRspJson
{
	
	private List<CheckItemJson> checkItemList = new ArrayList<CheckItemJson>();

	public List<CheckItemJson> getCheckItemList()
	{
		return checkItemList;
	}

	public void setCheckItemList(List<CheckItemJson> checkItemList)
	{
		this.checkItemList = checkItemList;
	}

	@Override
	public String toString()
	{
		return "GetCheckItemByIDRsp [checkItemList=" + checkItemList
				+ ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", obj=" + obj + "]";
	}

 




}
