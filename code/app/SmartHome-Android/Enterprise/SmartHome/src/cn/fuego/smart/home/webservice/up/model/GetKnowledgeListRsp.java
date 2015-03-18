package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.KnowledgeJson;


/**
 * 
* @ClassName: GetSensorListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:58 
*
 */
public class GetKnowledgeListRsp extends BaseJsonRsp
{
	private List<KnowledgeJson> knowledgeList = new ArrayList<KnowledgeJson>();

	public List<KnowledgeJson> getKnowledgeList()
	{
		return knowledgeList;
	}

	public void setKnowledgeList(List<KnowledgeJson> knowledgeList)
	{
		this.knowledgeList = knowledgeList;
	}
 

 



}
