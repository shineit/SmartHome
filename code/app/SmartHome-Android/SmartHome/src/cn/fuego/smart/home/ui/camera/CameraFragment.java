package cn.fuego.smart.home.ui.camera;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.base.BaseFragment;
import cn.fuego.smart.home.ui.camera.cameralist.CameraListActivity;
import cn.fuego.smart.home.ui.camera.util.VerifySmsCodeUtil;
import cn.fuego.smart.home.webservice.up.model.GetCaTokenByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCaTokenByIDRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

import com.videogo.androidpn.AndroidpnUtils;
import com.videogo.constant.Constant;
import com.videogo.openapi.EzvizAPI;





public class CameraFragment extends BaseFragment implements OnClickListener
{

	private FuegoLog log = FuegoLog.getLog(getClass());
	
	private View rootView;
	private Context mContext;
    
    private EzvizAPI mEzvizAPI = null;
    private ProgressDialog proDialog=null;
    private String curPhone=null;//临时存储，后台验证成功后存到内存
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{

		rootView= inflater.inflate(R.layout.camera_fragment, null);
		mContext= this.getActivity();
		
        initData();
        Button test_btn= (Button) rootView.findViewById(R.id.camera_fragment_login_btn);		
		test_btn.setOnClickListener(this);
		this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	
		return rootView;
	}

	private void initData()
	{
		mEzvizAPI = EzvizAPI.getInstance();


	}

	@Override
	public void handle(MispHttpMessage message)
	{
		
		if (message.isSuccess())
		{
			MemoryCache.setCachePhone(curPhone);
			GetCaTokenByIDRsp rsp = (GetCaTokenByIDRsp) message.getMessage().obj;
			log.info("GetCaTokenByIDRsp is:"+rsp);
			String caToken = rsp.getCaToken().getAccessToken();
			log.info("授权登陆caToken:"+caToken);
            mEzvizAPI.setAccessToken(caToken);
            Intent toIntent = new Intent(mContext, CameraListActivity.class);
            toIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(toIntent);
            AndroidpnUtils.startPushServer(mContext);
            proDialog.dismiss();

		}
		else if(message.getErrorCode()==ErrorMessageConst.CAMERA_LINK_ERROR||message.getErrorCode()==ErrorMessageConst.CAMERA_ACCOUNT_NOT_BUNDLE)
		{
			proDialog.dismiss();
			VerifySmsCodeUtil.openSmsVerifyDialog(Constant.SMS_VERIFY_LOGIN, mContext,curPhone);
			
		}
		else
		{
			this.showMessage(message);
			proDialog.dismiss();
		}
		
	}

	@Override
	public void initRes()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v)
	{
		log.info("授权登录");
		String cachePhone=MemoryCache.getCachePhone();
		if(cachePhone!=null)
		{
			showCameraList(cachePhone);
		}
		else
		{
			inputPhone();//进入app 首次验证，需要手动输入电话号码
		}

	}

	private void showCameraList(String phone)
	{
		proDialog = ProgressDialog.show(mContext, "请稍等", "正在建立安全连接……");
		GetCaTokenByIDReq req = new GetCaTokenByIDReq();
		req.setToken(MemoryCache.getToken());
		req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		req.setPhone(phone);
		WebServiceContext.getInstance().getUserManageRest(this).getCaToken(req);
		//req.setUserID(654321);
		//req.setPhone("18620783584");

	}

	private void inputPhone()
	{
		final EditText txt_phone= new EditText(mContext);
		txt_phone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		if(!ValidatorUtil.isEmpty(MemoryCache.getLoginInfo().getCustomer().getPhone()))
		{
			txt_phone.setText(MemoryCache.getLoginInfo().getCustomer().getPhone());
		}	
		//txt_phone.setText(cachePhone);
		AlertDialog.Builder dialog= new AlertDialog.Builder(mContext);
		dialog.setTitle("您将使用以下手机号码进行安全绑定");
		dialog.setView(txt_phone);
		txt_phone.requestFocus();
		txt_phone.requestFocusFromTouch();
		dialog.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				String inputPhone= txt_phone.getText().toString().trim();
				if(inputPhone!=null)
				{
					if(ValidatorUtil.isPhone(inputPhone))
					{
						
						curPhone=inputPhone;
						dialog.dismiss();
						showCameraList(inputPhone);
						 		
					}
					else
					{
						Toast.makeText(mContext, "输入电话格式有误", Toast.LENGTH_LONG).show();
					}
				}
				else
				{
					Toast.makeText(mContext, "输入不能为空", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		dialog.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();  
				
			}
		} );
		dialog.create().show();
	}


}
