package cn.fuego.smart.home.ui.safe;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;

public class SafeConfigActivity extends BaseActivtiy implements OnClickListener
{
    //测试数据
	private static final String[] countries={"China","Russia","Germany","America","Belarus","Japan"}; 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.safe_manage);
		ExitApplication.getInstance().addActivity(this);
		Intent intent = this.getIntent();
		
		//TextView txt_label =(TextView) findViewById(R.id.safe_label);
		//txt_label.setText(intent.getStringExtra("label"));
		Spinner markSpinner =  (Spinner) findViewById(R.id.safe_mark_spinner);
		List<String> markData= new ArrayList<String>();
		for(int i=0;i<countries.length;i++)
		{
			markData.add(countries[i]);
			
		}
		ArrayAdapter<String> markAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item , markData);
		markAdapter.setDropDownViewResource(R.layout.dropdown_item);//自定义样式
		markSpinner.setAdapter(markAdapter);
		
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

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}
}
