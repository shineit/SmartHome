/**   
* @Title: ProductManageAction.java 
* @Package cn.fuego.smart.home.web.action.mall 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-7 下午3:39:27 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.mall;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.Product;
import cn.fuego.smart.home.service.KnowledgeManageService;
import cn.fuego.smart.home.service.ProductManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.action.info.KnowledgeManageAction;


 /** 
 * @ClassName: ProductManageAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-7 下午3:39:27 
 *  
 */
public class ProductManageAction extends DWZTableAction<Product>
{
	private Log log = LogFactory.getLog(KnowledgeManageAction.class);
	
	
	private static final long serialVersionUID = 1L;
	private ProductManageService service = ServiceContext.getInstance().getProductManageService();
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<Product> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}
}
