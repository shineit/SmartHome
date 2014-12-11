package cn.fuego.smart.home.ui.safe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.ExitApplication;

public class SafeConfigActivity extends Activity implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.safe_manage);
		ExitApplication.getInstance().addActivity(this);
		Intent intent = this.getIntent();
		
		TextView txt_label =(TextView) findViewById(R.id.safe_label);
		txt_label.setText(intent.getStringExtra("label"));

		Button back_btn=(Button)findViewById(R.id.safe_manage_back);
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
