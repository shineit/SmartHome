/**   
* @Title: EzvizApplication.java 
* @Package cn.fuego.smart.home.ui 
* @Description: TODO
* @author Aether
* @date 2015-1-7 上午10:33:46 
* @version V1.0   
*/ 
package cn.fuego.smart.home;

import cn.fuego.smart.home.constant.EzvizConfigConst;

import com.videogo.openapi.EzvizAPI;

import android.app.Application;

/** 
 * @ClassName: EzvizApplication 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-7 上午10:33:46 
 *  
 */
public class EzvizApplication extends Application
{
           
    @Override
    public void onCreate() {
        super.onCreate();
        
        EzvizAPI.init(this, EzvizConfigConst.APP_KEY, EzvizConfigConst.SECRET_KEY); 
        EzvizAPI.getInstance().setServerUrl(EzvizConfigConst.API_URL, EzvizConfigConst.WEB_URL);     
    }

}
