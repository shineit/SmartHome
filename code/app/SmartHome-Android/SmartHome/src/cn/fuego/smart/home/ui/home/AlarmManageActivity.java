package cn.fuego.smart.home.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.ui.MainTabbarActivity;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.model.AlarmViewModel;

import com.fuego.smarthome.R;

public class AlarmManageActivity extends BaseActivtiy implements View.OnClickListener
{

	private AlarmViewModel alarmModel= new AlarmViewModel();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_manage);
		ExitApplication.getInstance().addActivity(this);
		
		Intent intent = this.getIntent();
		//String value = intent.getStringExtra("alarm_id"); 
		//alarmID = intent.getIntExtra("alarm_id", 0);
		TextView txt_eventID = (TextView) findViewById(R.id.alarm_manage_id);
		txt_eventID.setText(intent.getCharSequenceExtra(alarmModel.getEventID()));
		
		TextView txt_objID = (TextView) findViewById(R.id.alarm_manage_objID);
		txt_objID.setText(intent.getCharSequenceExtra(alarmModel.getObjID()));
		
		TextView txt_objType = (TextView) findViewById(R.id.alarm_manage_objType);
		txt_objType.setText(intent.getCharSequenceExtra(alarmModel.getObj()));
		
		TextView txt_alarmType = (TextView) findViewById(R.id.alarm_manage_type);
		txt_alarmType.setText(intent.getCharSequenceExtra(alarmModel.getTitle()));		
		//alarm_id_text.setText(String.valueOf(alarmID));
		//initView();
		//Toast.makeText(AlarmManageActivity.this, value, Toast.LENGTH_LONG);
		Button back_btn=(Button)findViewById(R.id.safe_manage_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		
		Button remove_btn=(Button) findViewById(R.id.alarm_remove_btn);
		remove_btn.setOnClickListener(this);
		remove_btn.setTag(2);

	}


	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
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
		Intent intent = new Intent(this,MainTabbarActivity.class);
 		startActivity(intent);
 		
		
	}
}
