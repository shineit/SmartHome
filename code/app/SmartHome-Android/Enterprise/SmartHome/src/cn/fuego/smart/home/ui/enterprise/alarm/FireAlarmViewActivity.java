package cn.fuego.smart.home.ui.enterprise.alarm;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;

public class FireAlarmViewActivity extends MispBaseActivtiy 
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private FireAlarmJson fireAlarm;

	@Override
	public void initRes()
	{
		
		this.activityRes.setName("智慧告警");

		this.activityRes.setAvtivityView(R.layout.activity_fire_alarm_view);
		
		this.activityRes.getButtonIDList().add(R.id.fire_alarm_loc);

		fireAlarm= (FireAlarmJson) this.getIntent().getSerializableExtra(ListViewResInfo.SELECT_ITEM);
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

	}
 
	@Override
	public void onClick(View v)
	{
		SensorLocationActivity.jump(this, fireAlarm);
	}



}
