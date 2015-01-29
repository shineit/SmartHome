package cn.fuego.smart.home;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.ListViewResInfo;
import cn.fuego.smart.home.constant.AlarmClearEnum;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.info.AlarmManageActivity;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.base.HomeAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class AlarmPageActivity extends BaseActivtiy implements OnClickListener, OnScrollListener, OnItemClickListener 
{

	private FuegoLog log = FuegoLog.getLog(getClass());
	private SimpleAdapter mSimpleAdapter;
    private ListView lv;
    private ProgressBar pg;
    private ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();;
    // ListView底部View
    private View moreView;
    private Handler mhandler=new Handler();
    // 设置一个最大的数据条数，超过即不再加载
    private int MaxDateNum=50;
    // 最后可见条目的索引
    private int lastVisibleIndex;
    //自定义每页显示数量
    private int defPageSize=10;
    //显示第几页
    private int curPage=1;
    //标记是否完成加载
    private boolean isfinished=false;
    //数据源
    private ArrayList<HomeAlarmJson> datasource=new ArrayList<HomeAlarmJson>();
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        Button back_btn= (Button) findViewById(R.id.alarm_back);
        back_btn.setOnClickListener(this);
        lv = (ListView) findViewById(R.id.alarmlist);
        
        // 实例化底部布局
        moreView = getLayoutInflater().inflate(R.layout.listview_moredata, null);
        pg = (ProgressBar) moreView.findViewById(R.id.listview_moredata_pg);
        // 实例化SimpleAdapter
        mSimpleAdapter = new SimpleAdapter(this, list, R.layout.alarm_item,
                new String[] { "icon", "title","status","time"},
                new int[] { R.id.item_alarm_icon,R.id.item_alarm_title, R.id.item_alarm_status,R.id.item_alarm_time });
        // 加上底部View，注意要放在setAdapter方法前
        lv.addFooterView(moreView);
        lv.setAdapter(mSimpleAdapter);
        loadMoreDate();
        // 绑定监听器
        lv.setOnScrollListener(this);
        lv.setOnItemClickListener(this);


    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		HomeAlarmJson selectItem = datasource.get(position);
		if(selectItem!=null)
		{
			Bundle mbundle = new Bundle();
			mbundle.putSerializable(ListViewResInfo.SELECT_ITEM, selectItem);
			Intent i = new Intent();   
	        i.putExtras(mbundle);
			i.setClass(AlarmPageActivity.this, AlarmManageActivity.class);
			this.startActivity(i);
		}
		
	}
    private void loadMoreDate() 
    {
		GetHistoryAlarmListReq req = new GetHistoryAlarmListReq();
		req.setToken(MemoryCache.getToken());
		req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		PageJson page = new PageJson();
		page.setPageSize(defPageSize);
		page.setCurrentPage(curPage);
		req.setPage(page);		
		WebServiceContext.getInstance().getSensorManageRest(this).getAlarmList(req);
    }

    @Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
			curPage=curPage+1;
			GetHistoryAlarmListRsp rsp =(GetHistoryAlarmListRsp) message.getMessage().obj;
			
			if(!ValidatorUtil.isEmpty(rsp.getAlarmList()))
			{
				
				for(HomeAlarmJson json:rsp.getAlarmList())
				{
					datasource.add(json);
					HashMap<String, Object> map = new HashMap<String, Object>();
					if(AlarmTypeEnum.getEnumByInt(json.getAlarmType())==AlarmTypeEnum.FIRE_ALARM)
					{
						map.put("icon", R.drawable.fire);
					}
					else
					{
						map.put("icon", R.drawable.prealarm);
					}
					map.put("title", json.getSensorTypeName()+AlarmTypeEnum.getEnumByInt(json.getAlarmType()).getStrValue());
		            map.put("status", AlarmClearEnum.getEnumByInt(json.getClearStatus()).getStrValue());
		            map.put("time", DateUtil.getStrTime((json.getAlarmTime())));
		            list.add(map);
				}
				mSimpleAdapter.notifyDataSetChanged();
			}
			else
			{
				showToast(AlarmPageActivity.this, "数据全部加载完成，没有更多数据！");
				isfinished=true;
			}
			
			
		}
		else
		{

			showToast(this, message);
		}
		pg.setVisibility(View.GONE);

	}

	@Override
    public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount)
	{
        // 计算最后可见条目的索引
        lastVisibleIndex = firstVisibleItem + visibleItemCount - 1;
        // 所有的条目已经和最大条数相等，则移除底部的View
        if (totalItemCount == MaxDateNum + 1) 
        {
            lv.removeFooterView(moreView);
            showToast(this, "没有更多可以查看的数据！");
        }

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        // 滑到底部后自动加载，判断listview已经停止滚动并且最后可视的条目等于adapter的条目
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE&& lastVisibleIndex == mSimpleAdapter.getCount())
        {
            if (!isfinished) 
			{
            	// 当滑到底部时自动加载
    			pg.setVisibility(View.VISIBLE);
    			mhandler.postDelayed(new Runnable()
    			{

    				@Override
    				public void run()
    				{
    					loadMoreDate();

    				}

    			}, 2000);
			}
            else
            {
            	showToast(AlarmPageActivity.this, "数据全部加载完成，没有更多数据！");
            }


        }

    }

	@Override
	public void onClick(View v)
	{
		this.finish();
		
	}
}
