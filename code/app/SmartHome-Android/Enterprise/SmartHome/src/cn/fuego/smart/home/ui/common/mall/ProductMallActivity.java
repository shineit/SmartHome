package cn.fuego.smart.home.ui.common.mall;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.ui.imgScroll.MyImgScroll;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.webservice.up.model.GetProductListReq;
import cn.fuego.smart.home.webservice.up.model.GetProductListRsp;
import cn.fuego.smart.home.webservice.up.model.base.ProductJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class ProductMallActivity extends MispListActivity<ProductJson>
{

	MyImgScroll myPager; // 图片容器
	LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组
	
	@Override
	public void initRes()
	{
		this.activityRes.setName("设备商城");
		this.activityRes.setAvtivityView(R.layout.activity_product_mall);
		
		this.listViewRes.setListType(ListViewResInfo.VIEW_TYPE_GRID);
		this.listViewRes.setListView(R.id.product_mall_gridview);
		this.listViewRes.setListItemView(R.layout.item_mall_product);
		this.listViewRes.setNoResult(true);
		this.listViewRes.setClickActivityClass(ProductViewActivity.class);
		
		
	} 

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myPager = (MyImgScroll) findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) findViewById(R.id.vb);
		InitViewPager();//初始化图片
		//开始滚动
		myPager.start(this, listViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_focused, R.drawable.dot_normal);
	}

	@Override
	public View getListItemView(View view, ProductJson item)
	{
		TextView product_name= (TextView) view.findViewById(R.id.item_product_name);
		product_name.setText(item.getName());
		TextView product_price = (TextView) view.findViewById(R.id.item_product_price);
		product_price.setText("￥ "+String.valueOf(item.getPrice()));
		
		ImageView product_pic= (ImageView) view.findViewById(R.id.item_product_img);
		LoadImageUtil.getInstance().loadImage(product_pic, MemoryCache.getImageUrl()+item.getPicLabel());
	

		return view;
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
	@Override
	public void loadSendList()
	{
		GetProductListReq req = new GetProductListReq();
 		req.setUserID(AppCache.getInstance().getUser().getUserID());		
		WebServiceContext.getInstance().getProductManageRest(this).getProductList(req);
		
		
	}

	@Override
	public List<ProductJson> loadListRecv(Object obj)
	{
		GetProductListRsp rsp = (GetProductListRsp) obj;
		return rsp.getProductList();
	}

}
