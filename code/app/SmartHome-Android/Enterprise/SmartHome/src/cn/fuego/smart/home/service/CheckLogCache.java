/**   
* @Title: CheckItemCache.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Aether
* @date 2015-3-17 下午2:07:13 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.webservice.up.model.base.CheckItemJson;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;

/** 
 * @ClassName: CheckItemCache 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-17 下午2:07:13 
 *  
 */
public class CheckLogCache 
{

	private List<CheckLogJson> checkLogList = new ArrayList<CheckLogJson>();

	private static CheckLogCache instance;
 
	private String companyID,companyName;
	
	private boolean isChecked=false;
	
	private CheckLogCache()
	{
		
	}
	
	synchronized public static CheckLogCache getInstance()
	{
		if(null == instance)
		{
			instance = new CheckLogCache();
		}
		return instance;
	}
	
	public void init(int companyID,String companyName,List<CheckItemJson> checkItemList)
	{
		if(!ValidatorUtil.isEmpty(checkItemList))
		{
			checkLogList.clear();
			isChecked=false;
			for(CheckItemJson json :checkItemList)
			{
				CheckLogJson temp= new CheckLogJson();
				temp.setCompanyID(companyID);
				temp.setCompanyName(companyName);
				temp.setCheckItem(json.getItemName());
				temp.setCheckSys(json.getItemSys());
				temp.setChecker(AppCache.getInstance().getCustomer().getCustomerName());
				checkLogList.add(temp);
			}
			
		}
		else
		{
			checkLogList.clear();
		}
	}
	
	public void update(CheckLogJson json)
	{
		if(!isChecked)
		{
			isChecked=true;
		}
		for(CheckLogJson e :checkLogList)
		{
			if(e.equals(json))
			{
				e.setAbnormalDesp(json.getAbnormalDesp());
				e.setAbnormalPic(json.getAbnormalPic());
				e.setCheckResult(json.getCheckResult());
				break;
			}
		}
	}
 
	
 

	public List<CheckLogJson> getCheckLogList()
	{
		return checkLogList;
	}

	public void setCheckLogList(List<CheckLogJson> checkLogList)
	{
		this.checkLogList = checkLogList;
	}

	public String getCompanyID()
	{
		return companyID;
	}

	public void setCompanyID(String companyID)
	{
		this.companyID = companyID;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public boolean isChecked()
	{
		return isChecked;
	}

	public void setChecked(boolean isChecked)
	{
		this.isChecked = isChecked;
	}
	
	
}
