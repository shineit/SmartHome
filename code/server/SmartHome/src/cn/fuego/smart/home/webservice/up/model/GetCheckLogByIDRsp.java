package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;

/** 
* @ClassName: GetCompanyListRsp 
* @Description: TODO
* @author Aether
* @date 2015-3-13 上午10:17:00 
*  
*/
public class GetCheckLogByIDRsp extends BaseJsonRsp
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
		return "GetCheckLogByIDRsp [checkLogList=" + checkLogList + ", result="
				+ result + "]";
	}


}
