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
	private FuegoLog log = FuegoLog.getLog(MispBaseActivtiy.class);
	private Window window;
	private PopupWindow popupWindow=null; 
	private View view;
	
	private FireAlarmJson fireAlarm;

	@Override
	public void initRes()
	{
		
		this.activityRes.setName("智慧告警");

		this.activityRes.setAvtivityView(R.layout.activity_fire_alarm_view);

		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		window=FireAlarmViewActivity.this.getWindow();
		fireAlarm= (FireAlarmJson) this.getIntent().getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		//log.info("fireAlarm is :"+fireAlarm.getAlarmTypeName());
		Button location_btn = (Button) findViewById(R.id.fire_alarm_loc);
		
		TextView txt_machineID= (TextView) findViewById(R.id.alarm_machine_id);
		txt_machineID.setText(fireAlarm.getMachineID());
		TextView txt_loopID= (TextView) findViewById(R.id.alarm_loop_id);
		txt_loopID.setText(String.valueOf(fireAlarm.getLoopID()));
		TextView txt_codeID= (TextView) findViewById(R.id.alarm_code_id);
		txt_codeID.setText(String.valueOf(fireAlarm.getCodeID()));
		TextView txt_sensorType= (TextView) findViewById(R.id.alarm_sensor_type);
		txt_sensorType.setText(fireAlarm.getSensorTypeName());
		TextView txt_alarmType= (TextView) findViewById(R.id.alarm_type_name);
		txt_alarmType.setText(fireAlarm.getAlarmTypeName());

	}
	

	private void loadLocaPic(View parent) {

		if (popupWindow == null)
		{		
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);			
			view = layoutInflater.inflate(R.layout.pop_fire_alarm_location, null);

			// 创建一个PopuWidow对象
			 popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			// 使其聚集
			popupWindow.setFocusable(true);
			// 设置允许在外点击消失
			popupWindow.setOutsideTouchable(true);

			// 实例化一个ColorDrawable颜色为半透明
			ColorDrawable dw = new ColorDrawable(0x90000000);
			popupWindow.setBackgroundDrawable(dw);
			popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);         
			popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
			 
		}


        //设置背景变暗
        WindowManager.LayoutParams lp=window.getAttributes();
        lp.alpha = 0.4f;
        window.setAttributes(lp); 
		

        
		
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

		popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);//在屏幕居中，无偏移
		
		popupWindow.setOnDismissListener(new OnDismissListener()
		{
			
			@Override
			public void onDismiss()
			{
				WindowManager.LayoutParams lp=window.getAttributes();
			    lp.alpha = 1f;
			    window.setAttributes(lp);
			    popupWindow=null;
			}
		});
		
	}


}
