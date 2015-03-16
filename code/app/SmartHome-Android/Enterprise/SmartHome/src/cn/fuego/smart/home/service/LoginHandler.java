/**   
* @Title: LoginHandler.java 
* @Package cn.fuego.smart.home.ui.base 
* @Description: TODO
* @author Aether
* @date 2015-1-26 上午9:29:49 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.Set;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.constant.ClientTypeEnum;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.ui.HomeActivity;
import cn.fuego.smart.home.ui.LoginActivity;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.SharedPreUtil;
import cn.fuego.smart.home.ui.base.UserEntity;
import cn.fuego.smart.home.ui.info.AlarmPageActivity;
import cn.fuego.smart.home.ui.info.NewsPageActivity;
import cn.fuego.smart.home.webservice.up.model.LoginReq;
import cn.fuego.smart.home.webservice.up.model.LoginRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/** 
 * @ClassName: LoginHandler 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-26 上午9:29:49 
 *  
 */
public class LoginHandler extends BaseActivtiy
{
	private FuegoLog log = FuegoLog.getLog(LoginHandler.class);
	private Context mContext;
	private String spName,spPwd;
	private Boolean spHas;
	private ProgressDialog proDialog;
    public LoginHandler(Context context)
    {
    	this.mContext=context;
    }

	public void  checkLogin(String userName,String pwd,Boolean hasData)
	{
		if(!hasData)
		{
			proDialog =ProgressDialog.show(mContext, "请稍等", "正在加载数据……");
		}	
		this.spName=userName;
		this.spPwd=pwd;
		this.spHas=hasData;
		
		LoginReq req = new LoginReq();
		req.setPassword(MD5(pwd));
		req.setUserName(userName);
		req.setClientType(ClientTypeEnum.ANDRIOD_CLIENT.getIntValue());
		req.setClientVersion(MemoryCache.getVersion());
		req.setDevToken(getDeviceID());
		
		req.setPush_userID(req.getUserName());
		
		JPushInterface.setAliasAndTags(mContext, userName, null, mAliasCallback);
	

	    
		WebServiceContext.getInstance().getUserManageRest(this).login(req);
	}
	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
			MemoryCache.setLogin(true);
			
			LoginRsp rsp = (LoginRsp) message.getMessage().obj;
			SystemUser user = new  SystemUser();
			user.setRole(rsp.getUser().getRole());
			user.setUserID(rsp.getUser().getUserID());
			user.setUserName(rsp.getUser().getUserName());
			
            MemoryCache.getLoginInfo().setUser(user);
            MemoryCache.setToken(rsp.getToken());
            MemoryCache.getLoginInfo().setCustomer(rsp.getCustomer());
            //本地缓存策略,如果spHas=true,那么说明本地保存有数据
            if(!spHas)
            {
                UserEntity userInfo = new UserEntity();
                userInfo.setUserName(spName);
                userInfo.setPassword(spPwd);
                SharedPreUtil.getInstance().putUser(userInfo);
                proDialog.dismiss();
            }
            if(MemoryCache.getEnterFlag()==IntentCodeConst.FIRE_ALARM_ENTER)
            {
            	jumpIntent(AlarmPageActivity.class);
            }
            else if(MemoryCache.getEnterFlag()==IntentCodeConst.NEWS_ENTER)
            {
            	jumpIntent(NewsPageActivity.class);
            }
            else
            {
            	jumpIntent(HomeActivity.class);
            }
            
            
		}
		else
		{
			if(spHas)
			{
				//自动登录失效，跳转到登录页面
				jumpIntent(LoginActivity.class);
				MemoryCache.setFlag(1);//非首次进入
			}
			else
			{
				proDialog.dismiss();
			    showToast(mContext, message);
			}
			
			
		}
		

	}
	private void jumpIntent(Class clazz)
	{
		Intent toIntent = new Intent();
		toIntent.setClass(mContext, clazz);
		mContext.startActivity(toIntent);
		//this.finish();
	}
	private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
 
            switch (code) {
            case 0:
                log.info("Set tag and alias success");
                log.info("the user is " + MemoryCache.getLoginInfo().getUser());
                break;
                
            case 6002:
                 log.error("Failed to set alias and tags due to timeout. Try again after 60s.");
                 log.error("the user is " + MemoryCache.getLoginInfo().getUser());
                break;
            
            default:
      
                log.error("Failed with errorCode" + code);
                log.error("the user is " + MemoryCache.getLoginInfo().getUser());
            }
           
        }
	    
	};
	public String getDeviceID()
	{
		TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
		 return tm.getDeviceId();
	}

	@Override
	public void initRes() {
		// TODO Auto-generated method stub
		
	}

	
}
