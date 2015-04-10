package cn.fuego.smart.home.ui.common.mall;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.imgScroll.FixedSpeedScroller;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.pager.ImagePagerAdapter;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.service.AdDataCache;
import cn.fuego.smart.home.webservice.up.model.GetAdListReq;
import cn.fuego.smart.home.webservice.up.model.GetAdListRsp;
import cn.fuego.smart.home.webservice.up.model.GetProductListReq;
import cn.fuego.smart.home.webservice.up.model.GetProductListRsp;
import cn.fuego.smart.home.webservice.up.model.base.AdvertisementJson;
import cn.fuego.smart.home.webservice.up.model.base.ProductJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class ProductMallActivity extends MispListActivity<ProductJson>
{

	private ViewGroup group;
	private ViewPager viewPager;
	private Timer timer;
	
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
		
		this.waitDailog.show();
		
	} 

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		group = (ViewGroup)findViewById(R.id.mall_ad_image_group);  
		viewPager = (ViewPager) findViewById(R.id.mall_ad_image);
		viewPager.getLayoutParams().height = (int) (this.getScreenWidth()*0.4);
		loadAdd();

	}

	private void loadAdd()
	{
		if(AdDataCache.getInstance().isEmpty())
		{
			GetAdListReq req = new GetAdListReq();
			req.setUserID(AppCache.getInstance().getUser().getUserID());
			
			WebServiceContext.getInstance().getMallManageRest(new MispHttpHandler(){
				@Override
				public void handle(MispHttpMessage message)
				{
					if(message.isSuccess())
					{
						GetAdListRsp rsp =(GetAdListRsp) message.getMessage().obj;
						AdDataCache.getInstance().init(rsp.getAdList());

						initAdView(rsp.getAdList());
					}
					else
					{
						showMessage(message);
					}
				}


			}).getAdList(req);
			
		}
		else
		{
			initAdView(AdDataCache.getInstance().getDataList());
		}

	}
	private void initAdView(List<AdvertisementJson> adList)
	{
 		
		List<String> urlList = new ArrayList<String>();
		for(AdvertisementJson json : adList)
		{
			urlList.add(MemoryCache.getImageUrl()+json.getAdImg());
		}
		ImagePagerAdapter adapter = new ImagePagerAdapter(this,group,urlList);
	    viewPager.setAdapter(adapter);  
	    viewPager.setCurrentItem(0); 
	    viewPager.setOnPageChangeListener(adapter);
	    if(urlList.size()>1)
	    {
	    	// 设置滑动动画时间  ,如果用默认动画时间可不用 ,反射技术实现
	    	new FixedSpeedScroller(this).setDuration(viewPager, 700);
		    startCarouselTimer(adHandler);
	    }

	}
	 
	private Handler adHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			// TODO Auto-generated method stub
			viewPager.setCurrentItem(msg.what);
		}
		
	};

	private void startCarouselTimer(final Handler handler)
	{
 		timer = new Timer();
		timer.schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				int index = viewPager.getCurrentItem()+1;
				if(index>=3)
				{
					index = 0;
				}
				handler.sendEmptyMessage(index);
			}
		}, 4000, 4000);
	}

	@Override
	public void onDestroy()
	{
		if (timer != null)
			timer.cancel();

 		super.onDestroy();
	}
	@Override
	public View getListItemView(View view, ProductJson item)
	{
		TextView product_name= (TextView) view.findViewById(R.id.item_product_name);
		product_name.setText(item.getName());
		TextView product_price = (TextView) view.findViewById(R.id.item_product_price);
		product_price.setText("￥ "+String.valueOf(item.getPrice()));
		//暂时不显示
		product_price.setVisibility(View.GONE);
		ImageView product_pic= (ImageView) view.findViewById(R.id.item_product_img);
		LoadImageUtil.getInstance().loadImage(product_pic, MemoryCache.getImageUrl()+item.getPicLabel());
	

		return view;
	}


	@Override
	public void loadSendList()
	{
		GetProductListReq req = new GetProductListReq();
 		req.setUserID(AppCache.getInstance().getUser().getUserID());		
		WebServiceContext.getInstance().getMallManageRest(this).getProductList(req);
		
		
	}

	@Override
	public List<ProductJson> loadListRecv(Object obj)
	{
		waitDailog.dismiss();
		GetProductListRsp rsp = (GetProductListRsp) obj;
		return rsp.getProductList();
	}

}
