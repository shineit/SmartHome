/**   
* @Title: TableAction.java 
* @Package cn.fuego.misp.web.action.basic 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-16 下午11:23:14 
* @version V1.0   
*/ 
package cn.fuego.misp.web.action.basic;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.util.validate.ValidatorUtil;

/** 
 * @ClassName: TableAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-16 下午11:23:14 
 *  
 */

public abstract class TableAction extends MISPAction
{
	public static final String SHOW_INFO = "showInfo";
	public static final String EDIT_INFO ="editInfo";
	private String jumpActionName;
	private String selectedID;
	private String[] selectedIDList;
	private String operateType;//对应左侧menu

 
	abstract public String create();
	abstract public String delete();
	abstract public String deleteList();
	abstract public String modify();
	abstract public String show();
	

	
	public List<String> convertToPageMessage(List<String> messageList)
	{
		List<String> resourceList = new ArrayList<String>();
		if(!ValidatorUtil.isEmpty(messageList))
		{
			for(String message : messageList)
			{
				resourceList.add(this.getText(message));
			}
		}
		return resourceList;

	}


	public String getOperateType()
	{
		return operateType;
	}

	public void setOperateType(String operateType)
	{
		this.operateType = operateType;
	}

	public String[] getSelectedIDList()
	{
		return selectedIDList;
	}

	public void setSelectedIDList(String[] selectedIDList)
	{
		this.selectedIDList = selectedIDList;
	}

	public String getSelectedID()
	{
		return selectedID;
	}

	public void setSelectedID(String selectedID)
	{
		this.selectedID = selectedID;
	}
	public String getJumpActionName()
	{
		return jumpActionName;
	}
	public void setJumpActionName(String jumpActionName)
	{
		this.jumpActionName = jumpActionName;
	}
 
	public static String getShowInfo()
	{
		return SHOW_INFO;
	}

	

}
