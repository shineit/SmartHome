package cn.fuego.smart.home.ui.enterprise.alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.fuego.smart.home.R;

public class FireAlarmActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fire_alarm);
		
		Button back_btn = (Button) findViewById(R.id.fire_alarm_back);
		back_btn.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				Intent i= new Intent();
				i.setClass(FireAlarmActivity.this, FireAlarmViewActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
		        startActivity(i);
				
			}
		});
	}
}
