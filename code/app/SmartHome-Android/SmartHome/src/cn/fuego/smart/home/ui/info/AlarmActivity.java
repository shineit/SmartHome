package cn.fuego.smart.home.ui.info;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.base.HomeAlarmJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class AlarmActivity extends MispListActivity<HomeAlarmJson> implements OnClickListener
{
	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.alarm);
		
		this.listViewRes.setListView(R.id.alarmlist);
		this.listViewRes.setListItemView(R.layout.alarm_item);
		this.listViewRes.setClickActivityClass(AlarmManageActivity.class);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Button back_btn= (Button) findViewById(R.id.alarm_back);
		back_btn.setTag(1);
		back_btn.setOnClickListener(this);
	}

	@Override
	public void loadSendList()
	{
		GetHistoryAlarmListReq req = new GetHistoryAlarmListReq();
		req.setToken(MemoryCache.getToken());
		req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		WebServiceContext.getInstance().getSensorManageRest(this).getAlarmList(req);
		
	}

	@Override
	public List<HomeAlarmJson> loadListRecv(Object obj)
	{
		GetHistoryAlarmListRsp rsp = (GetHistoryAlarmListRsp) obj;
	
		return rsp.getAlarmList();
	}

	@Override
	public View getListItemView(View view, HomeAlarmJson item)
	{
		ImageView icon= (ImageView) view.findViewById(R.id.item_alarm_icon);
		if(AlarmTypeEnum.getEnumByInt(item.getAlarmType())==AlarmTypeEnum.FIRE_ALARM)
		{
			//icon.setBackgroundResource(R.drawable.fire);
			icon.setImageResource(R.drawable.fire);
		}
		else
		{
			//icon.setBackgroundResource(R.drawable.prealarm);
			icon.setImageResource(R.drawable.prealarm);
		}
		
		TextView txt_title= (TextView) view.findViewById(R.id.item_alarm_title);
		txt_title.setText(AlarmTypeEnum.getEnumByInt(item.getAlarmType()).getStrValue());
		
		TextView txt_time= (TextView) view.findViewById(R.id.item_alarm_time);
		txt_time.setText(DateUtil.getStrTime(item.getAlarmTime()));
		
		return view;
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		switch(tag)
		{
		case 1: this.finish();
				break;

		default:break;
		}
		
	}


}
