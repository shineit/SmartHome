/**   
* @Title: AdDataCache.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Aether
* @date 2015-3-21 上午10:08:34 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.constant.BageKindEnum;
import cn.fuego.smart.home.webservice.up.model.base.BageNumJson;


/** 
* @ClassName: BageNumDataCache 
* @Description: TODO
* @author Aether
* @date 2015-4-3 下午6:53:29 
*  
*/
public class BageNumDataCache
{

	private  List<BageNumJson> alarmBageList = new ArrayList<BageNumJson>();
	private  List<BageNumJson> statusBageList = new ArrayList<BageNumJson>();
	private  List<BageNumJson> checkBageList = new ArrayList<BageNumJson>();
	private static BageNumDataCache instance;
	private BageNumDataCache()
	{
		 
	}
	
	public synchronized static BageNumDataCache getInstance()
	{
		if(null == instance)
		{
			instance = new BageNumDataCache();
		}
		return instance;
		
	}

	public void classifyNumList(List<BageNumJson> numList)
	{
		if(!ValidatorUtil.isEmpty(numList))
		{
			clear();
			for(BageNumJson json:numList)
			{
				switch (BageKindEnum.getEnumByInt(json.getBageKind()))
				{
				case ALARM:
					alarmBageList.add(json);
					break;
				case STATUS:
					statusBageList.add(json);
					break;
				case CHECK_LOG:
					checkBageList.add(json);
					break;
				default:
					break;					
				}
				
			}
		}

	}
	private void clear()
	{
		alarmBageList.clear();
		statusBageList.clear();
		checkBageList.clear();
	}
	public List<BageNumJson> getAlarmBageList()
	{
		return alarmBageList;
	}

	public void setAlarmBageList(List<BageNumJson> alarmBageList)
	{
		this.alarmBageList = alarmBageList;
	}

	public List<BageNumJson> getStatusBageList()
	{
		return statusBageList;
	}

	public void setStatusBageList(List<BageNumJson> statusBageList)
	{
		this.statusBageList = statusBageList;
	}

	public List<BageNumJson> getCheckBageList()
	{
		return checkBageList;
	}

	public void setCheckBageList(List<BageNumJson> checkBageList)
	{
		this.checkBageList = checkBageList;
	}

	
}
