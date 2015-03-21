/**   
* @Title: ProductJson.java 
* @Package cn.fuego.smart.home.webservice.up.model.base 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-17 上午10:21:03 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;

 /** 
 * @ClassName: ProductJson 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-17 上午10:21:03 
 *  
 */
public class ProductJson implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int productID;			//产品ID，自增长，主键
	private String name;			//产品名称
	private String desp;			//产品描述
	private float price;			//产品价格
	private String picLabel;		//产品标识图片（主图）路径
	private String picDetail1;		//产品图片1
	private String picDetail2;		//产品图片2
	private String picDetail3;		//产品图片3
	private int type=0;				//产品面向终端类型，0-公共；1-家庭端，2-消防端
	public int getProductID()
	{
		return productID;
	}
	public void setProductID(int productID)
	{
		this.productID = productID;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDesp()
	{
		return desp;
	}
	public void setDesp(String desp)
	{
		this.desp = desp;
	}
	public float getPrice()
	{
		return price;
	}
	public void setPrice(float price)
	{
		this.price = price;
	}
	public String getPicLabel()
	{
		return picLabel;
	}
	public void setPicLabel(String picLabel)
	{
		this.picLabel = picLabel;
	}
	public String getPicDetail1()
	{
		return picDetail1;
	}
	public void setPicDetail1(String picDetail1)
	{
		this.picDetail1 = picDetail1;
	}
	public String getPicDetail2()
	{
		return picDetail2;
	}
	public void setPicDetail2(String picDetail2)
	{
		this.picDetail2 = picDetail2;
	}
	public String getPicDetail3()
	{
		return picDetail3;
	}
	public void setPicDetail3(String picDetail3)
	{
		this.picDetail3 = picDetail3;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	
	
}
