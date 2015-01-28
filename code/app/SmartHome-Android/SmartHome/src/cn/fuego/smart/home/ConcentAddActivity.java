package cn.fuego.smart.home;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.fuego.smart.home.ui.base.BaseActivtiy;

import com.example.smartlinklib.ModuleInfo;
import com.example.smartlinklib.SmartLinkManipulator;
import com.example.smartlinklib.SmartLinkManipulator.ConnectCallBack;

public class ConcentAddActivity extends BaseActivtiy implements OnClickListener
{

	private EditText txt_ssid,txt_pwd;
	private SmartLinkManipulator sm;
	private boolean isconncting = false;
	private Button config_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.concent_add);

		Button back_btn= (Button) findViewById(R.id.concent_add_back_btn);
		back_btn.setOnClickListener(this);
		config_btn =(Button) findViewById(R.id.concent_add_config_btn);
		config_btn.setOnClickListener(this);

		txt_ssid = (EditText) findViewById(R.id.concent_add_ssid);
		txt_ssid.setText(getSSid());
		
		txt_pwd = (EditText) findViewById(R.id.concent_add_pwd);
		sm = SmartLinkManipulator.getInstence(this);
		
	}

	
	
	Handler hand = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what) 
			{
			case 1:
				config_btn.setText("停止连接");
				break;
			case 2:
				config_btn.setText("开始连接");
				break;
			default:
				break;
			}
		};
	};
	/** callback**/
	ConnectCallBack callback = new ConnectCallBack()
	{
		
		@Override
		public void onConnectTimeOut()
		{
			// TODO Auto-generated method stub
			hand.post(new Runnable() 
			{
				
				@Override
				public void run() 
				{

					showToast(ConcentAddActivity.this, "连接超时");
					config_btn.setText("重新连接");
					isconncting = false;
				}
			});
		}
		
		@Override
		public void onConnect(final ModuleInfo mi) {
			// TODO Auto-generated method stub
			hand.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					showToast(ConcentAddActivity.this, "发现设备"+mi.getMac());
				}
			});
		}

		@Override
		public void onConnectOk() 
		{
			// TODO Auto-generated method stub
			hand.post(new Runnable() 
			{
				
				@Override
				public void run() 
				{
					// TODO Auto-generated method stub
					showToast(ConcentAddActivity.this, "配置完成");
					config_btn.setText("完成");
					isconncting = false;
				}
			});
		}
	};
	/** callback end**/
	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.concent_add_back_btn:
			this.finish();
			break;
		case R.id.concent_add_config_btn:
			connectWifi();
			break;
		default:break;
		}
		
	}

	private void connectWifi()
	{
		if(!isconncting)
		{
			isconncting = true;
			String ss = txt_ssid.getText().toString().trim();
			String ps = txt_pwd.getText().toString().trim();
			hand.sendEmptyMessage(1);
			sm.setConnection(ss, ps);
			sm.Startconnection(callback);
		}
		else
		{
			sm.StopConnection();
			hand.sendEmptyMessage(2);
			isconncting = false;
		}
		
	}
	
	private String getSSid()
	{
		WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
		if(wm != null)
		{
			WifiInfo wi = wm.getConnectionInfo();
			//showToast(ConcentAddActivity.this,wi.getSSID());
			if(wi != null)
			{
				String s = wi.getSSID();
				//不明白源代码作者想表达啥
/*				if(s.length()>2&&s.charAt(0) == '"'&&s.charAt(s.length() -1) == '"')
				{
					showToast(ConcentAddActivity.this,s.substring(0,s.length()-1));
					return s.substring(0,s.length()-1);
				}*/
				return s;
				
			}
		}
		return "";
	}


}
