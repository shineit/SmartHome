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
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.AlarmClearEnum;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.base.BaseFragment;
import cn.fuego.smart.home.ui.base.GetDetail;
import cn.fuego.smart.home.ui.model.NewsViewModel;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsListRsp;
import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class HomeFragment extends BaseFragment implements OnCheckedChangeListener,OnItemClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
    private GetDetail getDetail = new GetDetail();
	//private ProgressDialog proDialog;

    //private AlarmViewModel alarmModel= new AlarmViewModel();
    private NewsViewModel newsModel = new NewsViewModel();
	private static final int[] alarmViewAttrs = new int[]
	{ R.id.item_alarm_icon, R.id.item_alarm_title,
			R.id.item_alarm_content, R.id.item_alarm_status,
			R.id.item_alarm_time,R.id.alarm_view_id ,R.id.alarm_view_objID,R.id.alarm_manage_objType,R.id.alarm_view_value};
	
	private static final String[] alarmItemAttrs = new String[] 
			{ "icon", "title", "content", "status", "time","eventID","objID","obj","alarmValue"};
	
	private static final int[] newsViewAttrs= new int[]
	{
		R.id.item_news_title,R.id.item_news_time,R.id.item_news_content,R.id.item_news_author
	};
	private static final String[] newsItemAttrs = new String[]{"title","time","content","author"};

    private ListView alarmViewList ;
    private ListView newsViewList ;
    private List<Map<String, Object>> newsItems = new ArrayList<Map<String, Object>>();
    private SimpleAdapter adapterNews ;
    private List<Map<String, Object>> alarmItems = new ArrayList<Map<String, Object>>();
    private SimpleAdapter adapterAlarm ;

    //private int listID=0;// 用于判断当前listview


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
	    // 切换radiobutton监听
		RadioGroup group = (RadioGroup) rootView.findViewById(R.id.nav_group);
		group.setOnCheckedChangeListener(this);
	    //默认初始界面为告警信息
        if(MemoryCache.getFlag()==0)
        {
        	updateAlarms();
        	MemoryCache.setFlag(1);
        }
        //首页优先显示新闻公告
        if(MemoryCache.getFlag()==2)
        {
        	MemoryCache.setFlag(1);
        	group.check(R.id.nav_news);
        }
        

		return rootView;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{


		
		int radioButtonId = group.getCheckedRadioButtonId();
		if (radioButtonId == R.id.nav_alarm)
		{   
			//listID=0;
			updateAlarms();
			alarmViewList.setVisibility(View.VISIBLE);
			newsViewList.setVisibility(View.INVISIBLE);
			
		}
		if (radioButtonId == R.id.nav_news)
		{
			//listID=1;
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
						//告警信息ID，用于索引，在页面不显示
						listItem.put(alarmItemAttrs[5], json.getId());
						listItem.put(alarmItemAttrs[6], json.getObjID());
						//listItem.put(alarmItemAttrs[7], SensorKindEunm.getEnumByInt(json.getObjType()).getStrValue());
						//listItem.put(alarmItemAttrs[8], json.getDataValue());
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
					listItem.put(newsItemAttrs[2], json.getContent());
					listItem.put(newsItemAttrs[3], json.getAuthor());
					newsItems.add(listItem);
					
				}
				adapterNews.notifyDataSetChanged();

			}
		}).getNewsList(req);
		
	}



	@Override
	public void onItemClick(AdapterView<?> parent,View view, int position, long id)
	{
		//Toast toast ;
		if(parent==alarmViewList)
		{ 
			
			HashMap<String, Object> selectAlarm = (HashMap<String, Object>) alarmItems.get(position); 
			if(selectAlarm!=null)
			{
			    AlarmJson alarm = new AlarmJson();//传递需要以下三个参数
			    alarm.setId(Integer.valueOf(selectAlarm.get(alarmItemAttrs[5]).toString()));//告警事件ID
			    alarm.setAlarmType(AlarmTypeEnum.getEnumByStr(selectAlarm.get(alarmItemAttrs[1]).toString()).getIntValue());//告警类型
			    alarm.setObjID(Integer.valueOf(selectAlarm.get(alarmItemAttrs[6]).toString()));//传感器ID

			    getDetail.showHomeSensor(this.getActivity(), alarm);

			}
			

			
		}
		if(parent==newsViewList)
		{
			HashMap<String, Object> selectNews = (HashMap<String, Object>) newsItems.get(position); 
			if(selectNews!=null)
			{
				Intent intent = new Intent(this.getActivity(),NewsViewActivity.class);
				intent.putExtra(newsModel.getTitle(),selectNews.get(newsItemAttrs[0]).toString());
				intent.putExtra(newsModel.getContent(),selectNews.get(newsItemAttrs[2]).toString());
				intent.putExtra(newsModel.getAuthor(),selectNews.get(newsItemAttrs[3]).toString());
				this.startActivity(intent); 
				
			}
		}
		
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}
}
