package cn.fuego.misp.web.action.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.web.constant.SessionAttrNameConst;
import cn.fuego.misp.web.model.menu.MenuModel;
import cn.fuego.misp.web.model.menu.MenuTreeModel;
import cn.fuego.misp.web.model.message.MispMessageModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MISPAction extends ActionSupport
{
	private int selectedMenuID;
	private List<MenuModel> menuHeadList;
	private MispMessageModel operateMessage = new MispMessageModel();
	public static final String MISP_DONE_PAGE = "misp-done";
	
	 


	public MispMessageModel getOperateMessage()
	{
		return operateMessage;
	}


	public void setOperateMessage(MispMessageModel operateMessage)
	{
		this.operateMessage = operateMessage;
	}


	/*
	 * Public Function 
	 * 用来方便Ajax获取单个参数使用
	 * 说明：因为从ajax 获取的参数如果是非常简单的String，则不需要用json处理，但是因为返回到后台始终以数组形式体现，
	 * 为了简化Action界面的代码量，故而在这个类里面实现相关单一参数提取功能。
	 */
	public String getAjaxPara(String paraName){
		
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> parameters = actionContext.getParameters();
		
		String[] paraList=(String[]) parameters.get(paraName);
		String para=paraList[0];


		return para;
		
	}


	public int getSelectedMenuID()
	{
		return selectedMenuID;
	}

	public void setSelectedMenuID(int selectedMenuID)
	{
		this.selectedMenuID = selectedMenuID;
	}


	public List<MenuModel> getMenuHeadList()
	{
		
		ActionContext actionContext = ActionContext.getContext();

		if(0==selectedMenuID)
		{
			selectedMenuID=(Integer) actionContext.getSession().get(SessionAttrNameConst.SELECTED_MENU_ID);
		}else
		{
			actionContext.getSession().put(SessionAttrNameConst.SELECTED_MENU_ID,selectedMenuID);
		}

		List<MenuTreeModel> menuTreeList = (List<MenuTreeModel>) actionContext.getSession().get(SessionAttrNameConst.MENU_TREE);
		
		menuHeadList = new ArrayList<MenuModel>();
  		int menuID = this.selectedMenuID;
  		MenuModel menu = getMenuByMenuID(menuID,menuTreeList);
		while(null != menu)
		{
			menuHeadList.add(menu);
			menu = getMenuByMenuID(menu.getParentID(),menuTreeList);
		}
		Collections.reverse(menuHeadList);
 		return menuHeadList;
	}
	private MenuModel getMenuByMenuID(int menuID,List<MenuTreeModel> menuTreeList)
	{
 		if(!ValidatorUtil.isEmpty(menuTreeList))
		{	
 			for(MenuTreeModel e : menuTreeList)
 			{
 				MenuModel menu =  e.getMenuModelByMenuID(menuID);
 				if(null != menu)
 				{	
 					return menu;
 				}
 			}
		}
		
		
		return null;
	}


	public void setMenuHeadList(List<MenuModel> menuHeadList)
	{
		this.menuHeadList = menuHeadList;
	}

	

 
	 
	
	
}
