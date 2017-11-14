package com.example1.mycanteen;

import java.lang.reflect.Field;
import java.util.List;

import com.example1.mycanteen.R.drawable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.ImageView;  
import android.widget.TextView;  
  
public class HorizontalScrollViewAdapter  extends BaseAdapter
{  
  
    private Context mContext;  
    private LayoutInflater mInflater;  
    private List<Dish> mDatas;  
  
    public HorizontalScrollViewAdapter(Context context, List<Dish> mDatas)  
    {  
        this.mContext = context;  
        mInflater = LayoutInflater.from(context);  
        this.mDatas = mDatas;  
    }  
  
    public int getCount()  
    {  
        return mDatas.size();  
    }  
  
    public Object getItem(int position)  
    {  
        return mDatas.get(position);  
    }  
  
    public long getItemId(int position)  
    {  
        return position;  
    }  
  
    public View getView(int position, View convertView, ViewGroup parent)  
    {  
        ViewHolder viewHolder = null;  
        if (convertView == null)  
        {  
            viewHolder = new ViewHolder();  
            convertView = mInflater.inflate(  
                    R.layout.activity_index_gallery_item, parent, false);  
            viewHolder.mImg = (ImageView) convertView  
                    .findViewById(R.id.id_index_gallery_item_image);  
            viewHolder.mText = (TextView) convertView  
                    .findViewById(R.id.id_index_gallery_item_text);  
  
            convertView.setTag(viewHolder);  
        } else  
        {  
            viewHolder = (ViewHolder) convertView.getTag();  
        }  
        Class<drawable> drawable  =  R.drawable.class;
        Field field = null;
        try {
            field = drawable.getField(mDatas.get(position).getPicturename());
            int r_id = field.getInt(field.getName());
            viewHolder.mImg.setImageResource(r_id);
        } catch (Exception e) {
            Log.i("ERROR", "PICTURE NOT¡¡FOUND£¡");
        }
        //viewHolder.mImg.setImageResource(mDatas.get(position));  
        viewHolder.mText.setText(mDatas.get(position).getDishname());  
  
        return convertView;  
    }  
  
    private class ViewHolder  
    {  
        ImageView mImg;  
        TextView mText;  
    }  
  
}  