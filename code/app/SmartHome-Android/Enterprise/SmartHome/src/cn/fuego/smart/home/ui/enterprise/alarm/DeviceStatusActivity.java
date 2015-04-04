package cn.fuego.smart.home.ui.enterprise.alarm;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.AlarmKindEnum;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.constant.AttributeConst;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.webservice.down.model.AlarmPushInfoJson;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class DeviceStatusActivity extends FireAlarmActivity
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	@Override
	public void initRes()
	{
 
		super.initRes();
		this.activityRes.setName("设备状态");
		AlarmPushInfoJson a = new AlarmPushInfoJson();
		a.setCompanyID(8);
		a.setPushType(2);
		String b = JsonConvert.ObjectToJson(a);
		AlarmPushInfoJson c = (AlarmPushInfoJson) JsonConvert.jsonToObject(b, AlarmPushInfoJson.class);
		log.error(""+c);
		Object obj = JsonConvert.jsonToObject(b, Object.class);
		String objS = JsonConvert.ObjectToJson(obj);
		log.info(objS);
		
		PushMessageJson json = new PushMessageJson();
		json.setObj(a);
		json.setObjType(1);
		String json1 = JsonConvert.ObjectToJson(json);
		PushMessageJson pOjb = (PushMessageJson) JsonConvert.jsonToObject(json1, PushMessageJson.class);
		Object aaaa = pOjb.getObj();
		String aaas = JsonConvert.ObjectToJson(aaaa);
		
		AlarmPushInfoJson aaa = (AlarmPushInfoJson) JsonConvert.jsonToObject(aaas, AlarmPushInfoJson.class);
		
		log.info(""+aaa);

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
		Intent intent=new Intent();
        intent.putExtra(IntentCodeConst.HOME_REFRESH, 3);
        intent.setAction("android.intent.action.bageNotify");//action与接收器相同
        sendBroadcast(intent);
		super.onDestroy();
	}


}
