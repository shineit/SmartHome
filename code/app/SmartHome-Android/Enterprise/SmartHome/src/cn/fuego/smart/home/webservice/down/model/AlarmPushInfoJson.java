/**   
* @Title: AlarmPushInfoJson.java 
* @Package cn.fuego.smart.home.webservice.down.model 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-20 上午11:35:59 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.model;

 /** 
 * @ClassName: AlarmPushInfoJson 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-20 上午11:35:59 
 *  
 */
public class AlarmPushInfoJson
{
	private int pushType;
	private int companyID;
	public int getPushType()
	{
		return pushType;
	}
	public void setPushType(int pushType)
	{
		this.pushType = pushType;
	}
	public int getCompanyID()
	{
		return companyID;
	}
	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}
	

}
