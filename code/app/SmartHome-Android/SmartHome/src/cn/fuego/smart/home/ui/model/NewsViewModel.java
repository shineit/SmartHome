/**   
* @Title: NewsViewModel.java 
* @Package cn.fuego.smart.home.ui.model 
* @Description: TODO
* @author Aether
* @date 2014-12-9 下午10:06:40 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.model;

/** 
 * @ClassName: NewsViewModel 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-9 下午10:06:40 
 *  
 */
public class NewsViewModel
{
	private String title="title";
	private String time="time";
	private String content="content";
	private String author="author";
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	
}
