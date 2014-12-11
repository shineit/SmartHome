/**   
* @Title: GetDetail.java 
* @Package cn.fuego.smart.home.ui.base 
* @Description: TODO
* @author Aether
* @date 2014-12-10 上午10:39:59 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.base;

import android.content.Context;
import android.content.Intent;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.constant.AlarmObjTypeEnmu;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.constant.SensorKindEunm;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.home.AlarmManageActivity;
import cn.fuego.smart.home.ui.home.NewsViewActivity;
import cn.fuego.smart.home.ui.model.AlarmViewModel;
import cn.fuego.smart.home.ui.model.NewsViewModel;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetNewsByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

/** 
 * @ClassName: GetDetail 
 * @Description: 显示通知栏的具体信息
 * @author Aether
 * @date 2014-12-10 上午10:39:59 
 *  
 */
public class GetDetail
{
	//自定义信息内容和页面跳转
	private AlarmViewModel alarmModel = new AlarmViewModel();
	private NewsViewModel newsModel = new NewsViewModel();
	private Intent newsIntent= new Intent();
	private Intent alarmIntent= new Intent();	
	
	public void showAlarm(final Context context,PushMessageJson pushMsg)
	{
		GetAlarmByIDReq alarmReq = new GetAlarmByIDReq();
		alarmReq.setAlarmID(String.valueOf(pushMsg.getObj()));
		alarmReq.setToken(MemoryCache.getToken());
		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage msg)
			{
				GetAlarmByIDRsp rsp = (GetAlarmByIDRsp) msg.getMessage().obj;
				if(rsp.getAlarm().getObjType()==AlarmObjTypeEnmu.CONCENTRATOR_ALARM.getIntValue())
        		{
        			return;
        		}
        		if(rsp.getAlarm().getObjType()==AlarmObjTypeEnmu.HOME_SENSOR.getIntValue())
        		{
        			showHomeSensor(context,rsp.getAlarm());
        		}
        		if(rsp.getAlarm().getObjType()==AlarmObjTypeEnmu.FIRE_SENSOR.getIntValue())
        		{
        			return;
        		}
			}
		}).getAlarm(alarmReq);
	}
	
	public void showHomeSensor(final Context context,final AlarmJson alarm)
	{
		GetSensorByIDReq req = new GetSensorByIDReq();
    	req.setSensorID(String.valueOf(alarm.getObjID()));
  		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage msg) {

				 GetSensorByIDRsp rsp = (GetSensorByIDRsp)msg.getMessage().obj;
				 
				 
				 alarmIntent.putExtra(alarmModel.getEventID(), String.valueOf(alarm.getId()));
				 alarmIntent.putExtra(alarmModel.getObjID(), String.valueOf(rsp.getSensor().getSensorID()));
				 alarmIntent.putExtra(alarmModel.getObj(), SensorKindEunm.getEnumByInt(rsp.getSensor().getSensorKind()).getStrValue());
     		     alarmIntent.putExtra(alarmModel.getDescription(),rsp.getSensor().getDescriptions());
				 alarmIntent.putExtra(alarmModel.getWarnValue(), String.valueOf(rsp.getSensor().getWarnValue()));
				 alarmIntent.putExtra(alarmModel.getErrorValue(), String.valueOf(rsp.getSensor().getErrorValue()));
				 
				 alarmIntent.putExtra(alarmModel.getTitle(),AlarmTypeEnum.getEnumByInt(alarm.getAlarmType()).getStrValue());
		         alarmIntent.setClass(context,AlarmManageActivity.class);
                 alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                 context.startActivity(alarmIntent);

			}
		}).getSensor(req);
  		
		
	}
	
	public void showNews(final Context context,PushMessageJson pushMsg)
	{
		GetNewsByIDReq newsReq = new GetNewsByIDReq();
		newsReq.setToken(MemoryCache.getToken());
		newsReq.setNewsID(String.valueOf(pushMsg.getObj()));
		WebServiceContext.getInstance().getNewsManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage msg)
			{
				GetNewsByIDRsp rsp = (GetNewsByIDRsp) msg.getMessage().obj;
				newsIntent.putExtra(newsModel.getTitle(), rsp.getNews().getTitle());
				newsIntent.putExtra(newsModel.getContent(), rsp.getNews().getContent());
				newsIntent.putExtra(newsModel.getAuthor(), rsp.getNews().getAuthor());
				newsIntent.setClass(context,NewsViewActivity.class);
				newsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context.startActivity(newsIntent);
			}
			
		}).getNews(newsReq);
	}
}
