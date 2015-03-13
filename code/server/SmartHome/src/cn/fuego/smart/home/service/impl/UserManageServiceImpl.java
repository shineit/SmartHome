/**   
* @Title: UserManageSerivceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午9:07:54 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.constant.PrivilegeAccessObjTypeEnum;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.impl.MISPUserServiceImpl;
import cn.fuego.smart.home.constant.UserStatusEnum;
import cn.fuego.smart.home.constant.UserTypeEnum;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.Customer;
import cn.fuego.smart.home.domain.UserMark;
import cn.fuego.smart.home.service.UserManageService;

 /** 
 * @ClassName: UserManageSerivceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午9:07:54 
 *  
 */
public class UserManageServiceImpl extends MISPUserServiceImpl<SystemUser> implements UserManageService
{

	private Log log = LogFactory.getLog(UserManageServiceImpl.class);
	
	public SystemUser Login(String userName, String password)
	{
		SystemUser user = super.Login(userName, password);
		
		
		return user;
	}
    @Override
    public void create(int userID,SystemUser user)
    {
    	user.setPassword(SystemConfigInfo.getDefaultPassword());
    	user.setStatus(UserStatusEnum.REGISTERED.getIntValue());//默认已注册
    	
    	super.create(user);
    	if(UserTypeEnum.TERMINAL.getTypeValue()==user.getRole())
    	{
        	Customer oldCustomer= DaoContext.getInstance().getCustomerDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL, "userID", user.getUserID()));
        	if(oldCustomer==null)
        	{
        		Customer newCustomer = new Customer();
        		newCustomer.setUserID(user.getUserID());
        		newCustomer.setCustomerName(user.getUserName());
        		DaoContext.getInstance().getCustomerDao().create(newCustomer);
        	}
        	else
        	{
        		throw new MISPException(MISPErrorMessageConst.USER_EXISTED);
        	}
        	
    	}

    	
    }
 

	@Override
	public void delete(List<String> userIDList)
	{
		for(int i=0;i<userIDList.size();i++)
		{
			String userID = userIDList.get(i);
			SystemUser oldUser= MISPDaoContext.getInstance().getSystemUserDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL, "userID", userID));
			if(oldUser.getRole()==UserTypeEnum.ADMIN.getTypeValue())
			{
				throw new MISPException(MISPErrorMessageConst.ADMIN_NOT_DELETED);
			}
		}
		//QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, "userID", userIDList);		
		//MISPDaoContext.getInstance().getSystemUserDao().delete(condition);
		super.delete(userIDList);
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.UserManageService#getUseMark()
	 */
	@Override
	public List<UserMark> getUserMark(int userID)
	{
		 List<UserMark> list  = new ArrayList<UserMark>();
		 list= DaoContext.getInstance().getUserMarkDao().getAll(new QueryCondition(ConditionTypeEnum.EQUAL, "userID", userID));
		 return list;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.UserManageService#deleteUserMark(cn.fuego.smart.home.domain.UserMark)
	 */
	@Override
	public void deleteUserMark(UserMark userMark)
	{
		
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,UserMark.getUserIDAttr(),String.valueOf(userMark.getUserID())));
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,UserMark.getMarkAttr(),userMark.getMark()));

		DaoContext.getInstance().getUserMarkDao().delete(conditionList);
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.UserManageService#createUserMark(cn.fuego.smart.home.domain.UserMark)
	 */
	@Override
	public void createUserMark(UserMark userMark)
	{
		DaoContext.getInstance().getUserMarkDao().create(userMark);
		
	}
	@Override
	public void modifyCustomer(Customer customer)
	{
		Customer oldCustomer= getCustomer(customer.getUserID());
		oldCustomer.setCustomerName(customer.getCustomerName());
		oldCustomer.setPhone(customer.getPhone());
		oldCustomer.setEmail(customer.getEmail());
		oldCustomer.setAddr(customer.getAddr());
		oldCustomer.setStatus(customer.getStatus());
		DaoContext.getInstance().getCustomerDao().update(customer);
	}
	@Override
	public Customer getCustomer(int userID)
	{
		Customer customer = DaoContext.getInstance().getCustomerDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,"userID",userID));
		
		return customer;
	}


}
