/**   
* @Title: ProductManageAction.java 
* @Package cn.fuego.smart.home.web.action.mall 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-7 下午3:39:27 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.mall;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.Advertisement;
import cn.fuego.smart.home.service.AdManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.action.info.KnowledgeManageAction;


 /** 
 * @ClassName: ProductManageAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-7 下午3:39:27 
 *  
 */
public class AdManageAction extends DWZTableAction<Advertisement>
{
	private Log log = LogFactory.getLog(KnowledgeManageAction.class);
	
    private String adName;
    private String oldPicName;
 	private AdManageService service = ServiceContext.getInstance().getAdManageService();
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<Advertisement> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}
	
	@Override
	public List<QueryCondition> getFilterCondition()
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(!ValidatorUtil.isEmpty(adName))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"adName",adName));
 		}

		return conditionList;
	}

	@Override
	public String create()
	{
		
		String fileName = saveUploadFile();
 		this.obj.setAdImg(fileName);
 

		return super.create();
	}
	
	@Override
	public String modify()
	{
		String fileName = saveUploadFile();
		if(!ValidatorUtil.isEmpty(fileName))
		{
			deleteUploadFileByName(oldPicName);
		}
		this.obj.setAdImg(fileName);
		return super.modify();
	}

	public String getAdName()
	{
		return adName;
	}
	public void setAdName(String adName)
	{
		this.adName = adName;
	}

	public String getOldPicName()
	{
		return oldPicName;
	}

	public void setOldPicName(String oldPicName)
	{
		this.oldPicName = oldPicName;
	}

}
