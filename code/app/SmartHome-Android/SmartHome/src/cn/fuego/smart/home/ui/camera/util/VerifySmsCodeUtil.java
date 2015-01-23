/* 
 * @ProjectName ezviz-openapi-android-demo
 * @Copyright HangZhou Hikvision System Technology Co.,Ltd. All Right Reserved
 * 
 * @FileName VerifySmsCodeUtil.java
 * @Description 这里对文件进行描述
 * 
 * @author chenxingyf1
 * @data 2014-12-12
 * 
 * @note 这里写本文件的详细功能描述和注释
 * @note 历史记录
 * 
 * @warning 这里写本文件的相关警告
 */
package cn.fuego.smart.home.ui.camera.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.widget.WaitDialog;

import com.videogo.exception.BaseException;
import com.videogo.exception.ErrorCode;
import com.videogo.openapi.EzvizAPI;
import com.videogo.util.ConnectionDetector;
import com.videogo.util.Utils;

/**
 * 在此对类做相应的描述
 * @author chenxingyf1
 * @data 2014-12-12
 */
public class VerifySmsCodeUtil 
{
    
	public static void openSmsVerifyDialog(final int type, final Context context,String curPhone) 
    {
        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup smsVerifyView = (ViewGroup) mLayoutInflater.inflate(R.layout.token_verify, null, true);

        //以下为自定义样式
        final EditText phoneEt =(EditText) smsVerifyView.findViewById(R.id.token_verify_phone);
        if(curPhone!=null)
        {
        	 phoneEt.setText(curPhone);
        }

        final String userId= String.valueOf(MemoryCache.getLoginInfo().getUser().getUserID());
        final EditText smsCodeEt = (EditText) smsVerifyView.findViewById(R.id.token_verify_sms_code);
        final EditText userNameEt =  (EditText) smsVerifyView.findViewById(R.id.token_verify_account);
        userNameEt.setText(MemoryCache.getLoginInfo().getUser().getUserName());
        final Button getSmsBtn = (Button) smsVerifyView.findViewById(R.id.token_verify_code_btn);
        getSmsBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //String getSmsCodeSign = signEt.getText().toString();
            	String getSmsCodeSign=null;
                String phone = phoneEt.getText().toString();
                
               
                //String userId = userIdEt.getText().toString();
                //String userId="654321";//测试用
                if(TextUtils.isEmpty(getSmsCodeSign) && !TextUtils.isEmpty(phone)) {
                    getSmsCodeSign = SignUtil.getGetSmsCodeSign(phone,userId);
                }
                new GetSmsCodeTask(type, context).execute(getSmsCodeSign);
            }
            
        });

        new  AlertDialog.Builder(context)  
        .setTitle("安全验证")   
        .setIcon(android.R.drawable.ic_dialog_info)   
        .setView(smsVerifyView)
        .setPositiveButton(R.string.submit_sms_code, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                new VerifySmsCodeTask(type, context, userId,phoneEt.getText().toString(),smsCodeEt.getText().toString()).execute();
            }
            
        })   
        .setNegativeButton(R.string.cancel, null)
        .show();  
    }

    private static class GetSmsCodeTask extends AsyncTask<String, Void, Void> {
        private Context mContext;
        private int mType;
        private Dialog mWaitDialog;
        private int mErrorCode = 0;
        
        public GetSmsCodeTask(int type, Context context) {
            mType = type;
            mContext = context;
        }
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mWaitDialog = new WaitDialog(mContext, android.R.style.Theme_Translucent_NoTitleBar);
            mWaitDialog.setCancelable(false);
            mWaitDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            if (!ConnectionDetector.isNetworkAvailable(mContext)) {
                mErrorCode = ErrorCode.ERROR_WEB_NET_EXCEPTION;
                return null;
            }

            try {
                EzvizAPI.getInstance().getSmsCode(mType, params[0]);
                return null;

            } catch (BaseException e) {
                mErrorCode = e.getErrorCode();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mWaitDialog.dismiss();

            if (mErrorCode != 0)
                onError(mErrorCode);
        }

        protected void onError(int errorCode) {
            switch (errorCode) {
                case ErrorCode.ERROR_WEB_SESSION_ERROR:
                case ErrorCode.ERROR_WEB_SESSION_EXPIRE:
                case ErrorCode.ERROR_WEB_HARDWARE_SIGNATURE_ERROR:
                    EzvizAPI.getInstance().gotoLoginPage();
                    break;

                default:
                    Utils.showToast(mContext, R.string.get_sms_code_fail, mErrorCode);
                    break;
            }
        }
    }
    
    private static class VerifySmsCodeTask extends AsyncTask<Void, Void, Void> {
        private Context mContext;
        private int mType;
        private String mUserId;
        private String mSmsCode;
        private String mPhone;
        private Dialog mWaitDialog;
        private int mErrorCode = 0;
        
        public VerifySmsCodeTask(int type, Context context, String userId, String phone, String smsCode) {
            mType = type;
            mContext = context;
            mUserId = userId;
            mPhone = phone;
            mSmsCode = smsCode;
        }
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mWaitDialog = new WaitDialog(mContext, android.R.style.Theme_Translucent_NoTitleBar);
            mWaitDialog.setCancelable(false);
            mWaitDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            if (!ConnectionDetector.isNetworkAvailable(mContext)) {
                mErrorCode = ErrorCode.ERROR_WEB_NET_EXCEPTION;
                return null;
            }

            try {
                EzvizAPI.getInstance().verifySmsCode(mType, mUserId, mPhone, mSmsCode);
                return null;

            } catch (BaseException e) {
                mErrorCode = e.getErrorCode();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mWaitDialog.dismiss();
            
            if (mErrorCode != 0)
            onError(mErrorCode);
            else
            MemoryCache.setCachePhone(mPhone);
            
        }

        protected void onError(int errorCode) {
            switch (errorCode) {
                case ErrorCode.ERROR_WEB_SESSION_ERROR:
                case ErrorCode.ERROR_WEB_SESSION_EXPIRE:
                case ErrorCode.ERROR_WEB_HARDWARE_SIGNATURE_ERROR:
                    EzvizAPI.getInstance().gotoLoginPage();
                    break;

                default:
                    Utils.showToast(mContext, R.string.verify_sms_code_fail, mErrorCode);
                    break;
            }
        }
    }
}
