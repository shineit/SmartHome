package cn.fuego.smart.home.ui.setting.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.constant.ServiceOrderStatusEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.webservice.up.model.GetServiceOrderListReq;
import cn.fuego.smart.home.webservice.up.model.GetServiceOrderListRsp;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class ServiceActivity extends BaseActivtiy implements View.OnClickListener
{
	private static final int[] serviceViewAttrs = new int[]
	{ R.id.service_id, R.id.service_title,R.id.apply_name };
	private static final String[] serviceItemAttrs = new String[] { "orderID", "title", "status"};
	private ListView serviceList;
    private List<Map<String, Object>> serviceItems = new ArrayList<Map<String, Object>>();
    private SimpleAdapter serviceAdapter ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);
		ExitApplication.getInstance().addActivity(this);
		
		Button back_btn=(Button)findViewById(R.id.service_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		
		Button add_btn=(Button) findViewById(R.id.add_apply);
		add_btn.setOnClickListener(this);
		add_btn.setTag(2);
		serviceList = (ListView) findViewById(R.id.order_list);
		serviceAdapter = new SimpleAdapter(this,serviceItems,R.layout.service_item,serviceItemAttrs, serviceViewAttrs);
		serviceList.setAdapter(serviceAdapter);
		init();
	}

	private void init()
	{
		GetServiceOrderListReq req = new GetServiceOrderListReq();
		req.setToken(MemoryCache.getToken());
		//req.setUserID(userID);
		WebServiceContext.getInstance().getOrderManageRest(this).getOrderList(req);
		
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		Intent intent = null;
		switch(tag)
		{
		case 1: this.finish();
				break;
		case 2: intent = new Intent(this,ServiceApplyActivity.class);
		 		startActivity(intent);
		 		break;

		default:break;
		}
		
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		Message msg = message.getMessage();
	 
		GetServiceOrderListRsp rsp = (GetServiceOrderListRsp) msg.obj;
		serviceItems.clear();
		if(ErrorMessageConst.SUCCESS==rsp.getResult().getErrorCode())
		{
			for(ServiceOrderJson json : rsp.getOrderList())
			{
				 Map<String, Object> listItem = new HashMap<String, Object>();
				 listItem.put(serviceItemAttrs[0], json.getOrderID());
				 listItem.put(serviceItemAttrs[1], json.getOrderName());
				 listItem.put(serviceItemAttrs[2], ServiceOrderStatusEnum.getEnumByInt(json.getOrderStatus()).getStrValue());
				 serviceItems.add(listItem);
				 
			}
			serviceAdapter.notifyDataSetChanged();
			
		}

		
	}


}
