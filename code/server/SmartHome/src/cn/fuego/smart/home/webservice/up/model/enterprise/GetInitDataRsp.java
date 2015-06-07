/**   
* @Title: GetInitDataRsp.java 
* @Package cn.fuego.smart.home.webservice.up.model.enterprise 
* @Description: TODO
* @author Aether
* @date 2015-6-5 下午8:39:10 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.model.enterprise;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.BageNumJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.CustomerJson;

/** 
 * @ClassName: GetInitDataRsp 
 * @Description: TODO
 * @author Aether
 * @date 2015-6-5 下午8:39:10 
 *  
 */
public class GetInitDataRsp extends MispBaseRspJson
{

	private CustomerJson customer;
	private List<BageNumJson> numList = new ArrayList<BageNumJson>();
	
	private List<CompanyJson> companyList = new ArrayList<CompanyJson>();

	
	public CustomerJson getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerJson customer)
	{
		this.customer = customer;
	}

	public List<BageNumJson> getNumList()
	{
		return numList;
	}

	public void setNumList(List<BageNumJson> numList)
	{
		this.numList = numList;
	}

	public List<CompanyJson> getCompanyList()
	{
		return companyList;
	}

	public void setCompanyList(List<CompanyJson> companyList)
	{
		this.companyList = companyList;
	}

	@Override
	public String toString()
	{
		return "GetInitDataRsp [customer=" + customer + ", numList=" + numList
				+ ", companyList=" + companyList + "]";
	}


	
	
}
