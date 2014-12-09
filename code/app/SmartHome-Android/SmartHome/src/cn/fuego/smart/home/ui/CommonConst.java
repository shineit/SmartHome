/**   
* @Title: CommonConst.java 
* @Package cn.fuego.smart.home.ui 
* @Description: TODO
* @author Aether
* @date 2014-12-9 下午5:20:32 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui;

/** 
 * @ClassName: CommonConst 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-9 下午5:20:32 
 *  
 */
public class CommonConst
{
    
    public static  int flag=0;//判断进入页面方式，0-表示首次进入，1-其他切换进入

	public static int getFlag()
	{
		return flag;
	}

	public static void setFlag(int flag)
	{
		CommonConst.flag = flag;
	}
    
}
