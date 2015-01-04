/**   
* @Title: DataPrivilegeManage.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-10 上午10:50:02 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.UserConcentrator;

 /** 
 * @ClassName: DataPrivilegeManage 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-10 上午10:50:02 
 *  
 */
public class DataPrivilegeManage 
{
	public static List<Integer> getUserOfConcentor(long concentorID)
	{
		List<Integer> userIDList = new ArrayList<Integer>();
		
		QueryCondition conditon = new QueryCondition(ConditionTypeEnum.EQUAL, UserConcentrator.attr_concentratorID,concentorID);
		List<UserConcentrator> privilegeList = DaoContext.getInstance().getUserConcentratorDao().getAll(conditon);
		for(UserConcentrator e : privilegeList)
		{
			userIDList.add(e.getUserID());
		}
		return userIDList;
	}
	public static List<Long> getConcentorOfUser(int userID)
	{
		List<Long> dataIDList = new ArrayList<Long>();
		
		QueryCondition conditon = new QueryCondition(ConditionTypeEnum.EQUAL, UserConcentrator.attr_userID,userID);

		List<UserConcentrator> privilegeList = DaoContext.getInstance().getUserConcentratorDao().getAll(conditon);
		for(UserConcentrator e : privilegeList)
		{
			dataIDList.add(e.getConcentratorID());
		}
		return dataIDList;
	}

}
