package cn.fuego.smart.home.ui.setting.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.webservice.up.model.SetCustomerReq;
import cn.fuego.smart.home.webservice.up.model.base.CustomerJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class UserManageActivity extends BaseActivtiy implements View.OnClickListener
{
	private TextView user_name;
	private EditText user_email,user_phone,user_addr;
	private ProgressDialog proDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		//统一activity管理，用于退出APP
		ExitApplication.getInstance().addActivity(this);
		
		initData();
	
	}

	private void initData()
	{
		user_name = (TextView) findViewById(R.id.user_info_username);
		user_email = (EditText) findViewById(R.id.user_info_email);
		user_phone = (EditText) findViewById(R.id.user_info_phone);
		user_addr = (EditText) findViewById(R.id.user_info_address);
		
		String view_name = MemoryCache.getLoginInfo().getUser().getUserName();
		String view_email = MemoryCache.getLoginInfo().getCustomer().getEmail();
		String view_phone = MemoryCache.getLoginInfo().getCustomer().getPhone();
		String view_addr = MemoryCache.getLoginInfo().getCustomer().getAddr();
		user_name.setText(view_name);
		if(view_email!=null)
		{
			user_email.setText(view_email);
		}
		if(view_phone!=null)
		{
			user_phone.setText(view_phone);
		}
		if(view_addr!=null)
		{
			user_addr.setText(view_addr);
		}
		
		Button back_btn=(Button)findViewById(R.id.user_info_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		
		Button user_info_btn = (Button) findViewById(R.id.modify_userinfo_sure);
		user_info_btn.requestFocus();
		user_info_btn.requestFocusFromTouch();
		user_info_btn.setOnClickListener(this);
		user_info_btn.setTag(2);
		
		Button modify_pwd_btn =  (Button) findViewById(R.id.modify_pwd_btn);
		modify_pwd_btn.setOnClickListener(this);
		modify_pwd_btn.setTag(3);
		
		Button logout_btn =  (Button) findViewById(R.id.logout_btn);
		logout_btn.setOnClickListener(this);
		logout_btn.setTag(4);	
		
		
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		Intent intent = null;
		switch(tag)
		{
		case 1: this.finish();  
				break;//个人中心返回
		case 2: modfiyInfo();
		 		break;//个人信息保存修改
		case 3: intent = new Intent(this,ModifyPwdActivity.class);
 				startActivity(intent);
 				break;//跳转到密码修改页面
		case 4: exitDialog(UserManageActivity.this);
 				break;//退出当前应用 		
		default:break;
		}
		
	}

	private void modfiyInfo()
	{
		SetCustomerReq req = new SetCustomerReq();
		req.setToken(MemoryCache.getToken());
		CustomerJson json = new CustomerJson();
		json.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		json.setCustomerName(MemoryCache.getLoginInfo().getUser().getUserName());
		json.setEmail(user_email.getText().toString());
		json.setPhone(user_phone.getText().toString());
		json.setAddr(user_addr.getText().toString());		
		req.setCustomer(json);
		proDialog =ProgressDialog.show(UserManageActivity.this, "请稍等", "正在保存数据……");
		WebServiceContext.getInstance().getUserManageRest(this).modifyCustomer(req);
		
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		
		if (message.isSuccess())
		{
			MemoryCache.getLoginInfo().getCustomer().setPhone(user_phone.getText().toString());
			MemoryCache.getLoginInfo().getCustomer().setEmail(user_email.getText().toString());
			MemoryCache.getLoginInfo().getCustomer().setAddr(user_addr.getText().toString());
			proDialog.dismiss();
			showToast(this,"用户信息保存成功！");
		}
		else
		{
			proDialog.dismiss();
			this.showMessage(message);
		}
		
	}

	
}
