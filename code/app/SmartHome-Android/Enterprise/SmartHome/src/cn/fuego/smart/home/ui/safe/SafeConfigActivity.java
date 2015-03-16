package cn.fuego.smart.home.ui.safe;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.constant.SensorSetCmdEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.service.SensorDataCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.model.SpinnerDataModel;
import cn.fuego.smart.home.ui.setting.user.MarkManageActivity;
import cn.fuego.smart.home.webservice.up.model.SetSensorReq;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class SafeConfigActivity extends BaseActivtiy implements OnClickListener, OnItemSelectedListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	private List<String> markList =  null;
	private List<SpinnerDataModel> ctrList=null ;
	private ArrayAdapter<String> markAdapter;
	private ArrayAdapter<SpinnerDataModel> ctrAdapter;
	private Spinner markSpinner,ctrSpinner ;
	private String defaultLabel;
	
	private TextView txt_concentID,txt_sensorID,txt_sensorType,txt_terminID;
	private EditText txt_desp,txt_warn,txt_error;
	private String selMark,selCtrSensorID,selCtrChannelID;
	private ProgressDialog configPDialog;
	private HomeSensorJson sensor;
	//private Bundle bundle = new Bundle();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.safe_manage);
		ExitApplication.getInstance().addActivity(this);
		Intent intent = this.getIntent();
		initData(intent);
		
		Button back_btn = (Button)findViewById(R.id.safe_manage_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		
		Button config_btn =  (Button) findViewById(R.id.safe_config_btn);
		config_btn.requestFocus();
		config_btn.requestFocusFromTouch();
		config_btn.setOnClickListener(this);
		config_btn.setTag(2);
		
		Button add_btn = (Button) findViewById(R.id.safe_mark_btn);
		add_btn.setOnClickListener(this);
		add_btn.setTag(3);

	}
	
	private void initData(Intent intent)
	{
		sensor = (HomeSensorJson) intent.getSerializableExtra(IntentCodeConst.BUNDLE_HOMESENSOR); 
		//不可更改项
/*		txt_concentID = (TextView) findViewById(R.id.safe_concnet_id);
		txt_concentID.setText(intent.getStringExtra(safeViewModel.getConcentratorID()));*/
		txt_terminID= (TextView) findViewById(R.id.safe_channel_id);
		txt_terminID.setText(String.valueOf(sensor.getChannelID()));
		txt_sensorID = (TextView) findViewById(R.id.safe_sensor_objID);
		txt_sensorID.setText(String.valueOf(sensor.getSensorID()));
		txt_sensorType = (TextView) findViewById(R.id.safe_sensor_objType);
		txt_sensorType.setText(sensor.getSensorTypeName());
		//用户更改字段
		txt_desp = (EditText) findViewById(R.id.safe_desp);
		txt_desp.setText(sensor.getDescriptions());
		txt_warn = (EditText) findViewById(R.id.safe_warnValue);
		txt_warn.setText(String.valueOf(sensor.getWarnValue()));
		txt_error = (EditText) findViewById(R.id.safe_errorValue);
		txt_error.setText(String.valueOf(sensor.getErrorValue()));
	
		defaultLabel = sensor.getMark();
		String defCtrSensorID = String.valueOf(sensor.getCtrSensorID());
		String defCtrChannelID = String.valueOf(sensor.getCtrChannelID());
		markSpinner =  (Spinner) findViewById(R.id.safe_mark_spinner);
		ctrSpinner = (Spinner) findViewById(R.id.safe_ctr_spinner);
        //获取标签栏选项数据
		if(markList==null)
		{
			markList=  SensorDataCache.getInstance().getMarkList();
			
		}
		log.info("markList is "+markList);
		markAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item , markList);
		//markAdapter.setDropDownViewResource(R.layout.dropdown_item);//自定义样式/
		markAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
		markSpinner.setAdapter(markAdapter);
		markSpinner.setSelection(getSelPosition(defaultLabel));
		markAdapter.notifyDataSetChanged();
		markSpinner.setOnItemSelectedListener(this);

		if(ctrList==null)
		{
			ctrList= SensorDataCache.getInstance().getCtrSpinnerList();
		}
		ctrAdapter = new ArrayAdapter<SpinnerDataModel>(this,android.R.layout.simple_spinner_item , ctrList);
		ctrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ctrSpinner.setAdapter(ctrAdapter);
		ctrSpinner.setSelection(getCtrPosition(defCtrSensorID,defCtrChannelID));
		ctrAdapter.notifyDataSetChanged();
		ctrSpinner.setOnItemSelectedListener(this);
	}

	
    private int getSelPosition(String label)
	{
    	int index=0;

		for(int i=0; i<markList.size();i++)
		{
			if(label.equals(markList.get(i)))
			{
			    index=i;
			    return index;
			    
			}
			
		}
		return index;
	}
    private int getCtrPosition(String defCtrSensorID,String defCtrChannelID)
    {
		int index=0;
		log.info("The ctrList is"+ctrList);
		for(int i=0;i<ctrList.size();i++)
		{
			if(defCtrSensorID.equals(ctrList.get(i).getCtrSensorID())&&defCtrChannelID.equals(ctrList.get(i).getCtrChannelID()))
			{
				index=i;
				return index;
				
			}
		}
    	return index;
    	
    }

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		switch(tag)
		{
		case 1: 
			this.finish();
				break;
		case 2:configSafe();
			break;
		case 3:markManage();
			break;
		default:break;
		}
		
	}

	private void configSafe()
	{
		configPDialog =ProgressDialog.show(SafeConfigActivity.this, "请稍等", "正在提交数据……");
		SetSensorReq req = new SetSensorReq();
		req.setToken(MemoryCache.getToken());
		req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		req.setCommand(SensorSetCmdEnum.MODIFY.getIntValue());
/*		HomeSensorJson homesensor= new HomeSensorJson();
		//不更改字段
		homesensor.setId(sensor.getId());
		homesensor.setSensorTypeName(sensor.getSensorTypeName());
		homesensor.setSensorType(sensor.getSensorType());
		//用户更改字段
		homesensor.setDescriptions(this.getTxt_desp().getText().toString().trim());
		//预留更改，目前可以做更改
		homesensor.setWarnValue(Float.parseFloat(this.getTxt_warn().getText().toString().trim()));
		homesensor.setErrorValue(Float.parseFloat(this.getTxt_error().getText().toString().trim()));
		//spinner 标签选中项
		homesensor.setMark(selMark);
		homesensor.setCtrSensorID(Long.valueOf(selCtrSensorID));
		homesensor.setCtrChannelID(Integer.valueOf(selCtrChannelID));
		req.setSensor(homesensor);*/
		
		sensor.setDescriptions(this.getTxt_desp().getText().toString().trim());
		sensor.setWarnValue(Float.parseFloat(this.getTxt_warn().getText().toString().trim()));
		sensor.setErrorValue(Float.parseFloat(this.getTxt_error().getText().toString().trim()));
		sensor.setMark(selMark);
		sensor.setCtrSensorID(Long.valueOf(selCtrSensorID));
		sensor.setCtrChannelID(Integer.valueOf(selCtrChannelID));
		req.setSensor(sensor);
		//回传参数做单个缓存用
		//bundle.putSerializable("newSensor", homesensor);
		
		WebServiceContext.getInstance().getSensorManageRest(this).setSensor(req);		
		
	}
    
	//配置传感器线程
	@Override
	public void handle(MispHttpMessage message)
	{
		
		//SetSensorRsp rsp = (SetSensorRsp) message.getMessage().obj;
		if(message.isSuccess())
		{
			configPDialog.dismiss();
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			//intent.putExtras(bundle);
			SafeConfigActivity.this.setResult(IntentCodeConst.RESULT_CODE,intent);
			this.finish();
            
		}

		else
		{
			configPDialog.dismiss();
			showToast(SafeConfigActivity.this, message);
			log.info("the error message is :"+message.getErrorCode());
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,long id)
	{
		if(parent==markSpinner)
		{
			
			//Toast.makeText(SafeConfigActivity.this, "mark"+markSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG);
			selMark=markSpinner.getSelectedItem().toString();
			log.info("mark"+selMark);
		}
		if(parent==ctrSpinner)
		{
			//Toast.makeText(SafeConfigActivity.this, "control"+ctrSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG);
			log.info("control"+ctrSpinner.getSelectedItem().toString());
			//selCtrID =ctrAdapter.getItem(ctrSpinner.getSelectedItemPosition()).getValue();
			selCtrSensorID=ctrAdapter.getItem(ctrSpinner.getSelectedItemPosition()).getCtrSensorID();
			selCtrChannelID= ctrAdapter.getItem(ctrSpinner.getSelectedItemPosition()).getCtrChannelID();
			//log.info("controlID"+selCtrID);
			log.info("selCtrSensorID is:"+selCtrSensorID+"; selCtrChannelID is"+selCtrChannelID);
			
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO Auto-generated method stub
		
	}
	//管理标签
	private void markManage()
	{
		Intent intent = new Intent();
		intent.setClass(SafeConfigActivity.this, MarkManageActivity.class);
		intent.putExtra("curMark", selMark);
		startActivity(intent);
		
	}
   



	public TextView getTxt_sensorID()
	{
		return txt_sensorID;
	}



	public EditText getTxt_desp()
	{
		return txt_desp;
	}

	public EditText getTxt_warn()
	{
		return txt_warn;
	}

	public EditText getTxt_error()
	{
		return txt_error;
	}

	@Override
	public void initRes() {
		// TODO Auto-generated method stub
		
	}


	
}
