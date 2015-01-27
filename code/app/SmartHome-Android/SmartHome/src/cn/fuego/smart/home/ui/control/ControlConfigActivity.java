package cn.fuego.smart.home.ui.control;

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
import cn.fuego.smart.home.ui.model.ControlViewModel;
import cn.fuego.smart.home.ui.setting.user.MarkManageActivity;
import cn.fuego.smart.home.webservice.up.model.SetSensorReq;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class ControlConfigActivity extends BaseActivtiy implements OnClickListener, OnItemSelectedListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());

    private ControlViewModel ctrViewModel = new ControlViewModel();
    private ProgressDialog configPDialog;
    private TextView txt_id;
    private EditText  txt_desp,txt_mark,txt_groupID;
	private List<String> markList =  null;
	private ArrayAdapter<String> markAdapter;
	private Spinner markSpinner;
	private String selMark;	
	private String targetID;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_manage);
		ExitApplication.getInstance().addActivity(this);
		Intent intent = this.getIntent();
		initData(intent);

		
		Button back_btn=(Button)findViewById(R.id.control_manage_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		
		Button config_btn = (Button) findViewById(R.id.control_config_btn);
		config_btn.setOnClickListener(this);
		config_btn.setTag(2);
		//设置首个聚焦
		config_btn.requestFocus();
		config_btn.requestFocusFromTouch();
		
		Button mark_btn = (Button) findViewById(R.id.control_mark_btn);
		mark_btn.setOnClickListener(this);
		mark_btn.setTag(3);
	}

	private void initData(Intent intent)
	{
		targetID=ctrViewModel.getId();
		TextView txt_concentID= (TextView) findViewById(R.id.control_manage_concentID);
		//txt_concentID.setText(String.valueOf(intent.getIntExtra(ctrViewModel.getConcentratorID(), 0)));
		txt_concentID.setText(intent.getStringExtra(ctrViewModel.getConcentratorID()));
		txt_id =(TextView) findViewById(R.id.control_manage_ctrID);
		//txt_id.setText(String.valueOf(intent.getIntExtra(ctrViewModel.getId(), 0)));
		txt_id.setText(intent.getStringExtra(ctrViewModel.getSensorID()));
		TextView txt_type= (TextView) findViewById(R.id.control_manage_ctrType);
		txt_type.setText(intent.getStringExtra(ctrViewModel.getSensorTypeName()));
		
		txt_desp = (EditText) findViewById(R.id.control_manage_desp);
		txt_desp.setText(intent.getStringExtra(ctrViewModel.getDescriptions()));
		
		String defaultLabel = intent.getStringExtra(ctrViewModel.getMark());
/*		txt_mark= (EditText) findViewById(R.id.control_manage_mark);
		txt_mark.setText(defaultMark);*/
		markSpinner =  (Spinner) findViewById(R.id.control_mark_spinner);

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
		txt_groupID = (EditText) findViewById(R.id.control_manage_groupID);
		txt_groupID.setText(String.valueOf(intent.getIntExtra(ctrViewModel.getGroupID(), 0)));

		
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
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,long id)
	{
		selMark=markSpinner.getSelectedItem().toString();
		log.info("mark"+selMark);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO Auto-generated method stub
		
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
		case 2: 
			configCtr();
				break;
		case 3: 
			markManage();
				break;
		default:break;
		}
		
	}



	private void configCtr()
	{
		configPDialog =ProgressDialog.show(ControlConfigActivity.this, "请稍等", "正在提交数据……");
		SetSensorReq req = new SetSensorReq();
		req.setToken(MemoryCache.getToken());
		req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		req.setCommand(SensorSetCmdEnum.MODIFY.getIntValue());
		HomeSensorJson homesensor= new HomeSensorJson();
		//后台通过id 索引
		homesensor.setId(Long.valueOf(targetID));
		homesensor.setDescriptions(this.getTxt_desp().getText().toString().trim());
		//后台不作处理
		
		//spinner 标签选中项
		homesensor.setMark(selMark);
		homesensor.setGroupID(Integer.valueOf(this.getTxt_groupID().getText().toString().trim()));
		req.setSensor(homesensor);
		
		WebServiceContext.getInstance().getSensorManageRest(this).setSensor(req);
		
	}
	@Override
	public void handle(MispHttpMessage message)
	{
		if(message.isSuccess())
		{
			configPDialog.dismiss();
			Intent intent = new Intent();
            //以下设置flag 有作用
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			ControlConfigActivity.this.setResult(IntentCodeConst.RESULT_CODE,intent);
			this.finish();
            
		}

		else
		{
			configPDialog.dismiss();
			showToast(ControlConfigActivity.this, message);
		}
		
	}
	//管理标签
	private void markManage()
	{
		Intent intent = new Intent();
		intent.setClass(ControlConfigActivity.this, MarkManageActivity.class);
		intent.putExtra("curMark", selMark);
		startActivity(intent);
		
	}
	public TextView getTxt_id()
	{
		return txt_id;
	}

	public EditText getTxt_desp()
	{
		return txt_desp;
	}

	public EditText getTxt_mark()
	{
		return txt_mark;
	}

	public EditText getTxt_groupID()
	{
		return txt_groupID;
	}




	
}
