package cn.fuego.smart.home.ui.safe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.SensorDataCache;
import cn.fuego.smart.home.ui.base.BaseFragment;
import cn.fuego.smart.home.ui.model.SafeViewModel;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;

public class SafeFragment extends BaseFragment  implements  OnChildClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
    private GroupListAdapter sensorAdapter;
    private SafeViewModel safeViewModel = new SafeViewModel();
    

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
    
	private void getSensorData()
	{
		
		SensorDataCache.getInstance().load(this);
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		sensorAdapter.setDatasource(SensorDataCache.getInstance().getMeasureSensorList());
		
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id)
	{
		
		HomeSensorJson selectItem=(HomeSensorJson) sensorAdapter.getChild(groupPosition, childPosition);;
		
		if(selectItem!=null)
		{
			Intent i = new Intent();
			i.setClass(this.getActivity(), SafeConfigActivity.class);
			i.putExtra(safeViewModel.getConcentratorID(), selectItem.getConcentratorID());
			i.putExtra(safeViewModel.getSensorTypeName(), selectItem.getSensorTypeName());
			i.putExtra(safeViewModel.getDescriptions(), selectItem.getDescriptions());
			i.putExtra(safeViewModel.getWarnValue(), selectItem.getWarnValue());
			i.putExtra(safeViewModel.getErrorValue(), selectItem.getErrorValue());
			i.putExtra(safeViewModel.getMark(), selectItem.getMark());
		    i.putExtra(safeViewModel.getCtrGroupID(), String.valueOf(selectItem.getCtrGroupID()));
			i.putExtra(safeViewModel.getId(), selectItem.getId());//作为修改数据的索引
			//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
			startActivityForResult(i, 1);
			
		}
		
		return false;
		
		
	}
    //返回时重新刷新页面

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		if(requestCode==1)
		{
			
			if(10==resultCode)
			{
				getSensorData();

			}
			else
			{
				log.info("onActivityResult failed ,the resultCode is "+resultCode);
			}
		}

	}



	
	
}
