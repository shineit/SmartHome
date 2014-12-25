package cn.fuego.smart.home.ui.safe;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.constant.SensorSetCmdEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.service.SensorDataCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.model.SafeViewModel;
import cn.fuego.smart.home.ui.model.SpinnerDataModel;
import cn.fuego.smart.home.webservice.up.model.GetUserMarkListReq;
import cn.fuego.smart.home.webservice.up.model.GetUserMarkListRsp;
import cn.fuego.smart.home.webservice.up.model.SetSensorReq;
import cn.fuego.smart.home.webservice.up.model.SetSensorRsp;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.up.model.base.UserMarkJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class SafeConfigActivity extends BaseActivtiy implements OnClickListener, OnItemSelectedListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	private SafeViewModel safeViewModel = new SafeViewModel();
	private List<String> markList =  null;
	private List<SpinnerDataModel> ctrList=null ;
	private ArrayAdapter<String> markAdapter;
	private ArrayAdapter<SpinnerDataModel> ctrAdapter;
	private Spinner markSpinner,ctrSpinner ;
	private String defaultLabel;
	
	private TextView txt_concentID,txt_sensorID,txt_sensorType;
	private EditText txt_desp,txt_warn,txt_error;
	private String selCtrID,selMark;
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
		config_btn.setOnClickListener(this);
		config_btn.setTag(2);
		
		Button add_btn = (Button) findViewById(R.id.safe_add_btn);
		add_btn.setOnClickListener(this);
		add_btn.setTag(3);
	}
	
	private void initData(Intent intent)
	{
		txt_concentID = (TextView) findViewById(R.id.safe_concnet_id);
		txt_concentID.setText(String.valueOf(intent.getIntExtra(safeViewModel.getConcentratorID(), 0)));	
		//txt_concentID.setText(intent.getStringExtra(safeViewModel.getConcentratorID()));	
		txt_sensorID = (TextView) findViewById(R.id.safe_sensor_objID);
		txt_sensorID.setText(String.valueOf(intent.getIntExtra(safeViewModel.getId(), 0)));
		txt_sensorType = (TextView) findViewById(R.id.safe_sensor_objType);
		txt_sensorType.setText(intent.getStringExtra(safeViewModel.getSensorTypeName()));
		
		txt_desp = (EditText) findViewById(R.id.safe_desp);
		txt_desp.setText(intent.getStringExtra(safeViewModel.getDescriptions()));
		txt_warn = (EditText) findViewById(R.id.safe_warnValue);
		txt_warn.setText(String.valueOf(intent.getIntExtra(safeViewModel.getWarnValue(), 0)));
		txt_error = (EditText) findViewById(R.id.safe_errorValue);
		txt_error.setText(String.valueOf(intent.getIntExtra(safeViewModel.getErrorValue(), 0)));
	
		defaultLabel = intent.getStringExtra(safeViewModel.getMark());
		//int defaultCtrID = intent.getIntExtra(safeViewModel.getCtrGroupID(), 0);
		String defaultCtrID = intent.getStringExtra(safeViewModel.getCtrGroupID());
		log.info("The defaultCtrID is"+defaultCtrID);
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
		//getMarkData();
		markSpinner.setOnItemSelectedListener(this);
		
		//获取控制器选项数据
		//ctrList = SensorDataCache.getInstance().getCtrSpinnerList();
		if(ctrList==null)
		{
			ctrList= SensorDataCache.getInstance().getCtrSpinnerList();
		}
		ctrAdapter = new ArrayAdapter<SpinnerDataModel>(this,android.R.layout.simple_spinner_item , ctrList);
		ctrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ctrSpinner.setAdapter(ctrAdapter);
		ctrSpinner.setSelection(getCtrPosition(defaultCtrID));
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
    private int getCtrPosition(String ctrID)
    {
		int index=0;
		log.info("The ctrList is"+ctrList);
		for(int i=0;i<ctrList.size();i++)
		{
			if(ctrID.equals(ctrList.get(i).getValue()))
			{
				index=i;
				return index;
				
			}
		}
    	return index;
    	
    }
	private void  getMarkData()
    {
    	
		markList= SensorDataCache.getInstance().getMarkList();
		log.info("markList is "+markList);
		markSpinner.setSelection(getSelPosition(defaultLabel));
		markAdapter.notifyDataSetChanged();
    
    	
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
		case 3:
			break;
		default:break;
		}
		
	}

	private void configSafe()
	{
		SetSensorReq req = new SetSensorReq();
		req.setToken(MemoryCache.getToken());
		req.setCommand(SensorSetCmdEnum.MODIFY.getIntValue());
		HomeSensorJson homesensor= new HomeSensorJson();
		//homesensor.setConcentratorID(Integer.valueOf(this.getTxt_concentID().getText().toString()));
		homesensor.setId(Integer.valueOf(this.getTxt_sensorID().getText().toString()));
		homesensor.setDescriptions(this.getTxt_desp().getText().toString().trim());
		//后台不作处理
		homesensor.setWarnValue(Float.parseFloat(this.getTxt_warn().getText().toString().trim()));
		homesensor.setErrorValue(Float.parseFloat(this.getTxt_error().getText().toString().trim()));
		
		//spinner 标签选中项
		homesensor.setMark(selMark);
		homesensor.setCtrGroupID(selCtrID);
		req.setSensor(homesensor);
		
		WebServiceContext.getInstance().getSensorManageRest(this).setSensor(req);
		
		
	}
    
	//配置传感器线程
	@Override
	public void handle(MispHttpMessage message)
	{
		
		//SetSensorRsp rsp = (SetSensorRsp) message.getMessage().obj;
		if(message.isSuccess())
		{

			Intent intent = new Intent();
			//intent.setClass(this.getApplicationContext(),  SafeFragment.this.getClass());
			//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
          // startActivity(intent); 
			//intent.se
            this.finish();
		}

		else
		{
			super.sendMessage(message);
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
			selCtrID =ctrAdapter.getItem(ctrSpinner.getSelectedItemPosition()).getValue();
			log.info("controlID"+selCtrID);
			
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO Auto-generated method stub
		
	}

	public TextView getTxt_concentID()
	{
		return txt_concentID;
	}

	public TextView getTxt_sensorID()
	{
		return txt_sensorID;
	}

	public TextView getTxt_sensorType()
	{
		return txt_sensorType;
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


	
}
