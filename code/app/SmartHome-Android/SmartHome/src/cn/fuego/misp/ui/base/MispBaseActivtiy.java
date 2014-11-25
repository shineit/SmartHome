package cn.fuego.misp.ui.base;

import cn.fuego.misp.constant.MISPErrorMessageConst;
import android.app.Activity;
import android.widget.Toast;

public class MispBaseActivtiy extends Activity
{

	public void showMessage(int errorCode)
	{
		showMessage(MISPErrorMessageConst.getMessageByErrorCode(errorCode));
	}
	
	public void showMessage(String message)
	{
		Toast toast;
		toast = Toast.makeText(getApplicationContext(), message , Toast.LENGTH_LONG);
		toast.show();
	}
}
