/**   
* @Title: CompanyDataCache.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Aether
* @date 2015-6-5 下午8:54:00 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;

/** 
 * @ClassName: CompanyDataCache 
 * @Description: TODO
 * @author Aether
 * @date 2015-6-5 下午8:54:00 
 *  
 */
public class CompanyDataCache
{

	private static CompanyDataCache instance;
	private List<CompanyJson> companyList =new ArrayList<CompanyJson>();
	private boolean hasCompany=false;
	private CompanyDataCache()
	{
		
	}
	
	synchronized public static CompanyDataCache getInstance()
	{
		if(null == instance)
		{
			instance = new CompanyDataCache();
		}
		return instance;
	}

	public List<CompanyJson> getCompanyList()
	{
		return companyList;
	}

	public void setCompanyList(List<CompanyJson> companyList)
	{
		this.companyList = companyList;
	}

	public boolean isHasCompany()
	{
		if(this.companyList.size()==0)
			return false;

		else 
			return true;
	}

	public void setHasCompany(boolean hasCompany)
	{
		this.hasCompany = hasCompany;
	}
	
	
}
