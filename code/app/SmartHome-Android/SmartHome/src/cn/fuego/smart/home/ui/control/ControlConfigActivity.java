package cn.fuego.smart.home.ui.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.R.id;
import cn.fuego.smart.home.R.layout;
import cn.fuego.smart.home.ui.base.ExitApplication;

public class ControlConfigActivity extends Activity implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_manage);
		ExitApplication.getInstance().addActivity(this);
		Intent intent = this.getIntent();
		
		TextView txt_id =(TextView) findViewById(R.id.control_objID);
		txt_id.setText(intent.getStringExtra("id"));
		TextView txt_room =(TextView) findViewById(R.id.control_label);
		txt_room.setText(intent.getStringExtra("room"));
		
		Button back_btn=(Button)findViewById(R.id.control_manage_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		switch(tag)
		{
		case 1: 
			this.finish();
				break;

		default:break;
		}
		
	}
}
