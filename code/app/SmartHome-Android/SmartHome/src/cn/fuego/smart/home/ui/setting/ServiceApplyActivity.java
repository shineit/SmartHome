package cn.fuego.smart.home.ui.setting;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.constant.ServiceOrderTypeEnum;
import cn.fuego.smart.home.constant.SharedPreferenceConst;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.webservice.up.model.SetServiceOrderReq;
import cn.fuego.smart.home.webservice.up.model.SetServiceOrderRsp;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

import com.fuego.smarthome.R;
import com.fuego.smarthome.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ServiceApplyActivity extends Activity implements View.OnClickListener,OnCheckedChangeListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private EditText textName,textContent,textPerson,textPhone,textAddr;
	private int applyType=0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_apply);
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
		//serviceOrder.setContactName(this.getTextPerson().getText().toString().trim());
		serviceOrder.setContactName("你好");
		serviceOrder.setPhoneNum(this.getTextPhone().getText().toString().trim());
		serviceOrder.setContactAddr(this.getTextAddr().getText().toString().trim());
		
		//serviceOrder.setCreator("admin");//需要从本地读出
		SharedPreferences userInfo = getSharedPreferences(SharedPreferenceConst.UESR_INFO, 0);
		serviceOrder.setCreator(userInfo.getString(SharedPreferenceConst.NAME, ""));
		//URLEncoder.encode(serviceOrder.getOrderName(), "utf-8");
		req.setServiceOrder(serviceOrder);
		WebServiceContext.getInstance().getOrderManageRest(new Handler(){
			@Override
			public void handleMessage(Message msg)
			{
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				SetServiceOrderRsp rsp = (SetServiceOrderRsp) msg.obj;
				Toast toast;
				if(ErrorMessageConst.SUCCESS==rsp.getResult().getErrorCode())
				{
					toast = Toast.makeText(getApplicationContext(), MISPErrorMessageConst.getMessageByErrorCode(rsp.getResult().getErrorCode()), Toast.LENGTH_SHORT);
					toast.show();
					Intent intent = new Intent(ServiceApplyActivity.this, ServiceActivity.class);  
	                startActivity(intent);
				}
				else
				{
					toast = Toast.makeText(getApplicationContext(), MISPErrorMessageConst.getMessageByErrorCode(rsp.getResult().getErrorCode()), Toast.LENGTH_SHORT);
					toast.show();
				}
				
			}
		}).setServiceOrder(req);
		
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
	
}
