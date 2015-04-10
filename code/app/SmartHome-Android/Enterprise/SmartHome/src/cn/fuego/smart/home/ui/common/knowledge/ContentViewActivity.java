package cn.fuego.smart.home.ui.common.knowledge;

import android.os.Bundle;
import android.widget.TextView;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.webservice.up.model.base.KnowledgeJson;

public class ContentViewActivity extends MispBaseActivtiy
{


	private KnowledgeJson knowledge;
	@Override
	public void initRes()
	{
		this.activityRes.setName("消防常识");

		this.activityRes.setAvtivityView(R.layout.activity_content_view);
		
		knowledge= (KnowledgeJson) this.getIntent().getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView title= (TextView) findViewById(R.id.content_view_title);
		title.setText(knowledge.getTitle());
		
		TextView content = (TextView) findViewById(R.id.content_view_content);
		content.setText(knowledge.getContent());
	}
	
}
