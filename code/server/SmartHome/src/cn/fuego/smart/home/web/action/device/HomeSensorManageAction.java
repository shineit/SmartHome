package cn.fuego.smart.home.web.action.device;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.smart.home.constant.DeviceKindEunm;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.HomeSensorFilterModel;

public class HomeSensorManageAction extends DWZTableAction<HomeSensor>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());
	private HomeSensorFilterModel hsFilter = new HomeSensorFilterModel();

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<HomeSensor> getService()
	{
		// TODO Auto-generated method stub
		return ServiceContext.getInstance().getSensorManageService();
	}
	
	
    @Override
	public List<QueryCondition> getFilterCondition()
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(!ValidatorUtil.isEmpty(this.getSelectedID()))
		{
			this.hsFilter.setConcentratorID(this.getSelectedID());
		}
		if(!ValidatorUtil.isEmpty(hsFilter.getConcentratorID()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",hsFilter.getConcentratorID()));
		}

		if (!ValidatorUtil.isEmpty(hsFilter.getSensorID()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"sensorID", hsFilter.getSensorID()));
		}
		if (!ValidatorUtil.isEmpty(hsFilter.getSensorKind()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"sensorKind", DeviceKindEunm.getEnumByStr(hsFilter.getSensorKind()).getIntValue()));
		}
		return conditionList;
	}
 
	/**
     * 
     * 传感器配置修改提交
     */
    public String syncSensor()
    {
    	try
		{
    		ServiceContext.getInstance().getSensorManageService().syncSensor(Long.valueOf(this.getSelectedID()));
        	
		}catch(MISPException e)
		{
			log.error("syncSensor failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch (Exception e)
		{
			log.error("syncSensor failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		return MISP_DONE_PAGE;
    	
    }


	public HomeSensorFilterModel getHsFilter()
	{
		return hsFilter;
	}


	public void setHsFilter(HomeSensorFilterModel hsFilter)
	{
		this.hsFilter = hsFilter;
	}
    
    
	  

}
