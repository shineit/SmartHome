/**   
* @Title: EzvizApplication.java 
* @Package cn.fuego.smart.home.ui 
* @Description: TODO
* @author Aether
* @date 2015-1-7 上午10:33:46 
* @version V1.0   
*/ 
package cn.fuego.smart.home;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.EzvizConfigConst;
import cn.jpush.android.api.JPushInterface;

import com.videogo.openapi.EzvizAPI;

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
        
        EzvizAPI.init(this, EzvizConfigConst.APP_KEY, EzvizConfigConst.SECRET_KEY); 
        EzvizAPI.getInstance().setServerUrl(EzvizConfigConst.API_URL, EzvizConfigConst.WEB_URL);     
		PackageManager packageManager = getPackageManager();
		
		JPushInterface.init(this);  
		try
		{
			PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
			AppCache.getInstance().setVersionCode(packInfo.versionCode);
			AppCache.getInstance().setVersionNname(packInfo.versionName);


		} catch (NameNotFoundException e)
		{
			log.info("can not get the package information");
		}
    }

}
