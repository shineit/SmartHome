package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.CameraAccessJson;


/**
 * 
* @ClassName: GetUserMarkListReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:10 
*
 */
public class GetCaTokenByIDRsp extends MispBaseRspJson
{

	private CameraAccessJson caToken = new CameraAccessJson();

	public CameraAccessJson getCaToken()
	{
		return caToken;
	}

	public void setCaToken(CameraAccessJson caToken)
	{
		this.caToken = caToken;
	}

 

 
}