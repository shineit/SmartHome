package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.KnowledgeJson;


/** 
* @ClassName: GetCommonSenseListRsp 
* @Description: TODO
* @author Aether
* @date 2015-3-18 下午3:25:54 
*  
*/
public class GetCommonSenseListRsp extends BaseJsonRsp
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
