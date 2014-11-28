/**   
* @Title: ConcenFilterModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Aether
* @date 2014-11-4 下午10:14:41 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.constant.ConcentratorPermissionEnum;
import cn.fuego.smart.home.constant.ConcentratorStatusEnum;

/** 
 * @ClassName: ConcenFilterModel 
 * @Description: ~ConcentratorManageAction
 * @author Aether
 * @date 2014-11-4 下午10:14:41 
 *  
 */
public class ConcentFilterModel
{
	private String concentratorID;     //集中器编号
	private String status;				//集中器状态，0 离线 1在线
	private String name;			//集中器名称(预留字段)
	private String description;		//集中器描述(预留字段)
	private String cityName;//仅提供页面交互，数据库中不存在

	private ConcentratorStatusEnum[]  concentStatusList = ConcentratorStatusEnum.values();
	
	private ConcentratorPermissionEnum[] permissionTypeList = ConcentratorPermissionEnum.values();
	
	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		 

			

			if(!ValidatorUtil.isEmpty(this.getConcentratorID()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",this.getConcentratorID()));
			}
			if(!ValidatorUtil.isEmpty(this.getDescription()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"description",this.getDescription()));
			}
			if(!ValidatorUtil.isEmpty(this.getStatus()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"status",String.valueOf(ConcentratorStatusEnum.getEnumByStr(this.getStatus()).getIntValue())));
			}
			
		 
		return conditionList;
    }

	public String getConcentratorID()
	{
		return concentratorID;
	}


	public void setConcentratorID(String concentratorID)
	{
		this.concentratorID = concentratorID;
	}


	public String getStatus()
	{
		return status;
	}


	public void setStatus(String status)
	{
		this.status = status;
	}


	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public ConcentratorStatusEnum[] getConcentStatusList()
	{
		return concentStatusList;
	}
	public void setConcentStatusList(ConcentratorStatusEnum[] concentStatusList)
	{
		this.concentStatusList = concentStatusList;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public ConcentratorPermissionEnum[] getPermissionTypeList()
	{
		return permissionTypeList;
	}

	public void setPermissionTypeList(
			ConcentratorPermissionEnum[] permissionTypeList)
	{
		this.permissionTypeList = permissionTypeList;
	}

}
