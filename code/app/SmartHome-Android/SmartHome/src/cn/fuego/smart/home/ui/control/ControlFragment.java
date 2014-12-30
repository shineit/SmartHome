package cn.fuego.smart.home.ui.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.service.SensorDataCache;
import cn.fuego.smart.home.ui.base.BaseFragment;
import cn.fuego.smart.home.ui.model.ControlViewModel;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;

public class ControlFragment extends BaseFragment implements  OnChildClickListener
{

	private FuegoLog log = FuegoLog.getLog(getClass());
    private ControlListAdapter controlAdapter;
    private ControlViewModel ctrViewModel = new ControlViewModel();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View rootView = inflater.inflate(R.layout.control_fragment, null);
		this.controlAdapter = new ControlListAdapter(this.getActivity());
		ExpandableListView ctrListView = (ExpandableListView) rootView.findViewById(R.id.control_main_list);

		getSensorData();
		ctrListView.setAdapter(this.controlAdapter);
		ctrListView.setOnChildClickListener(this);
		return rootView;
	}

	private void getSensorData()
	{
		
		SensorDataCache.getInstance().load(this);
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		controlAdapter.setDatasource(SensorDataCache.getInstance().getCtrSensorList());
		
	}



	@Override
	public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id)
	{
		HomeSensorJson selectItem=(HomeSensorJson) controlAdapter.getChild(groupPosition, childPosition);
		if(selectItem!=null)
		{
			Intent i = new Intent();
			i.setClass(this.getActivity(), ControlConfigActivity.class);
			i.putExtra(ctrViewModel.getId(), selectItem.getId());
			i.putExtra(ctrViewModel.getConcentratorID(), selectItem.getConcentratorID());
			i.putExtra(ctrViewModel.getSensorTypeName(), selectItem.getSensorTypeName());
			i.putExtra(ctrViewModel.getDescriptions(), selectItem.getDescriptions());
			i.putExtra(ctrViewModel.getMark(), selectItem.getMark());
			i.putExtra(ctrViewModel.getGroupID(), selectItem.getGroupID());
			startActivityForResult(i, IntentCodeConst.REQUEST_CODE);
		}
		return false;
	}



	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode==IntentCodeConst.REQUEST_CODE)
		{
			
			if(resultCode==IntentCodeConst.RESULT_CODE)
			{
				getSensorData();

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
 
}
