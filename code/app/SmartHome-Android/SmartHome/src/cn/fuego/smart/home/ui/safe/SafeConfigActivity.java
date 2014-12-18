package cn.fuego.smart.home.ui.safe;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.service.SensorDataCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.model.SafeViewModel;
import cn.fuego.smart.home.ui.model.SpinnerDataModel;
import cn.fuego.smart.home.webservice.up.model.GetUserMarkListReq;
import cn.fuego.smart.home.webservice.up.model.GetUserMarkListRsp;
import cn.fuego.smart.home.webservice.up.model.base.UserMarkJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class SafeConfigActivity extends BaseActivtiy implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	private SafeViewModel safeViewModel = new SafeViewModel();
	private List<String> markList =  new ArrayList<String>();
	private List<SpinnerDataModel> ctrList = SensorDataCache.getInstance().getCtrSpinnerList();
	private ArrayAdapter<String> markAdapter;
	private ArrayAdapter<SpinnerDataModel> ctrAdapter;
	private Spinner markSpinner,ctrSpinner ;
	private String defaultLabel;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.safe_manage);
		ExitApplication.getInstance().addActivity(this);
		Intent intent = this.getIntent();
		initData(intent);

		
		Button back_btn=(Button)findViewById(R.id.safe_manage_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
	}
	
	private void initData(Intent intent)
	{
		TextView txt_concentID = (TextView) findViewById(R.id.safe_concnet_id);
		txt_concentID.setText(String.valueOf(intent.getIntExtra(safeViewModel.getConcentratorID(), 0)));	
		//txt_concentID.setText(intent.getStringExtra(safeViewModel.getConcentratorID()));	
		TextView txt_sensorID = (TextView) findViewById(R.id.safe_sensor_objID);
		txt_sensorID.setText(String.valueOf(intent.getIntExtra(safeViewModel.getId(), 0)));
		TextView txt_sensorType = (TextView) findViewById(R.id.safe_sensor_objType);
		txt_sensorType.setText(intent.getStringExtra(safeViewModel.getSensorTypeName()));
		
		TextView txt_desp = (TextView) findViewById(R.id.safe_desp);
		txt_desp.setText(intent.getStringExtra(safeViewModel.getDescriptions()));
		TextView txt_warn = (TextView) findViewById(R.id.safe_warnValue);
		txt_warn.setText(String.valueOf(intent.getIntExtra(safeViewModel.getWarnValue(), 0)));
		TextView txt_error = (TextView) findViewById(R.id.safe_errorValue);
		txt_error.setText(String.valueOf(intent.getIntExtra(safeViewModel.getErrorValue(), 0)));
	
		defaultLabel = intent.getStringExtra(safeViewModel.getMark());
		//int defaultCtrID = intent.getIntExtra(safeViewModel.getCtrGroupID(), 0);
		String defaultCtrID = intent.getStringExtra(safeViewModel.getCtrGroupID());
		log.info("The defaultCtrID is"+defaultCtrID);
		markSpinner =  (Spinner) findViewById(R.id.safe_mark_spinner);
		ctrSpinner = (Spinner) findViewById(R.id.safe_ctr_spinner);
        //获取标签栏选项数据
		markAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item , markList);
		//markAdapter.setDropDownViewResource(R.layout.dropdown_item);//自定义样式/
		markAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
		markSpinner.setAdapter(markAdapter);
		getMarkData();
		//获取控制器选项数据
		//ctrList = SensorDataCache.getInstance().getCtrSpinnerList();
		ctrAdapter = new ArrayAdapter<SpinnerDataModel>(this,android.R.layout.simple_spinner_item , ctrList);
		ctrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ctrSpinner.setAdapter(ctrAdapter);
		ctrSpinner.setSelection(getCtrPosition(defaultCtrID));
		ctrAdapter.notifyDataSetChanged();
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
    	
    	GetUserMarkListReq req  = new GetUserMarkListReq();
    	req.setToken(MemoryCache.getToken());
    	req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
    	WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler(){
    		@Override
    		public void handle(MispHttpMessage msg)
    		{
				if (msg.isSuccess())
				{
					GetUserMarkListRsp rsp = (GetUserMarkListRsp) msg.getMessage().obj;
					List<UserMarkJson> userMarkList = rsp.getMarkList();
					for(int i=0;i<userMarkList.size();i++)
					{
						markList.add(userMarkList.get(i).getMark());
						
					}
					log.info("markList is "+markList);
					markSpinner.setSelection(getSelPosition(defaultLabel));
					markAdapter.notifyDataSetChanged();
				}
				else
				{
					super.sendMessage(msg);
				}
    		}
    	}).getUserMarkList(req);
    
    	
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

		default:break;
		}
		
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}
}
