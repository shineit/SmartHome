package cn.fuego.smart.home.ui;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.webservice.up.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsListRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;
import cn.fuego.smart.home.webservice.up.rest.interceptor.AuthInterceptor;

import com.fuego.smarthome.R;

public class MainActivity extends Activity
{
	private FuegoLog log = FuegoLog.getLog(AuthInterceptor.class);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		new Thread(){
			@Override
			public void run(){
			//你要执行的方法
			//执行完毕后给handler发送一个空消息
				
				GetSensorListRsp str = null;
				try
				{
					GetNewsListReq req = new GetNewsListReq();
					req.setToken(MemoryCache.getToken());
					GetNewsListRsp rsp = WebServiceContext.getInstance().getNewsManageRest(null).getNewsList(req);
					log.info("rsp is " + rsp);
				}
				catch(Exception e)
				{
					log.info("",e);
					log.info(e.getMessage());
				}
				log.info(str.toString());
			}
			}.start();

		
	
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		
		InputStream is = cl.getSystemResourceAsStream("META-INF/services/javax.ws.rs.ext.RuntimeDelegate");
		 String javah = System.getProperty("java.home");
		 BufferedReader rd;
		 String factoryClassName = null;
		try
		{
			rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			factoryClassName = rd.readLine();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("factoryClassName");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.news_item)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void myClickButton2(View v)
	{  
 		Log.e(this.getClass().toString(), "aaaa");
    }
	
	public void loginClick(View v)
	{  
 		Log.e(this.getClass().toString(), "aaaa");
    }
}
