package cn.fuego.smart.home.ui.enterprise.alarm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.base.MispHttpActivtiy;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.webservice.up.model.GetSensorPlanByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorPlanByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.SensorPlanJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class SensorLocationActivity extends MispHttpActivtiy
{

	private static final int POINT_SIZE = 3;
	private FireAlarmJson alarm; 
 	private Canvas canvas;
	@Override
	public void initRes()
	{
		this.activityRes.setName("定位");
		this.activityRes.setAvtivityView(R.layout.activity_sensor_location);
		alarm = (FireAlarmJson) this.getIntent().getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		loadPlan();

	}
	public static void jump(Context context,FireAlarmJson alarm)
	{
		Intent intent = new Intent(context,SensorLocationActivity.class);
		intent.putExtra(ListViewResInfo.SELECT_ITEM, alarm);
		context.startActivity(intent);
 	}

	public void loadPlan()
	{
		GetSensorPlanByIDReq req = new GetSensorPlanByIDReq();
		req.setPlanID(alarm.getPlanID());
		WebServiceContext.getInstance().getCompanyManageRest(this).getPlanByID(req);
	}
	@Override
	public void handle(MispHttpMessage message)
	{
		if(message.isSuccess())
		{
			GetSensorPlanByIDRsp rsp = (GetSensorPlanByIDRsp) message.getMessage().obj;
			loadLoactionImage(rsp.getPlan());
		}
		else
		{
			showMessage(message);
		}
		
	}
	public void loadLoactionImage(SensorPlanJson plan)
	{
		ImageLoader.getInstance().loadImage(MemoryCache.getImageUrl()+plan.getPicPath(),
				new ImageLoadingListener() 
		{

			@Override
			public void onLoadingCancelled(String arg0, View arg1)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2)
			{
				drawMark(arg2);
				
			}

			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2)
			{
				showMessage(MISPErrorMessageConst.NET_FAIL);
				
			}

			@Override
			public void onLoadingStarted(String arg0, View arg1)
			{
				showMessage("加载中");
				
			}
			
		});

	}
	
	public void drawMark(Bitmap baseBitmap)
	{
		ImageView image = (ImageView) findViewById(R.id.sensor_loaction_image);
		Bitmap bit  = baseBitmap.copy(Bitmap.Config.ARGB_8888, true); 
        canvas = new Canvas(bit);  
		//canvas = new Canvas(baseBitmap.copy(Bitmap.Config.ARGB_8888, true));  
		Paint paint = new Paint();  
        paint.setStrokeWidth(5);  
        paint.setColor(Color.BLUE); 
 
        RectF r = new RectF(alarm.getLocationX()-POINT_SIZE, alarm.getLocationY()-POINT_SIZE, alarm.getLocationX()+POINT_SIZE, alarm.getLocationY()+POINT_SIZE);
		canvas.drawRect(r, paint);
		//canvas.drawColor(Color.WHITE);  
		
		image.setImageBitmap(bit);
	}

	

}
