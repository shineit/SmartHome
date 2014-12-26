package cn.fuego.smart.home.ui.safe;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.SensorSetCmdEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.service.SensorDataCache;
import cn.fuego.smart.home.ui.LoginActivity;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.model.SafeViewModel;
import cn.fuego.smart.home.ui.model.SpinnerDataModel;
import cn.fuego.smart.home.webservice.up.model.SetSensorReq;
import cn.fuego.smart.home.webservice.up.model.SetUserMarkReq;
import cn.fuego.smart.home.webservice.up.model.SetUserMarkRsp;
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
	
	private PopupWindow popupWindow=null; 
	private View view,popParent;  
	private String newMark;
	private ProgressDialog addPDialog,configPDialog;
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
		
		Button add_btn = (Button) findViewById(R.id.safe_add_btn);
		add_btn.setOnClickListener(this);
		add_btn.setTag(3);
		
		popParent= findViewById(R.id.safe_manage_head);
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
		case 3:showPopWindow(popParent);
			break;
		default:break;
		}
		
	}

	private void configSafe()
	{
		configPDialog =ProgressDialog.show(SafeConfigActivity.this, "请稍等", "正在提交数据……");
		SetSensorReq req = new SetSensorReq();
		req.setToken(MemoryCache.getToken());
		req.setCommand(SensorSetCmdEnum.MODIFY.getIntValue());
		HomeSensorJson homesensor= new HomeSensorJson();
		//后台通过id 索引
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
			configPDialog.dismiss();
			Intent intent = new Intent();
            //以下设置flag 有作用
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			SafeConfigActivity.this.setResult(10,intent);
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
    private void showPopWindow(View parent)
    {
      		  
		if (popupWindow == null)
		{
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			view = layoutInflater.inflate(R.layout.pop_window_add_mark, null);

			// 创建一个PopuWidow对象
			// popupWindow = new PopupWindow(view, 300, 350);
			popupWindow = new PopupWindow(view, getWindowManager()
					.getDefaultDisplay().getWidth(), getWindowManager()
					.getDefaultDisplay().getHeight());
		}

		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);

		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		popupWindow.setBackgroundDrawable(dw);

		
		final EditText txt_mark = (EditText) view.findViewById(R.id.pop_window_mark);
		
		Button sureBtn = (Button) view.findViewById(R.id.pop_window_sure_btn);
		sureBtn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				addPDialog =ProgressDialog.show(SafeConfigActivity.this, "请稍等", "正在提交数据……");

				newMark = txt_mark.getText().toString().trim();
				if (newMark != null && newMark.length() > 0)
				{
					SetUserMarkReq req = new SetUserMarkReq();
					req.setToken(MemoryCache.getToken());
					UserMarkJson userMark = new UserMarkJson();
					userMark.setMark(newMark);
					userMark.setUserID(MemoryCache.getLoginInfo().getUser()
							.getUserID());
					req.setUserMark(userMark);
					WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler()
							{
								@Override
								public void handle(MispHttpMessage message)
								{
									// SetUserMarkRsp rsp = (SetUserMarkRsp)
									// message.getMessage().obj;
									if (message.isSuccess())
									{
										log.info("标签新增成功");

										SensorDataCache.getInstance().getMarkList().add(newMark);
										//markList.add(newMark);
										markAdapter.notifyDataSetChanged();

										addPDialog.dismiss();
										popupWindow.dismiss();

									} else
									{
										super.sendMessage(message);
									}
								}
							}).addUserMark(req);

				} else
				{
					log.info("新增标签输入为空");
					Toast.makeText(SafeConfigActivity.this, "请输入用户标签！",
							Toast.LENGTH_LONG);
				}
			}
		});
		Button cancelBtn = (Button) view.findViewById(R.id.pop_window_cancel);
		cancelBtn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				popupWindow.dismiss();

			}
		});

		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		// 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
		int xPos = windowManager.getDefaultDisplay().getWidth() / 2
				- popupWindow.getWidth() / 2;

		popupWindow.showAsDropDown(parent, xPos, 0);

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


	
}
