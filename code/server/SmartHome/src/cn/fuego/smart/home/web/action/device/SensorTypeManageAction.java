/**   
* @Title: SensorTypeAction.java 
* @Package cn.fuego.smart.home.web.action.device 
* @Description: TODO
* @author Aether
* @date 2015-1-24 下午2:33:01 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.device;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.page.TableDataModel;
import cn.fuego.smart.home.domain.SensorType;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.HomeSensorFilterModel;
import cn.fuego.smart.home.web.model.SensorTypeFilterModel;

/** 
 * @ClassName: SensorTypeAction 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-24 下午2:33:01 
 *  
 */
public class SensorTypeManageAction extends  DWZTableAction<SensorType>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());
	private SensorTypeFilterModel stFilter= new SensorTypeFilterModel();
	
	@Override
	public List<QueryCondition> getFilterCondition()
	{
		// TODO Auto-generated method stub
		return stFilter.getConidtionList();
	}



	@Override
	public MispCommonService<SensorType> getService()
	{
		// TODO Auto-generated method stub
		return ServiceContext.getInstance().getSensorTypeManageService();
	}



	@Override
	public String execute()
	{
		super.execute();
			
		List<SensorType> sensorTypeList=this.getService().getDataSource().getAllPageData();
		for(SensorType type:sensorTypeList)
		{
			if(!this.getStFilter().getSysList().contains(type.getTypeSys()))
			{
				this.getStFilter().getSysList().add(type.getTypeSys());
			}
			
		}
		return SUCCESS;
	}



	public SensorTypeFilterModel getStFilter()
	{
		return stFilter;
	}



	public void setStFilter(SensorTypeFilterModel stFilter)
	{
		this.stFilter = stFilter;
	}


}
