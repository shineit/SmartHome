package cn.fuego.smart.home.ui.setting.concent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.webservice.up.model.SetConcentratorReq;
import cn.fuego.smart.home.webservice.up.model.base.ConcentratorJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class ConcentConfigActivity extends BaseActivtiy implements OnClickListener, OnCheckedChangeListener
{

	private TextView txt_id;
	private EditText txt_name,txt_desp;
	private ProgressDialog proDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.concent_config);
		ExitApplication.getInstance().addActivity(this);
		Intent intent = this.getIntent();
		initData(intent);
	}

	private void initData(Intent intent)
	{
		ConcentratorJson concent =(ConcentratorJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		Button back_btn = (Button) findViewById(R.id.concent_config_back_btn);
		back_btn.setOnClickListener(this);
		Button config_btn= (Button) findViewById(R.id.concent_config_sure_btn);
		config_btn.setOnClickListener(this);
		txt_id = (TextView) findViewById(R.id.concent_config_id);
		txt_id.setText(String.valueOf(concent.getConcentratorID()));
		txt_id.requestFocus();
		txt_id.requestFocusFromTouch();
		//用于展示列表
		CheckBox list_chk = (CheckBox) findViewById(R.id.concnet_config_list_chk);
		list_chk.setOnCheckedChangeListener(this);
		ListView sensor_list= (ListView) findViewById(R.id.concent_config_termin_list);
		
		txt_name = (EditText) findViewById(R.id.concent_config_name);
		txt_name.setText(concent.getName());
		txt_desp = (EditText) findViewById(R.id.concent_config_desp);
		txt_desp.setText(concent.getDescription());
	
	}

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.concent_config_back_btn: 
			this.finish();
			break;
		case R.id.concent_config_sure_btn: 
			configConcent();
			break;
		default:break;
		}
		
	}

	private void configConcent()
	{
		proDialog =ProgressDialog.show(ConcentConfigActivity.this, "请稍等", "正在同步数据……");
		SetConcentratorReq req = new SetConcentratorReq();
		ConcentratorJson json = new ConcentratorJson();
		json.setConcentratorID(Long.valueOf(txt_id.getText().toString()));
		json.setDescription(txt_desp.getText().toString());
		json.setName(txt_name.getText().toString());
		req.setConcentrator(json);
		WebServiceContext.getInstance().getConcentManageRest(this).modifyConcent(req);
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{

			proDialog.dismiss();
			Toast.makeText(ConcentConfigActivity.this, "集中器信息修改成功！", Toast.LENGTH_SHORT).show();
			Intent i= new Intent();
			i.setClass(ConcentConfigActivity.this, ConcentListActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );

	        this.startActivity(i);
			this.finish();
		}
		else
		{
			proDialog.dismiss();
			this.showMessage(message);
		}
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initRes() {
		// TODO Auto-generated method stub
		
	}
}
