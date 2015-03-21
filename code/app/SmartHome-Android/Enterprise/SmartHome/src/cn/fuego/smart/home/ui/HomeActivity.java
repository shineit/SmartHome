package cn.fuego.smart.home.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.about.AboutUsActivity;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.common.knowledge.CommonSenseActivity;
import cn.fuego.smart.home.ui.common.mall.ProductMallActivity;
import cn.fuego.smart.home.ui.enterprise.alarm.DeviceStatusActivity;
import cn.fuego.smart.home.ui.enterprise.alarm.FireAlarmActivity;
import cn.fuego.smart.home.ui.enterprise.check.CheckActivity;
import cn.fuego.smart.home.ui.enterprise.check.CheckLogActivity;
import cn.fuego.smart.home.ui.enterprise.company.CompanyListActivity;
import cn.fuego.smart.home.ui.enterprise.company.CompanyViewActivity;

public class HomeActivity extends BaseActivtiy implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	@Override
	public void initRes() 
	{
		this.activityRes.setAvtivityView(R.layout.activity_home);
		
		this.activityRes.getButtonIDList().add(R.id.home_menu_alarm);
		this.activityRes.getButtonIDList().add(R.id.home_menu_status);
		this.activityRes.getButtonIDList().add(R.id.home_menu_check);
		this.activityRes.getButtonIDList().add(R.id.home_menu_account);
		this.activityRes.getButtonIDList().add(R.id.home_menu_manage);
		this.activityRes.getButtonIDList().add(R.id.home_menu_knowledge);
		this.activityRes.getButtonIDList().add(R.id.home_menu_mall);
		this.activityRes.getButtonIDList().add(R.id.home_about_us_btn);
	} 
	public static void jump(Context context)
	{
 
 		Intent intent = new Intent();
  		intent.setClass(context, HomeActivity.class);
 		context.startActivity(intent);
  	}

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{

		case R.id.home_menu_alarm:	
			CompanyListActivity.jump(this, FireAlarmActivity.class);
			break;
		case R.id.home_menu_status:
			CompanyListActivity.jump(this, DeviceStatusActivity.class);
			break;
		case R.id.home_menu_check:	
			CompanyListActivity.jump(this, CheckActivity.class);
			break;			
		case R.id.home_menu_account: 
			CompanyListActivity.jump(this, CompanyViewActivity.class);
			break;
		case R.id.home_menu_manage:
			CompanyListActivity.jump(this, CheckLogActivity.class);
			break;
			
		case R.id.home_menu_knowledge:
			jumpActivity(CommonSenseActivity.class);
			break;
		case R.id.home_menu_mall: 
			jumpActivity(ProductMallActivity.class);
			break;
		case R.id.home_about_us_btn:
			jumpActivity(AboutUsActivity.class);
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

	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
            // 创建退出对话框  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            // 设置对话框标题  
            isExit.setTitle("系统提示");  
            // 设置对话框消息  
            isExit.setMessage("确定要退出吗");  
            // 添加选择按钮并注册监听  
            isExit.setButton("确定", listener);  
            isExit.setButton2("取消", listener);  
            // 显示对话框  
            isExit.show();  
  
        }  
          
        return false;  
          
    }  
    /**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
                finish();  
                //int nPid = android.os.Process.myPid();  
                //android.os.Process.killProcess(nPid);  
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
                break;  
            default:  
                break;  
            }  
        }  
    };


     
	
}
