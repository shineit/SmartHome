package cn.fuego.smart.home.ui.common.news;

import android.os.Bundle;
import android.widget.TextView;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.webservice.up.model.GetNewsByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class NewsViewActivity extends MispBaseActivtiy
{

	private NewsJson news;
	private String newsID;
	
	private TextView title;
	private TextView content;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		title= (TextView) findViewById(R.id.content_view_title);	
		content = (TextView) findViewById(R.id.content_view_content);
		if(newsID!=null)
		{
			getNewsByID(newsID);
		}
		else
		{
			title.setText(news.getTitle());
			content.setText(news.getContent());
		}
		
	}
	@Override
	public void initRes()
	{
		this.activityRes.setName("新闻公告");
		this.activityRes.setAvtivityView(R.layout.activity_content_view);
		newsID=this.getIntent().getStringExtra(IntentCodeConst.NEWS_ID);
		news = (NewsJson) this.getIntent().getSerializableExtra(ListViewResInfo.SELECT_ITEM);

		
		
	}
	
	private void getNewsByID(String id)
	{
		
		GetNewsByIDReq req = new GetNewsByIDReq();
		req.setNewsID(id);
		WebServiceContext.getInstance().getNewsManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetNewsByIDRsp rsp = (GetNewsByIDRsp) message.getMessage().obj;
					news=rsp.getNews();
					content.setText(news.getContent());
					content.setText(news.getContent());
				}
			}
		}).getNews(req);

	}




}
