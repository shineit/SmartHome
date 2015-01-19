/**   
* @Title: MISPClientVersionService.java 
* @Package cn.fuego.misp.service 
* @Description: TODO
* @author Aether
* @date 2015-1-16 下午9:27:19 
* @version V1.0   
*/ 
package cn.fuego.misp.service;

import cn.fuego.smart.home.domain.ClientVersion;

/** 
 * @ClassName: MISPClientVersionService 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-16 下午9:27:19 
 *  
 */
public interface MISPClientVersionService extends MispCommonService<ClientVersion>
{
	ClientVersion getClientVersion();
}
