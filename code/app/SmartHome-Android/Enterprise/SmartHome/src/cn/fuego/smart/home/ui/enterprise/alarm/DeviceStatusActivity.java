package cn.fuego.smart.home.ui.enterprise.alarm;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.AlarmKindEnum;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.constant.AttributeConst;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class DeviceStatusActivity extends FireAlarmActivity
{
	//private FuegoLog log = FuegoLog.getLog(getClass());

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
	public void saveOnClick(View v)
	{
		Intent i = new Intent();
		i.setClass(this, AlarmHistoryActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
		Bundle mBundle= new Bundle();
		mBundle.putSerializable(IntentCodeConst.COMPANY_INFO, company);
		mBundle.putString(IntentCodeConst.COMPANY_ID, String.valueOf(companyID));
		mBundle.putString(IntentCodeConst.ALARM_KIND, String.valueOf(AlarmKindEnum.STATUS.getIntValue()));
        i.putExtras(mBundle);
		this.startActivity(i);
	}


	@Override
	public void loadSendList()
	{
		GetFireAlarmByIDReq req= new GetFireAlarmByIDReq();
        req.setCompanyID(String.valueOf(companyID));
        
    	List<AttributeJson> attrList = new ArrayList<AttributeJson>();
    	AttributeJson attr = new AttributeJson();

		attr.setAttrName(AttributeConst.ALARM_KIND);
		attr.setAttrValue(String.valueOf(AlarmKindEnum.STATUS.getIntValue()));
		attrList.add(attr);
		req.setFilterList(attrList);
		WebServiceContext.getInstance().getSensorManageRest(this).getFireAlarm(req);
		
	}


	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,FireAlarmJson item)
	{
		if(item.getAlarmTypeName().equals(AlarmTypeEnum.OFF_LINE.getStrValue()))
		{
			return;
		}
		else
		{
			super.onItemListClick(parent, view, id, item);
		}
		
	}
	@Override
	protected void onDestroy()
	{
/*		Intent intent=new Intent();
        intent.putExtra(IntentCodeConst.HOME_REFRESH, 3);
        intent.setAction("android.intent.action.bageNotify");//action与接收器相同
        sendBroadcast(intent);*/
        AppCache.getInstance().setHomeRefreshFlag(2);
		super.onDestroy();
	}


}
