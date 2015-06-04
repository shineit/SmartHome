package cn.fuego.smart.home.domain;

import java.util.Date;

import cn.fuego.common.domain.PersistenceObject;
import cn.fuego.common.util.format.DateUtil;


/**
 * 
* @ClassName: News 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:01:08 
* @edit Aether
 */
public class News implements PersistenceObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String PRI_KEY = "newsID";
	
	private int   newsID; //新闻编号,自增长
	private String title; //新闻标题
	private String author; //发布人
	private String content; //分布内容
	private Date date= DateUtil.getCurrentDate();     //发布时间
	private Integer status; //发布状态，0-未发布，1-已发布
	
	private String org_id;
	public int getNewsID()
	{
		return newsID;
	}
	public void setNewsID(int newsID)
	{
		this.newsID = newsID;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	public String getOrg_id()
	{
		return org_id;
	}
	public void setOrg_id(String org_id)
	{
		this.org_id = org_id;
	}
	 
	
	
}
