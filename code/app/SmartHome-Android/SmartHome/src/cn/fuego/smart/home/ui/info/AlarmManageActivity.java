package cn.fuego.smart.home.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.ListViewResInfo;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.base.HomeAlarmJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class AlarmManageActivity extends BaseActivtiy implements View.OnClickListener
{

	private EditText txt_alarmtime,txt_concentDesp,txt_terminDesp,txt_terminType,txt_alarmType;
	private String alarmID=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_manage);
		ExitApplication.getInstance().addActivity(this);
		
		txt_alarmtime = (EditText) findViewById(R.id.alarm_manage_time);
		txt_concentDesp  = (EditText) findViewById(R.id.alarm_manage_concent);	
		txt_terminDesp = (EditText) findViewById(R.id.alarm_manage_termin);	
		txt_terminType = (EditText) findViewById(R.id.alarm_manage_terminType);	
		txt_alarmType = (EditText) findViewById(R.id.alarm_manage_type);		
		Intent intent = this.getIntent();
		initView(intent);

		Button back_btn=(Button)findViewById(R.id.alarm_manage_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		
		Button remove_btn=(Button) findViewById(R.id.alarm_remove_btn);
		remove_btn.setOnClickListener(this);
		remove_btn.setTag(2);

	}



	private void initView(Intent intent)
	{
		HomeAlarmJson alarm =(HomeAlarmJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		alarmID=String.valueOf(alarm.getId());
		txt_alarmtime.setText(DateUtil.getStrTime(alarm.getAlarmTime()));
		txt_concentDesp.setText(alarm.getConcentDesp());
		txt_terminDesp.setText(alarm.getSensorDesp());
		txt_terminType.setText(alarm.getSensorTypeName());
		txt_alarmType.setText(alarm.getAlarmType());
	}



	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		switch(tag)
		{
		case 1: this.finish();
				break;
		case 2: alarmRemove();
		 		break;

		default:break;
		}
		
	}


	private void alarmRemove()
	{
		ClearAlarmByIDReq req = new ClearAlarmByIDReq();
		req.setToken(MemoryCache.getToken());
		req.setAlarmID(alarmID);
		req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		WebServiceContext.getInstance().getSensorManageRest(this).clearAlarm(req);
 		
		
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		

		if (message.isSuccess())
		{
		      
			Intent intent = new Intent(AlarmManageActivity.this,AlarmActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
			//MemoryCache.setFlag(0);
			this.startActivity(intent);		
	 		this.finish();

		}
		else
		{
			this.showMessage(message);
		}
		
	}
}
