package cn.fuego.smart.home.ui.safe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.LoginActivity;
import cn.fuego.smart.home.ui.MainTabbarActivity;
import cn.fuego.smart.home.ui.base.BaseFragment;
import cn.fuego.smart.home.webservice.up.model.GetSensorListReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.up.model.LoginRsp;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class SafeFragment extends BaseFragment  implements  OnChildClickListener 
{
	private ListView livingViewList ;
	private ListView parentList;
    private SimpleAdapter adapterLiving;
    private GroupListAdapter sensorAdapter;
    private CheckBox living_ckx;
	private static final String[] safeItemAttrs = new String[] 
			{ "icon", "label"};
	
	private static final int[] safeViewAttrs= new int[]
	{
		R.id.safe_item_icon,R.id.safe_item_label
	};


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View rootView = inflater.inflate(R.layout.safe_fragment, null);
	
		this.sensorAdapter = new GroupListAdapter(this.getActivity());
		ExpandableListView safeListView = (ExpandableListView) rootView.findViewById(R.id.safe_main_parent);
		
		getSensorData();	
		safeListView.setAdapter(this.sensorAdapter);
		safeListView.setOnChildClickListener(this);
		
		return rootView;
	}
    
	private List<Map<String, Object>> getSensorData()
	{
		
		// 获取数据源
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		GetSensorListReq req = new GetSensorListReq();
		req.setToken(MemoryCache.getToken());
		req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage msg)
			{
				if (msg.isSuccess())
				{
					GetSensorListRsp rsp =(GetSensorListRsp) msg.getMessage().obj;
					sensorAdapter.setDatasource(rsp.getSensorList());
				}
				else
				{
					super.sendMessage(msg);
				}
 
				
			}
		}).getSensorList(req);
		
		
		
		return list;
	}
	private List<Map<String, Object>> getLivingData()
	{
		// 生成数据源
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 每个Map结构为一条数据，key与Adapter中定义的String数组中定义的一一对应。
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("icon", R.drawable.smoke);
		map.put("label", "烟雾报警器1");
		list.add(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("icon", R.drawable.temperature);
		map2.put("label", "温度报警器1");
		list.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("icon", R.drawable.temperature);
		map3.put("label", "湿度报警器1");
		list.add(map3);
		return list;
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id)
	{
		
		HomeSensorJson selectItem=(HomeSensorJson) sensorAdapter.getChild(groupPosition, childPosition);;
		
		if(selectItem!=null)
		{
			Intent i = new Intent();
			i.setClass(this.getActivity(), SafeConfigActivity.class);
			//i.putExtra("id", selectItem.get("id").toString());
			i.putExtra("label", selectItem.getMark());
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
	        this.startActivity(i);
			
		}
		Toast.makeText(this.getActivity(), "click group"+groupPosition+"child"+childPosition, Toast.LENGTH_SHORT).show();
		return false;
		
		
	}
	
}
