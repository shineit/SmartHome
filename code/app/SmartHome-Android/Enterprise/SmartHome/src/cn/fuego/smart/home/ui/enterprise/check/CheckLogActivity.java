package cn.fuego.smart.home.ui.enterprise.check;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;
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
	
	private Window window;
	private PopupWindow popupWindow=null; 
	private View view;
	
	@Override
	public void initRes()
	{
		this.waitDailog.show();
		this.activityRes.setAvtivityView(R.layout.activity_check_log);
		this.activityRes.setName("智慧管理");
		
		this.listViewRes.setListView(R.id.check_log_list);
		this.listViewRes.setListItemView(R.layout.item_check);
	
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
	public void onItemListClick(AdapterView<?> parent, View view, long id,CheckLogJson item)
	{
		
		String title= item.getCheckItem()+CheckResultEnum.getEnumByInt(item.getCheckResult()).getStrValue();
		String content = item.getAbnormalDesp();
		String picUrl= MemoryCache.getImageUrl()+item.getAbnormalPic();
		showDetail(view, title, content,picUrl);
	}

	private void showDetail(View parent, String title, String content,String picUrl)
	{
		
		if (popupWindow == null)
		{		
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);			
			view = layoutInflater.inflate(R.layout.pop_window_with_img, null);

			// 创建一个PopuWidow对象
			popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			// 使其聚集
			popupWindow.setFocusable(true);
			// 设置允许在外点击消失
			popupWindow.setOutsideTouchable(true);

			// 实例化一个ColorDrawable颜色为半透明
			ColorDrawable dw = new ColorDrawable(0x90000000);
			popupWindow.setBackgroundDrawable(dw);
			popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);         
			popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
			
			TextView txt_title= (TextView) view.findViewById(R.id.pop_window_title);
			txt_title.setText(title);
			TextView txt_content = (TextView) view.findViewById(R.id.pop_window_content);
			txt_content.setText(content);
			 
			ImageView img_area= (ImageView) view.findViewById(R.id.pop_window_imgarea);
			LoadImageUtil.getInstance().loadImage(img_area, picUrl);
		}

		window=CheckLogActivity.this.getWindow();
        //设置背景变暗
        WindowManager.LayoutParams lp=window.getAttributes();
        lp.alpha = 0.4f;
        window.setAttributes(lp); 
		popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);//在屏幕居中，无偏移
		
		popupWindow.setOnDismissListener(new OnDismissListener()
		{
			
			@Override
			public void onDismiss()
			{
				WindowManager.LayoutParams lp=window.getAttributes();
			    lp.alpha = 1f;
			    window.setAttributes(lp);
			    popupWindow=null;
			}
		});
		
		
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
		this.waitDailog.dismiss();
		GetCheckLogByIDRsp rsp = (GetCheckLogByIDRsp) obj;
		if(ValidatorUtil.isEmpty(rsp.getCheckLogList()))
		{
			showToast(this, "当前无不良巡检记录！");
		}
		return rsp.getCheckLogList();
	}

}
