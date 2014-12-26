package cn.fuego.smart.home.ui.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;

/** 
* @ClassName: ControlListAdapter 
* @Description: TODO
* @author Aether
* @date 2014-12-26 上午11:41:26 
*  
*/
public class ControlListAdapter extends BaseExpandableListAdapter 
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private Context mContext = null;

	private List<String> groupList = new ArrayList<String>();

	private List<List<HomeSensorJson>> itemList = new ArrayList<List<HomeSensorJson>>();
	
	private List<HomeSensorJson> dataSource;


	public ControlListAdapter(Context context)
	{
		this.mContext = context;
	}
	public void setDatasource(List<HomeSensorJson> dataSource)
	{
		this.dataSource = dataSource;
		initData();
		this.notifyDataSetChanged();
	}

	private void initData()
	{
		Map<String, List<HomeSensorJson>> sensorMap = new HashMap<String, List<HomeSensorJson>>();
		itemList.clear();
		for(HomeSensorJson json : dataSource)
		{
			List<HomeSensorJson> sensorList = sensorMap.get(json.getSensorTypeName());
			if(null == sensorList)
			{
				sensorList = new ArrayList<HomeSensorJson>();
				sensorList.add(json);
				sensorMap.put(json.getSensorTypeName(), sensorList);
			}
			else
			{
				sensorList.add(json);
			}
			 
		}
		
		groupList = new ArrayList<String>(sensorMap.keySet());

    		for(String group : groupList)
    		{
     			itemList.add(sensorMap.get(group));
    		}
     


	}
	@Override
	public Object getChild(int groupPosition, int childPosition)
	{
		
		return itemList.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition)
	{
		
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent)
	{
		HomeSensorJson control = itemList.get(groupPosition).get(childPosition);
		//自定义样式
		LayoutInflater inflater = LayoutInflater.from(mContext);
	    View layout = inflater.inflate(R.layout.control_item_child,null);
	    
	    TextView txt_id = (TextView) layout.findViewById(R.id.item_control_id);
	    txt_id.setText(String.valueOf(control.getId()));
	    TextView txt_mark = (TextView) layout.findViewById(R.id.item_control_room);
	    txt_mark.setText(control.getMark());
		return layout;
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		// TODO Auto-generated method stub
		return itemList.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition)
	{
		// TODO Auto-generated method stub
		return groupList.get(groupPosition);
	}

	@Override
	public int getGroupCount()
	{
		// TODO Auto-generated method stub
		return groupList.size();
	}

	@Override
	public long getGroupId(int groupPosition)
	{
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent)
	{
		String name = groupList.get(groupPosition);
		LayoutInflater inflater = LayoutInflater.from(mContext);
	    View layout = inflater.inflate(R.layout.control_item_parent,null);
	    
	    ImageView icon = (ImageView) layout.findViewById(R.id.control_parent_icon);
	    TextView  title = (TextView) layout.findViewById(R.id.control_parent_title);
	    ImageView dropFlag= (ImageView) layout.findViewById(R.id.control_parent_drop);
	    
	    title.setText(name);
	    if(isExpanded)
	    {   
	    	dropFlag.setImageResource(R.drawable.up);   
	    }
	    else
	    {   
	    	dropFlag.setImageResource(R.drawable.down); 
	    }  
		return layout;
	}

	@Override
	public boolean hasStableIds()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		// 如果不为true，则不能点击child项
		return true;
	}

}
