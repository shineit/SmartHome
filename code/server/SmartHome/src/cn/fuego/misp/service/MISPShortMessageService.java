/**   
* @Title: ShortMessageService.java 
* @Package cn.fuego.misp.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-30 上午10:48:33 
* @version V1.0   
*/ 
package cn.fuego.misp.service;

import java.util.List;

 /** 
 * @ClassName: ShortMessageService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-30 上午10:48:33 
 *  
 */
public interface MISPShortMessageService
{
	public boolean sendMessage(List<String> phoneNumList,String content);

}
