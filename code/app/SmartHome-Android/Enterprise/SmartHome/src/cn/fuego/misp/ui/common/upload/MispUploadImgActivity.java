package cn.fuego.misp.ui.common.upload;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.common.upload.UploadUtil.OnUploadProcessListener;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.R;

public class MispUploadImgActivity extends MispBaseActivtiy implements OnUploadProcessListener
{

	//去上传文件
	protected static final int TO_UPLOAD_FILE = 1;  
	//上传文件响应
	protected static final int UPLOAD_FILE_DONE = 2;  
	//选择文件
	public static final int TO_SELECT_PHOTO = 3;
	//上传初始化
	private static final int UPLOAD_INIT_PROCESS = 4;
	//上传中
	private static final int UPLOAD_IN_PROCESS = 5;
	//请求服务器uri
	private static String requestURL = MemoryCache.getWebContextUrl()+"/UploadFile!uploadFile";
 	private ImageView imageView;
 
	private ProgressBar progressBar;
 	private String picPath = null;
	private ProgressDialog progressDialog;
	private View submit_btn_area;
	
	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.misp_upload_img);
		this.activityRes.getButtonIDList().add(R.id.upload_img_camera);
		this.activityRes.getButtonIDList().add(R.id.upload_img_submitbtn);
		
	}
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
 		progressBar = (ProgressBar) findViewById(R.id.upload_img_progressBar);
        imageView = (ImageView) findViewById(R.id.upload_imgview_area);
        submit_btn_area = findViewById(R.id.submit_btn_area);
        submit_btn_area.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(this);
        
    }
    
	public static void jump(Activity activity, int requestCode)
	{
		Intent intent = new Intent(activity,MispUploadImgActivity.class);
		activity.startActivityForResult(intent, requestCode);
	}
	 
	public void onClick(View v) {
		switch (v.getId()) 
		{

		case R.id.upload_img_submitbtn:
			if(picPath!=null)
			{		
				MispHttpMessage msg = new MispHttpMessage();
			    msg.getMessage().what = TO_UPLOAD_FILE;
			    
			    handler.sendMessage(msg);
 			}else{
				Toast.makeText(this, "图片不存在", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.upload_img_camera:
			Intent intent1 = new Intent(this,SelectPicActivity.class);
			startActivityForResult(intent1, TO_SELECT_PHOTO);
			break;
 
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{

		if(resultCode==Activity.RESULT_OK && requestCode == TO_SELECT_PHOTO)
		{
 			//imageView.setImageBitmap(null);
 			picPath = data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH);
 			LoadImageUtil.getInstance().loadImage(imageView, "file://"+picPath);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	 
	public void onUploadDone(MispBaseRspJson rsp)
	{
		progressDialog.dismiss();
		MispHttpMessage msg = new MispHttpMessage();
		msg.getMessage().what = UPLOAD_FILE_DONE;
  
 		msg.getMessage().obj = rsp;
		handler.sendMessage(msg);
	}
	
	private void toUploadFile()
	{
 		progressDialog.setMessage("正在上传");
 
		progressDialog.show();
		String fileKey = "upload";
		UploadUtil uploadUtil = UploadUtil.getInstance();;
		uploadUtil.setOnUploadProcessListener(this);   		
		Map<String, String> params = new HashMap<String, String>();
		uploadUtil.uploadFile( picPath,fileKey, requestURL,params);
	}
	
	private MispHttpHandler handler = new MispHttpHandler(){
		@Override
		public void handle(MispHttpMessage msg) {
			switch (msg.getMessage().what) {
			case TO_UPLOAD_FILE:
				progressBar.setVisibility(View.VISIBLE);
				submit_btn_area.setVisibility(View.GONE);
				toUploadFile();
				break;			
			case UPLOAD_INIT_PROCESS:
				progressBar.setMax(msg.getMessage().arg1);
				break;
			case UPLOAD_IN_PROCESS:
				progressBar.setProgress(msg.getMessage().arg1);
				break;
			case UPLOAD_FILE_DONE:
				msg.getMessage().what = MISPErrorMessageConst.SUCCESS;
				if(msg.isSuccess())
				{
					MispBaseRspJson json = (MispBaseRspJson) msg.getMessage().obj;
			        Intent intent = new Intent();
			        intent.putExtra(RETURN_DATA, json);
			        intent.putExtra("picPath", picPath);//本地图片地址
			        setResult(0, intent);

					finish();
				}
				showMessage(msg);
 				
 				break;
			default:
				break;
			}
 		}
		
	};

	 
	public void onUploadProcess(int uploadSize) 
	{
		MispHttpMessage msg = new MispHttpMessage();
		msg.getMessage().what = UPLOAD_IN_PROCESS;
		msg.getMessage().arg1 = uploadSize;
		handler.sendMessage(msg);
	}
	 
	public void initUpload(int fileSize) 
	{
		MispHttpMessage msg = new MispHttpMessage();
		msg.getMessage().what = UPLOAD_INIT_PROCESS;
		msg.getMessage().arg1 = fileSize;
	  
		handler.sendMessage(msg );
	}


}
