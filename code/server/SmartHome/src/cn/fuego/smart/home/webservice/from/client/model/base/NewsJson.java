package cn.fuego.smart.home.webservice.from.client.model.base;

import cn.fuego.common.util.format.DateUtil;
import cn.fuego.smart.home.domain.News;


/**
 * 
* @ClassName: NewsJson 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:57 
*
 */
public class NewsJson
{
	private int   newsID;
	private String title;
	private String author;
	private String content;
	private String date;
	
	public void loadWithNews(News news)
	{
		this.newsID = news.getNewsID();
		this.title = news.getTitle();
		this.author  = news.getAuthor();
		this.content = news.getContent();
		this.date = DateUtil.DateToString(news.getDate());
	}
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
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	
	

}
