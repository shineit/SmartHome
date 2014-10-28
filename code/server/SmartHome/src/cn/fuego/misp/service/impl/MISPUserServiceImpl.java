/**   
* @Title: MISPUserServiceImpl.java 
* @Package cn.fuego.misp.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-29 下午11:33:37 
* @version V1.0   
*/ 
package cn.fuego.misp.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.service.spi.ServiceException;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.exception.CommonExceptionMsg;
import cn.fuego.misp.constant.MISPOperLogConsant;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.MISPUserService;
import cn.fuego.misp.service.cache.SystemMenuCache;
import cn.fuego.misp.web.model.menu.MenuTreeModel;
import cn.fuego.misp.web.model.user.UserModel;

/** 
 * @ClassName: MISPUserServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-29 下午11:33:37 
 *  
 */

public class MISPUserServiceImpl implements MISPUserService
{
	private Log log = LogFactory.getLog(MISPUserServiceImpl.class);

	private SystemUser getSystemUserByUserName(String userName)
	{
		SystemUser targetUser = (SystemUser) MISPDaoContext.getInstance().getSystemUserDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,SystemUser.getUserNameAttr(),userName));
		 
		return targetUser;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.UserService#Login(java.lang.String, java.lang.String)
	 */
	@Override
	public UserModel Login(String userName, String password)
	{
		SystemUser targetUser = this.getSystemUserByUserName(userName);
		if (null == targetUser )
		{
			// User isnot existant
			log.warn("User not exist：" + userName);
			throw new ServiceException(CommonExceptionMsg.LOGIN_FAILED);
		}
		else if (!targetUser.getPassword().equals(password))
		{
			log.warn("the password is wrong");
			throw new ServiceException(CommonExceptionMsg.LOGIN_FAILED);
		}
		else
		{
			log.info("User Login : " + userName);
		}
		MISPServiceContext.getInstance().getMISPOperLogService().recordLog(userName, MISPOperLogConsant.LOGIN, null, MISPOperLogConsant.OPERATE_SUCCESS);


		UserModel userModel = new UserModel();
		userModel.setUserID(targetUser.getUserID());
		userModel.setUserName(targetUser.getUserName());
		userModel.setAccountType(targetUser.getAccountType());
		userModel.setRegDate(targetUser.getRegDate());
		
		return userModel;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.UserService#modifyPassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void modifyPassword(String userName, String oldPwd, String newPwd)
	{
		SystemUser targetUser = this.getSystemUserByUserName(userName);
		if (null == targetUser )
		{
			// User isnot existant
			log.warn("User not exist：" + userName);
			throw new ServiceException(CommonExceptionMsg.USER_NOT_EXISTED);
		}
		else if (!targetUser.getPassword().equals(oldPwd))
		{
			log.warn("the password is wrong");
			throw new ServiceException(CommonExceptionMsg.OLD_PASSWORD_WORD);
		}
		else
		{
			targetUser.setPassword(newPwd);
			MISPDaoContext.getInstance().getSystemUserDao().update(targetUser);
			MISPServiceContext.getInstance().getMISPOperLogService().recordLog(userName, MISPOperLogConsant.MODIFY_PASSWORD, null, MISPOperLogConsant.OPERATE_SUCCESS);
		}
	}
	
	public List<MenuTreeModel> getMenuTreeByUserID(String userID)
	{
		Set<String> menuIDList = MISPServiceContext.getInstance().MISPPrivilegeManage().getMenuIDListByUser(userID);
		
		return SystemMenuCache.getInstance().getMenuListWithShowIDList(menuIDList);
	}

}
