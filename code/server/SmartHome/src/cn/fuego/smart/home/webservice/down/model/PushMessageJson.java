/**   
* @Title: PushMessage.java 
* @Package cn.fuego.smart.home.webservice.down.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-8 上午10:21:24 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.model;

 /** 
 * @ClassName: PushMessage 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-8 上午10:21:24 
 *  
 */
public class PushMessageJson
{

	private int objType;
	private Object obj;
	 
	public int getObjType()
	{
		return objType;
	}
	public void setObjType(int objType)
	{
		this.objType = objType;
	}
	public Object getObj()
	{
		return obj;
	}
	public void setObj(Object obj)
	{
		this.obj = obj;
	}
	@Override
	public String toString()
	{
		return "PushMessageJson [objType=" + objType + ", obj=" + obj + "]";
	}
	 
	
}
