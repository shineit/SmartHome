package cn.fuego.smart.home.ui.common.knowledge;

import java.util.List;

import android.view.View;
import android.widget.TextView;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.webservice.up.model.GetCommonSenseListReq;
import cn.fuego.smart.home.webservice.up.model.GetCommonSenseListRsp;
import cn.fuego.smart.home.webservice.up.model.base.KnowledgeJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class CommonSenseActivity extends MispListActivity<KnowledgeJson>
{


	@Override
	public void initRes()
	{
		this.activityRes.setName("消防常识");

		this.activityRes.setAvtivityView(R.layout.activity_common_sense);
		
		this.listViewRes.setListView(R.id.common_sense_list);	
		this.listViewRes.setListItemView(R.layout.misp_list_item_btntype);
		this.listViewRes.setNoResult(true);
		this.listViewRes.setClickActivityClass(ContentViewActivity.class);
		
		
	}

	@Override
	public View getListItemView(View view, KnowledgeJson item)
	{
		TextView title= (TextView) view.findViewById(R.id.item_btntype_name);
		title.setText(item.getTitle());
		return view;
	}

	@Override
	public void loadSendList()
	{
		GetCommonSenseListReq req = new GetCommonSenseListReq();
 		req.setUserID(AppCache.getInstance().getUser().getUserID());

		WebServiceContext.getInstance().getKnowledgeManageRest(this).getCommonSenseList(req);
		
	}

	@Override
	public List<KnowledgeJson> loadListRecv(Object obj)
	{
		GetCommonSenseListRsp rsp = (GetCommonSenseListRsp) obj;
		return rsp.getKnowledgeList();
	}

}
