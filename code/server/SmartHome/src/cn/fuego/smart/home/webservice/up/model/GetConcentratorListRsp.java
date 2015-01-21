package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
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
	private List<ConcentratorJson> concentList=new ArrayList<ConcentratorJson>();

	public List<ConcentratorJson> getConcentList()
	{
		return concentList;
	}

	public void setConcentList(List<ConcentratorJson> concentList)
	{
		this.concentList = concentList;
	}

	@Override
	public String toString()
	{
		return "GetConcentratorListRsp [concentList=" + concentList
				+ ", result=" + result + "]";
	}



}
