/**   
* @Title: MessageInfoJson.java 
* @Package cn.fuego.smart.home.webservice.down.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-4 下午3:24:25 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.model;

 /** 
 * @ClassName: MessageInfoJson 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-4 下午3:24:25 
 *  
 */
public class MessageInfoJson
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
	
	
}
