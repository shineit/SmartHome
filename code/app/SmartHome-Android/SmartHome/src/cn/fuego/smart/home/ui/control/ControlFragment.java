package cn.fuego.smart.home.ui.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.BaseFragment;

public class ControlFragment extends BaseFragment implements OnItemClickListener, OnCheckedChangeListener
{

	private ListView doorViewList,curtainViewList,fireViewList ;
    private SimpleAdapter adapterDoor,adapterCurtain,adapterFire;
    private CheckBox door_btn,curtain_btn,fire_btn;
    //private int listID = 0;
	private static final String[] controlItemAttrs = new String[] 
			{ "id", "room"};
	
	private static final int[] controlViewAttrs= new int[]
	{
		R.id.item_control_id,R.id.item_control_room
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View rootView = inflater.inflate(R.layout.control_fragment, null);
	    
	    doorViewList =(ListView) rootView.findViewById(R.id.doorList);
	    curtainViewList = (ListView) rootView.findViewById(R.id.curtainList);
	    fireViewList = (ListView) rootView.findViewById(R.id.fireList);
	    
	    adapterDoor= new SimpleAdapter(this.getActivity(),getDoorData(),R.layout.control_item,controlItemAttrs, controlViewAttrs);
	    adapterCurtain= new SimpleAdapter(this.getActivity(),getCurtainData(),R.layout.control_item,controlItemAttrs, controlViewAttrs);
	    adapterFire= new SimpleAdapter(this.getActivity(),getFireData(),R.layout.control_item,controlItemAttrs, controlViewAttrs);
	    
	    doorViewList.setAdapter(adapterDoor);
	    curtainViewList.setAdapter(adapterCurtain);
	    fireViewList.setAdapter(adapterFire);
	    
	    doorViewList.setOnItemClickListener(this);
	    curtainViewList.setOnItemClickListener(this);
	    fireViewList.setOnItemClickListener(this);
	    
		door_btn= (CheckBox) rootView.findViewById(R.id.control_door_btn);
		door_btn.setOnCheckedChangeListener(this);
		curtain_btn= (CheckBox) rootView.findViewById(R.id.control_curtain_btn);
		curtain_btn.setOnCheckedChangeListener(this);
		fire_btn= (CheckBox) rootView.findViewById(R.id.control_fire_btn);
		fire_btn.setOnCheckedChangeListener(this);

		 BaseActivtiy.setListViewHeightBasedOnChildren(doorViewList); 
		 BaseActivtiy.setListViewHeightBasedOnChildren(curtainViewList); 
		 BaseActivtiy.setListViewHeightBasedOnChildren(fireViewList); 
		return rootView;
	}

	private List<Map<String, Object>> getFireData()
	{
		// 生成数据源
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 每个Map结构为一条数据，key与Adapter中定义的String数组中定义的一一对应。
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "灭火控制器1");
		map.put("room", "厨房");
		list.add(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", "灭火控制器2");
		map2.put("room", "卧室");
		list.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("id", "灭火控制器3");
		map3.put("room", "客厅");
		list.add(map3);
		return list;
	}

	private List<Map<String, Object>> getCurtainData()
	{
		// 生成数据源
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 每个Map结构为一条数据，key与Adapter中定义的String数组中定义的一一对应。
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "窗帘控制器1");
		map.put("room", "厨房");
		list.add(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", "窗帘控制器2");
		map2.put("room", "卧室");
		list.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("id", "窗帘控制器3");
		map3.put("room", "客厅");
		list.add(map3);
		return list;
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		//Toast.makeText(this.getActivity(),position,Toast.LENGTH_SHORT).show();

		if(parent==doorViewList)
		{
			HashMap<String, Object> selectDoor = (HashMap<String, Object>) getDoorData().get(position); 
			if(selectDoor!=null)
			{

				
				Toast.makeText(this.getActivity(),"selectDoor"+position,Toast.LENGTH_SHORT).show();
				showControl(selectDoor);

			}
		}

		if(parent==curtainViewList)
		{
			HashMap<String, Object> selectCurtain = (HashMap<String, Object>) getCurtainData().get(position); 
			if(selectCurtain!=null)
			{

				Toast.makeText(this.getActivity(),"selectCurtain"+position,Toast.LENGTH_SHORT).show();
				showControl(selectCurtain);
			}
		}
		if(parent==fireViewList)
		{
			HashMap<String, Object> selectFire = (HashMap<String, Object>) getFireData().get(position); 
			if(selectFire!=null)
			{

				Toast.makeText(this.getActivity(),"selectFire"+position,Toast.LENGTH_SHORT).show();
				showControl(selectFire);
			}
		}
	}


	private void showControl(HashMap<String, Object> selectItem)
	{
		Intent i = new Intent();
		i.setClass(this.getActivity(), ControlConfigActivity.class);
		i.putExtra("id", selectItem.get("id").toString());
		i.putExtra("room", selectItem.get("room").toString());
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        this.startActivity(i);
		
	}

	public List<Map<String, Object>> getDoorData()
	{
		// 生成数据源
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 每个Map结构为一条数据，key与Adapter中定义的String数组中定义的一一对应。
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "门控制器1");
		map.put("room", "厨房");
		list.add(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", "门控制器2");
		map2.put("room", "卧室");
		list.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("id", "门控制器3");
		map3.put("room", "客厅");
		list.add(map3);
		return list;
	}

  



	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{


		if(door_btn.isChecked())
		{
			doorViewList.setVisibility(View.VISIBLE);
		}
		else
		{
			doorViewList.setVisibility(View.GONE);
		}
		if(curtain_btn.isChecked())
		{
			curtainViewList.setVisibility(View.VISIBLE);
		}
		else
		{
			curtainViewList.setVisibility(View.GONE);
		}
		if(fire_btn.isChecked())
		{
			fireViewList.setVisibility(View.VISIBLE);
		}
		else
		{
			fireViewList.setVisibility(View.GONE);
		}
		
	}


}
