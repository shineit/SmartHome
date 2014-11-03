/**   
* @Title: MISPUserServiceImpl.java 
* @Package cn.fuego.misp.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-29 下午11:33:37 
* @version V1.0   
*/ 
package cn.fuego.misp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.constant.MISPOperLogConsant;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.MispButton;
import cn.fuego.misp.domain.SystemMenu;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
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
	public SystemUser Login(String userName, String password)
	{
		SystemUser targetUser = this.getSystemUserByUserName(userName);
		if (null == targetUser )
		{
			// User isnot existant
			log.warn("User not exist：" + userName);
			throw new MISPException(MISPErrorMessageConst.ERROR_LOGIN_FAILED);
		}
		else if (!targetUser.getPassword().equals(password))
		{
			log.warn("the password is wrong");
			throw new MISPException(MISPErrorMessageConst.ERROR_LOGIN_FAILED);
		}
		else
		{
			log.info("User Login : " + userName);
		}
		MISPServiceContext.getInstance().getMISPOperLogService().recordLog(userName, MISPOperLogConsant.LOGIN, null, MISPOperLogConsant.OPERATE_SUCCESS);
 
		return targetUser;
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
			throw new MISPException(MISPErrorMessageConst.ERROR_USER_NOT_EXISTED);
		}
		else if (!targetUser.getPassword().equals(oldPwd))
		{
			log.warn("the password is wrong");
			throw new MISPException(MISPErrorMessageConst.ERROR_OLD_PASSWORD_WORD);
		}
		else
		{
			targetUser.setPassword(newPwd);
			MISPDaoContext.getInstance().getSystemUserDao().update(targetUser);
			MISPServiceContext.getInstance().getMISPOperLogService().recordLog(userName, MISPOperLogConsant.MODIFY_PASSWORD, null, MISPOperLogConsant.OPERATE_SUCCESS);
		}
	}
	
	public List<MenuTreeModel> getMenuTreeByUserID(int userID)
	{
		Set<String> menuIDList = MISPServiceContext.getInstance().MISPPrivilegeManage().getMenuIDListByUser(String.valueOf(userID));
		
		return SystemMenuCache.getInstance().getMenuListWithShowIDList(menuIDList);
	}
	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPUserService#getMenuListByUserID(int)
	 */
	@Override
	public List<SystemMenu> getMenuListByUserID(int userID)
	{
		List<SystemMenu> menuList = new ArrayList<SystemMenu>();
		return menuList;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPUserService#getButtonListByUserID(int)
	 */
	@Override
	public List<MispButton> getButtonListByUserID(int userID)
	{
		 List<MispButton> buttonList = new  ArrayList<MispButton>();
		return buttonList;
	}
 
}
