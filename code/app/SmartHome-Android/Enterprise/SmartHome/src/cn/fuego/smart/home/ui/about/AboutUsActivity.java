package cn.fuego.smart.home.ui.about;

import java.io.Serializable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.setting.upgrade.UpgradeActivity;
import cn.fuego.smart.home.webservice.up.model.GetClientVersionReq;
import cn.fuego.smart.home.webservice.up.model.GetClientVersionRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class AboutUsActivity extends BaseActivtiy implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);
		inidView();
	}
	
	private void inidView()
	{
		Button back_btn = (Button) findViewById(R.id.about_us_back_btn);
		back_btn.setOnClickListener(this);
		Button update_btn = (Button) findViewById(R.id.about_us_update_btn);
		update_btn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v)
	{
        switch (v.getId()) {

        case R.id.about_us_back_btn:
            this.finish();
            break;
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
	public void initRes() {
		// TODO Auto-generated method stub
		
	}


}
