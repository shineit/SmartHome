/**   
 * @Title: MenuTreeModel.java 
 * @Package cn.fuego.misp.web.model.menu 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-3-17 上午12:02:36 
 * @version V1.0   
 */
package cn.fuego.misp.web.model.menu;

import java.util.List;

import cn.fuego.common.util.validate.ValidatorUtil;

/**
 * @ClassName: MenuTreeModel
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-3-17 上午12:02:36
 * 
 */

public class MenuTreeModel
{
	private MenuModel menu;
	private List<MenuTreeModel> childMenuList;

	public MenuModel getMenuModelByMenuID(String menuID)
	{
		if(menuID.equals(menu.getMenuID()))
		{
			return menu;
		}
		if(!ValidatorUtil.isEmpty(childMenuList))
		{
			for(MenuTreeModel menuTree : childMenuList)
			{
				MenuModel menu = menuTree.getMenuModelByMenuID(menuID);
				if(null != menu)
				{
					return menu;
				}
			}	
		}
		return null;
		
		
	}
	public MenuModel getMenu()
	{
		return menu;
	}

	public void setMenu(MenuModel menu)
	{
		this.menu = menu;
	}

	public List<MenuTreeModel> getChildMenuList()
	{
		return childMenuList;
	}

	public void setChildMenuList(List<MenuTreeModel> childMenuList)
	{
		this.childMenuList = childMenuList;
	}

	@Override
	public String toString()
	{
		return "MenuTreeModel [menu=" + menu + ", childMenuList=" + childMenuList + "]";
	}
	
 

}
