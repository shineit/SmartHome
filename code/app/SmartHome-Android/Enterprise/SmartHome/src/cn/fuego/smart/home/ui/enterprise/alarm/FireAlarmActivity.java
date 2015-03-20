package cn.fuego.smart.home.ui.enterprise.alarm;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.StrUtil;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.AlarmKindEnum;
import cn.fuego.smart.home.constant.AttributeConst;
import cn.fuego.smart.home.service.AlarmSoundService;
import cn.fuego.smart.home.webservice.up.model.GetFireAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetFireAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class FireAlarmActivity extends MispListActivity<FireAlarmJson>
{

	protected CompanyJson company;
	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.activity_fire_alarm);
		this.activityRes.setName("智慧告警");
		this.listViewRes.setListView(R.id.fire_alarm_list);
		this.activityRes.getButtonIDList().add(R.id.fire_alarm_mute_btn);
		this.listViewRes.setListItemView(R.layout.fire_alarm_item);
		this.setAdapterForScrollView();
		
		this.listViewRes.setClickActivityClass(FireAlarmViewActivity.class);
		
		Intent intent = this.getIntent();
		company = (CompanyJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		
		
		

	}

	@Override
	public void onClick(View v)
	{

		if(v.getId()==R.id.fire_alarm_mute_btn)
		{
			Intent serviceIntent = new Intent(this, AlarmSoundService.class);
			stopService(serviceIntent);
		}
	}

	@Override
	public View getListItemView(View view, FireAlarmJson item)
	{
		ImageView item_icon= (ImageView) view.findViewById(R.id.item_alarm_icon);
		if(item.getAlarmKind()==AlarmKindEnum.ALARM.getIntValue())
		{
			item_icon.setImageResource(R.drawable.fire);
		}
		else
		{
			item_icon.setImageResource(R.drawable.prealarm);
		}
		TextView item_title = (TextView) view.findViewById(R.id.item_alarm_title);
		item_title.setText(item.getSensorTypeName()+"发生"+item.getAlarmTypeName());
		TextView item_content = (TextView) view.findViewById(R.id.item_alarm_content);
		item_content.setText(StrUtil.noNullStr(item.getLocationDesp()));
		
		return view;
	}

	@Override
	public void loadSendList()
	{
		GetFireAlarmByIDReq req= new GetFireAlarmByIDReq();
        req.setCompanyID(String.valueOf(company.getCompanyID()));
    	List<AttributeJson> attrList = new ArrayList<AttributeJson>();
    	AttributeJson attr = new AttributeJson();
		attr.setAttrName(AttributeConst.ALARM_KIND);
		attr.setAttrValue(String.valueOf(AlarmKindEnum.ALARM.getIntValue()));
		attrList.add(attr);
		req.setFilterList(attrList);
		WebServiceContext.getInstance().getSensorManageRest(this).getFireAlarm(req);
		
	}

	@Override
	public List<FireAlarmJson> loadListRecv(Object obj)
	{
		GetFireAlarmByIDRsp rsp = (GetFireAlarmByIDRsp) obj;
		
		return rsp.getFireAlarmList();
	}



}
