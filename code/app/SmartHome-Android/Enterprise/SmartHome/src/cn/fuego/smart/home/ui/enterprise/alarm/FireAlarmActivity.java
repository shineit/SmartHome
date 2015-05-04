package cn.fuego.smart.home.ui.enterprise.alarm;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.StrUtil;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.AlarmKindEnum;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.constant.AttributeConst;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.service.AlarmSoundService;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCompanyByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCompanyByIDRsp;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class FireAlarmActivity extends MispListActivity<FireAlarmJson>
{

	protected CompanyJson company;
	protected int companyID;
	@Override
	public void initRes()
	{
		
		this.activityRes.setAvtivityView(R.layout.activity_fire_alarm);
		this.activityRes.setName("智慧告警");
		this.setAdapterForScrollView();
		this.listViewRes.setListView(R.id.fire_alarm_list);
		this.activityRes.getButtonIDList().add(R.id.fire_alarm_mute_btn);
		this.listViewRes.setListItemView(R.layout.fire_alarm_item);
		
		
		this.listViewRes.setClickActivityClass(FireAlarmViewActivity.class);
		
		Intent intent = this.getIntent();
		company = (CompanyJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		if(company!=null)
		{
			companyID=company.getCompanyID();
		}
		else
		{
			companyID =intent.getIntExtra(IntentCodeConst.COMPANY_ID, 0);
			loadCompayInfo(companyID);
		}

	}
	

	private void loadCompayInfo(int companyID)
	{
		GetCompanyByIDReq req = new GetCompanyByIDReq();
		req.setCompanyID(companyID);
		WebServiceContext.getInstance().getCompanyManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetCompanyByIDRsp rsp = (GetCompanyByIDRsp) message.getMessage().obj;
					company=rsp.getCompany();
					showMessage(company.getApplyName());
				}
				else
				{
					showMessage(message);
				}
			}
		}).getCompanyByID(req);
		
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
	public void onItemListClick(AdapterView<?> parent, View view, long id,	FireAlarmJson item)
	{
		Intent i = new Intent();
		i.setClass(this, FireAlarmViewActivity.class);
		Bundle mBundle= new Bundle();
	    mBundle.putSerializable(ListViewResInfo.SELECT_ITEM,item);
	    mBundle.putSerializable(IntentCodeConst.COMPANY_INFO, company);
        i.putExtras(mBundle);
        this.startActivity(i);
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
		StringBuffer sbTitle= new StringBuffer();
		sbTitle.append(StrUtil.noNullStr(item.getSensorTypeName()));
		sbTitle.append("发生");
		sbTitle.append(item.getAlarmTypeName());
		TextView item_title = (TextView) view.findViewById(R.id.item_alarm_title);
		item_title.setText(sbTitle.toString());
		TextView item_content = (TextView) view.findViewById(R.id.item_alarm_content);
		if(item.getAlarmTypeName().equals(AlarmTypeEnum.OFF_LINE.getStrValue()))
		{
			item_content.setText(DateUtil.getStrTime(item.getAlarmTime()));
		}
		else
		{
			item_content.setText(StrUtil.noNullStr(item.getLocationDesp()));
		}
		
		
		return view;
	}


	@Override
	public void loadSendList()
	{
		this.waitDailog.show();
		GetFireAlarmByIDReq req= new GetFireAlarmByIDReq();
        req.setCompanyID(String.valueOf(companyID));
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
		this.waitDailog.dismiss();
		GetFireAlarmByIDRsp rsp = (GetFireAlarmByIDRsp) obj;
		if(ValidatorUtil.isEmpty(rsp.getFireAlarmList()))
		{
			showToast(this, "当前无异常信息！");
		}
		return rsp.getFireAlarmList();
	}


	@Override
	protected void onDestroy()
	{
/*		Intent intent=new Intent();
        intent.putExtra(IntentCodeConst.HOME_REFRESH, 1);
        intent.setAction("android.intent.action.bageNotify");//action与接收器相同
        sendBroadcast(intent);*/
        AppCache.getInstance().setHomeRefreshFlag(1);
		super.onDestroy();
	}



}
