package cn.fuego.smart.home.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.common.MispWebViewActivity;
import cn.fuego.misp.ui.model.ImageDisplayInfo;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.service.BageNumDataCache;
import cn.fuego.smart.home.ui.base.AppShortCutUtil;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.common.about.AboutUsActivity;
import cn.fuego.smart.home.ui.common.knowledge.CommonSenseActivity;
import cn.fuego.smart.home.ui.common.mall.ProductMallActivity;
import cn.fuego.smart.home.ui.enterprise.alarm.DeviceStatusActivity;
import cn.fuego.smart.home.ui.enterprise.alarm.FireAlarmActivity;
import cn.fuego.smart.home.ui.enterprise.check.CheckActivity;
import cn.fuego.smart.home.ui.enterprise.check.CheckLogActivity;
import cn.fuego.smart.home.ui.enterprise.company.CompanyListActivity;
import cn.fuego.smart.home.ui.enterprise.company.CompanyViewActivity;
import cn.fuego.smart.home.webservice.up.model.GetCustomerByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCustomerByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.BageNumJson;
import cn.fuego.smart.home.webservice.up.model.base.CustomerJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCheckLogNumByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCheckLogNumByIDRsp;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireAlarmNumByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireAlarmNumByIDRsp;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireStatusNumByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireStatusNumByIDRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

import com.readystatesoftware.viewbadger.BadgeView;

public class HomeActivity extends BaseActivtiy implements OnClickListener
{
	//private FuegoLog log = FuegoLog.getLog(getClass());

	private BadgeView alarmBageView,statusBageView,checkBageView;
	private Button alarm_btn,status_btn,check_log_btn;
	
	private List<BageNumJson> alarmNumList=new ArrayList<BageNumJson>();
	private List<BageNumJson> statusNumList=new ArrayList<BageNumJson>();
	private List<BageNumJson> checkNumList=new ArrayList<BageNumJson>();
	
	private int backRunFlag=0;
	
