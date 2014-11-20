package cn.fuego.smart.home.ui;

import com.fuego.smarthome.R;
import com.fuego.smarthome.R.id;
import com.fuego.smarthome.R.layout;
import com.fuego.smarthome.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity
{
    private Button loginBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		loginBtn = (Button)findViewById(R.id.loginbtn);
		loginBtn.setOnClickListener(loginClick);
	}
	private OnClickListener loginClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.v("sigin", "Login");
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, MainTabbarActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
}
