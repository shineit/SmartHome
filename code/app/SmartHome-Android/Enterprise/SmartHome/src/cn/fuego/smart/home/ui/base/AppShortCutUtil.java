/**   
* @Title: AppShortCutUtil.java 
* @Package cn.fuego.smart.home.ui.base 
* @Description: TODO
* @author Aether
* @date 2015-2-9 下午11:14:46 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.base;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.widget.Toast;
import cn.fuego.smart.home.ui.MainActivity;

/** 
 * @ClassName: AppShortCutUtil 
 * @Description: TODO
 * @author Aether
 * @date 2015-2-9 下午11:14:46 
 *  
 */
public class AppShortCutUtil extends Activity
{
	private final static String lancherActivityClassName = MainActivity.class.getName();

	private static Context mContext;

    public AppShortCutUtil(Context context)
    {
    	this.mContext=context;
    }
	public void addBage()
	{

		//临时调整，待修改
		sendBadgeNumber(String.valueOf("!"));
		
	}
	   public void sendBadgeNumber(String number) 
	   {
	        
	       if (TextUtils.isEmpty(number)||number.equals("0")) 
	       {
	           number = "";
	       } else {
	           int numInt = Integer.valueOf(number);
	           number = String.valueOf(Math.max(0, Math.min(numInt, 99)));
	       }

	       if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) 
	       {
	           sendToXiaoMi(number);
	       }
	       else if (Build.MANUFACTURER.equalsIgnoreCase("samsung"))
	       {
	           sendToSony(number);
	       } 
	       else if (Build.MANUFACTURER.toLowerCase().contains("sony"))
	       {
	           sendToSamsumg(number);
	       }
	       else 
	       {
	           Toast.makeText(mContext, "Not Support", Toast.LENGTH_LONG).show();
	       }
	   }

	   private  void sendToXiaoMi(String number) {
	       try {
	           Class miuiNotificationClass = Class.forName("android.app.MiuiNotification");
	           Object miuiNotification = miuiNotificationClass.newInstance();
	           Field field = miuiNotification.getClass().getDeclaredField("messageCount");
	           field.setAccessible(true);
	           field.set(miuiNotification, number);// 设置信息数-->这种发送必须是miui 6才行
	       } catch (Exception e) {
	           e.printStackTrace();
	           //miui 6之前的版本
	               Intent localIntent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
	               localIntent.putExtra("android.intent.extra.update_application_component_name",mContext.getPackageName() + "/"+ lancherActivityClassName );
	               localIntent.putExtra("android.intent.extra.update_application_message_text",number);
	               mContext.sendBroadcast(localIntent);
	       }

	   }

	   private  void sendToSony(String number) {
	       boolean isShow = true;
	       if ("0".equals(number)) {
	           isShow = false;
	       }
	       Intent localIntent = new Intent();
	       localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE",isShow);//是否显示
	       localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
	       localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME",lancherActivityClassName );//启动页
	       localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", number);//数字
	       localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME",mContext.getPackageName());//包名
	       mContext.sendBroadcast(localIntent);

	       Toast.makeText(mContext, "Sony," + "isSendOk", Toast.LENGTH_LONG).show();
	   }

	   private  void sendToSamsumg(String number) 
	   {
	       Intent localIntent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
	       localIntent.putExtra("badge_count", number);//数字
	       localIntent.putExtra("badge_count_package_name", mContext.getPackageName());//包名
	       localIntent.putExtra("badge_count_class_name",lancherActivityClassName ); //启动页
	       mContext.sendBroadcast(localIntent);
	       Toast.makeText(mContext, "Samsumg," + "isSendOk", Toast.LENGTH_LONG).show();
	   }



}
