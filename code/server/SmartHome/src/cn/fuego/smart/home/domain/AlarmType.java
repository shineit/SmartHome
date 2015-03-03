/**   
* @Title: AlarmType.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-3-3 上午10:59:58 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

/** 
 * @ClassName: AlarmType 
 * @Description: 告警类型表
 * @author Aether
 * @date 2015-3-3 上午10:59:58 
 *  
 */
public class AlarmType 
{
	private int typeID;			//主键，设备配置好编码
	private String typeName;	//类型名称
	private int kind;			//0-告警；1-设备状态；
	private int isPush; 	//0-不推送；1-推送；
	private int pushType;		//0-不长鸣；1-长鸣；n-循环次数(作为拓展)
	private int isFeedback;	//0-为恢复类型；1-不为恢复类型；
	private int feedbackID; 	//恢复码
	public int getTypeID()
	{
		return typeID;
	}
	public void setTypeID(int typeID)
	{
		this.typeID = typeID;
	}
	public String getTypeName()
	{
		return typeName;
	}
	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}
	public int getKind()
	{
		return kind;
	}
	public void setKind(int kind)
	{
		this.kind = kind;
	}

	public int getIsPush()
	{
		return isPush;
	}
	public void setIsPush(int isPush)
	{
		this.isPush = isPush;
	}
	public int getIsFeedback()
	{
		return isFeedback;
	}
	public void setIsFeedback(int isFeedback)
	{
		this.isFeedback = isFeedback;
	}
	public int getPushType()
	{
		return pushType;
	}
	public void setPushType(int pushType)
	{
		this.pushType = pushType;
	}

	public int getFeedbackID()
	{
		return feedbackID;
	}
	public void setFeedbackID(int feedbackID)
	{
		this.feedbackID = feedbackID;
	}
	
}
