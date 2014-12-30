package cn.fuego.misp.service.http;

import android.os.Message;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;

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
		if(MISPErrorMessageConst.SUCCESS != message.what)
		{
			return false;
		}
		if(MISPErrorMessageConst.SUCCESS != getErrorCode())
		{
			return false;
		}
		return true;
		 
	}
	
	public boolean isNetSuccess()
	{
		if(MISPErrorMessageConst.SUCCESS != message.what)
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
		return MISPErrorMessageConst.NET_FAIL;
	}
	

}