	private final String product_auth_baseURL="http://114.112.48.163/lableFind.jsp?isFirst=isNotFirst&start=0&curPageNum=null&queryValue=1&recordFactoryId=&lableCode=";
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
		this.activityRes.getButtonIDList().add(R.id.home_menu_productauth);
	} 
	public static void jump(Context context)
	{
 
 		Intent intent = new Intent();
  		intent.setClass(context, HomeActivity.class);
 		context.startActivity(intent);
  	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
    	alarm_btn = (Button) findViewById(R.id.home_menu_alarm);
    	status_btn = (Button) findViewById(R.id.home_menu_status);
    	check_log_btn = (Button) findViewById(R.id.home_menu_manage);
    	
		alarmBageView = new BadgeView(HomeActivity.this, alarm_btn);
		regBageView(alarmBageView);
   	
    	statusBageView = new BadgeView(HomeActivity.this, status_btn);
    	regBageView(statusBageView);
    	//showAlarmBadge();
    	showFireAlarmBage();
    	showFireStatusBage();
    	checkBageView = new BadgeView(HomeActivity.this, check_log_btn);
    	regBageView(checkBageView);
    	showCheckBage();
/*        //注册广播，接收service中启动的线程发送过来的信息，同时更新UI  
        IntentFilter filter = new IntentFilter("android.intent.action.bageNotify");  
        this.registerReceiver(new HomeReceiver(), filter);  */
        
        updateCustomer();
        
        //注册广播  ,监听home键
        registerReceiver(mHomeKeyEventReceiver, new IntentFilter(  
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS)); 

        //AppShortCutUtil.getInstance(this).createShortCut();
	}
	
	@Override
	protected void onRestart()
	{
		
		super.onRestart();
		//程序后台运行
		if(backRunFlag!=0)
		{
			//this.showToast(this, "restart!");
	    	showFireAlarmBage();
	    	showFireStatusBage();
	    	showCheckBage();
	    	backRunFlag=0;
	    	
	    	AppCache.getInstance().setAppBageNum(0);
	    	AppShortCutUtil.getInstance(this).sendBadgeNumber("0");
		}
		//刷新首页数字提醒
		switch (AppCache.getInstance().getHomeRefreshFlag())
		{
		case 1:showFireAlarmBage();
			break;
		case 2:showFireStatusBage();
			break;
		case 3:showCheckBage();
			break;
		default :break;	
		}
		AppCache.getInstance().setHomeRefreshFlag(0);

	}

	/** 
     * 监听是否点击了home键将客户端推到后台 
     */  
    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {  
        String SYSTEM_REASON = "reason";  
        String SYSTEM_HOME_KEY = "homekey";  
        String SYSTEM_HOME_KEY_LONG = "recentapps";  
           
        @Override  
        public void onReceive(Context context, Intent intent) {  
            String action = intent.getAction();  
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {  
                String reason = intent.getStringExtra(SYSTEM_REASON);  
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {  
                     //表示按了home键,程序到了后台  
                   // Toast.makeText(getApplicationContext(), "home", 1).show();  
                backRunFlag=1;
                }else if(TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)){  
                    //表示长按home键,显示最近使用的程序列表  
                }  
            }   
        }  
    };
	//更新用户信息
	private void updateCustomer()
	{
		GetCustomerByIDReq req =new GetCustomerByIDReq();
		req.setUserID(AppCache.getInstance().getUser().getUserID());
		WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetCustomerByIDRsp rsp =  (GetCustomerByIDRsp) message.getMessage().obj;
					CustomerJson customer= rsp.getCustomer();
					if(null!=customer)
					{
						AppCache.getInstance().update(customer);
					}
				
				}
			}
		}).getCustomer(req);
		
	}
	private void showFireAlarmBage()
	{
		GetFireAlarmNumByIDReq req = new GetFireAlarmNumByIDReq();
		req.setUserID(AppCache.getInstance().getUser().getUserID());
		WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetFireAlarmNumByIDRsp rsp = (GetFireAlarmNumByIDRsp) message.getMessage().obj;
					
					alarmNumList= rsp.getNumList();
					BageNumDataCache.getInstance().setAlarmBageList(alarmNumList);
					showBage(alarmBageView,alarmNumList);
				
				}
			}
		}).getAlarmNum(req);
	}
	private void showCheckBage()
	{
		GetCheckLogNumByIDReq req = new GetCheckLogNumByIDReq();
		req.setUserID(AppCache.getInstance().getUser().getUserID());		
		WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetCheckLogNumByIDRsp rsp =  (GetCheckLogNumByIDRsp) message.getMessage().obj;
					checkNumList= rsp.getNumList();
					BageNumDataCache.getInstance().setCheckBageList(checkNumList);
					showBage(checkBageView, checkNumList);

				}
			}
		}).getCheckNum(req);
		
	}
	private void showFireStatusBage()
	{
		GetFireStatusNumByIDReq req = new GetFireStatusNumByIDReq();
		req.setUserID(AppCache.getInstance().getUser().getUserID());
		WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetFireStatusNumByIDRsp rsp = (GetFireStatusNumByIDRsp) message.getMessage().obj;
					statusNumList= rsp.getNumList();
					BageNumDataCache.getInstance().setStatusBageList(statusNumList);
					showBage(statusBageView,statusNumList);
			    	
				}
			}


		}).getStatusNum(req);
		
	}
	/**
	 * UI显示数字提醒
	 * @param statusBageView
	 * @param statusNumList
	 */
	private void showBage(BadgeView bageView,	List<BageNumJson> numList)
	{
		int count=0;
    	for(BageNumJson json:numList)
    	{
    		count+=json.getNum();
    	}
    	if(count!=0)
    	{

			if(count>99)
			{
				bageView.setText("99+");
			}
			else
			{
				bageView.setText(String.valueOf(count));
			}
			bageView.show();
    	}
    	else
    	{
    		if(bageView.isShown())
    		{
    			bageView.toggle();
    		}
    		
    	}
		
		
	}
	
	/**
	 * 注册统一样式bageview
	 * @param bageView
	 */
	private void regBageView(BadgeView bageView)
	{
		bageView.setTextColor(Color.RED);
		bageView.setBadgeBackgroundColor(Color.YELLOW);
		bageView.setTextSize(15);
		
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
		case R.id.home_menu_productauth:
			//CompanyListActivity.jump(this, CheckLogActivity.class);
			openDialog();
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

	private void openDialog()
	{
		 Builder dialog = new AlertDialog.Builder(this);
		 dialog.setCancelable(false);
		 dialog.setTitle("请输入产品编码");
		 LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.dialog_product_auth, null);
		 dialog.setView(layout);
		 final EditText code_text = (EditText)layout.findViewById(R.id.product_code);
		     
		 dialog.setPositiveButton("查询", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		        	String labelCode=code_text.getText().toString().trim();
		        	if(!ValidatorUtil.isEmpty(labelCode))
		        	{
		        		ImageDisplayInfo displayInfo = new ImageDisplayInfo();
		        		displayInfo.setTilteName("产品查询");
						displayInfo.setUrl(product_auth_baseURL+labelCode);
		    			MispWebViewActivity.jump(HomeActivity.this, displayInfo);
		        	}
		        	else
		        	{
		        		showToast(HomeActivity.this, "输入不能为空！");
		        	}
		        }
		    });
		     
		 dialog.setNegativeButton("取消", null);
		 dialog.show();	
		
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
            isExit.setMessage("确定要退出吗?");  
            // 添加选择按钮并注册监听  
            isExit.setButton("确定", listener);  
            isExit.setButton2("取消", listener);  
            // 显示对话框  
            isExit.show();  
  
        }  
        if(keyCode == KeyEvent.KEYCODE_HOME) 
        {
        	this.showToast(this, "home");
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

/*    class HomeReceiver extends BroadcastReceiver
    {

		@Override
		public void onReceive(Context context, Intent intent)
		{

			int refreshCode = intent.getIntExtra(IntentCodeConst.HOME_REFRESH, 0);
			switch(refreshCode)
			{
				case 1:showFireAlarmBage();
							break;
				case 2:showCheckBage();
							break;
				case 3:showFireStatusBage();
							break;
				default:break;
			}

		}
	};*/
     
	
}
