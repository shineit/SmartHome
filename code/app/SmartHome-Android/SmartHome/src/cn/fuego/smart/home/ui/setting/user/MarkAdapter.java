/**   
* @Title: MarkAdapter.java 
* @Package cn.fuego.smart.home.ui.setting.user 
* @Description: TODO
* @author Aether
* @date 2014-12-29 上午11:09:44 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.setting.user;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.webservice.up.model.SetUserMarkReq;
import cn.fuego.smart.home.webservice.up.model.base.UserMarkJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

/** 
 * @ClassName: MarkAdapter 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-29 上午11:09:44 
 *  
 */
public class MarkAdapter extends BaseAdapter
{
	private FuegoLog log = FuegoLog.getLog(MarkAdapter.class);
	private Context mContext;
	private itemViewHolder holder;
	private List<String> mList;
    private int selPosition;
    private String curMark=null;
	public MarkAdapter(Context context,List<String> list,String mark)
	{
		this.mContext=context;
		this.mList=list;
		this.curMark=mark;
	}
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		if (convertView != null)
		{
			holder = (itemViewHolder) convertView.getTag();
		} else
		{

			LayoutInflater mInflater = LayoutInflater.from(mContext);
			convertView = mInflater.inflate(R.layout.list_mark_item, null);
			holder = new itemViewHolder();
			holder.markTitle = (TextView) convertView
					.findViewById(R.id.list_mark_item_title);
			holder.delBtn = (Button) convertView
					.findViewById(R.id.list_mark_item_deletebtn);
			convertView.setTag(holder);
		}
        //log.info("mList:"+mList.toString());
		final String markItem = mList.get(position);
		if (markItem != null)
		{
			holder.markTitle.setText(markItem);

			holder.delBtn.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					delDialog(markItem);
					selPosition = position;

				}
			});

		}
         if(markItem.equals("未分组"))
         {
        	 convertView.setVisibility(View.GONE);
         }
		return convertView;
	}
	//移除item
	public void removeItem(int position)
	{

		mList.remove(position);
		this.notifyDataSetChanged();
	}

	public void delDialog(final String target)
	{
        if(curMark.equals(target))
        {
        	Toast.makeText(mContext, target+"为当前标签，不可删除！", Toast.LENGTH_LONG).show();
        	
        }
        else
        {
    		AlertDialog.Builder builder = new Builder(mContext);
    		builder.setMessage("确定要删除该标签:"+target+"?");
    		builder.setTitle("提示");
    		builder.setPositiveButton("确认",
    				new android.content.DialogInterface.OnClickListener()
    				{
    					@Override
    					public void onClick(DialogInterface dialog, int which)
    					{
    						dialog.dismiss();
    						delMark(target);

    					}


    				});
    		builder.setNegativeButton("取消",
    				new android.content.DialogInterface.OnClickListener()
    				{
    					@Override
    					public void onClick(DialogInterface dialog, int which)
    					{
    						dialog.dismiss();
    					}
    				});
    		builder.create().show();
        }
  	
    }

	private void delMark(String targetMark)
	{
		SetUserMarkReq req= new SetUserMarkReq();
		req.setToken(MemoryCache.getToken());
		UserMarkJson userMark = new UserMarkJson();
		userMark.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		userMark.setMark(targetMark);
		req.setUserMark(userMark);
		WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{

					removeItem(selPosition);
		            
				}

				else
				{
					super.sendMessage(message);
				}
			}
		}).deleteUserMark(req);
		
	}
	private class itemViewHolder
	{
	        TextView markTitle;
	        Button delBtn;
	}

}
