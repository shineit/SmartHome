package cn.fuego.smart.home.ui.common.about;

import java.io.Serializable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.ui.setting.model.ConfigInfo;
import cn.fuego.smart.home.ui.setting.upgrade.UpgradeActivity;
import cn.fuego.smart.home.webservice.up.model.GetClientVersionReq;
import cn.fuego.smart.home.webservice.up.model.GetClientVersionRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class AboutUsActivity extends MispBaseActivtiy implements OnCheckedChangeListener 
{

	private CheckBox alarm_chk;
	private ConfigInfo config;
	@Override
	public void initRes() 
	{
		this.activityRes.setAvtivityView(R.layout.about_us);
		this.activityRes.setName("关于我们");
		this.activityRes.getButtonIDList().add(R.id.about_us_update_btn);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		alarm_chk= (CheckBox) findViewById(R.id.setting_alarm_sound);
		
		config=AppCache.getInstance().getConfig();
		alarm_chk.setChecked(config.isSound());
		alarm_chk.setOnCheckedChangeListener(this);

	}

	@Override
	public void onClick(View v)
	{
        switch (v.getId()) 
        {
        case R.id.about_us_update_btn:
        	updateVersion();
            break;	
        default:
            break;
        }
		
	}
	private void updateVersion()
	{
		GetClientVersionReq req = new GetClientVersionReq();
		WebServiceContext.getInstance().getSystemManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetClientVersionRsp rsp = (GetClientVersionRsp) message.getMessage().obj;
					Intent intent = new Intent(AboutUsActivity.this,UpgradeActivity.class);
					intent.putExtra(UpgradeActivity.CLIENT_INFO, (Serializable) rsp.getObj());
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				}
				else
				{
					showMessage(message);
				}
				
			}
			
			
		}).getAppVersion(req);

	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		if(alarm_chk.isChecked())
		{

			config.setSound(true);
			showToast(this, "告警音开启成功！");

		}
		else
		{
			config.setSound(false);
			showToast(this, "告警音禁止成功！");
		}
		AppCache.getInstance().saveConfig(config);
		
	}




}
