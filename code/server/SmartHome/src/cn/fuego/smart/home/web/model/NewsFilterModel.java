/**   
* @Title: NewsFilterModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Aether
* @date 2014-11-3 下午6:12:58 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;

/** 
 * @ClassName: NewsFilterModel 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-3 下午6:12:58 
 *  
 */
public class NewsFilterModel
{
	private String title;  //标题条件
	private String author; //发布人
	private String content; //发布内容
	private String startDate; //起始时间
	private String endDate;  //截止时间
	
	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		 
			if(!ValidatorUtil.isEmpty(this.getTitle()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"title",this.getTitle()));
			}
			if(!ValidatorUtil.isEmpty(this.getAuthor()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"author",this.getAuthor()));
			}
			if(!ValidatorUtil.isEmpty(this.getContent()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"content",this.getContent()));
			}
			if(!ValidatorUtil.isEmpty(this.getStartDate()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.BIGER_EQ,"date",this.getStartDate()));
			}
			if(!ValidatorUtil.isEmpty(this.getEndDate()))
			{
				Date endDate = DateUtil.stringToDate(this.getEndDate());
				conditionList.add(new QueryCondition(ConditionTypeEnum.LOWER,"date",DateUtil.DateToString(DateUtil.dayCalculate(endDate, 1))));
			}				
		 
		return conditionList;
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
	public String getStartDate()
	{
		return startDate;
	}
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	public String getEndDate()
	{
		return endDate;
	}
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	
}
