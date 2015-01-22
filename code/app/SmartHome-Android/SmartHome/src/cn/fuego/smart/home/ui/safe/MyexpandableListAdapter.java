/**   
* @Title: MyexpandableListAdapter.java 
* @Package cn.fuego.smart.home.ui.safe 
* @Description: TODO
* @author Aether
* @date 2015-1-21 下午6:58:53 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.safe;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.SensorStatusEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.webservice.up.model.BatchOperateSensorReq;
import cn.fuego.smart.home.webservice.up.model.BatchOperateSensorRsp;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

/** 
 * @ClassName: MyexpandableListAdapter 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-21 下午6:58:53 
 *  
 */
public class MyexpandableListAdapter extends BaseExpandableListAdapter
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private ArrayList<Group> groupList = new ArrayList<Group>();
	private Context context;
    private LayoutInflater inflater;
    private List<List<HomeSensorJson>> childList = new ArrayList<List<HomeSensorJson>>();

    private ProgressDialog chkPDialog;

        public MyexpandableListAdapter(Context context,ArrayList<Group> groupList,List<List<HomeSensorJson>> childList) 
        {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.groupList=groupList;
            this.childList= childList;
        }

		public void clearView()
		{
			
		}
        // 返回父列表个数
        @Override
        public int getGroupCount() 
        {
            return groupList.size();
        }

        // 返回子列表个数
        @Override
        public int getChildrenCount(int groupPosition) {
            return childList.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {

            return groupList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childList.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {

            return true;
        }

        @Override
		public boolean isEmpty()
		{
			// 一个空视图是否应该被显示
			return true;
		}


		@Override
        public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) 
        {
            GroupHolder groupHolder = null;
            
            if (convertView == null) 
            {
                groupHolder = new GroupHolder();
                convertView = inflater.inflate(R.layout.safe_item_parent, null);
                groupHolder.textView = (TextView) convertView
                        .findViewById(R.id.safe_group_tag);
                groupHolder.imageView = (ImageView) convertView.findViewById(R.id.safe_group_icon);
                //groupHolder.imageView.setImageResource(R.drawable.down);
                convertView.setTag(groupHolder);
            } 
            else 
            {
                groupHolder = (GroupHolder) convertView.getTag();
            }

            groupHolder.textView.setText(((Group) getGroup(groupPosition)).getTitle());
            if (isExpanded)// ture is Expanded or false is not isExpanded
            {
            	groupHolder.imageView.setImageResource(R.drawable.up);
            	((Group) getGroup(groupPosition)).setIsExpand(true);
            }
            else
            {
            	groupHolder.imageView.setImageResource(R.drawable.down);
            	((Group) getGroup(groupPosition)).setIsExpand(false);
            }
               
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                boolean isLastChild, View convertView, ViewGroup parent) 
        {
        	final HomeSensorJson sensor =   childList.get(groupPosition).get(childPosition);
        	ChildHolder childHolder = null;
            if (convertView == null) 
            {
                childHolder = new ChildHolder();
                convertView = inflater.inflate(R.layout.safe_item_child, null);

                childHolder.imageView = (ImageView)convertView.findViewById(R.id.safe_item_icon);
                
                childHolder.textName =(TextView) convertView.findViewById(R.id.safe_item_label);
                childHolder.chkBtn=(CheckBox) convertView.findViewById(R.id.safe_item_chk);

                convertView.setTag(childHolder);
            } 
            else 
            {
                childHolder = (ChildHolder) convertView.getTag();
            }

            childHolder.textName.setText(((HomeSensorJson) getChild(groupPosition,childPosition)).getSensorTypeName());

    	    if(sensor.getStatus()==SensorStatusEnum.ENABLE.getIntValue())
    	    {
    	    	childHolder.chkBtn.setChecked(true);
    	    }
    	    if(sensor.getStatus()==SensorStatusEnum.DISABLE.getIntValue())
    	    {
    	    	childHolder.chkBtn.setChecked(false);
    	    }
    	    final CheckBox chkBtn=(CheckBox) convertView.findViewById(R.id.safe_item_chk);
            //checkBox不要添加OnCheckedChangeListener监听，该监听每次父节点被展开或者收缩时都会被调用，导致状态被还原，必须要用使用onclickListener监听替换。
    	    chkBtn.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					if(chkBtn.isChecked())
					enableSensor(sensor);
					else
					disableSensor(sensor);	
					
					
				}
			});

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) 
        {
            return true;
        }

	private void enableSensor(final HomeSensorJson sensor)
	{
		chkPDialog= ProgressDialog.show(context, "请稍等", "数据提交中……");
		BatchOperateSensorReq req = new BatchOperateSensorReq();
		req.setToken(MemoryCache.getToken());	
		List<String> snesorList =  new ArrayList<String>();
		snesorList.add(String.valueOf(sensor.getId()));		
		req.setSensorList(snesorList);
		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
					BatchOperateSensorRsp rsp = (BatchOperateSensorRsp) message.getMessage().obj;
					if(message.isSuccess())
					{
						chkPDialog.dismiss();
						Toast.makeText(context, sensor.getSensorTypeName()+"开启成功", Toast.LENGTH_SHORT).show();
					}
					else
					{
						
						chkPDialog.dismiss();
					}
			}
		}).enable(req);
		
	}
	private void disableSensor(final HomeSensorJson sensor)
	{
		chkPDialog= ProgressDialog.show(context, "请稍等", "数据提交中……");
		BatchOperateSensorReq req = new BatchOperateSensorReq();
		req.setToken(MemoryCache.getToken());
		
		List<String> snesorList =  new ArrayList<String>();
		snesorList.add(String.valueOf(sensor.getId()));		
		req.setSensorList(snesorList);
		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
					BatchOperateSensorRsp rsp = (BatchOperateSensorRsp) message.getMessage().obj;
					if(message.isSuccess())
					{
	
						chkPDialog.dismiss();
						Toast.makeText(context, sensor.getSensorTypeName()+"禁止成功", Toast.LENGTH_SHORT).show();
					}
					else
					{
						
						chkPDialog.dismiss();
					}
			}
		}).disable(req);
		
	}
    class GroupHolder 
    {
        TextView textView;
        ImageView imageView;
    }

    class ChildHolder 
    {
        TextView textName;
        ImageView imageView;
        CheckBox chkBtn;
    }
    
}	

