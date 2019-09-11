package com.example.meili;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meili.R;
import com.example.meili.list_adapter;

public class editOrders_list_adapter extends ArrayAdapter<String> {

    String[] itemName;
    int[] itemImage;
    Context mContext;
    ImageButton update,delete;

    public editOrders_list_adapter(Context context, String[] itemName,int[] itemImage) {
        super(context, R.layout.edit_orders_list_view);
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
        list_adapter.ViewHolder mViewHolder = new list_adapter.ViewHolder();
        if(convertView == null) {
            //ViewHolder viewHolder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.edit_orders_list_view, parent, false);
            mViewHolder.mItemImage = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.mItemName = (TextView) convertView.findViewById(R.id.textView);
            //was trying to set button clicks for update and delete items///////////////////////////////////////////////////////////////////////////////
            /*viewHolder.update = (ImageButton) convertView.findViewById(R.id.imageButton9);
            viewHolder.delete = (ImageButton) convertView.findViewById(R.id.imageButton10);

            mViewHolder.update.setOnClickListener(new View.onClickListener(){


            });
            mViewHolder.delete.setOnClickListener(new View.onClickListener(){
                //edit_order_activity.DeleteOrderClick();

            });*/
            convertView.setTag(mViewHolder);


        }else{
            mViewHolder = (list_adapter.ViewHolder) convertView.getTag();
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
