/**   
* @Title: MispButton.java 
* @Package cn.fuego.misp.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-1 下午5:17:52 
* @version V1.0   
*/ 
package cn.fuego.misp.domain;

import cn.fuego.common.domain.PersistenceObject;

 /** 
 * @ClassName: MispButton 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-1 下午5:17:52 
 *  
 */
public class MispButton implements PersistenceObject
{
	private int id;
	private String module;
	private String name;
	private String value;
	private String type;
	private String css;
	private String url;
	private String method;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getModule()
	{
		return module;
	}
	public void setModule(String module)
	{
		this.module = module;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getCss()
	{
		return css;
	}
	public void setCss(String css)
	{
		this.css = css;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getMethod()
	{
		return method;
	}
	public void setMethod(String method)
	{
		this.method = method;
	}
	

}
