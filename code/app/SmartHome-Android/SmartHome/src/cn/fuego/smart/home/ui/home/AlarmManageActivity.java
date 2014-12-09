package cn.fuego.smart.home.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.CommonConst;
import cn.fuego.smart.home.ui.MainTabbarActivity;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.model.AlarmViewModel;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class AlarmManageActivity extends BaseActivtiy implements View.OnClickListener
{

	private AlarmViewModel alarmModel= new AlarmViewModel();
	private String alarmID=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_manage);
		ExitApplication.getInstance().addActivity(this);
		
		Intent intent = this.getIntent();
		//Bundle alarmView=intent.getExtras()
		//String value = intent.getStringExtra("alarm_id"); 
		//alarmID = intent.getIntExtra("alarm_id", 0);
		TextView txt_eventID = (TextView) findViewById(R.id.alarm_manage_id);
		txt_eventID.setText(intent.getStringExtra(alarmModel.getEventID()));
		alarmID=intent.getStringExtra(alarmModel.getEventID());
		TextView txt_objID = (TextView) findViewById(R.id.alarm_manage_objID);
		txt_objID.setText(intent.getStringExtra(alarmModel.getObjID()));
		
		TextView txt_objType = (TextView) findViewById(R.id.alarm_manage_objType);
		txt_objType.setText(intent.getStringExtra(alarmModel.getObj()));
		
		TextView txt_alarmType = (TextView) findViewById(R.id.alarm_manage_type);
		txt_alarmType.setText(intent.getStringExtra(alarmModel.getTitle()));
		
		TextView txt_alarmDesp = (TextView) findViewById(R.id.alarm_manage_desp);
		txt_alarmDesp.setText(intent.getStringExtra(alarmModel.getDescription()));
		
		TextView txt_alarmWarn = (TextView) findViewById(R.id.alarm_manage_warn);
		txt_alarmWarn.setText(intent.getStringExtra(alarmModel.getWarnValue()));
		
		TextView txt_alarmError = (TextView) findViewById(R.id.alarm_manage_error);
		txt_alarmError.setText(intent.getStringExtra(alarmModel.getErrorValue()));		

		Button back_btn=(Button)findViewById(R.id.safe_manage_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		
		Button remove_btn=(Button) findViewById(R.id.alarm_remove_btn);
		remove_btn.setOnClickListener(this);
		remove_btn.setTag(2);

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
		WebServiceContext.getInstance().getSensorManageRest(this).clearAlarm(req);
 		
		
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		

		if (this.isMessageSuccess(message))
		{
		      
			Intent intent = new Intent(AlarmManageActivity.this,MainTabbarActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
			CommonConst.setFlag(0);
			startActivity(intent);
			
	 		this.finish();

		}
		else
		{
			this.showMessage(message);
		}
		
	}
}
