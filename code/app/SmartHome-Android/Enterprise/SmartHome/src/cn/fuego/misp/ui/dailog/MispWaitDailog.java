package cn.fuego.misp.ui.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.fuego.smart.enterprise.R;

public class MispWaitDailog extends Dialog
{
	Context context;

	public MispWaitDailog(Context context, int theme)
	{
		super(context, theme);
		this.context = context;
	 
	}

	public MispWaitDailog(Context context)
	{
		super(context,R.style.MispWaitDailog);
		this.context = context;
	 
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.misp_wait_dialog);
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// 按下键盘上返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			dismiss();
		}
		return true;
	}
	public Dialog LoadingDialog(Context context, String msg) 
	 {  
		  
	   LayoutInflater inflater = LayoutInflater.from(context);  
      View v = inflater.inflate(R.layout.misp_loading_dialog, null);// 得到加载view  
      LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局  
      // main.xml中的ImageView  
      ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);  
      TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字  
      // 加载动画  
      Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
              context, R.anim.loading_animation);  
      // 使用ImageView显示动画  
      spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
      tipTextView.setText(msg);// 设置加载信息  

      Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog  
      //loadingDialog.setCancelable(false);//点击返回不可取消  
      loadingDialog.setCanceledOnTouchOutside(false);//点击外面不可取消  
	   loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(  
	                LinearLayout.LayoutParams.WRAP_CONTENT,  
	                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局  
	   
	    return loadingDialog;  
	 }

}
