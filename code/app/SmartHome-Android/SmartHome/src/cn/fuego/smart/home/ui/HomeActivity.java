package cn.fuego.smart.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.Customer;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.info.AlarmActivity;
import cn.fuego.smart.home.ui.info.NewsActivity;
import cn.fuego.smart.home.ui.setting.service.ServiceActivity;
import cn.fuego.smart.home.ui.setting.user.UserManageActivity;
import cn.fuego.smart.home.webservice.up.model.GetCustomerByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCustomerByIDRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class HomeActivity extends BaseActivtiy implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private Boolean isLoad=false;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ExitApplication.getInstance().addActivity(this);
		
		loadCustomerInfo();
		
		Button safe_btn= (Button) findViewById(R.id.home_menu_safe);
		safe_btn.setTag(1);
		safe_btn.setOnClickListener(this);
		Button ctr_btn= (Button) findViewById(R.id.home_menu_control);
		ctr_btn.setTag(2);
		ctr_btn.setOnClickListener(this);	
		Button camera_btn= (Button) findViewById(R.id.home_menu_camera);
		camera_btn.setTag(3);
		camera_btn.setOnClickListener(this);		
		Button alarm_btn= (Button) findViewById(R.id.home_menu_alarm);
		alarm_btn.setTag(4);
		alarm_btn.setOnClickListener(this);		
		Button plane_btn= (Button) findViewById(R.id.home_menu_plane);
		plane_btn.setTag(5);
		plane_btn.setOnClickListener(this);
		
		Button concent_btn= (Button) findViewById(R.id.home_menu_concent);
		concent_btn.setTag(6);
		concent_btn.setOnClickListener(this);		
		Button area_btn= (Button) findViewById(R.id.home_menu_area);
		area_btn.setTag(7);
		area_btn.setOnClickListener(this);
		
		Button account_btn= (Button) findViewById(R.id.home_menu_account);
		account_btn.setTag(8);
		account_btn.setOnClickListener(this);
		Button apply_btn= (Button) findViewById(R.id.home_menu_apply);
		apply_btn.setTag(9);
		apply_btn.setOnClickListener(this);
		Button news_btn= (Button) findViewById(R.id.home_menu_news);
		news_btn.setTag(10);
		news_btn.setOnClickListener(this);
		
	}

	//用户信息加载
	@Override
	public void handle(MispHttpMessage message)
	{
		
		if (message.isSuccess())
		{
			GetCustomerByIDRsp rsp = (GetCustomerByIDRsp) message.getMessage().obj;
			log.info("GetCustomerByIDRsp is "+rsp);
			Customer customer= new Customer();
			customer.setCustomerName(rsp.getCustomer().getCustomerName());
			customer.setPhone(rsp.getCustomer().getPhone());
			customer.setEmail(rsp.getCustomer().getEmail());
			customer.setAddr(rsp.getCustomer().getAddr());
			
			MemoryCache.getLoginInfo().setCustomer(customer);
			
		}
		else
		{
			this.showMessage(message);
		}
		
		isLoad=true;
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		switch(tag)
		{
		case 1: jumpTab(1);
				break;
		case 2: jumpTab(2);
				break;
		case 3: jumpTab(3);
				break;
		case 4:	jumpActivity(AlarmActivity.class);
				break;
		case 5: showDisable();
				break;
		case 6: showDisable();
				break;
		case 7: showDisable();
				break;
		case 8: 
			if(isLoad)
			{
				jumpActivity(UserManageActivity.class);
			}
			else
			{
				loadCustomerInfo();
			}
			
				break;
		case 9: jumpActivity(ServiceActivity.class);
				break;
		case 10:jumpActivity(NewsActivity.class);
				break;
		default:break;
		}
		
	}

	private void jumpActivity(Class jumpActivityClass)
	{
		Intent i= new Intent();
		i.setClass(HomeActivity.this, jumpActivityClass);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        this.startActivity(i);
		//this.finish();
		
	}


	private void showDisable()
	{
		Toast.makeText(HomeActivity.this, "功能还在开发中……", Toast.LENGTH_LONG).show();
		
	}



	private void jumpTab(int index)
	{
		Intent i= new Intent();
		i.setClass(HomeActivity.this, MainTabbarActivity.class);
		i.putExtra("num", index);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        this.startActivity(i);
		this.finish();
		
	}
	private void loadCustomerInfo()
	{
		GetCustomerByIDReq req =new GetCustomerByIDReq();
		req.setToken(MemoryCache.getToken());
		req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		WebServiceContext.getInstance().getUserManageRest(this).getCustomer(req);
	}
	
	
	//Android按返回键退出程序但不销毁，程序后台运行
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{

		if (keyCode == KeyEvent.KEYCODE_BACK) 
		{
			moveTaskToBack(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	} 
     
	
}
