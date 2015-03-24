/**   
* @Title: MISPPrivilegeManage.java 
* @Package cn.fuego.misp.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-21 上午10:16:50 
* @version V1.0   
*/ 
package cn.fuego.misp.service;

import java.util.Set;

 /** 
 * @ClassName: MISPPrivilegeManage 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-21 上午10:16:50 
 *  
 */
public interface MISPPrivilegeManage
{
	/**
	 * 
	 * @param userID
	 * @return
	 */
	public Set<String> getMenuIDListByUser(String userID);
	
	/**
	 * 
	 * @param roleID
	 * @return
	 */
	public Set<String> getMenuIDListByRole(String roleID);
	
	/**
	 * 
	 * @param userID
	 * @param accessObjType
	 * @param accessObjValue
	 * @return
	 */
	public boolean hasPrivilege(String userID, String accessObjType,String accessObjValue);

	/**
	 * 
	 * @param userID
	 * @param prvilegeID
	 * @return
	 */
	public boolean hasPrivilege(String userID,int prvilegeID);
	
	/**
	 * 
	 * @param userID
	 * @param accessObjType
	 * @return
	 */
	public Set<String> getObjectIDListByUser(String accessObjType,String userID);
	
	/**
	 * 根据公司ID获取关联用户表
	 * @param companyID
	 * @return
	 */
	public Set<String>  getUserIDListByCommpany(String companyID);
	
	/**
	 * 创建用户公司关联表
	 * @param userID
	 * @param companyID
	 * @return
	 */
	public void createUserCompany(String userID,String companyID);
	
	/**
	 * 删除用户公司关联
	 * @param userID
	 * @param companyID
	 */
	public void deleteUserCompany(String userID,String companyID);
	
	
}
