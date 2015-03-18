package cn.fuego.smart.home.ui.common.mall;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import cn.fuego.misp.ui.imgScroll.MyImgScroll;
import cn.fuego.smart.home.R;

public class DeviceMallActivity extends Activity 
{


	MyImgScroll myPager; // 图片容器
	LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_mall);
		myPager = (MyImgScroll) findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) findViewById(R.id.vb);
		InitViewPager();//初始化图片
		//开始滚动
		myPager.start(this, listViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_focused, R.drawable.dot_normal);

	}
    
	@Override
	protected void onRestart() {
		myPager.startTimer();
		super.onRestart();
	}
    
	@Override
	protected void onStop() {
		myPager.stopTimer();
		super.onStop();
	}

	public void stop(View v) {
		myPager.stopTimer();
	}

	/**
	 * 初始化图片
	 */
	private void InitViewPager() {
		listViews = new ArrayList<View>();
		int[] imageResId = new int[] {  R.drawable.b,
				R.drawable.c, R.drawable.d};
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {// 设置图片点击事件

				}
			});
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}
}
