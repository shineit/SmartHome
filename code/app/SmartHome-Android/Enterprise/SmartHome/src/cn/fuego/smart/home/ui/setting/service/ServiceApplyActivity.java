package cn.fuego.smart.home.ui.setting.service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.ServiceOrderTypeEnum;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.webservice.up.model.DeleteOrderByIDReq;
import cn.fuego.smart.home.webservice.up.model.SetServiceOrderReq;
import cn.fuego.smart.home.webservice.up.model.SetServiceOrderRsp;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class ServiceApplyActivity extends BaseActivtiy implements View.OnClickListener,OnCheckedChangeListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private EditText textName,textContent,textPerson,textPhone,textAddr,
						textHandler,textResult;
	private int applyType=0;
	private Button sure_btn,cancel_btn;
	private View contactsView,resultView;
	private RadioGroup group;
	private RadioButton repairBtn,consultBtn;
	private String orderID;
	private ProgressDialog proDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_apply);
		ExitApplication.getInstance().addActivity(this);
		
		Button back_btn=(Button)findViewById(R.id.apply_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		sure_btn=(Button) findViewById(R.id.apply_sure_btn);
		sure_btn.setOnClickListener(this);
		sure_btn.setTag(2);
		cancel_btn=(Button) findViewById(R.id.apply_cancel_btn);
		cancel_btn.setOnClickListener(this);
		cancel_btn.setTag(3);
		
		group = (RadioGroup) this.findViewById(R.id.apply_type_group);
		group.setOnCheckedChangeListener(this); 
		repairBtn = (RadioButton) findViewById(R.id.apply_repair);
		consultBtn = (RadioButton) findViewById(R.id.apply_consult);
		
		contactsView= this.findViewById(R.id.service_apply_contactview);
		resultView= this.findViewById(R.id.service_apply_resultview);
		
		textName =(EditText) findViewById(R.id.apply_name);
		textContent =(EditText) findViewById(R.id.apply_content);
		
		textPerson = (EditText) findViewById(R.id.contact_person);
		textPhone = (EditText) findViewById(R.id.contact_phone);
		textAddr = (EditText) findViewById(R.id.contact_addr);
		
		textHandler=(EditText) findViewById(R.id.service_apply_handler);
		textResult=(EditText) findViewById(R.id.service_apply_result);
		
		initView(this.getIntent());
		
		
		
	}

	private void initView(Intent intent)
	{
		ServiceOrderJson serviceOrder = (ServiceOrderJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		if(serviceOrder==null)
		{
			contactsView.setVisibility(View.VISIBLE);
			resultView.setVisibility(View.GONE);
			sure_btn.setVisibility(View.VISIBLE);
			cancel_btn.setVisibility(View.GONE);
			sure_btn.requestFocus();            
			sure_btn.requestFocusFromTouch();
			
		}
		else
		{
			//group.setClickable(false);
			repairBtn.setClickable(false);
			consultBtn.setClickable(false);
			textName.setEnabled(false);
			textContent.setEnabled(false);
			contactsView.setVisibility(View.GONE);
			resultView.setVisibility(View.VISIBLE);
			sure_btn.setVisibility(View.GONE);
			cancel_btn.setVisibility(View.VISIBLE);
			cancel_btn.requestFocus();            
			cancel_btn.requestFocusFromTouch();
			
			initData(serviceOrder);

			
		}
		
	}


	private void initData(ServiceOrderJson serviceOrder)
	{
		orderID=serviceOrder.getOrderID();
		if(ServiceOrderTypeEnum.getEnumByInt(serviceOrder.getOrderType())==ServiceOrderTypeEnum.REPAIR)
		{
			repairBtn.setChecked(true);
		}
		else
		{
			consultBtn.setChecked(true);
		}
		textName.setText(serviceOrder.getOrderName());
		textContent.setText(serviceOrder.getContent());
		textHandler.setText(serviceOrder.getHandler());
		textResult.setText(serviceOrder.getHandleResult());
		
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
		case 3: //this.finish();
			deleteApply();
	 		break;
		default:break;
		}
		
	}



	private void deleteApply()
	{
		proDialog =ProgressDialog.show(ServiceApplyActivity.this, "请稍等", "正在删除数据……");
		DeleteOrderByIDReq req = new DeleteOrderByIDReq();
		req.setOrderID(orderID);
		WebServiceContext.getInstance().getOrderManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
		            jumpActivity();
				}

				showToast(ServiceApplyActivity.this, message);
				proDialog.dismiss();


			}
		}).deleteAlarm(req);
		
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
		proDialog =ProgressDialog.show(ServiceApplyActivity.this, "请稍等", "正在提交数据……");
		SetServiceOrderReq req = new SetServiceOrderReq();
		
		ServiceOrderJson serviceOrder = new ServiceOrderJson();
		serviceOrder.setOrderType(this.getApplyType());
		serviceOrder.setOrderName(this.getTextName().getText().toString().trim());
		serviceOrder.setContent(this.getTextContent().getText().toString().trim());
		serviceOrder.setContactName(this.getTextPerson().getText().toString().trim());
	
		serviceOrder.setPhoneNum(this.getTextPhone().getText().toString().trim());
		serviceOrder.setContactAddr(this.getTextAddr().getText().toString().trim());
		
		serviceOrder.setCreator(AppCache.getInstance().getUser().getUserName());
		req.setServiceOrder(serviceOrder);
		WebServiceContext.getInstance().getOrderManageRest(this).setServiceOrder(req);
		
	}


	@Override
	public void handle(MispHttpMessage message)
	{
		SetServiceOrderRsp rsp = (SetServiceOrderRsp) message.getMessage().obj;
		
		if(message.isSuccess())
		{


            jumpActivity();
		}

		showToast(ServiceApplyActivity.this, message);
		proDialog.dismiss();

	}

	private void jumpActivity()
	{
		Intent intent = new Intent();
		intent.setClass(this.getApplicationContext(),  ServiceActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);  
        startActivity(intent);          
        this.finish();
		
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
	public void initRes() {
		// TODO Auto-generated method stub
		
	}
	
}
