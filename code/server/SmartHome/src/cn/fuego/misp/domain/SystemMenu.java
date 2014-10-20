/**   
 * @Title: SystemMenu.java 
 * @Package cn.fuego.misp.domain.po 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2014-3-23 下午9:49:53 
 * @version V1.0   
 */
package cn.fuego.misp.domain;

/**
 * @Title: SystemMenu.java
 * @Package cn.fuego.misp.domain.po
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2014-3-23 下午9:49:53
 * @version V1.0
 */

public class SystemMenu
{

	private int menuID;
	private String name;
	private String value;
	private String type;
	private String icon;
	private String external;
	private String fresh;
	private String url;
	private int parentID;
	private String functionID;

	public int getMenuID()
	{
		return menuID;
	}

	public void setMenuID(int menuID)
	{
		this.menuID = menuID;
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

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public String getExternal()
	{
		return external;
	}

	public void setExternal(String external)
	{
		this.external = external;
	}

	public String getFresh()
	{
		return fresh;
	}

	public void setFresh(String fresh)
	{
		this.fresh = fresh;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public int getParentID()
	{
		return parentID;
	}

	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}

	public String getFunctionID()
	{
		return functionID;
	}

	public void setFunctionID(String functionID)
	{
		this.functionID = functionID;
	}

	@Override
	public String toString()
	{
		return "SystemMenu [menuID=" + menuID + ", name=" + name + ", value=" + value + ", type=" + type + ", icon=" + icon + ", external=" + external + ", fresh=" + fresh + ", url=" + url
				+ ", parentID=" + parentID + ", functionID=" + functionID + "]";
	}

}
