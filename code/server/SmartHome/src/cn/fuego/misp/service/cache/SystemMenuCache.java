/**   
 * @Title: SystemMenuCache.java 
 * @Package cn.fuego.misp.service.cache 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-3-25 下午11:13:07 
 * @version V1.0   
 */
package cn.fuego.misp.service.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.Dao;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.SystemMenu;
import cn.fuego.misp.web.model.menu.MenuModel;
import cn.fuego.misp.web.model.menu.MenuTreeModel;

/**
 * @ClassName: SystemMenuCache
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-3-25 下午11:13:07
 * 
 */

public class SystemMenuCache
{
	private Dao systemMenuDao = MISPDaoContext.getInstance().getSystemMenuDao();

	private Log log = LogFactory.getLog(SystemMenuCache.class);

	private static SystemMenuCache instance;
	private List<MenuTreeModel> cache;
	private List<String> functionList;

	private SystemMenuCache()
	{
		this.reload();

	}

	public static synchronized SystemMenuCache getInstance()
	{
		if (null == instance)
		{
			instance = new SystemMenuCache();
		}
		return instance;
	}

	public void reload()
	{
		// there is no parent menu when parent id is 0
		cache = loadMenuTreeByParentID(0);
		log.info("loaded all menu list" + cache);

	}

	/**
	 * 通过有权限的菜单ID，获取系统菜单系统，有权限的ID会为selected 设置为true
	 * 应用可以根据该字段判断是否要显示该菜单。
	 * @param menuIDList
	 * @return
	 */
	public List<MenuTreeModel> getMenuListWithShowIDList(Set<String> menuIDList)
	{

		return getMenuTreeByShowMenuIDList(this.cache, menuIDList);
	}

	public List<MenuTreeModel> getAllMenu()
	{
		return this.cache;
	}



	private List<MenuTreeModel> getMenuTreeByShowMenuIDList(
			List<MenuTreeModel> menuTreeList, Set<String> menuIDList)
	{
		List<MenuTreeModel> nowMenuTreeList = new ArrayList<MenuTreeModel>();
		for (MenuTreeModel menuModel : menuTreeList)
		{

			MenuTreeModel nowMenuTree = new MenuTreeModel();

			nowMenuTree.setMenu(menuModel.getMenu().clone());
			if (null != menuModel.getChildMenuList())
			{
				List<MenuTreeModel> childMenuList = getMenuTreeByShowMenuIDList(menuModel.getChildMenuList(), menuIDList);
				nowMenuTree.setChildMenuList(childMenuList);
			}
			if (null != menuIDList
					&& menuIDList.contains(menuModel.getMenu().getMenuID()))
			{

				nowMenuTree.getMenu().setSelected(true);
			}
			nowMenuTreeList.add(nowMenuTree);

		}
		return nowMenuTreeList;
	}
	
	
	/*public List<MenuTreeModel> getAllByFunctionIDList(Set<String> functionList)
	{

		return getMenuTreeByFunctionIDList(this.cache, functionList);

	}
	private List<MenuTreeModel> getMenuTreeByFunctionIDList(
			List<MenuTreeModel> menuTreeList, Set<String> functionList)
	{
		List<MenuTreeModel> nowMenuTreeList = new ArrayList<MenuTreeModel>();
		for (MenuTreeModel menuModel : menuTreeList)
		{

			MenuTreeModel nowMenuTree = new MenuTreeModel();

			nowMenuTree.setMenu(menuModel.getMenu().clone());
			if (null != menuModel.getChildMenuList())
			{
				List<MenuTreeModel> childMenuList = getMenuTreeByFunctionIDList(
						menuModel.getChildMenuList(), functionList);
				nowMenuTree.setChildMenuList(childMenuList);
			}
			if (null != functionList
					&& functionList.contains(menuModel.getMenu()
							.getFunctionID()))
			{

				nowMenuTree.getMenu().setSelected(true);
			}
			nowMenuTreeList.add(nowMenuTree);

		}
		return nowMenuTreeList;
	}*/

	private List<SystemMenu> getMenuByParentID(int parentID)
	{
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL,
				"parentID", String.valueOf(parentID));
		return (List<SystemMenu>) systemMenuDao.getAll(condition);
	}

	/**
	 * load menu tree by parent id
	 * 
	 * @param parentID
	 * @return
	 */
	private List<MenuTreeModel> loadMenuTreeByParentID(int parentID)
	{
		List<MenuTreeModel> menuTreeList = new ArrayList<MenuTreeModel>();

		for (SystemMenu menu : getMenuByParentID(parentID))
		{
			MenuTreeModel menuTreeModel = new MenuTreeModel();
			menuTreeModel.setMenu(convertSystemMenuToMenuModel(menu));
			if (null != getMenuByParentID(menu.getId())
					&& !getMenuByParentID(menu.getId()).isEmpty())
			{
				menuTreeModel.setChildMenuList(loadMenuTreeByParentID(menu
						.getId()));
			}
			menuTreeList.add(menuTreeModel);
		}
		return menuTreeList;
	}

	private MenuModel convertSystemMenuToMenuModel(SystemMenu menu)
	{
		MenuModel menuModel = new MenuModel();
		menuModel.setMenuID(String.valueOf(menu.getId()));
		menuModel.setFresh(menu.getFresh());
		menuModel.setIcon(menu.getIcon());
		menuModel.setName(menu.getName());
		menuModel.setType(menu.getType());
		menuModel.setParentID(menu.getParentID());
		menuModel.setUrl(menu.getUrl());
		menuModel.setFunctionID(menu.getFunctionID());
		menuModel.setValue(menu.getValue());
		menuModel.setExternal(menu.getExternal());
		return menuModel;
	}
}
