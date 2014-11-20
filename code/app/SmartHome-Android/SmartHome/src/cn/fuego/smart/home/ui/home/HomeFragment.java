package cn.fuego.smart.home.ui.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsListRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

import com.fuego.smarthome.R;

public class HomeFragment extends Fragment implements OnCheckedChangeListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	

	private static final int[] alarmViewAttrs = new int[]
	{ R.id.item_alarm_icon, R.id.item_alarm_title,
			R.id.item_alarm_content, R.id.item_alarm_status,
			R.id.item_alarm_time };
	
	private static final String[] alarmItemAttrs = new String[] { "icon", "title", "content", "status", "time" };
	
	private static final int[] newsViewAttrs= new int[]
	{
		R.id.item_news_title,R.id.item_news_time
	};
	private static final String[] newsItemAttrs = new String[]{"title","time"};
	private String[] values = new String[]
	{ "侏儒", "人类", "暗夜精灵", "矮人", "德莱尼", "狼人" };

    private ListView alarmViewList ;
    private ListView newsViewList ;
    private List<Map<String, Object>> newsItems = new ArrayList<Map<String, Object>>();
    private SimpleAdapter adapterNews ;
    private List<Map<String, Object>> alarmItems = new ArrayList<Map<String, Object>>();
    private SimpleAdapter adapterAlarm ;
    private int flag=0;
   
    private Handler handle ;
    

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
	    newsViewList.setAdapter(adapterNews);
	    //默认初始界面为告警信息
        if(flag==0)
        {
        	updateAlarms();
        	flag=1;
        }
        handle = new Handler(){
			@Override
			public void handleMessage(Message msg){
			//你要执行的方法
			//执行完毕后给handler发送一个空消息
				switch (msg.what)
				{
					//定义0对应新闻公告消息处理
					case 0:adapterNews.notifyDataSetChanged();
					//定义1对应告警线程处理
					case 1:adapterAlarm.notifyDataSetChanged();
				
				}
				
				 
			}
			};
	    
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
			
			updateAlarms();
			alarmViewList.setVisibility(View.VISIBLE);
			newsViewList.setVisibility(View.INVISIBLE);
		}
		if (radioButtonId == R.id.nav_news)
		{
			
			updateNews();
			alarmViewList.setVisibility(View.INVISIBLE);
			newsViewList.setVisibility(View.VISIBLE);
			
		}

	}
	
	private void updateAlarms()
	{
		GetHistoryAlarmListReq req = new GetHistoryAlarmListReq();
		req.setUserID(1);
		WebServiceContext.getInstance().getSensorManageRest(new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				 alarmItems.clear();
				 GetHistoryAlarmListRsp rsp = (GetHistoryAlarmListRsp)msg.obj;
				 for(AlarmJson json : rsp.getAlarmList()){
					 Map<String, Object> listItem = new HashMap<String, Object>();
						listItem.put(alarmItemAttrs[0], R.drawable.smoke);
						listItem.put(alarmItemAttrs[1], ""+json.getAlarmType());
						listItem.put(alarmItemAttrs[2], null);
						listItem.put(alarmItemAttrs[3], null);
						listItem.put(alarmItemAttrs[4], null);
						alarmItems.add(listItem);
					 
				 }
				//alarmViewList.setAdapter(adapterAlarm);
				adapterAlarm.notifyDataSetChanged();
			}
		}).setSensor(req);
	}
 
	private void updateNews()
	{
		GetNewsListReq req = new GetNewsListReq();
		req.setToken(MemoryCache.getToken());
		WebServiceContext.getInstance().getNewsManageRest(new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				newsItems.clear();
				GetNewsListRsp rsp = (GetNewsListRsp)msg.obj;
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
}
