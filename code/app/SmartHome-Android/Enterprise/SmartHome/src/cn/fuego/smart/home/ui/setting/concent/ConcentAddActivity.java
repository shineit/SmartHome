package cn.fuego.smart.home.ui.setting.concent;

import android.app.ProgressDialog;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.smart.home.R;

import com.example.smartlinklib.ModuleInfo;
import com.example.smartlinklib.SmartLinkManipulator;
import com.example.smartlinklib.SmartLinkManipulator.ConnectCallBack;

public class ConcentAddActivity extends MispBaseActivtiy implements OnClickListener
{

	Button config_btn;
	EditText pswd,ssid;
	SmartLinkManipulator sm;
	boolean isconncting = false;
	boolean isfinished = false;
	String viewSSID;
	ProgressDialog proDialog;
	Handler hand = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				config_btn.setText("停止链接");
				break;
			case 2:
				config_btn.setText("开始链接");
				break;
			default:
				break;
			}
		};
	};
	
	ConnectCallBack callback = new ConnectCallBack() {
		
		@Override
		public void onConnectTimeOut() {
			// TODO Auto-generated method stub
			hand.post(new Runnable() {
				
				@Override
				public void run() {

					proDialog.dismiss();
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

					showToast(ConcentAddActivity.this, "发现设备"+mi.getMac());
				}
			});
		}

		@Override
		public void onConnectOk() {
			// TODO Auto-generated method stub
			hand.post(new Runnable() {
				
				@Override
				public void run() {

					proDialog.dismiss();
					showToast(ConcentAddActivity.this, "配置完成");
					config_btn.setText("完成");
					isconncting = false;
					isfinished=true;
				}
			});
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.concent_add);
		
		Button back_btn= (Button) findViewById(R.id.concent_add_back_btn);
		back_btn.setOnClickListener(this);
		config_btn =(Button) findViewById(R.id.concent_add_config_btn);
		config_btn.setOnClickListener(this);

		ssid = (EditText) findViewById(R.id.concent_add_ssid);
		ssid.setText(getRealSSID());
		
		pswd = (EditText) findViewById(R.id.concent_add_pwd);
		sm = SmartLinkManipulator.getInstence(this);
		
		
		
		config_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				proDialog =ProgressDialog.show(ConcentAddActivity.this, "请稍等", "正在连接WIFI……");
				if(!isfinished)
				{
					if(!isconncting)
					{
						isconncting = true;
						//String ss = ssid.getText().toString().trim();
						String ss=getSSid();
						String ps = pswd.getText().toString().trim();
						hand.sendEmptyMessage(1);
						sm.setConnection(ss, ps);
						sm.Startconnection(callback);
					}else{
						sm.StopConnection();
						hand.sendEmptyMessage(2);
						isconncting = false;
					}
					
				}
				else
				{
					ConcentAddActivity.this.finish();
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private String getSSid(){
		WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
		if(wm != null){
			WifiInfo wi = wm.getConnectionInfo();
			if(wi != null){
				String s = wi.getSSID();
				if(s.length()>2&&s.charAt(0) == '"'&&s.charAt(s.length() -1) == '"'){
					return s.substring(0,s.length()-1);
				}
				
			}
		}
		return "";
	}

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.concent_add_back_btn:
			this.finish();
			break;
/*		case R.id.concent_add_config_btn:
			//connectWifi();
			break;*/
		default:break;
		}
		
	}

	private String getRealSSID()
	{
		String realSSID="";
		WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
		if(wm != null)
		{
			WifiInfo wi = wm.getConnectionInfo();
			if(wi != null)
			{
				if(wi.getSSID()!=null)
				{
					realSSID=wi.getSSID();
				}
				else
				{
					showToast(ConcentAddActivity.this, "移动设备尚未连接WIFI");
				}

				
			}
		}
		return realSSID;
		
	}

	@Override
	public void initRes() {
		// TODO Auto-generated method stub
		
	}


}
