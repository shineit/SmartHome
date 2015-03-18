package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;

/** 
* @ClassName: CheckLogJson 
* @Description: TODO
* @author Aether
* @date 2015-3-16 下午5:14:50 
*  
*/ 
public class CheckLogJson implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long logID;     		//自增长，主键
	private int  companyID; 		//单位编号，
	private String checkItem; 		//项目名称；
	private String checkSys; 		//项目所属系统；
	private int checkResult;		//巡检结果，0-未设置，1-正常，2-异常
	private String abnormalDesp;	//异常描述
	private String abnormalPic; 	//异常图片地址
	private String checker;			//巡查员名字
	private long  checkTime;		//巡检时间
	private String handler;			//处理人名字
	private String handleResult;	//处理结论
	private long handleTime;		//处理时间
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

	public long getCheckTime()
	{
		return checkTime;
	}
	public void setCheckTime(long checkTime)
	{
		this.checkTime = checkTime;
	}
	
	public long getHandleTime()
	{
		return handleTime;
	}
	public void setHandleTime(long handleTime)
	{
		this.handleTime = handleTime;
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

	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	@Override
	public String toString()
	{
		return "CheckLogJson [logID=" + logID + ", companyID=" + companyID
				+ ", checkItem=" + checkItem + ", checkSys=" + checkSys
				+ ", checkResult=" + checkResult + ", abnormalDesp="
				+ abnormalDesp + ", abnormalPic=" + abnormalPic + ", checker="
				+ checker + ", checkTime=" + checkTime + ", handler=" + handler
				+ ", handleResult=" + handleResult + ", handleTime="
				+ handleTime + ", status=" + status + "]";
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((checkItem == null) ? 0 : checkItem.hashCode());
		result = prime * result
				+ ((checkSys == null) ? 0 : checkSys.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckLogJson other = (CheckLogJson) obj;
		if (checkItem == null)
		{
			if (other.checkItem != null)
				return false;
		} else if (!checkItem.equals(other.checkItem))
			return false;
		if (checkSys == null)
		{
			if (other.checkSys != null)
				return false;
		} else if (!checkSys.equals(other.checkSys))
			return false;
		return true;
	}
	
	
}
