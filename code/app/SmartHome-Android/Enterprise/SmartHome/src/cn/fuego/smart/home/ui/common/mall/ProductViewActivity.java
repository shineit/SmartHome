package cn.fuego.smart.home.ui.common.mall;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.webservice.up.model.base.ProductJson;

public class ProductViewActivity extends MispBaseActivtiy
{

	private ProductJson product;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ImageView product_img = (ImageView) findViewById(R.id.product_view_img);
		LoadImageUtil.getInstance().loadImage(product_img, MemoryCache.getImageUrl()+product.getPicLabel());
		TextView product_name= (TextView) findViewById(R.id.product_view_name);
		product_name.setText(product.getName());
		TextView product_desp= (TextView) findViewById(R.id.product_view_desp);
		product_desp.setText(product.getDesp());
		
	}

	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.activity_product_view);
		this.activityRes.setName("产品详情");
		
		product=(ProductJson) this.getIntent().getSerializableExtra(ListViewResInfo.SELECT_ITEM);
	}
}
