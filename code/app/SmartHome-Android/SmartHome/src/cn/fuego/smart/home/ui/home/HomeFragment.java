package cn.fuego.smart.home.ui.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.constant.AlarmClearEnum;
import cn.fuego.smart.home.constant.AlarmObjTypeEnmu;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.base.BaseFragment;
import cn.fuego.smart.home.ui.model.AlarmViewModel;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsListRsp;
import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

import com.fuego.smarthome.R;

public class HomeFragment extends BaseFragment implements OnCheckedChangeListener,OnItemClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	
    private AlarmViewModel alarmModel= new AlarmViewModel();
	private static final int[] alarmViewAttrs = new int[]
	{ R.id.item_alarm_icon, R.id.item_alarm_title,
			R.id.item_alarm_content, R.id.item_alarm_status,
			R.id.item_alarm_time,R.id.alarm_view_id ,R.id.alarm_view_objID,R.id.alarm_manage_objType,R.id.alarm_view_value};
	
	private static final String[] alarmItemAttrs = new String[] 
			{ "icon", "title", "content", "status", "time","eventID","objID","obj","alarmValue"};
	
	private static final int[] newsViewAttrs= new int[]
	{
		R.id.item_news_title,R.id.service_id
	};
	private static final String[] newsItemAttrs = new String[]{"title","time"};

    private ListView alarmViewList ;
    private ListView newsViewList ;
    private List<Map<String, Object>> newsItems = new ArrayList<Map<String, Object>>();
    private SimpleAdapter adapterNews ;
    private List<Map<String, Object>> alarmItems = new ArrayList<Map<String, Object>>();
    private SimpleAdapter adapterAlarm ;
    private int flag=0;//判断进入页面方式，0-表示首次进入，1-其他切换进入
    private int listID=0;// 用于判断当前listview


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		 
		View rootView = inflater.inflate(R.layout.home_fragment, null);
	    alarmViewList = (ListView) rootView .findViewById(R.id.alarmlist);
	    newsViewList = (ListView) rootView .findViewById(R.id.newslist);
	    adapterNews = new SimpleAdapter(getActivity(),newsItems,R.layout.news_item,newsItemAttrs, newsViewAttrs);
	    adapterAlarm = new SimpleAdapter(getActivity(),alarmItems,R.layout.alarm_item,alarmItemAttrs, alarmViewAttrs);
	    alarmViewList.setAdapter(adapterAlarm);
	    alarmViewList.setOnItemClickListener(this);
	    newsViewList.setAdapter(adapterNews);
	    newsViewList.setOnItemClickListener(this);
	    //默认初始界面为告警信息
        if(flag==0)
        {
        	updateAlarms();
        	flag=1;
        }
        
	    // 切换radiobutton监听
		RadioGroup group = (RadioGroup) rootView.findViewById(R.id.nav_group);
		group.setOnCheckedChangeListener(this);
		return rootView;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{


		
		int radioButtonId = group.getCheckedRadioButtonId();
		if (radioButtonId == R.id.nav_alarm)
		{   
			listID=0;
			updateAlarms();
			alarmViewList.setVisibility(View.VISIBLE);
			newsViewList.setVisibility(View.INVISIBLE);
			
		}
		if (radioButtonId == R.id.nav_news)
		{
			listID=1;
			updateNews();
			alarmViewList.setVisibility(View.INVISIBLE);
			newsViewList.setVisibility(View.VISIBLE);
			
		}

	}
	
	private void updateAlarms()
	{
		GetHistoryAlarmListReq req = new GetHistoryAlarmListReq();
		req.setUserID(1);
		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage msg) {
				// TODO Auto-generated method stub
			 
				 alarmItems.clear();
				 GetHistoryAlarmListRsp rsp = (GetHistoryAlarmListRsp)msg.getMessage().obj;
				 for(AlarmJson json : rsp.getAlarmList()){
					 Map<String, Object> listItem = new HashMap<String, Object>();
						//String[] alarmIcon =getResources().getStringArray(R.array.alarm_icons);
						TypedArray alarmIcon=getResources().obtainTypedArray(R.array.alarm_icons);
						listItem.put(alarmItemAttrs[0], alarmIcon.getResourceId(json.getAlarmType(), 0));
						listItem.put(alarmItemAttrs[1], AlarmTypeEnum.getEnumByInt(json.getAlarmType()).getStrValue());
						listItem.put(alarmItemAttrs[2], null);
						listItem.put(alarmItemAttrs[3], AlarmClearEnum.getEnumByInt(json.getClearStatus()).getStrValue());
						listItem.put(alarmItemAttrs[4], DateUtil.getStrTime(json.getAlarmTime()));
						listItem.put(alarmItemAttrs[5], json.getId());//告警信息ID，用于索引，在页面不显示
						listItem.put(alarmItemAttrs[6], json.getObjID());
						listItem.put(alarmItemAttrs[7], AlarmObjTypeEnmu.getEnumByInt(json.getObjType()).getStrValue());
						listItem.put(alarmItemAttrs[8], json.getDataValue());
						alarmItems.add(listItem);
				 }
				//alarmViewList.setAdapter(adapterAlarm);
				 
				adapterAlarm.notifyDataSetChanged();
			}
		}).getAlarmList(req);
	}
 
	private void updateNews()
	{
		GetNewsListReq req = new GetNewsListReq();
		req.setToken(MemoryCache.getToken());
		WebServiceContext.getInstance().getNewsManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage msg) {
				// TODO Auto-generated method stub
			 
				newsItems.clear();
				GetNewsListRsp rsp = (GetNewsListRsp)msg.getMessage().obj;
				for(NewsJson json : rsp.getNewsList())
				{
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put(newsItemAttrs[0], json.getTitle());
					listItem.put(newsItemAttrs[1], DateUtil.getStrTime(json.getDate()));
					//listItem.put(newsItemAttrs[1], json.getDate());
					newsItems.add(listItem);
					
				}
				adapterNews.notifyDataSetChanged();
			}
		}).getNewsList(req);
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		alarmItems.clear();
		 GetHistoryAlarmListRsp rsp = (GetHistoryAlarmListRsp)message.getMessage().obj;
		 for(AlarmJson json : rsp.getAlarmList()){
			 Map<String, Object> listItem = new HashMap<String, Object>();
				//String[] alarmIcon =getResources().getStringArray(R.array.alarm_icons);
				TypedArray alarmIcon=getResources().obtainTypedArray(R.array.alarm_icons);
				listItem.put(alarmItemAttrs[0], alarmIcon.getResourceId(json.getAlarmType(), 0));
				listItem.put(alarmItemAttrs[1], AlarmTypeEnum.getEnumByInt(json.getAlarmType()).getStrValue());
				listItem.put(alarmItemAttrs[2], null);
				listItem.put(alarmItemAttrs[3], AlarmClearEnum.getEnumByInt(json.getClearStatus()).getStrValue());
				listItem.put(alarmItemAttrs[4], DateUtil.getStrTime(json.getAlarmTime()));
				alarmItems.add(listItem);
		 }
		//alarmViewList.setAdapter(adapterAlarm);
		adapterAlarm.notifyDataSetChanged();
		
	}

	@Override
	public void onItemClick(AdapterView<?>parent,View view, int position, long id)
	{
		//Toast toast ;
		if(listID==0)
		{ 
			
			HashMap<String, Object> selectAlarm = (HashMap<String, Object>) alarmItems.get(position); 
			if(selectAlarm!=null)
			{
				Intent intent = new Intent(this.getActivity(),AlarmManageActivity.class);
				//int selected_alarm_id=(Integer) selectAlarm.get(alarmItemAttrs[5]);
				//int selected_obj_id =(Integer) selectAlarm.get(alarmItemAttrs[6]);
				intent.putExtra(alarmModel.getTitle(),selectAlarm.get(alarmItemAttrs[1]).toString());
				intent.putExtra(alarmModel.getEventID(),selectAlarm.get(alarmItemAttrs[5]).toString());
				//intent.putExtra(alarmModel.getObjID(), selected_alarm_id);
				intent.putExtra(alarmModel.getObjID(), selectAlarm.get(alarmItemAttrs[6]).toString());
				intent.putExtra(alarmModel.getObj(),selectAlarm.get(alarmItemAttrs[7]).toString());

				//Toast.makeText(this.getActivity(), selectAlarm.get(keyString[5]).toString(), Toast.LENGTH_SHORT).show();
				this.startActivity(intent); 
			}
			

			
		}
		if(listID==1)
		{
			Toast.makeText(this.getActivity(), "newsList positon is"+position, Toast.LENGTH_SHORT).show();
		}
		
	}
}
