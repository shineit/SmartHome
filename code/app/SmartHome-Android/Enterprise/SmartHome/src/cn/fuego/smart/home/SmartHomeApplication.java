/**   
* @Title: EzvizApplication.java 
* @Package cn.fuego.smart.home.ui 
* @Description: TODO
* @author Aether
* @date 2015-1-7 上午10:33:46 
* @version V1.0   
*/ 
package cn.fuego.smart.home;

import java.io.File;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.dao.SharedPreUtil;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.smart.home.cache.AppCache;
import cn.jpush.android.api.JPushInterface;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

/** 
 * @ClassName: EzvizApplication 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-7 上午10:33:46 
 *  
 */
public class SmartHomeApplication extends Application
{
	private FuegoLog log = FuegoLog.getLog(getClass());       
    @Override
    public void onCreate() {
        super.onCreate();
        
        //EzvizAPI.init(this, EzvizConfigConst.APP_KEY, EzvizConfigConst.SECRET_KEY); 
        //EzvizAPI.getInstance().setServerUrl(EzvizConfigConst.API_URL, EzvizConfigConst.WEB_URL);     
		PackageManager packageManager = getPackageManager();
		SharedPreUtil.initSharedPreference(getApplicationContext());
 
		initImageCache();

		JPushInterface.init(this);  
		try
		{
			PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
			MemoryCache.setVersionCode(packInfo.versionCode);
			MemoryCache.setVersionNname(packInfo.versionName);
			MemoryCache.setDensity(getResources().getDisplayMetrics().density);

 			AppCache.getInstance().load();

		} catch (NameNotFoundException e)
		{
			log.info("can not get the package information");
		}
    }
    
    private void initImageCache()
	{
		File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "smarthome/imageloader/Cache");  
		ImageLoaderConfiguration config = new ImageLoaderConfiguration  
			    .Builder(this)  
			    .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽  
			   // .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个  
			    .threadPoolSize(30)//线程池内加载的数量  
			    .threadPriority(Thread.NORM_PRIORITY - 2)  
			    .denyCacheImageMultipleSizesInMemory()  
			    .memoryCache(new UsingFreqLimitedMemoryCache(5 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现  
			    .memoryCacheSize(5 * 1024 * 1024)    
			    .discCacheSize(50 * 1024 * 1024)    
			    .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密  
			    .tasksProcessingOrder(QueueProcessingType.LIFO)  
			    .discCacheFileCount(500) //缓存的文件数量  
			    .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径  
			    .defaultDisplayImageOptions(DisplayImageOptions.createSimple())  
			    .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间  
			    .writeDebugLogs() // Remove for release app  
			    .build();//开始构建  
		ImageLoader.getInstance().init(config);
	}

}
