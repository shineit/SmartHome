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


	private String value = "";
	private String text = "";

	public SpinnerDataModel() 
	{
		value = "";
		text = "";
	}


	@Override
	public String toString()
	{
		return text;
	}

	public String getValue() 
	{
		return value;
	}

	public String getText() 
	{
		return text;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public void setText(String text)
	{
		this.text = text;
	}
	
}
