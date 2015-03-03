/**   
* @Title: CheckLog.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-3-3 上午11:55:47 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

import java.util.Date;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: CheckLog 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-3 上午11:55:47 
 *  
 */
public class CheckLog implements PersistenceObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PRI_KEY = "logID";
	
	private long logID;     		//自增长，主键
	private int  companyID; 		//单位编号，
	private String checkItem; 		//项目名称；
	private String checkSys; 		//项目所属系统；
	private int checkResult;		//巡检结果，0-未设置，1-正常，2-异常
	private String abnormalDesp;	//异常描述
	private String abnormalPic; 	//异常图片地址
	private String checker;			//巡查员名字
	private Date  checkTime;		//巡检时间
	private String handler;			//处理人名字
	private String handleResult;	//处理结论
	private Date handleTime;		//处理时间
	private int status;          	//处理状态，0-未处理；1-已处理
	
	public long getLogID()
	{
		return logID;
	}
	public void setLogID(long logID)
	{
		this.logID = logID;
	}
	public int getCompanyID()
	{
		return companyID;
	}
	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}
	public String getCheckItem()
	{
		return checkItem;
	}
	public void setCheckItem(String checkItem)
	{
		this.checkItem = checkItem;
	}
	public String getCheckSys()
	{
		return checkSys;
	}
	public void setCheckSys(String checkSys)
	{
		this.checkSys = checkSys;
	}
	public int getCheckResult()
	{
		return checkResult;
	}
	public void setCheckResult(int checkResult)
	{
		this.checkResult = checkResult;
	}
	public String getAbnormalDesp()
	{
		return abnormalDesp;
	}
	public void setAbnormalDesp(String abnormalDesp)
	{
		this.abnormalDesp = abnormalDesp;
	}
	public String getAbnormalPic()
	{
		return abnormalPic;
	}
	public void setAbnormalPic(String abnormalPic)
	{
		this.abnormalPic = abnormalPic;
	}
	public String getChecker()
	{
		return checker;
	}
	public void setChecker(String checker)
	{
		this.checker = checker;
	}
	public Date getCheckTime()
	{
		return checkTime;
	}
	public void setCheckTime(Date checkTime)
	{
		this.checkTime = checkTime;
	}
	public String getHandler()
	{
		return handler;
	}
	public void setHandler(String handler)
	{
		this.handler = handler;
	}
	public String getHandleResult()
	{
		return handleResult;
	}
	public void setHandleResult(String handleResult)
	{
		this.handleResult = handleResult;
	}
	public Date getHandleTime()
	{
		return handleTime;
	}
	public void setHandleTime(Date handleTime)
	{
		this.handleTime = handleTime;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public static String getPriKey()
	{
		return PRI_KEY;
	}
	
	
	
}
