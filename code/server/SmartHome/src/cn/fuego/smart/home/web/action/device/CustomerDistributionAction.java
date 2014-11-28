package cn.fuego.smart.home.web.action.device;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.action.basic.MISPAction;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.service.ConcentratorManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.ConcentFilterModel;

/** 
* @ClassName: CustomerDistributionAction 
* @Description: TODO
* @author Aether
* @date 2014-11-5 下午12:00:46 
*  
*/ 
public class CustomerDistributionAction extends MISPAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(CustomerDistributionAction.class);
	private ConcentratorManageService concentService = ServiceContext.getInstance().getConcentratorManageService();
	private ConcentFilterModel filter =new ConcentFilterModel();
	private String locationJson;

	@Override
	public String execute()
	{
		AbstractDataSource<Concentrator> dataSource= concentService.getDataSource(filter.getConidtionList());
		ObjectMapper mapper = new ObjectMapper();  
		try
		{

			locationJson = mapper.writeValueAsString(dataSource.getAllPageData());
		} catch (Exception e)
		{
			log.error("convert failed",e);
		}
		return SUCCESS;
		
	}

 

	public ConcentratorManageService getConcentService()
	{
		return concentService;
	}

	public void setConcentService(ConcentratorManageService concentService)
	{
		this.concentService = concentService;
	}


	public String getLocationJson()
	{
		return locationJson;
	}

	public void setLocationJson(String locationJson)
	{
		this.locationJson = locationJson;
	}

	public ConcentFilterModel getFilter()
	{
		return filter;
	}

	public void setFilter(ConcentFilterModel filter)
	{
		this.filter = filter;
	}


	
}
