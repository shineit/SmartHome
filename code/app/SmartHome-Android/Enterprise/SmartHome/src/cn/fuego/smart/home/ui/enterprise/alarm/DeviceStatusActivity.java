package cn.fuego.smart.home.ui.enterprise.alarm;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.AlarmKindEnum;
import cn.fuego.smart.home.constant.AttributeConst;
import cn.fuego.smart.home.webservice.up.model.GetFireAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class DeviceStatusActivity extends FireAlarmActivity
{

	@Override
	public void initRes()
	{
 
		super.initRes();
		this.activityRes.setName("设备状态");
	}
 

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View btn_area = findViewById(R.id.alarm_btn_area);
		btn_area.setVisibility(View.GONE);
	}


	@Override
	public void loadSendList()
	{
		GetFireAlarmByIDReq req= new GetFireAlarmByIDReq();
        req.setCompanyID(String.valueOf(company.getCompanyID()));
        
    	List<AttributeJson> attrList = new ArrayList<AttributeJson>();
    	AttributeJson attr = new AttributeJson();

		attr.setAttrName(AttributeConst.ALARM_KIND);
		attr.setAttrValue(String.valueOf(AlarmKindEnum.STATUS.getIntValue()));
		attrList.add(attr);
		req.setFilterList(attrList);
		WebServiceContext.getInstance().getSensorManageRest(this).getFireAlarm(req);
		
	}


}
