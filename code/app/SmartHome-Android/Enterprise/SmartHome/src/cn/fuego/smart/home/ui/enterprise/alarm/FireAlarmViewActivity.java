package cn.fuego.smart.home.ui.enterprise.alarm;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;
import cn.fuego.smart.home.R;

public class FireAlarmViewActivity extends Activity {

	private Window window;
	private PopupWindow popupWindow=null; 
	private View view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fire_alarm_view);
		window=FireAlarmViewActivity.this.getWindow();
		Button location_btn = (Button) findViewById(R.id.fire_alarm_loc);
		location_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//loadLocaPic(v);
				Toast.makeText(FireAlarmViewActivity.this, "OK", Toast.LENGTH_SHORT).show();
				loadLocaPic(v);
			}
		});
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
