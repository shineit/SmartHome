package cn.fuego.misp.service.http;

import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import android.os.Message;

public class MispHttpMessage 
{
	private Message message = new Message();

	public Message getMessage()
	{
		return message;
	}

	public void setMessage(Message message)
	{
		this.message = message;
	}
	
	public boolean isSuccess()
	{
		if(ErrorMessageConst.SUCCESS != message.what)
		{
			return false;
		}
		if(ErrorMessageConst.SUCCESS != getErrorCode())
		{
			return false;
		}
		return true;
		 
	}
	
	public boolean isNetSuccess()
	{
		if(ErrorMessageConst.SUCCESS != message.what)
		{
			return false;
		}
		return true;
	}

	public int getErrorCode()
	{
		BaseJsonRsp rsp = (BaseJsonRsp) message.obj;
		if(null != rsp)
		{
			if(null != rsp.getResult())
			{
 				return rsp.getResult().getErrorCode();
			}
		}
		return ErrorMessageConst.NET_FAIL;
	}
	

}
