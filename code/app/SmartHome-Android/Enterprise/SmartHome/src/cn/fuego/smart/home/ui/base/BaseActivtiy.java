package cn.fuego.smart.home.ui.base;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import cn.fuego.misp.service.http.HttpListener;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispHttpActivtiy;
import cn.fuego.smart.home.cache.AppCache;

public abstract class BaseActivtiy extends MispHttpActivtiy implements HttpListener
{

	private Context contextDialog ;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		//重载，禁止所有activity竖屏
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	public void showMessage(MispHttpMessage message)
	{
		super.showMessage(message.getErrorCode());	
	}
	public void exitDialog(Context context) 
	{ 
		contextDialog = context;
		
        AlertDialog.Builder builder = new Builder(contextDialog);   
        builder.setMessage("确定要退出吗?");   
        builder.setTitle("提示");   
        builder.setPositiveButton("确认",  new android.content.DialogInterface.OnClickListener() {   
            @Override   
            public void onClick(DialogInterface dialog, int which) {   
                dialog.dismiss();   
                //android.os.Process.killProcess(android.os.Process.myPid()); 

                AppCache.getInstance().clear();
                ExitApplication.getInstance().exit(contextDialog);
                
            }   
        });   
        builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {   
            @Override   
            public void onClick(DialogInterface dialog, int which) {   
                dialog.dismiss();   
            }   
        });   
        builder.create().show(); 
        ExitApplication.getInstance().addActivity(this);
    }

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}



}
