package cn.fuego.smart.home.ui.enterprise.check;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.CheckResultEnum;
import cn.fuego.smart.home.webservice.up.model.GetCheckLogByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckLogByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class CheckLogActivity extends MispListActivity<CheckLogJson>
{

	private CompanyJson company;
	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.activity_check_log);
		this.activityRes.setName("智慧管理");
		
		this.listViewRes.setListView(R.id.check_log_list);
		this.listViewRes.setListItemView(R.layout.item_check);
		this.listViewRes.setClickActivityClass(CheckOperateActivity.class);
		
		Intent intent = this.getIntent();
		company = (CompanyJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
	}

	@Override
	public View getListItemView(View view, CheckLogJson item)
	{
		TextView txt_title= (TextView) view.findViewById(R.id.item_check_title);
		txt_title.setText(item.getCheckItem());		
		TextView txt_content= (TextView) view.findViewById(R.id.item_check_content);
		txt_content.setText(item.getCheckSys());
		TextView txt_result= (TextView) view.findViewById(R.id.item_check_result);
		txt_result.setText(CheckResultEnum.getEnumByInt(item.getCheckResult()).getStrValue());
		ImageView icon_result= (ImageView) view.findViewById(R.id.item_check_img);
		if(!ValidatorUtil.isEmpty(item.getAbnormalPic()))
		{
			icon_result.setVisibility(View.VISIBLE);
		}
		else
		{
			icon_result.setVisibility(View.INVISIBLE);
		}
		
		return view;
	}

	@Override
	public void loadSendList()
	{
		GetCheckLogByIDReq req = new GetCheckLogByIDReq();

		req.setCompanyID(company.getCompanyID());
		//PageJson page = new PageJson();		
		//page.setCurrentPage(curPage);
		//req.setPage(page);
		WebServiceContext.getInstance().getCheckManageRest(this).getCheckLogByID(req);
		
	}

	@Override
	public List<CheckLogJson> loadListRecv(Object obj)
	{
		GetCheckLogByIDRsp rsp = (GetCheckLogByIDRsp) obj;
		return rsp.getCheckLogList();
	}


}
