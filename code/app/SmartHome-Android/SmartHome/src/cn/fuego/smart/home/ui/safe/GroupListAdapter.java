/**   
 * @Title: GroupListAdapter.java 
 * @Package cn.fuego.smart.home.ui.safe 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-16 下午4:50:27 
 * @version V1.0   
 */
package cn.fuego.smart.home.ui.safe;

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
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;

/**
 * @ClassName: GroupListAdapter
 * @Description: TODO
 * @author Aether
 * @date 2014-12-16 下午4:50:27
 * 
 */
public class GroupListAdapter extends BaseExpandableListAdapter
{
	// private List<String> listTag = null;
	private Context mContext = null;

	private List<String> groupList = new ArrayList<String>();

	private List<List<HomeSensorJson>> itemList = new ArrayList<List<HomeSensorJson>>();
	
	private List<HomeSensorJson> dataSource;

	public GroupListAdapter(Context context)
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
		
		for(HomeSensorJson json : dataSource)
		{
			List<HomeSensorJson> sensorList = sensorMap.get(json.getMark());
			if(null == sensorList)
			{
				sensorList = new ArrayList<HomeSensorJson>();
				sensorList.add(json);
				sensorMap.put(json.getMark(), sensorList);
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

	public boolean areAllItemsEnabled()
	{
		return false;
	}

	public Object getChild(int groupPosition, int childPosition)
	{
		return itemList.get(groupPosition).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}

	public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent)
	{

		HomeSensorJson sensor =   itemList.get(groupPosition).get(childPosition);
		//自定义样式
		LayoutInflater inflater = LayoutInflater.from(mContext);
	    View layout = inflater.inflate(R.layout.safe_item_child,null);
	    
	    ImageView safeChildIcon = (ImageView)layout.findViewById(R.id.safe_item_icon);
	    safeChildIcon.setImageResource(R.drawable.smoke);
	    TextView safeChildLabel = (TextView) layout.findViewById(R.id.safe_item_label);
	    safeChildLabel.setText(sensor.getSensorTypeName());
	    //CheckBox safeChildChk= (CheckBox) layout.findViewById(R.id.safe_item_chk);
		return layout;
		
	}

	public int getChildrenCount(int groupPosition)
	{
		return itemList.get(groupPosition).size();
	}

	public Object getGroup(int groupPosition)
	{
		return groupList.get(groupPosition);
	}

	public int getGroupCount()
	{
		return groupList.size();
	}
	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent)
	{

		String name = (String) groupList.get(groupPosition);
		//自定义样式
		LayoutInflater inflater = LayoutInflater.from(mContext);
	    View layout = inflater.inflate(R.layout.safe_item_parent,null);
	    TextView safeTag = (TextView)layout.findViewById(R.id.safe_group_tag);
	    safeTag.setText(name);
	    ImageView dropIcon= (ImageView) layout.findViewById(R.id.safe_group_icon);
	    if(isExpanded)
	    {   
	    	dropIcon.setImageResource(R.drawable.up);   
	    }
	    else
	    {   
	    	dropIcon.setImageResource(R.drawable.down); 
	    }  
		return layout;
	}

	public boolean isEmpty()
	{
		return false;
	}

	public void onGroupCollapsed(int groupPosition)
	{
	}

	public void onGroupExpanded(int groupPosition)
	{
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
		// TODO Auto-generated method stub
		return true;
	}

	public void setGroupList(List<String> groupList)
	{
		this.groupList = groupList;
	}

	public List<String> getGroupList()
	{
		return groupList;
	}

}
