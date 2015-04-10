/**   
* @Title: CustomerManageAction.java 
* @Package cn.fuego.smart.home.web.action.sys 
* @Description: TODO
* @author Aether
* @date 2015-4-7 下午5:34:46 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.sys;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Customer;
import cn.fuego.smart.home.service.CustomerManageService;
import cn.fuego.smart.home.service.ServiceContext;

/** 
 * @ClassName: CustomerManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2015-4-7 下午5:34:46 
 *  
 */
public class CustomerManageAction extends DWZTableAction<Customer>
{

	private CustomerManageService service = ServiceContext.getInstance().getCustomerManageService();
	
	private  String customerName,userName;
	@Override
	public MispCommonService<Customer> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}
	


	@Override
	public List<QueryCondition> getFilterCondition()
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(!ValidatorUtil.isEmpty(customerName))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"customerName",customerName));
 		}
		if(!ValidatorUtil.isEmpty(userName))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"userName",userName));
 		}
		return conditionList;
	}



	public String getCustomerName()
	{
		return customerName;
	}
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}



	public String getUserName()
	{
		return userName;
	}



	public void setUserName(String userName)
	{
		this.userName = userName;
	}

}
