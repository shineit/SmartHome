/**   
* @Title: UserFilterModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Aether
* @date 2014-11-4 上午11:41:56 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.constant.UserStatusEnum;
import cn.fuego.smart.home.constant.UserTypeEnum;

/** 
 * @ClassName: UserFilterModel 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-4 上午11:41:56 
 *  
 */
public class UserFilterModel
{
	private int userID; 
	private String userName;
	private String password;
	private String accountType;
	private String startDate;
	private String endDate;
	private UserTypeEnum[] userTypeList = UserTypeEnum.values();
	private UserStatusEnum[] userStatusList = UserStatusEnum.values();
	
	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		 

			//conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"userID",String.valueOf(this.getUserID())));
    	
			if(!ValidatorUtil.isEmpty(this.getUserName()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"userName",this.getUserName()));
			}
			if(!ValidatorUtil.isEmpty(this.getAccountType()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"role",String.valueOf(UserTypeEnum.getEnumByStr(this.getAccountType()).getTypeValue())));
			}
			if(!ValidatorUtil.isEmpty(this.getStartDate()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.BIGER_EQ,"regDate",this.getStartDate()));
			}
			if(!ValidatorUtil.isEmpty(this.getEndDate()))
			{
				Date endDate = DateUtil.stringToDate(this.getEndDate());
				conditionList.add(new QueryCondition(ConditionTypeEnum.LOWER,"regDate",DateUtil.DateToString(DateUtil.dayCalculate(endDate, 1))));
			}				
		 
		return conditionList;
    }
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getAccountType()
	{
		return accountType;
	}
	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}
	public String getStartDate()
	{
		return startDate;
	}
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	public String getEndDate()
	{
		return endDate;
	}
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	public UserTypeEnum[] getUserTypeList()
	{
		return userTypeList;
	}
	public void setUserTypeList(UserTypeEnum[] userTypeList)
	{
		this.userTypeList = userTypeList;
	}
	public UserStatusEnum[] getUserStatusList()
	{
		return userStatusList;
	}
	public void setUserStatusList(UserStatusEnum[] userStatusList)
	{
		this.userStatusList = userStatusList;
	}
	
	
}
