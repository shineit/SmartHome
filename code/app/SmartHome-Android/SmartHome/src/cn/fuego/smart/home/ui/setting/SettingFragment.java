package cn.fuego.smart.home.ui.setting;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fuego.smarthome.R;

public class SettingFragment extends Fragment implements View.OnClickListener
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.setting_fragment, null);
		
		Button person_btn=(Button) rootView.findViewById(R.id.add_apply);
		person_btn.setOnClickListener(this);
		person_btn.setTag(1);
		
		Button about_btn=(Button) rootView.findViewById(R.id.apply_cancel_btn);
		about_btn.setOnClickListener(this);
		about_btn.setTag(2);
		
		Button rank_btn=(Button) rootView.findViewById(R.id.apply_sure_btn);
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
		case 1:;
		case 2:;
		case 3:;
		case 4:;
		case 5:  intent = new Intent(this.getActivity(),ServiceActivity.class);
	    		 startActivity(intent);
		case 6:;
		case 7:;
		default:break;
		}
		
	}
	
	
}
