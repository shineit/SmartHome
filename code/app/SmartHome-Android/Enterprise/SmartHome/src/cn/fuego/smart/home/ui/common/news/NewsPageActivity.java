package cn.fuego.smart.home.ui.common.news;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.webservice.up.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsListRsp;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class NewsPageActivity extends MispListActivity<NewsJson> implements OnScrollListener
{
    private ListView lv;
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

	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.news);
		this.activityRes.setName("新闻公告");
		
		this.listViewRes.setListView(R.id.newslist);
		this.listViewRes.setListItemView(R.layout.news_item);
		this.listViewRes.setClickActivityClass(NewsViewActivity.class);
		
		this.setPage(true);
	}

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        lv = (ListView) findViewById(R.id.newslist);       
        // 绑定监听器
        lv.setOnScrollListener(this);


    }
	@Override
	public View getListItemView(View view, NewsJson item)
	{
		TextView title= (TextView) view.findViewById(R.id.item_news_title);
		title.setText(item.getTitle());
		
		TextView time= (TextView) view.findViewById(R.id.item_news_time);
		time.setText(DateUtil.getStrTime(item.getDate()));
		
		return view;
	}

	@Override
	public void loadSendList()
	{
		this.waitDailog.show();
		GetNewsListReq req = new GetNewsListReq();
		req.setOrg_id(AppCache.getInstance().getUser().getOrg_id());
		PageJson page = new PageJson();
		page.setPageSize(defPageSize);
		page.setCurrentPage(curPage);
		req.setPage(page);
		WebServiceContext.getInstance().getNewsManageRest(this).getNewsList(req);
		
	}

	@Override
	public List<NewsJson> loadListRecv(Object obj)
	{
		this.waitDailog.dismiss();
		curPage=curPage+1;
		GetNewsListRsp rsp = (GetNewsListRsp) obj;
		if(ValidatorUtil.isEmpty(rsp.getNewsList()))
		{
			showToast(NewsPageActivity.this, "数据全部加载完成，没有更多数据！");
			isfinished=true;
		}
		return rsp.getNewsList();
	}


  
	@Override
    public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount)
	{
		//showToast(this, "ggg");
		// 计算最后可见条目的索引
        lastVisibleIndex = firstVisibleItem + visibleItemCount ;//修改过，后续查找问题原因时可考虑+1
        // 所有的条目已经和最大条数相等，则移除底部的View
        if (totalItemCount == MaxDateNum + 1) 
        {
           
            showToast(this, "没有更多可以查看的数据！");
        }

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        // 滑到底部后自动加载，判断listview已经停止滚动并且最后可视的条目等于adapter的条目
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE&& lastVisibleIndex ==this.getAdapter().getCount())
        {
        	//showToast(this, "当滑到底部时自动加载");
        	if (!isfinished) 
			{
            	// 当滑到底部时自动加载
    			this.waitDailog.show();
    			mhandler.postDelayed(new Runnable()
    			{

    				@Override
    				public void run()
    				{
    					loadSendList();

    				}

    			}, 2000);
			}
            else
            {
            	showToast(NewsPageActivity.this, "数据全部加载完成，没有更多数据！");
            }


        }

    }




    
}
