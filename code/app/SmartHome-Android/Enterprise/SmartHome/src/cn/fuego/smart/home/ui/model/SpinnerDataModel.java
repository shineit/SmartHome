/**   
* @Title: SpinnerDataModel.java 
* @Package cn.fuego.smart.home.ui.model 
* @Description: TODO
* @author Aether
* @date 2014-12-18 下午2:27:39 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.model;

/** 
 * @ClassName: SpinnerDataModel 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-18 下午2:27:39 
 *  
 */
public class SpinnerDataModel
{


	private String ctrSensorID="";
	private String ctrChannelID="";
	private String text = "";

	public SpinnerDataModel() 
	{
		ctrSensorID = "";
		ctrChannelID ="";
		text = "";
	}


	@Override
	public String toString()
	{
		return text;
	}

	public String getText() 
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}


	public String getCtrSensorID()
	{
		return ctrSensorID;
	}


	public void setCtrSensorID(String ctrSensorID)
	{
		this.ctrSensorID = ctrSensorID;
	}


	public String getCtrChannelID()
	{
		return ctrChannelID;
	}


	public void setCtrChannelID(String ctrChannelID)
	{
		this.ctrChannelID = ctrChannelID;
	}


	
}
