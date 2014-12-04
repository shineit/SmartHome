package cn.fuego.smart.home.ui.setting;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.setting.service.ServiceActivity;
import cn.fuego.smart.home.ui.setting.user.UserManageActivity;

public class SettingFragment extends Fragment implements View.OnClickListener
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.setting_fragment, null);
		
		Button person_btn=(Button) rootView.findViewById(R.id.user_center_btn);
		person_btn.setOnClickListener(this);
		person_btn.setTag(1);
		
		Button about_btn=(Button) rootView.findViewById(R.id.about_btn);
		about_btn.setOnClickListener(this);
		about_btn.setTag(2);
		
		Button rank_btn=(Button) rootView.findViewById(R.id.rank_btn);
		rank_btn.setOnClickListener(this);
		rank_btn.setTag(3);
		
		Button notice_btn=(Button) rootView.findViewById(R.id.notice_btn);
		notice_btn.setOnClickListener(this);
		notice_btn.setTag(4);	
		
		Button service_btn=(Button) rootView.findViewById(R.id.service_btn);
		service_btn.setOnClickListener(this);
		service_btn.setTag(5);
		
		Button new_f_btn=(Button) rootView.findViewById(R.id.new_function_btn);
		new_f_btn.setOnClickListener(this);
		new_f_btn.setTag(6);
		
		Button feedbck_btn=(Button) rootView.findViewById(R.id.feedbck_btn);
		feedbck_btn.setOnClickListener(this);
		feedbck_btn.setTag(7);
		
		return rootView;
	}
	
	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		Intent intent = null;
		switch(tag)
		{
		case 1: intent = new Intent(this.getActivity(),UserManageActivity.class);
				startActivity(intent);
				break;//个人中心
		case 2: //关于我们
				break;
		case 3: //为我们评分
				break;
		case 4: //通知设置
				break;
		case 5:  intent = new Intent(this.getActivity(),ServiceActivity.class);
	    		 startActivity(intent);
	    		 break;//服务
		case 6: //新功能
				break;
		case 7: //意见反馈
				break;
		default:break;
		}
		
	}
	
	
}
