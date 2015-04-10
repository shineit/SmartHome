package cn.fuego.smart.home.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import cn.fuego.smart.enterprise.R;

public class AlarmSoundService extends Service {
	
	
	public static final String TAG = "MyService";
	MediaPlayer player;
	private MyBinder myBinder=new MyBinder();
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "My Service created", Toast.LENGTH_LONG).show();
		Log.i(TAG, "onCreate");
		player = MediaPlayer.create(this, R.raw.firealarm);
		player.setLooping(true);
		super.onCreate();
	}
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "MyService onUnbind",Toast.LENGTH_LONG).show();
		Log.d("MyService","onUnbind()");
		return super.onUnbind(intent);
	}
	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "MyService onRebind", Toast.LENGTH_LONG ).show();
		Log.d("MyService","onRebind()");
		super.onRebind(intent);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "My Service Stoped", Toast.LENGTH_LONG).show();
		Log.i(TAG, "onDestroy");
		player.stop();
		player.release();
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "My Service Start", Toast.LENGTH_LONG).show();
		Log.i(TAG, "onStart");
		player.start();
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "MyService onBind", Toast.LENGTH_LONG).show();
		Log.d("MyService","onBind()");
		return myBinder;
	}
	public class MyBinder extends Binder{
		AlarmSoundService getService(){
			return AlarmSoundService.this;
		}
	}
}
