/**   
* @Title: ConfigInfo.java 
* @Package cn.fuego.smart.home.ui.setting.model 
* @Description: TODO
* @author Aether
* @date 2015-4-4 下午7:24:04 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.setting.model;

import java.io.Serializable;

/** 
 * @ClassName: ConfigInfo 
 * @Description: TODO
 * @author Aether
 * @date 2015-4-4 下午7:24:04 
 *  
 */
public class ConfigInfo implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isSound=true;

	public boolean isSound()
	{
		return isSound;
	}

	public void setSound(boolean isSound)
	{
		this.isSound = isSound;
	}
	

}
