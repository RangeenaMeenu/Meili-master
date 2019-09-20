package com.example.meili;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meili.Database.DBHelper;
import com.example.meili.R;
import com.example.meili.list_adapter;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class editOrders_list_adapter extends ArrayAdapter<String> {

    //String[] orderId;
    //int[] itemImage;

    String[] orderId;
    String[] shipId;
    Context context;

    DBHelper db;


    //ArrayList orderId,shipId;
    Context mContext;
    ImageButton update,delete;

    public editOrders_list_adapter(Context context, ArrayList orderId, ArrayList shipId) {
        super(context, R.layout.edit_orders_list_view);
        this.orderId = GetStringArray(orderId);
        this.shipId = GetStringArray(shipId);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return orderId.length;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if(convertView == null) {
            //ViewHolder viewHolder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.edit_orders_list_view, parent, false);
            //mViewHolder.mItemImage = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.orderId = (TextView) convertView.findViewById(R.id.textView);
            mViewHolder.shipId = (TextView) convertView.findViewById(R.id.textView1);
            mViewHolder.price = (TextView) convertView.findViewById(R.id.textView2);

            mViewHolder.update = (ImageButton) convertView.findViewById(R.id.imageButton9);
            mViewHolder.delete = (ImageButton) convertView.findViewById(R.id.imageButton10);


            final ViewHolder finalMViewHolder = mViewHolder;
            //put button clicks //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            mViewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(mContext)
                            .setTitle("Delete")
                            .setMessage("Are you sure you want to delete ?")
                            .setNegativeButton("No",null)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    db = new DBHelper(mContext);
                                    //call delete method for order
                                    boolean status = db.deleteOrder(Integer.parseInt(orderId[position]));
                                    if(status == true){
                                        Toast.makeText(mContext,"Successfully deleted",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(mContext,edit_order_activity.class);
                                        mContext.startActivity(intent);
                                    }else{
                                        Toast.makeText(mContext,"Error occured",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }).create().show();
                }
            });

            mViewHolder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //navigate to update delivery page
                   Intent intent = new Intent(mContext,edit_delivery_page.class);
                   intent.putExtra("SHIP_ID", shipId[position]);
                   Log.d("Rangeena",""+shipId[position]);
                   mContext.startActivity(intent);
                }
            });
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (editOrders_list_adapter.ViewHolder) convertView.getTag();
        }
        //mViewHolder.mItemImage.setImageResource(itemImage[position]);
        mViewHolder.shipId.setText("Shipping number : "+shipId[position]);
        mViewHolder.orderId.setText("Order number :"+orderId[position]);

        return convertView;
    }
//    public void editorderStart(){
//        Intent intent = new Intent(editOrders_list_adapter.this,edit_order_activity.class);
//        startActivity(intent);
//    }

    static class ViewHolder{
        public ImageButton update;
        public ImageButton delete;
        TextView shipId;
        TextView orderId;
        TextView price;
    }

    public static String[] GetStringArray(ArrayList<String> arr)
    {
        String str[] = new String[arr.size()];

        for (int j = 0; j < arr.size(); j++) {
            str[j] = arr.get(j);
        }

        return str;
    }

    public void show_update_delivery_popup(Activity activity){

        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_delivery_popup);
        dialog.setTitle("Update");

        EditText _firstName = dialog.findViewById(R.id.editText10);
        EditText _lastname = dialog.findViewById(R.id.editText11);
        EditText _contact = dialog.findViewById(R.id.editText15);
        EditText _address = dialog.findViewById(R.id.editText14);
        EditText _email = dialog.findViewById(R.id.editText12);
        EditText  _postalCode = dialog.findViewById(R.id.editText13);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

    }
}
