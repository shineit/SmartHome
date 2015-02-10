package cn.fuego.smart.home.ui.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.SensorStatusEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.webservice.up.model.BatchOperateSensorReq;
import cn.fuego.smart.home.webservice.up.model.BatchOperateSensorRsp;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

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

	private ProgressDialog chkPDialog;
	
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
		final HomeSensorJson sensor = itemList.get(groupPosition).get(childPosition);
		//自定义样式
		LayoutInflater inflater = LayoutInflater.from(mContext);
	    View layout = inflater.inflate(R.layout.control_item_child,null);
	    
	    //TextView txt_id = (TextView) layout.findViewById(R.id.item_control_id);
	   // txt_id.setText(String.valueOf(control.getId()));
	    TextView txt_desp = (TextView) layout.findViewById(R.id.item_control_desp);
	    txt_desp.setText(sensor.getDescriptions());
	    TextView txt_mark = (TextView) layout.findViewById(R.id.item_control_room);
	    txt_mark.setText(sensor.getMark());
	    final CheckBox ctr_chk = (CheckBox) layout.findViewById(R.id.item_control_chk);
	    if(sensor.getStatus()==SensorStatusEnum.ENABLE.getIntValue())
	    {
	    	ctr_chk.setChecked(true);
	    }
	    if(sensor.getStatus()==SensorStatusEnum.DISABLE.getIntValue())
	    {
	    	ctr_chk.setChecked(false);
	    }
	    ctr_chk.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				if(ctr_chk.isChecked())
				{
					enableSensor(sensor,ctr_chk);
				}
				else
				{
					disableSensor(sensor,ctr_chk);
				}
				
			}
		});

		return layout;
	}
	private void disableSensor(final HomeSensorJson sensor,final CheckBox chk)
	{
		chkPDialog= ProgressDialog.show(mContext, "请稍等", "数据提交中……");
		BatchOperateSensorReq req = new BatchOperateSensorReq();
		req.setToken(MemoryCache.getToken());
		
		List<String> snesorList =  new ArrayList<String>();
		snesorList.add(String.valueOf(sensor.getId()));		
		req.setSensorList(snesorList);
		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				BatchOperateSensorRsp rsp = (BatchOperateSensorRsp) message.getMessage().obj;
				if(message.isSuccess())
				{
					chk.setChecked(false);
					chkPDialog.dismiss();
					Toast.makeText(mContext, sensor.getSensorTypeName()+"禁止成功", Toast.LENGTH_SHORT).show();
					
				}
				else
				{
					chk.setChecked(true);
					chkPDialog.dismiss();
					Toast.makeText(mContext, sensor.getSensorTypeName()+"禁止失败", Toast.LENGTH_SHORT).show();
				}
			}
		}).disable(req);
		
	}

	private void enableSensor(final HomeSensorJson sensor,final CheckBox chk)
	{
		chkPDialog= ProgressDialog.show(mContext, "请稍等", "数据提交中……");
		BatchOperateSensorReq req = new BatchOperateSensorReq();
		req.setToken(MemoryCache.getToken());	
		List<String> snesorList =  new ArrayList<String>();
		snesorList.add(String.valueOf(sensor.getId()));		
		req.setSensorList(snesorList);
		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
					BatchOperateSensorRsp rsp = (BatchOperateSensorRsp) message.getMessage().obj;
					if(message.isSuccess())
					{
						chkPDialog.dismiss();
						Toast.makeText(mContext, sensor.getSensorTypeName()+"开启成功", Toast.LENGTH_SHORT).show();
						chk.setChecked(true);
					}
					else
					{
						chk.setChecked(false);
						chkPDialog.dismiss();
						Toast.makeText(mContext, sensor.getSensorTypeName()+"开启失败", Toast.LENGTH_SHORT).show();
						
					}
			}
		}).enable(req);
		
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
