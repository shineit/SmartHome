package cn.fuego.smart.home.ui.setting.concent;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.ConcentratorStatusEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.setting.service.ServiceActivity;
import cn.fuego.smart.home.webservice.up.model.GetConcentratorListReq;
import cn.fuego.smart.home.webservice.up.model.GetConcentratorListRsp;
import cn.fuego.smart.home.webservice.up.model.base.ConcentratorJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class ConcentListActivity extends MispListActivity<ConcentratorJson> implements OnClickListener
{


	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.concent_list);
		
		this.listViewRes.setListView(R.id.concent_list_content);
		this.listViewRes.setListItemView(R.layout.list_item_imgtype);
		this.listViewRes.setClickActivityClass(ConcentConfigActivity.class);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Button back_btn= (Button) findViewById(R.id.concent_list_back_btn);
		back_btn.setOnClickListener(this);
		Button add_btn = (Button) findViewById(R.id.concent_list_add_btn);
		add_btn.setOnClickListener(this);
	}
	@Override
	public void loadSendList()
	{
		GetConcentratorListReq req = new GetConcentratorListReq();
		req.setToken(MemoryCache.getToken());
		req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		WebServiceContext.getInstance().getConcentManageRest(this).getConcentList(req);
		
	}

	@Override
	public List<ConcentratorJson> loadListRecv(Object obj)
	{
		GetConcentratorListRsp rsp = (GetConcentratorListRsp) obj;
		return rsp.getConcentList();
	}

	@Override
	public View getListItemView(View view, ConcentratorJson item)
	{
		TextView txt_name= (TextView) view.findViewById(R.id.item_imgtype_name);
		txt_name.setText(item.getName());
		
		ImageView img_status= (ImageView) view.findViewById(R.id.item_imgtype_img);
		if(item.getStatus()==ConcentratorStatusEnum.ONLINE.getIntValue())
		{
			img_status.setImageResource(R.drawable.device_online);
		}
		else
		{
			img_status.setImageResource(R.drawable.device_offline);
		}
		return view;
	}
	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.concent_list_back_btn:
			this.finish();
			break;
		case R.id.concent_list_add_btn:
			Intent i= new Intent();
			i.setClass(this.getApplicationContext(),  ConcentAddActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);  
	        startActivity(i);  
		default:break;
		}
		
	}


}
