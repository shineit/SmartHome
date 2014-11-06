/**   
* @Title: IndexAction.java 
* @Package cn.fuego.misp.web.action.login 
* @Description: TODO
* @author Aether
* @date 2014-11-6 下午10:35:25 
* @version V1.0   
*/ 
package cn.fuego.misp.web.action.login;

import cn.fuego.misp.web.action.basic.MISPAction;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.News;

/** 
 * @ClassName: IndexAction 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-6 下午10:35:25 
 *  
 */
public class IndexAction extends MISPAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //首页获取内容
    private News newsContent ;
    private Alarm alarmContent;
    
	public String execute()
	{
		return SUCCESS;
	}

	public News getNewsContent()
	{
		return newsContent;
	}

	public void setNewsContent(News newsContent)
	{
		this.newsContent = newsContent;
	}

	public Alarm getAlarmContent()
	{
		return alarmContent;
	}

	public void setAlarmContent(Alarm alarmContent)
	{
		this.alarmContent = alarmContent;
	}

	
}
