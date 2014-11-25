package cn.fuego.smart.home.ui.base;

import java.security.MessageDigest;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;

public class BaseActivtiy extends MispBaseActivtiy
{

	private Context contextDialog ;
	
	public void showMessage(String message)
	{
		super.showMessage(message);
	}
	public void showMessage(int errorCode)
	{
		super.showMessage(errorCode);
	}
	public void showMessage(BaseJsonRsp rsp)
	{
		if(null != rsp)
		{
			if(null != rsp.getResult())
			{
				super.showMessage(rsp.getResult().getErrorCode());
				return ;
			}
		}
		super.showMessage(ErrorMessageConst.NET_FAIL);
		
	}
	public void exitDialog(Context context) { 
		contextDialog = context;
        AlertDialog.Builder builder = new Builder(contextDialog);   
        builder.setMessage("确定要退出吗?");   
        builder.setTitle("提示");   
        builder.setPositiveButton("确认",  new android.content.DialogInterface.OnClickListener() {   
            @Override   
            public void onClick(DialogInterface dialog, int which) {   
                dialog.dismiss();   
                //android.os.Process.killProcess(android.os.Process.myPid());   
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
	
	// MD5加密，32位
    public  String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
 
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
 
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
 
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
    public String getTrimText(EditText text)
    {
		String txt =text.getText().toString().trim();
		
    	return txt;
    	
    }


}
