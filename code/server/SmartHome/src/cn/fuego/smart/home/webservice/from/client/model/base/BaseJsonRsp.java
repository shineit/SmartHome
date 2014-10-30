package cn.fuego.smart.home.webservice.from.client.model.base;


/**
 * 
* @ClassName: BaseJsonRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:45 
*
 */
public class BaseJsonRsp
{
	private SetResultJson result = new SetResultJson();

	public SetResultJson getResult()
	{
		return result;
	}

	public void setResult(SetResultJson result)
	{
		this.result = result;
	}

	@Override
	public String toString()
	{
		return "BaseJsonRsp [result=" + result + "]";
	}
	
	
}
