package com.example.meili;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class list_adapter extends ArrayAdapter<String> {

    String[] itemName;
    int[] itemImage;
    Context mContext;

    public list_adapter(Context context, String[] itemName,int[] itemImage) {
        super(context, R.layout.listview_item);
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return itemName.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if(convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_item, parent, false);
            mViewHolder.mItemImage = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.mItemName = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }
            mViewHolder.mItemImage.setImageResource(itemImage[position]);
            mViewHolder.mItemName.setText(itemName[position]);

        return convertView;
    }

    static class ViewHolder{
        ImageView mItemImage;
        TextView mItemName;

    }
}
