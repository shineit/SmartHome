/**   
* @Title: MenuHeadModel.java 
* @Package cn.fuego.misp.web.model.menu 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-19 下午11:02:45 
* @version V1.0   
*/ 
package cn.fuego.misp.web.model.menu;

/** 
 * @ClassName: MenuHeadModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-19 下午11:02:45 
 *  
 */

public class MenuHeadModel
{
	private MenuModel menu;
	private MenuHeadModel childMenu;
	public MenuModel getMenu()
	{
		return menu;
	}
	public void setMenu(MenuModel menu)
	{
		this.menu = menu;
	}
	public MenuHeadModel getChildMenu()
	{
		return childMenu;
	}
	public void setChildMenu(MenuHeadModel childMenu)
	{
		this.childMenu = childMenu;
	}
	
	


}
