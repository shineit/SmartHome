/**
 * 
 */
package cn.fuego.smart.home.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import cn.fuego.common.domain.PersistenceObject;


/** 
* @ClassName: Organization 
* @Description: TODO
* @author Aether
* @date 2015-5-8 上午9:19:21 
*  
*/
 
public class Organization implements PersistenceObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5856044017982629423L;
  	private String org_uuid;
 	private String org_id;
	private String org_parent_id;
	private String org_name;//名称
 	private int org_type=0; 
	private String org_desp;
	
	private String admin_name;
	private int admin_role;
	public String getOrg_uuid()
	{
		return org_uuid;
	}
	public void setOrg_uuid(String org_uuid)
	{
		this.org_uuid = org_uuid;
	}
	public String getOrg_id()
	{
		return org_id;
	}
	public void setOrg_id(String org_id)
	{
		this.org_id = org_id;
	}
	public String getOrg_parent_id()
	{
		return org_parent_id;
	}
	public void setOrg_parent_id(String org_parent_id)
	{
		this.org_parent_id = org_parent_id;
	}
	public String getOrg_name()
	{
		return org_name;
	}
	public void setOrg_name(String org_name)
	{
		this.org_name = org_name;
	}
	public int getOrg_type()
	{
		return org_type;
	}
	public void setOrg_type(int org_type)
	{
		this.org_type = org_type;
	}
	public String getOrg_desp()
	{
		return org_desp;
	}
	public void setOrg_desp(String org_desp)
	{
		this.org_desp = org_desp;
	}
	public String getAdmin_name()
	{
		return admin_name;
	}
	public void setAdmin_name(String admin_name)
	{
		this.admin_name = admin_name;
	}
	public int getAdmin_role()
	{
		return admin_role;
	}
	public void setAdmin_role(int admin_role)
	{
		this.admin_role = admin_role;
	}
	
	
	
	
}
