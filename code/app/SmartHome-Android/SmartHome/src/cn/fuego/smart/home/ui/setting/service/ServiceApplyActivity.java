package cn.fuego.smart.home.ui.setting.service;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.constant.ServiceOrderTypeEnum;
import cn.fuego.smart.home.constant.SharedPreferenceConst;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.webservice.up.model.SetServiceOrderReq;
import cn.fuego.smart.home.webservice.up.model.SetServiceOrderRsp;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class ServiceApplyActivity extends BaseActivtiy implements View.OnClickListener,OnCheckedChangeListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private EditText textName,textContent,textPerson,textPhone,textAddr;
	private int applyType=0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_apply);
		ExitApplication.getInstance().addActivity(this);
		
		Button back_btn=(Button)findViewById(R.id.apply_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		Button sure_btn=(Button) findViewById(R.id.apply_sure_btn);
		sure_btn.setOnClickListener(this);
		sure_btn.setTag(2);
		Button cancel_btn=(Button) findViewById(R.id.apply_cancel_btn);
		cancel_btn.setOnClickListener(this);
		cancel_btn.setTag(3);
		
		RadioGroup group = (RadioGroup) this.findViewById(R.id.apply_type_group);
		group.setOnCheckedChangeListener(this); 
		
		textName =(EditText) findViewById(R.id.apply_name);
		textName.requestFocus();            
		textName.requestFocusFromTouch();
		textContent =(EditText) findViewById(R.id.apply_content);
		textPerson = (EditText) findViewById(R.id.contact_person);
		textPhone = (EditText) findViewById(R.id.contact_phone);
		textAddr = (EditText) findViewById(R.id.contact_addr);
		
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		
		switch(tag)
		{
		case 1: this.finish();
				break;
		case 2: try
			{
				submitApply();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				log.info("apply failed", e);
			}
		 		break;
		case 3: this.finish();
	 		break;
		default:break;
		}
		
	}



	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		int radioButtonId = group.getCheckedRadioButtonId();
		if (radioButtonId == R.id.apply_repair)
		{   
			
			applyType=ServiceOrderTypeEnum.REPAIR.getIntValue();

		}
		if (radioButtonId == R.id.apply_consult)
		{
			
			applyType=ServiceOrderTypeEnum.CONSULT.getIntValue();
			
		}
		
	}
	
	private void submitApply() throws Exception
	{
		SetServiceOrderReq req = new SetServiceOrderReq();
		req.setToken(MemoryCache.getToken());
		
		ServiceOrderJson serviceOrder = new ServiceOrderJson();
		serviceOrder.setOrderType(this.getApplyType());
		serviceOrder.setOrderName(this.getTextName().getText().toString().trim());
		serviceOrder.setContent(this.getTextContent().getText().toString().trim());
		serviceOrder.setContactName(this.getTextPerson().getText().toString().trim());
	
		serviceOrder.setPhoneNum(this.getTextPhone().getText().toString().trim());
		serviceOrder.setContactAddr(this.getTextAddr().getText().toString().trim());
		
		//serviceOrder.setCreator("admin");//需要从本地读出
		//SharedPreferences userInfo = getSharedPreferences(SharedPreferenceConst.UESR_INFO, 0);
		//serviceOrder.setCreator(userInfo.getString(SharedPreferenceConst.NAME, ""));
		//URLEncoder.encode(serviceOrder.getOrderName(), "utf-8");
		serviceOrder.setCreator(MemoryCache.getLoginInfo().getUser().getUserName());
		req.setServiceOrder(serviceOrder);
		WebServiceContext.getInstance().getOrderManageRest(this).setServiceOrder(req);
		
	}

	public int getApplyType()
	{
		return applyType;
	}


	public EditText getTextName()
	{
		return textName;
	}

	public EditText getTextContent()
	{
		return textContent;
	}

	public EditText getTextPerson()
	{
		return textPerson;
	}

	public EditText getTextPhone()
	{
		return textPhone;
	}

	public EditText getTextAddr()
	{
		return textAddr;
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		SetServiceOrderRsp rsp = (SetServiceOrderRsp) message.getMessage().obj;
		
		if(ErrorMessageConst.SUCCESS==rsp.getResult().getErrorCode())
		{

			Intent intent = new Intent();
			intent.setClass(this.getApplicationContext(),  ServiceActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
            startActivity(intent);
            
            this.finish();
		}

		showMessage(message);
	}
	
}
