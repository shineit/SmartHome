package cn.fuego.misp.web.action.upload;

import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.web.action.basic.MISPAction;
import cn.fuego.misp.webservice.up.model.MispBaseRspJson;

public class UploadFileAction extends MISPAction
{
	public String uploadFile()
	{
		MispBaseRspJson rsp = new MispBaseRspJson();
		try
		{
			String fileName = saveUploadFile();	
			rsp.setObj(fileName);
		}
		catch (Exception e) 
		{
			rsp.setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		
		ReturnJson(rsp);
		
		return null;
	}

}
