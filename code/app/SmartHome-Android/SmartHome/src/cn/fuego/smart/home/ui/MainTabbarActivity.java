package cn.fuego.smart.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.camera.CameraFragment;
import cn.fuego.smart.home.ui.control.ControlFragment;
import cn.fuego.smart.home.ui.safe.SafeFragment;

/** 
* @ClassName: MainTabbarActivity 
* @Description: TODO
* @author Aether from Seven
* @date 2014-11-17 上午10:50:15 
*  
*/
public class MainTabbarActivity extends FragmentActivity implements OnClickListener
{

    //定义FragmentTabHost对象  
    private FragmentTabHost mTabHost;  
          
    //定义一个布局  
    private LayoutInflater layoutInflater;  
              
    //定义数组来存放Fragment界面  
    private Class fragmentArray[] = {null,SafeFragment.class,ControlFragment.class,CameraFragment.class};  
          
    //定义数组来存放按钮图片  
    private int mImageViewArray[] = {R.drawable.tabbar_home_icon,R.drawable.tabbar_safe_icon,R.drawable.tabbar_control_icon,  
                                     R.drawable.tabbar_camera_icon};  
          
    //Tab选项卡的文字  
    private int mTextviewArray[] = {R.string.tabbar_home, R.string.tabbar_safe, R.string.tabbar_control, R.string.tabbar_camera};  
      
    //首页点击初始fragment
    private int num=0;
    
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main_tabbar);  
        ExitApplication.getInstance().addActivity(this);

        Intent i= this.getIntent();
        num=i.getIntExtra("num", 0);
        initView();  
        
    }  
           
    /** 
     * 初始化组件 
     */
    private void initView(){  
        //实例化布局对象  
        layoutInflater = LayoutInflater.from(this);  
                      
        //实例化TabHost对象，得到TabHost  
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);  
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);   
        mTabHost.setBackgroundColor(getResources().getColor(R.color.tabbarback)); 
        //mTabHost.setBackgroundColor(R.drawable.tabbar_background);
        
        //得到fragment的个数  
        int count = fragmentArray.length;     
                      
        for(int i = 0; i < count; i++){    
            //为每一个Tab按钮设置图标、文字和内容  
        	String str = getString(mTextviewArray[i]);
            TabSpec tabSpec = mTabHost.newTabSpec(str).setIndicator(getTabItemView(i));  
            //将Tab按钮添加进Tab选项卡中  
            mTabHost.addTab(tabSpec, fragmentArray[i], null);  
            //设置Tab按钮的背景 
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tabbar_background); 
        } 
        //选中tab
        mTabHost.setCurrentTab(num);
       // mTabHost.setOnTabChangedListener(this);
        mTabHost.getTabWidget().getChildAt(0).setOnClickListener(this);
    }  
                      
    /** 
     * 给Tab按钮设置图标和文字 
     */
    private View getTabItemView(int index){  
        View view = layoutInflater.inflate(R.layout.tabbar_item_view, null);  
        view.setPadding(0, 0, 0, 10);  
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);  
        imageView.setImageResource(mImageViewArray[index]);  
              
        TextView textView = (TextView) view.findViewById(R.id.textview);          
        textView.setText(mTextviewArray[index]);  
          
        return view;  
    }

	@Override
	public void onClick(View v)
	{
		Intent i = new Intent();
		i.setClass(getApplicationContext(), HomeActivity.class);
		// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
		// Intent.FLAG_ACTIVITY_CLEAR_TOP );
		this.startActivity(i);

	}

}
