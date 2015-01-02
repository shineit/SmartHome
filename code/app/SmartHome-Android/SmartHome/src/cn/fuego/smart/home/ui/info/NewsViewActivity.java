package cn.fuego.smart.home.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.ListViewResInfo;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.MainTabbarActivity;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.model.NewsViewModel;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;

public class NewsViewActivity extends BaseActivtiy implements View.OnClickListener
{

	private TextView txt_title,txt_content,txt_author;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_detail);
		ExitApplication.getInstance().addActivity(this);

		txt_title = (TextView) findViewById(R.id.news_detail_title);		
		txt_content = (TextView) findViewById(R.id.news_detail_content);
		txt_author =(TextView) findViewById(R.id.news_detail_author);
		
		Intent intent = this.getIntent();
		initView(intent);
		
		Button back_btn=(Button)findViewById(R.id.news_detail_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
	}

	private void initView(Intent intent)
	{
		NewsJson news =(NewsJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		if(news==null)
		{
			txt_content.setText("该消息无具体内容");
		}
		else
		{
			txt_title.setText(news.getTitle());
			txt_author.setText(news.getAuthor());
			txt_content.setText(news.getContent());
			
		}
		
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		switch(tag)
		{
		case 1: 
/*			Intent intent = new Intent(NewsViewActivity.this,MainTabbarActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
			MemoryCache.setFlag(2);
			startActivity(intent);
*/
			this.finish();
				break;

		default:break;
		}
		
	}
}
