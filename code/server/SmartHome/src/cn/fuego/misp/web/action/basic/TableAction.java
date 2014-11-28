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

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.common.util.meta.ReflectionUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.model.page.TableDataModel;

/** 
 * @ClassName: TableAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-16 下午11:23:14 
 *  
 */

public abstract class TableAction<E> extends MISPAction
{
	private static final FuegoLog log = FuegoLog.getLog(DataTypeConvert.class);

	public static final String SHOW_INFO = "showInfo";
	public static final String EDIT_INFO ="editInfo";
	private String nextPage;
	
	
	protected String selectedID;
	protected String[] selectedIDList;
	protected String operateType;//对应左侧menu
	protected TableDataModel<E> table = new TableDataModel<E>();
 
	protected E obj;

	public TableAction()
	{
		try
		{
			obj = (E) ReflectionUtil.getSuperClassGenricType(getClass()).newInstance();
		} catch (Exception e)
		{
			log.error("can not get the instance");
		}  
 
	}
	
	public String execute()
	{
		table.setDataSource(this.getService().getDataSource(this.getFilterCondition()));
		return SUCCESS;
	}

	abstract public MispCommonService<E> getService();
	abstract public String create();
	abstract public String delete();
	abstract public String deleteList();
	abstract public String modify();
	abstract public String show();
	
	public List<QueryCondition> getFilterCondition()
	{
		return null;
	}

	
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
 
 
	public String getNextPage()
	{
		nextPage = this.operateType;
		return nextPage;
	}

 

 

	public TableDataModel<E> getTable()
	{
		return table;
	}

	public void setTable(TableDataModel<E> table)
	{
		this.table = table;
	}

	public E getObj()
	{
		return obj;
	}

	public void setObj(E obj)
	{
		this.obj = obj;
	}

	

}
