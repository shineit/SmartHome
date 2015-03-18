package cn.fuego.smart.home.ui.common.mall;

import java.util.List;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.misp.service.MemoryCache;
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
