/**   
* @Title: NewsManageAction.java 
* @Package cn.fuego.smart.home.web.action.info 
* @Description: TODO
* @author Aether
* @date 2014-11-3 下午5:43:58 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.info;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.Knowledge;
import cn.fuego.smart.home.service.KnowledgeManageService;
import cn.fuego.smart.home.service.ServiceContext;

/** 
 * @ClassName: NewsManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-3 下午5:43:58 
 *  
 */
public class KnowledgeManageAction extends DWZTableAction<Knowledge>
{

	/**
	 * 
	 */
	private Log log = LogFactory.getLog(KnowledgeManageAction.class);
	
	
	private static final long serialVersionUID = 1L;
	private KnowledgeManageService service = ServiceContext.getInstance().getKnowledgeManageService();
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<Knowledge> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}
 
}
