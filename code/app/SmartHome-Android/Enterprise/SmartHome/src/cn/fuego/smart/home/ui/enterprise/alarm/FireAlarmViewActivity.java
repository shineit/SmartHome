package cn.fuego.smart.home.ui.enterprise.alarm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;

public class FireAlarmViewActivity extends MispBaseActivtiy 
{
	private FireAlarmJson fireAlarm;
	private CompanyJson company;

	@Override
	public void initRes()
	{
		
		this.activityRes.setName("设备信息");

		this.activityRes.setAvtivityView(R.layout.activity_fire_alarm_view);
		
		this.activityRes.getButtonIDList().add(R.id.fire_alarm_loc);
		this.activityRes.getButtonIDList().add(R.id.fire_alarm_dial);
		fireAlarm= (FireAlarmJson) this.getIntent().getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		company =(CompanyJson) this.getIntent().getSerializableExtra(IntentCodeConst.COMPANY_INFO);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		TextView txt_machineID= (TextView) findViewById(R.id.alarm_machine_id);
		txt_machineID.setText(String.valueOf(fireAlarm.getMachineID()));
		TextView txt_loopID= (TextView) findViewById(R.id.alarm_loop_id);
		txt_loopID.setText(String.valueOf(fireAlarm.getLoopID()));
		TextView txt_codeID= (TextView) findViewById(R.id.alarm_code_id);
		txt_codeID.setText(String.valueOf(fireAlarm.getCodeID()));
		TextView txt_sensorType= (TextView) findViewById(R.id.alarm_sensor_type);
		txt_sensorType.setText(fireAlarm.getSensorTypeName());
		TextView txt_alarmType= (TextView) findViewById(R.id.alarm_type_name);
		txt_alarmType.setText(fireAlarm.getAlarmTypeName());
		
		TextView txt_locaDesp= (TextView) findViewById(R.id.alarm_location_desp);
		txt_locaDesp.setText(fireAlarm.getLocationDesp());
		
		TextView txt_alarmtime= (TextView) findViewById(R.id.alarm_time);
		txt_alarmtime.setText(DateUtil.getStrTime(fireAlarm.getAlarmTime()));
		
		TextView txt_companyName= (TextView) findViewById(R.id.fire_alarm_company_name);
		txt_companyName.setText(company.getApplyName());
		
		TextView txt_contacts = (TextView) findViewById(R.id.fire_alarm_contacts);
		txt_contacts.setText(fireAlarm.getContacts());
		TextView txt_contactPhone = (TextView) findViewById(R.id.fire_alarm_contact_phone);
		txt_contactPhone.setText(fireAlarm.getContactPhone());	
		
		Button dial_btn = (Button) findViewById(R.id.fire_alarm_dial);
		if(ValidatorUtil.isEmpty(fireAlarm.getContactPhone()))
		{
			dial_btn.setVisibility(View.INVISIBLE);
		}
	}
 
	@Override
	public void onClick(View v)
	{
		if(v.getId()==R.id.fire_alarm_loc)
		{
			SensorLocationActivity.jump(this, fireAlarm);
		}
		if(v.getId()==R.id.fire_alarm_dial)
		{
			dial();
		}
		
	}
	private void dial()
	{
		
		final String content = fireAlarm.getContactPhone();
		if(ValidatorUtil.isEmpty(content))
		{
			return;
		}
		 
 		new AlertDialog.Builder(FireAlarmViewActivity.this)    
        .setTitle("客服电话").setMessage(content) 
                .setPositiveButton("拨打", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						 
		                //用intent启动拨打电话  
		                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+content));  
		                startActivity(intent); 
						
					}
					
					 
				})
                .setNegativeButton("取消", null)
                .show();  
	}
	


}
