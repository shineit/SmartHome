package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;



/**
 * 
* @ClassName: NewsJson 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:57 
*
 */
public class NewsJson implements Serializable
{
	private int   newsID;
	private String title;
	private String author;
	private String content;
	private long date;
	

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
	public long getDate()
	{
		return date;
	}
	public void setDate(long date)
	{
		this.date = date;
	}
	@Override
	public String toString()
	{
		return "NewsJson [newsID=" + newsID + ", title=" + title + ", author="
				+ author + ", content=" + content + ", date=" + date + "]";
	}
 
	
	

}
