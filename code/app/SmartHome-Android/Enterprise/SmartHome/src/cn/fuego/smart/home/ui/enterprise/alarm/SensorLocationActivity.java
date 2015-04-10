package cn.fuego.smart.home.ui.enterprise.alarm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispHttpActivtiy;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.webservice.up.model.GetSensorPlanByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorPlanByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.SensorPlanJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class SensorLocationActivity extends MispHttpActivtiy implements OnTouchListener
{

	private static final int POINT_SIZE = 10;
	private FireAlarmJson alarm; 
 	private Canvas canvas;
 	private ImageView locImg;
 	private int titleHeight;
 	private int activityHeight;
 	
 	
 	Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    DisplayMetrics dm;
    Bitmap bitmap;

    /** 最小缩放比例*/
    float minScaleR = 1.0f;
    /** 最大缩放比例*/
    static final float MAX_SCALE = 10f;

    /** 初始状态*/
    static final int NONE = 0;
    /** 拖动*/
    static final int DRAG = 1;
    /** 缩放*/
    static final int ZOOM = 2;
    
    /** 当前模式*/
    int mode = NONE;

    PointF prev = new PointF();
    PointF mid = new PointF();
    float dist = 1f;
	@Override
	public void initRes()
	{
		this.waitDailog.show();
		this.activityRes.setName("定位");
		this.activityRes.setAvtivityView(R.layout.activity_sensor_location);
		alarm = (FireAlarmJson) this.getIntent().getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		loadPlan();

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		titleHeight=(int) (getResources().getDisplayMetrics().density*(50));
		activityHeight=this.getActivityHeight();
		locImg = (ImageView) findViewById(R.id.sensor_loaction_image);// 获取控件

		locImg.setOnTouchListener(this);// 设置触屏监听
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);// 获取分辨率

        
        
	}
	public int Dp2Px(Context context, float dp) { 
	    final float scale = context.getResources().getDisplayMetrics().density; 
	    return (int) (dp * scale + 0.5f); 
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
		this.waitDailog.dismiss();
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
				waitDailog.dismiss();
			}

			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2)
			{
				drawMark(arg2);
				waitDailog.dismiss();
				
			}

			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2)
			{
				showMessage(MISPErrorMessageConst.NET_FAIL);
				waitDailog.dismiss();
				
			}

			@Override
			public void onLoadingStarted(String arg0, View arg1)
			{
				showMessage("正在加载平面图……");
				waitDailog.show();
				
			}
			
		});

	}
	
	public void drawMark(Bitmap baseBitmap)
	{

		Bitmap oldBitmap=baseBitmap.copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(oldBitmap);  
		Paint paint = new Paint();  
        paint.setStrokeWidth(5);  
        paint.setColor(Color.RED); 
        paint.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除
        //RectF r = new RectF(alarm.getLocationX()-POINT_SIZE, alarm.getLocationY()-POINT_SIZE, alarm.getLocationX()+POINT_SIZE, alarm.getLocationY()+POINT_SIZE);
		//canvas.drawRect(r, paint);
		//canvas.drawColor(Color.WHITE);  
	    canvas.drawCircle(alarm.getLocationX(), alarm.getLocationY(), POINT_SIZE, paint);// 小圆  
	    int bitmapWidth=oldBitmap.getWidth();
		int bitmapHeight=oldBitmap.getHeight();
		Matrix m = new Matrix();
		//如果图片的宽度大于高度，则需要旋转90重新生成图片
		if(bitmapWidth>bitmapHeight)
		{
			m.postRotate(90);  //旋转90度
		}
		else
		{
			m.postRotate(0);  //不做旋转
		}

		bitmap = Bitmap.createBitmap(oldBitmap, 0, 0, bitmapWidth, bitmapHeight,
	            m, false);
		locImg.setImageBitmap(bitmap);
	    center();
	    //顺序不能变化，否则导致初始化不能居中
	    locImg.setImageMatrix(matrix);
	}

	/**
     * 触屏监听
     */
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
        // 主点按下
        case MotionEvent.ACTION_DOWN:
            savedMatrix.set(matrix);
            prev.set(event.getX(), event.getY());
            mode = DRAG;
            break;
        // 副点按下
        case MotionEvent.ACTION_POINTER_DOWN:
            dist = spacing(event);
            // 如果连续两点距离大于10，则判定为多点模式
            if (spacing(event) > 10f) {
                savedMatrix.set(matrix);
                midPoint(mid, event);
                mode = ZOOM;
            }
            break;
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_POINTER_UP:
            mode = NONE;
            //savedMatrix.set(matrix);
            break;
        case MotionEvent.ACTION_MOVE:
            if (mode == DRAG) {
                matrix.set(savedMatrix);
                matrix.postTranslate(event.getX() - prev.x, event.getY()
                        - prev.y);
            } else if (mode == ZOOM) {
                float newDist = spacing(event);
                if (newDist > 10f) {
                    matrix.set(savedMatrix);
                    float tScale = newDist / dist;
                    matrix.postScale(tScale, tScale, mid.x, mid.y);
                }
            }
            break;
        }
        locImg.setImageMatrix(matrix);
        CheckView();
        return true;
    }

    /**
     * 限制最大最小缩放比例，自动居中
     */
    private void CheckView() {
        float p[] = new float[9];
        matrix.getValues(p);
        if (mode == ZOOM) {
            if (p[0] < minScaleR) {
            	//Log.d("", "当前缩放级别:"+p[0]+",最小缩放级别:"+minScaleR);
                matrix.setScale(minScaleR, minScaleR);
            }
            if (p[0] > MAX_SCALE) {
            	//Log.d("", "当前缩放级别:"+p[0]+",最大缩放级别:"+MAX_SCALE);
                matrix.set(savedMatrix);
            }
        }
        center();
    }


    private void center() {
        center(true, true);
    }

    /**
     * 横向、纵向居中
     */
    protected void center(boolean horizontal, boolean vertical) {

        Matrix m = new Matrix();
        m.set(matrix);
        RectF rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        m.mapRect(rect);

        float height = rect.height();
        float width = rect.width();

        float deltaX = 0, deltaY = 0;

        if (vertical) {
            // 图片小于屏幕大小，则居中显示。大于屏幕，上方留空则往上移，下方留空则往下移
            //int screenHeight = dm.heightPixels;
        	//app 非全屏模式下，需要交掉上方状态栏高度
        	int screenHeight=activityHeight;
            if (height < screenHeight) {
               // deltaY = (screenHeight - height) / 2 - rect.top;
            	//需要减掉title高度值
            	deltaY=(screenHeight - height-titleHeight) / 2 - rect.top;
            } else if (rect.top > 0) {
                deltaY = -rect.top;
            } else if (rect.bottom < screenHeight) {
                deltaY = locImg.getHeight() - rect.bottom;
            }
        }

        if (horizontal) {
            int screenWidth = dm.widthPixels;
            if (width < screenWidth) {
                deltaX = (screenWidth - width) / 2 - rect.left;
            } else if (rect.left > 0) {
                deltaX = -rect.left;
            } else if (rect.right < screenWidth) {
                deltaX = screenWidth - rect.right;
            }
        }
       
        matrix.postTranslate(deltaX,deltaY);
    }

    /**
     * 两点的距离
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    /**
     * 两点的中点
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
    
	

}
