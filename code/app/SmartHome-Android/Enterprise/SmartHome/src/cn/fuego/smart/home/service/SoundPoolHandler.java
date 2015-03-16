/**   
* @Title: SoundPoolHandler.java 
* @Package cn.fuego.smart.home.ui.base 
* @Description: TODO
* @author Aether
* @date 2015-1-31 上午11:34:21 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.HashMap;

import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.BaseActivtiy;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

/** 
 * @ClassName: SoundPoolHandler 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-31 上午11:34:21 
 *  
 */
public class SoundPoolHandler extends BaseActivtiy
{

	private static SoundPool sp= null;
	private static HashMap <Integer,Integer> hm;
	private static int currentStreamId;

	private static Boolean isFinishedLoad=false;
	//private Boolean isPausePlay=false;
	
	private static Context mContext;
	
    public SoundPoolHandler(Context context)
    {
    	this.mContext=context;
    }
	public void initSoundPool()
	{
		
		sp=new SoundPool(2,AudioManager.STREAM_MUSIC,0);
 
		sp.setOnLoadCompleteListener(new OnLoadCompleteListener() 
		{
			
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) 
			{
				
				isFinishedLoad=true;
			}
		});
		hm=new HashMap<Integer,Integer>();
		hm.put(1, sp.load(mContext, R.raw.alarm1, 0));
		hm.put(2, sp.load(mContext, R.raw.beep, 0));
		//System.out.println("-initSoundPool-");
	}
	

	public static void playSound(int sound,int loop)
	{

		if(!MemoryCache.isPausePlay())
		{
			AudioManager am = (AudioManager) mContext.getSystemService(AUDIO_SERVICE);
			float currentStreamVolume=am.getStreamVolume(AudioManager.STREAM_MUSIC);
			float maxStreamVolume=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			float setVolume=(float)currentStreamVolume/maxStreamVolume;
			if(isFinishedLoad)
				currentStreamId=sp.play(hm.get(sound), setVolume, setVolume, 1, loop, 1.0f);

		}
		else
		{
			//isPausePlay=false;
			MemoryCache.setPausePlay(false);
			sp.resume(currentStreamId);
		}
	}
	
	public static void pauseSound()
	{
		sp.pause(currentStreamId);
		MemoryCache.setPausePlay(true);
	}
	
	public static void stopSound()
	{
		sp.stop(currentStreamId);
		MemoryCache.setPausePlay(false);
	}

	@Override
	protected void onDestroy() 
	{
		
		super.onDestroy();
		sp.unload(currentStreamId);
		sp.release();
	}
	@Override
	public void initRes() {
		// TODO Auto-generated method stub
		
	}
}
