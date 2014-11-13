/**   
* @Title: MenuJson.java 
* @Package cn.fuego.smart.home.webservice.from.client.model.base 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-1 下午5:46:27 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.model.base;

import cn.fuego.misp.domain.SystemMenu;

 /** 
 * @ClassName: MenuJson 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-1 下午5:46:27 
 *  
 */
public class MenuJson
{
	private int id;
	private String name;
	
	public void loadWithMenu(SystemMenu menu)
	{
		this.id = menu.getId();
		this.name = menu.getName();
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	@Override
	public String toString()
	{
		return "MenuJson [id=" + id + ", name=" + name + "]";
	}
	

}
