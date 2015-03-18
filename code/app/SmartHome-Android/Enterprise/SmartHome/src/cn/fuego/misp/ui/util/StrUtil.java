/**   
* @Title: StrUtil.java 
* @Package cn.fuego.misp.ui.util 
* @Description: TODO
* @author Aether
* @date 2015-3-17 下午5:16:25 
* @version V1.0   
*/ 
package cn.fuego.misp.ui.util;

import cn.fuego.common.util.validate.ValidatorUtil;

/** 
 * @ClassName: StrUtil 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-17 下午5:16:25 
 *  
 */
public class StrUtil
{

	/**
	 * 处理字符串为空时页面显示null的问题
	 * @param str
	 * @return
	 */
	public static String noNullStr(String str)
	{
		String result;
		if(!ValidatorUtil.isEmpty(str))
		{
			result=str;
		}
		else
		{
			result="";
		}
		return result;
		
	}
}
