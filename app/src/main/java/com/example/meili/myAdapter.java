package com.example.meili;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class myAdapter extends ArrayAdapter<String> {

    String[] name;
    String[] price;
    int[] shoe;
    Context mcontext;

    public myAdapter(Context context, String[] name, String[] price, int[] shoe) {
        super(context, R.layout.shopping_cart_list_view);
        this.name = name;
        this.price = price;
        this.shoe = shoe;
        this.mcontext = context;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.shopping_cart_list_view, parent, false);
            viewholder.mshoe = (ImageView) convertView.findViewById(R.id.imageView);
            viewholder.mname = (TextView) convertView.findViewById(R.id.textView);
            viewholder.mprice = (TextView) convertView.findViewById(R.id.txtItemDescription);
            convertView.setTag(viewholder);
        }else{
            viewholder = (ViewHolder)convertView.getTag();
        }
        viewholder.mshoe. setImageResource(shoe[position]);
        viewholder.mname.setText(name[position]);
        viewholder.mprice.setText(price[position]);

        return convertView;
    }

    static class ViewHolder{
        ImageView mshoe;
        TextView mname;
        TextView mprice;
    }
}

