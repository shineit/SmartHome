/**   
* @Title: SystemToken.java 
* @Package cn.fuego.misp.domain 
* @Description: TODO
* @author Aether
* @date 2015-5-19 上午9:00:13 
* @version V1.0   
*/ 
package cn.fuego.misp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: SystemToken 
 * @Description: TODO
 * @author Aether
 * @date 2015-5-19 上午9:00:13 
 *  
 */
public class SystemToken implements PersistenceObject
{

 	private String token_id;
	private String obj_id;
	private String token_type;
	public String getToken_id()
	{
		return token_id;
	}
	public void setToken_id(String token_id)
	{
		this.token_id = token_id;
	}
	public String getObj_id()
	{
		return obj_id;
	}
	public void setObj_id(String obj_id)
	{
		this.obj_id = obj_id;
	}
	public String getToken_type()
	{
		return token_type;
	}
	public void setToken_type(String token_type)
	{
		this.token_type = token_type;
	}
	
	
	
}
