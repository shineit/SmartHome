package cn.fuego.smart.home.ui.safe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.list.IteratorSelector;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.expandable.PinnedHeaderExpandableListView;
import cn.fuego.misp.ui.expandable.PinnedHeaderExpandableListView.OnHeaderUpdateListener;
import cn.fuego.misp.ui.expandable.StickyLayout;
import cn.fuego.misp.ui.expandable.StickyLayout.OnGiveUpTouchEventListener;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.service.SensorDataCache;
import cn.fuego.smart.home.ui.base.BaseFragment;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;

public class SafeFragment extends BaseFragment implements OnHeaderUpdateListener, OnChildClickListener, OnGiveUpTouchEventListener 
{
	private FuegoLog log = FuegoLog.getLog(getClass());
    
    private PinnedHeaderExpandableListView safeListView;
    private StickyLayout stickyLayout;

    private MyexpandableListAdapter sensorAdapter;
    private ArrayList<Group> groupList = new ArrayList<Group>();
    private List<List<HomeSensorJson>> childList = new ArrayList<List<HomeSensorJson>>();
    private List<HomeSensorJson> dataSource;
    
    private int isFirst=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View rootView = inflater.inflate(R.layout.safe_fragment, null);

		safeListView = (PinnedHeaderExpandableListView) rootView.findViewById(R.id.safe_main_parent);
        stickyLayout = (StickyLayout)rootView.findViewById(R.id.sticky_layout);

		sensorAdapter = new MyexpandableListAdapter(this.getActivity(),groupList,childList);
		safeListView.setAdapter(this.sensorAdapter);
		if(isFirst==0)
		{
			isFirst=1;
			getSensorData();
		}
		
        // 展开所有group
/*        for (int i = 0, count = safeListView.getCount(); i < count; i++) {
        	safeListView.expandGroup(i);
        }*/
        safeListView.setOnHeaderUpdateListener(this);
        safeListView.setOnChildClickListener(this);
       // safeListView.setOnGroupClickListener(this);
        stickyLayout.setOnGiveUpTouchEventListener(this);
        
		return rootView;
	}
    
	private void getSensorData()
	{
		
		//SensorDataCache.getInstance().load(this);
		dataSource=SensorDataCache.getInstance().getMeasureSensorList();
		initData(dataSource);
		
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		dataSource=SensorDataCache.getInstance().getMeasureSensorList();
		initData(dataSource);
		
	}

    private void initData(List<HomeSensorJson> dataSource)
	{
    	Map<String, List<HomeSensorJson>> sensorMap = new HashMap<String, List<HomeSensorJson>>();
    	childList.clear();
    	if(!ValidatorUtil.isEmpty(dataSource))
    	{
    		for(HomeSensorJson json : dataSource)
    		{
    			List<HomeSensorJson> sensorList = sensorMap.get(json.getMark());
    			if(null == sensorList)
    			{
    				sensorList = new ArrayList<HomeSensorJson>();
    				sensorList.add(json);
    				sensorMap.put(json.getMark(), sensorList);
    				Group group = new Group();
    				group.setTitle(json.getMark());
    				group.setIsExpand(false);
      				if(!groupList.contains(group))
    				{
    					groupList.add(group);
    				}
    				
    			}
    			else
    			{
    				sensorList.add(json);
    			}
    			 
    		}
    	}
    	List<String> markList = IteratorSelector.selectColumn(groupList, "title");
		for(String mark : markList)
		{
			childList.add(sensorMap.get(mark));
		}
		sensorAdapter.notifyDataSetChanged();

	}
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id)
	{
		
		HomeSensorJson selectItem=(HomeSensorJson) sensorAdapter.getChild(groupPosition, childPosition);
		
		if(selectItem!=null)
		{
			Intent i = new Intent();
			Bundle mBundle= new Bundle();
		    mBundle.putSerializable(IntentCodeConst.BUNDLE_HOMESENSOR,selectItem);     
	        i.putExtras(mBundle);
			i.setClass(this.getActivity(), SafeConfigActivity.class);
			//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
			startActivityForResult(i, IntentCodeConst.REQUEST_CODE);
			
		}
		
		return false;
		
		
	}
    //返回时重新刷新页面

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		//HomeSensorJson newSensor = (HomeSensorJson) data.getSerializableExtra("newSensor");
		//log.info("newSensor:"+newSensor);
		if(requestCode==IntentCodeConst.REQUEST_CODE)
		{
			
			if(resultCode==IntentCodeConst.RESULT_CODE)
			{

				groupList.clear();
				childList.clear();
				SensorDataCache.getInstance().load(this);
				
			}
			else
			{
				log.info("onActivityResult failed ,the resultCode is "+resultCode);
			}
		}

	}

	@Override
	public void initRes()
	{
		// TODO Auto-generated method stub
		
	}

    

    /***
     * 数据源
     * 
     * 标题置顶相关
     * 
     */

	@Override
	public boolean giveUpTouchEvent(MotionEvent event)
	{
		if (safeListView.getFirstVisiblePosition() == 0) 
		{
		      View view = safeListView.getChildAt(0);
		      if (view != null && view.getTop() >= 0) 
		      {
		        return true;
		      }
		}
		return false;
	}

	@Override
	public View getPinnedHeader()
	{
        View headerView = (ViewGroup) this.getActivity().getLayoutInflater().inflate(R.layout.safe_item_parent, null);
        headerView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        return headerView;
	}

	@Override
	public void updatePinnedHeader(View headerView, int firstVisibleGroupPos)
	{
		if(!ValidatorUtil.isEmpty(groupList))
		{
	        Group firstVisibleGroup = (Group) sensorAdapter.getGroup(firstVisibleGroupPos);
        
	        TextView textView = (TextView) headerView.findViewById(R.id.safe_group_tag);
	        textView.setText(firstVisibleGroup.getTitle());
	        ImageView img=(ImageView) headerView.findViewById(R.id.safe_group_icon);
	        
        if(firstVisibleGroup.getIsExpand())
	        {
	        	img.setImageResource(R.drawable.up);
	        }
	        else
	        {
	        	img.setImageResource(R.drawable.down);
	        }
	        
		}

	}


	
}
