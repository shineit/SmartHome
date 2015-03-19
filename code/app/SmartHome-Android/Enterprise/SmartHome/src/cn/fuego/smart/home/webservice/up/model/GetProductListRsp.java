package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.ProductJson;


/**
 * 
* @ClassName: GetSensorListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:58 
*
 */
public class GetProductListRsp extends MispBaseRspJson
{
	private List<ProductJson> productList = new ArrayList<ProductJson>();

	public List<ProductJson> getProductList()
	{
		return productList;
	}

	public void setProductList(List<ProductJson> productList)
	{
		this.productList = productList;
	}

	@Override
	public String toString()
	{
		return "GetProductListRsp [productList=" + productList + ", errorCode="
				+ errorCode + ", errorMsg=" + errorMsg + ", obj=" + obj + "]";
	}

	 
 



}
