package cn.fuego.misp.web.action.basic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.file.FileUtil;
import cn.fuego.common.util.format.DataCreateUtil;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.web.constant.MispConstant;
import cn.fuego.misp.web.constant.SessionAttrNameConst;
import cn.fuego.misp.web.model.menu.MenuModel;
import cn.fuego.misp.web.model.menu.MenuTreeModel;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.misp.web.model.user.UserModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MISPAction extends ActionSupport
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String selectedMenuID;
	private List<MenuModel> menuHeadList;
	private MispMessageModel operateMessage = new MispMessageModel();
	public static final String MISP_DONE_PAGE = "misp-done";
	
	
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
 
	public String saveUploadFile()
	{
		String fileName ="";
		
		if(null != this.getUpload())
		{
			String path = MispConstant.getUploadPath();

			String prefix=uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
			fileName = DataCreateUtil.getUUID() + "." + prefix;
 			FileUtil.createFile(getUpload(), path+File.separator+fileName);
 		}
		else
		{
			log.warn("uplaod file is empty");
		}
		
		return fileName;
	}
	
 
	public void deleteUploadFileByName(String name)
	{
		if(!ValidatorUtil.isEmpty(name))
		{
			//String path = ServletActionContext.getServletContext().getRealPath("/") + "upload/";
			FileUtil.deleteFile(MispConstant.getUploadPath()+File.separator+name);
			FileUtil.deleteFile(name);
		}
		else
		{
			log.warn("delete file name is empty");
		}
	}
	protected void ReturnJson(Object rsp)
	{
		try
		{
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.write(JsonConvert.ObjectToJson(rsp));
		} catch (IOException e)
		{
			log.error("response failed",e);
		}
	}
	
    public UserModel getLoginUser()
    {
    	ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		if(session.get(SessionAttrNameConst.LOGIN_USER) != null)
		{
			return (UserModel) session.get(SessionAttrNameConst.LOGIN_USER); 
		}
		else
		{
			return null;
		}
    }
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


	 
	public String getSelectedMenuID()
	{
		return selectedMenuID;
	}
	public void setSelectedMenuID(String selectedMenuID)
	{
		this.selectedMenuID = selectedMenuID;
	}
	public List<MenuModel> getMenuHeadList()
	{
		
		ActionContext actionContext = ActionContext.getContext();

		if("0".equals(selectedMenuID))
		{
			selectedMenuID = (String) actionContext.getSession().get(SessionAttrNameConst.SELECTED_MENU_ID);
		}else
		{
			actionContext.getSession().put(SessionAttrNameConst.SELECTED_MENU_ID,selectedMenuID);
		}

		List<MenuTreeModel> menuTreeList = (List<MenuTreeModel>) actionContext.getSession().get(SessionAttrNameConst.MENU_TREE);
		
		menuHeadList = new ArrayList<MenuModel>();
  		String menuID = this.selectedMenuID;
  		MenuModel menu = getMenuByMenuID(menuID,menuTreeList);
		while(null != menu)
		{
			menuHeadList.add(menu);
			menu = getMenuByMenuID(menu.getParentID(),menuTreeList);
		}
		Collections.reverse(menuHeadList);
 		return menuHeadList;
	}
	private MenuModel getMenuByMenuID(String menuID,List<MenuTreeModel> menuTreeList)
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

	public File getUpload()
	{
		return upload;
	}

	public void setUpload(File upload)
	{
		this.upload = upload;
	}

	public String getUploadFileName()
	{
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType()
	{
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}
 

	

 
	 
	
	
}
