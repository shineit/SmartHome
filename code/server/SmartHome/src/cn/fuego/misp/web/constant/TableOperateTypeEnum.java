/**   
* @Title: OperateTypeConst.java 
* @Package cn.fuego.misp.web.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-17 下午11:44:12 
* @version V1.0   
*/ 
package cn.fuego.misp.web.constant;

/** 
 * @ClassName: OperateTypeConst 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-17 下午11:44:12 
 *  
 */

public enum TableOperateTypeEnum
{
	DELETE("delete"),
	MODIFY("modify"),
	VIEW("view"),
	CREATE("create");
	
    private String type;  
    private TableOperateTypeEnum(String type)
    {  
        this.type = type;  
    }
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}  

}
