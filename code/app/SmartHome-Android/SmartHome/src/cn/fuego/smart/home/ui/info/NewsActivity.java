package cn.fuego.smart.home.ui.info;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.webservice.up.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsListRsp;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class NewsActivity extends MispListActivity<NewsJson> implements OnClickListener 
{
	
	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.news);
		
		this.listViewRes.setListView(R.id.newslist);
		this.listViewRes.setListItemView(R.layout.news_item);
		this.listViewRes.setClickActivityClass(NewsViewActivity.class);
		
	}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Button back_btn= (Button) findViewById(R.id.news_back);
		back_btn.setTag(1);
		back_btn.setOnClickListener(this);
	}


	@Override
	public void loadSendList()
	{
		GetNewsListReq req = new GetNewsListReq();
		req.setToken(MemoryCache.getToken());
		WebServiceContext.getInstance().getNewsManageRest(this).getNewsList(req);
		
	}

	@Override
	public List<NewsJson> loadListRecv(Object obj)
	{
		GetNewsListRsp rsp = (GetNewsListRsp) obj;
		return rsp.getNewsList();
	}

	@Override
	public View getListItemView(View view, NewsJson item)
	{
		TextView txt_title = (TextView) view.findViewById(R.id.item_news_title);
		txt_title.setText(item.getTitle());

		TextView txt_time = (TextView) view.findViewById(R.id.item_news_time);
		txt_time.setText(DateUtil.getStrTime(item.getDate()));

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
