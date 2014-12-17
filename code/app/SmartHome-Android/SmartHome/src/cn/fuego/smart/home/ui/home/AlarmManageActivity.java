package cn.fuego.smart.home.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
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

		alarmID = intent.getStringExtra(alarmModel.getAlarmID());
		
		TextView txt_alarmtime = (TextView) findViewById(R.id.alarm_manage_time);
		txt_alarmtime.setText(intent.getStringExtra(alarmModel.getTime()));	
		
		TextView txt_concentDesp  = (TextView) findViewById(R.id.alarm_manage_concent);
		txt_concentDesp.setText(intent.getStringExtra(alarmModel.getContent()));
		
		TextView txt_terminDesp = (TextView) findViewById(R.id.alarm_manage_termin);
		txt_terminDesp.setText(intent.getStringExtra(alarmModel.getTerminDesp()));
		
		TextView txt_terminType = (TextView) findViewById(R.id.alarm_manage_terminType);
		txt_terminType.setText(intent.getStringExtra(alarmModel.getTerminType()));
		
		TextView txt_alarmType = (TextView) findViewById(R.id.alarm_manage_type);
		txt_alarmType.setText(intent.getStringExtra(alarmModel.getTitle()));
		
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
		case 1: Intent intent = new Intent(AlarmManageActivity.this,MainTabbarActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
				MemoryCache.setFlag(0);
				startActivity(intent);
				this.finish();
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
		

		if (message.isSuccess())
		{
		      
			Intent intent = new Intent(AlarmManageActivity.this,MainTabbarActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
			MemoryCache.setFlag(0);
			startActivity(intent);
			
	 		this.finish();

		}
		else
		{
			this.showMessage(message);
		}
		
	}
}
