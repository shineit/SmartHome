/**   
* @Title: IDCreateService.java 
* @Package cn.tinder.fuego.service 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-12 上午12:06:40 
* @version V1.0   
*/ 
package cn.fuego.misp.service;

import java.util.List;

/** 
 * @ClassName: IDCreateService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-12 上午12:06:40 
 *  
 */

public interface IDCreateService
{
	public static final String USER_ID_NAME = "USER_ID";
	public static final String USER_GROUP_ID_NAME = "USER_GROUP_ID";
	
	public static final String ORG_ID_NAME = "ORG_ID";

	public static final String ORDER_ID_NAME = "ORDER_ID";

	
	public static int INCREASE_MODE = 1;
	public static int DECREASE_MODE = 0;
	
	public static String ID_PRFIX = "0";

 
	/**
	 * get id list by id count
	 * @param idCount
	 * @return 
	 */
	public List<String> createIDList(int idCount);
	
	public String create();
	
	public String getUUID();
 
}
