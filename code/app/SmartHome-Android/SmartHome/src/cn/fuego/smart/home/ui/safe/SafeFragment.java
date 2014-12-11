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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.control.ControlConfigActivity;

public class SafeFragment extends Fragment implements OnItemClickListener 
{
	private ListView livingViewList ;
    private SimpleAdapter adapterLiving;
    private CheckBox living_ckx;
	private static final String[] safeItemAttrs = new String[] 
			{ "icon", "label"};
	
	private static final int[] safeViewAttrs= new int[]
	{
		R.id.safe_item_icon,R.id.safe_item_label
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.safe_fragment, null);
		
		livingViewList = (ListView) rootView.findViewById(R.id.living_roomList);
		adapterLiving = new SimpleAdapter(this.getActivity(),getLivingData(),R.layout.safe_item,safeItemAttrs, safeViewAttrs);
		livingViewList.setAdapter(adapterLiving);
		livingViewList.setOnItemClickListener(this);
		
		BaseActivtiy.setListViewHeightBasedOnChildren(livingViewList); 
		
		return rootView;
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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		if(parent==livingViewList)
		{
			HashMap<String, Object> selectLiving = (HashMap<String, Object>) getLivingData().get(position); 
			if(selectLiving!=null)
			{

				
				Toast.makeText(this.getActivity(),"selectLiving"+position,Toast.LENGTH_SHORT).show();
				showSafe(selectLiving);

			}
		}
		
	}
	private void showSafe(HashMap<String, Object> selectItem)
	
	{
		Intent i = new Intent();
		i.setClass(this.getActivity(), SafeConfigActivity.class);
		//i.putExtra("id", selectItem.get("id").toString());
		i.putExtra("label", selectItem.get("label").toString());
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        this.startActivity(i);
		
	}
	
}
