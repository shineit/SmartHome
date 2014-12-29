package cn.fuego.smart.home.ui.setting.user;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.service.SensorDataCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.webservice.up.model.SetUserMarkReq;
import cn.fuego.smart.home.webservice.up.model.base.UserMarkJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class MarkManageActivity extends BaseActivtiy implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private PopupWindow popupWindow=null; 
	private View view,parentView;
	private ProgressDialog addPDialog;
	private String newMark,curMark;
	private MarkAdapter markAdapter;
	private List<String> markList;
	private EditText txt_mark;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.user_mark_manage);
		ExitApplication.getInstance().addActivity(this);
		Intent intent = this.getIntent();
		curMark=intent.getStringExtra("curMark");
		log.info("传递curMark:"+curMark);
		
		ListView markListView = (ListView) findViewById(R.id.user_mark_manage_list);
	    markList = SensorDataCache.getInstance().getMarkList();
	    log.info("marklist is:"+markList);
	    markAdapter = new MarkAdapter(MarkManageActivity.this,markList,curMark);
		markListView.setAdapter(markAdapter);
		
		Button back_btn= (Button) findViewById(R.id.user_mark_manage_back);
		back_btn.setTag(1);
		back_btn.setOnClickListener(this);
		
		Button add_btn= (Button) findViewById(R.id.user_mark_manage_addbtn);
		add_btn.setTag(2);
		add_btn.setOnClickListener(this);	
		
		parentView=MarkManageActivity.this.getWindow().getDecorView();
		
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
			this.finish();
				break;
		case 2: 		
			showPopWindow(v);		
				break;

		default:break;
		}
		
	}
    private void showPopWindow(View parent)
    {
    	parentView.setAlpha((float) 0.5);	  
		if (popupWindow == null)
		{		
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);			
			view = layoutInflater.inflate(R.layout.pop_window_add_mark, null);

			// 创建一个PopuWidow对象
			 popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
/*			popupWindow = new PopupWindow(view, getWindowManager()
					.getDefaultDisplay().getWidth(), getWindowManager()
					.getDefaultDisplay().getHeight());*/
		}

		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);

		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x90000000);
		popupWindow.setBackgroundDrawable(dw);

		
		txt_mark = (EditText) view.findViewById(R.id.pop_window_mark);
		
		Button sureBtn = (Button) view.findViewById(R.id.pop_window_sure_btn);
		sureBtn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				

				newMark = txt_mark.getText().toString().trim();
				
				if (newMark != null && newMark.length() > 0)
				{
					if(isNewMark(newMark))
					{
						addPDialog =ProgressDialog.show(MarkManageActivity.this, "请稍等", "正在提交数据……");
						SetUserMarkReq req = new SetUserMarkReq();
						req.setToken(MemoryCache.getToken());
						UserMarkJson userMark = new UserMarkJson();
						userMark.setMark(newMark);
						userMark.setUserID(MemoryCache.getLoginInfo().getUser()
								.getUserID());
						req.setUserMark(userMark);
						WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler()
								{
									@Override
									public void handle(MispHttpMessage message)
									{
										// SetUserMarkRsp rsp = (SetUserMarkRsp)
										// message.getMessage().obj;
										if (message.isSuccess())
										{
											log.info("标签新增成功");

											SensorDataCache.getInstance().getMarkList().add(newMark);
											//markList.add(newMark);
											markAdapter.notifyDataSetChanged();

											addPDialog.dismiss();
											popupWindow.dismiss();

										} else
										{
											super.sendMessage(message);
										}
									}
								}).addUserMark(req);
					}
					else
					{
						Toast.makeText(MarkManageActivity.this, newMark+"标签已经被创建！",Toast.LENGTH_LONG).show();
					}


				} else
				{
					log.info("新增标签输入为空");
					Toast.makeText(MarkManageActivity.this, "请输入用户标签！",Toast.LENGTH_LONG).show();
				}
			}
		});
		Button cancelBtn = (Button) view.findViewById(R.id.pop_window_cancel);
		cancelBtn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				popupWindow.dismiss();
				txt_mark.setText("");
			}
		});
        
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		// 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
		//int xPos = windowManager.getDefaultDisplay().getWidth() / 2
		//		- popupWindow.getWidth() / 2;

		//popupWindow.showAsDropDown(parent, xPos, 0);
		popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);//在屏幕居中，无偏移
		
		popupWindow.setOnDismissListener(new OnDismissListener()
		{
			
			@Override
			public void onDismiss()
			{
				parentView.setAlpha((float) 1);
				
				
			}
		});

    } 
	private boolean isNewMark(String target)
	{
		boolean result=true;
		for(int i=0;i<markList.size();i++)
		{
			if(target.equals(markList.get(i)))
			{
				result=false;
			}
			
		}
		return result;
	}
}