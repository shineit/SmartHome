package cn.fuego.smart.home.ui.setting.service;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.HandleStatusEnum;
import cn.fuego.smart.home.webservice.up.model.GetServiceOrderListReq;
import cn.fuego.smart.home.webservice.up.model.GetServiceOrderListRsp;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class ServiceActivity extends MispListActivity<ServiceOrderJson> implements View.OnClickListener 
{
 

	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.service);
		
		this.listViewRes.setListView(R.id.order_list);
		this.listViewRes.setListItemView(R.layout.service_item);
		this.listViewRes.setClickActivityClass(ServiceApplyActivity.class);


	}
 

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Button back_btn= (Button) findViewById(R.id.service_back);
		back_btn.setTag(1);
		back_btn.setOnClickListener(this);

		Button apply_btn= (Button) findViewById(R.id.service_add_apply);
		apply_btn.setTag(2);
		apply_btn.setOnClickListener(this);
		
	}


	@Override
	public void loadSendList()
	{
		GetServiceOrderListReq req = new GetServiceOrderListReq();
		req.setUserID(AppCache.getInstance().getUser().getUserID());
		WebServiceContext.getInstance().getOrderManageRest(this).getOrderList(req);
		
	}

	@Override
	public List<ServiceOrderJson> loadListRecv(Object obj)
	{
 
		GetServiceOrderListRsp rsp = (GetServiceOrderListRsp) obj;
		return rsp.getOrderList();

	}

	@Override
	public View getListItemView(View view, ServiceOrderJson item)
	{
		TextView txt_id = (TextView) view.findViewById(R.id.service_id);
		txt_id.setText(item.getOrderID());
		
		TextView txt_title= (TextView) view.findViewById(R.id.service_title);
		txt_title.setText(item.getOrderName());
		
		TextView txt_status= (TextView) view.findViewById(R.id.service_status);
		txt_status.setText(HandleStatusEnum.getEnumByInt(item.getOrderStatus()).getStrValue());
		
		return view;
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
 
 
	


}
