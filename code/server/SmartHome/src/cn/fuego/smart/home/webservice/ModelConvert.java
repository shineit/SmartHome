/**   
* @Title: ModelConvert.java 
* @Package cn.fuego.smart.home.webservice 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-13 下午12:26:51 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice;

import java.util.Date;

import cn.fuego.common.util.format.DateUtil;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;

 /** 
 * @ClassName: ModelConvert 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-13 下午12:26:51 
 *  
 */
public class ModelConvert
{
	
	public static AlarmJson AlarmToJson(Alarm alarm)
	{
		AlarmJson json = new AlarmJson();
		json.setId(alarm.getId());
		json.setObjType(alarm.getObjType());
		json.setObjID(alarm.getObjID());
 		json.setAlarmType(alarm.getAlarmType());
		json.setDataValue(alarm.getDataValue());
		json.setAlarmTime(DateUtil.getDateTime(alarm.getAlarmTime())); 
		json.setClearUser(alarm.getClearUser());
		json.setClearStatus(alarm.getClearStatus());
		json.setClearTime(DateUtil.getDateTime(alarm.getClearTime()));
		return json;
		
	}
	public static ServiceOrder jsonToServiceOrder(ServiceOrderJson json)
	{
		if(null == json)
		{
			return null;
		}
		ServiceOrder order = new ServiceOrder();
		order.setOrderID(json.getOrderID());
		order.setOrderName(json.getOrderName());
		order.setOrderType(json.getOrderType());
		order.setContent(json.getContent());
		order.setCreator(json.getCreator());
		order.setCreateTime(new Date(json.getCreateTime()));
		order.setContactName(json.getContactName());
		order.setContactAddr(json.getContactAddr());
		order.setPhoneNum(json.getPhoneNum());
		order.setOrderStatus(json.getOrderStatus());
		order.setHandler(json.getHandler());
		order.setHandleResult(json.getHandleResult());
		order.setHandleTime(new Date(json.getHandleTime()));
		
		return order;
	}
}