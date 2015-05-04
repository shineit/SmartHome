package cn.fuego.smart.home.ui.enterprise.company;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import cn.fuego.misp.ui.info.MispInfoListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.StrUtil;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;

public class CompanyViewActivity extends MispInfoListActivity
{

	private CompanyJson company;
	@Override
	public void initRes()
	{
		super.initRes();
		//this.activityRes.setAvtivityView(R.layout.activity_company_view);
		
		//this.listViewRes.setListView(R.id.company_view_list);
		
		this.activityRes.setName("基本信息");
		
		Intent intent = this.getIntent();
		company = (CompanyJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		
		this.getDataList().clear();
		this.getDataList().addAll(getDisplayData());
		


	}
	
	public static void jump(Context context,CompanyJson company)
	{
		Intent intent = new Intent(context,CompanyViewActivity.class);
		intent.putExtra(ListViewResInfo.SELECT_ITEM, company);

		context.startActivity(intent);
	}
	
 
	private List<CommonItemMeta> getDisplayData()
	{
 		List<CommonItemMeta> list = new ArrayList<CommonItemMeta>();

		if(null != company)
		{
			list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));
			list.add(new CommonItemMeta(CommonItemMeta.TITLE_CONTENT, company.getCompanyName(), null));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "使用名称", company.getApplyName()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "单位地址", company.getCompanyAddr()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "单位类型", company.getCompanyType()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "火灾危险性", company.getFireRisk()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "建筑面积", company.getBuildingArea()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "单位电话", company.getCompanyPhone()));
			list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "法定代表人", StrUtil.noNullStr(company.getLegalOfficer())+
					"\n"+StrUtil.noNullStr(company.getDutyPhone())));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "消防安全管理人", StrUtil.noNullStr(company.getFireManager())+
					"\n"+StrUtil.noNullStr(company.getManagerPhone())));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "消防安全责任人", StrUtil.noNullStr(company.getFireDuty())+
					"\n"+StrUtil.noNullStr(company.getDutyPhone())));


			
		}
	 
		
		return list;
	}

 
}
