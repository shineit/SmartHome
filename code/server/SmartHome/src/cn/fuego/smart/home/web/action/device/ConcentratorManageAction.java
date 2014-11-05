package cn.fuego.smart.home.web.action.device;

import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.page.TableDataModel;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.service.ConcentratorManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.ConcentFilterModel;

public class ConcentratorManageAction extends DWZTableAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableDataModel<Concentrator> concentTable = new TableDataModel<Concentrator>();
	private ConcentratorManageService concentService = ServiceContext.getInstance().getConcentratorManageService();
	private ConcentFilterModel filter = new ConcentFilterModel();
	@Override
	public String execute()
	{
		concentTable.setPage(this.getPage());
		concentTable.setDataSource(concentService.getConcentDataSource(filter.getConidtionList()));
		return SUCCESS;
	}
	@Override
	public String create()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteList()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String show()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public TableDataModel<Concentrator> getConcentTable()
	{
		return concentTable;
	}
	public void setConcentTable(TableDataModel<Concentrator> concentTable)
	{
		this.concentTable = concentTable;
	}
	public ConcentratorManageService getConcentService()
	{
		return concentService;
	}
	public void setConcentService(ConcentratorManageService concentService)
	{
		this.concentService = concentService;
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
