/**   
* @Title: ServiceManageAction.java 
* @Package cn.fuego.smart.home.web.action.info 
* @Description: TODO
* @author Aether
* @date 2014-11-1 上午10:15:44 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.info;

import cn.fuego.misp.web.action.basic.DWZTableAction;

/** 
 * @ClassName: ServiceManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-1 上午10:15:44 
 *  
 */ 
public class ServiceManageAction  extends DWZTableAction
{

	public String execute()
	{
		return SUCCESS;
	}
	@Override
	public String create()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteList()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String show()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 服务申请单处理
	 */
	public String handle()
	{
		return EDIT_INFO;
		
	}

}
