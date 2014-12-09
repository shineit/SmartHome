package cn.fuego.smart.home.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.model.NewsViewModel;

public class NewsViewActivity extends BaseActivtiy implements View.OnClickListener
{

	private NewsViewModel newsModel = new NewsViewModel();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_detail);
		ExitApplication.getInstance().addActivity(this);
		
		Intent intent = this.getIntent();
		TextView txt_title = (TextView) findViewById(R.id.news_detail_title);
		txt_title.setText(intent.getStringExtra(newsModel.getTitle()));
		
		TextView txt_content = (TextView) findViewById(R.id.news_detail_content);
		txt_content.setText(intent.getStringExtra(newsModel.getContent()));
		
		TextView txt_author =(TextView) findViewById(R.id.news_detail_author);
		txt_author.setText(intent.getStringExtra(newsModel.getAuthor()));
		
		Button back_btn=(Button)findViewById(R.id.news_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
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
		case 1: this.finish();
				break;

		default:break;
		}
		
	}
}
