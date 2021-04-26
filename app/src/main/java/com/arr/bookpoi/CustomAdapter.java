package com.arr.bookpoi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Poi> mPois = new ArrayList<>();
    ArrayList<String> array1 = new ArrayList<>();
    ArrayList<String> array2 = new ArrayList<>();
    ArrayList<String> array3 = new ArrayList<>();
    LayoutInflater mInflater;

    public CustomAdapter(Context context, ArrayList<Poi> mBooks) {
        mContext = context;
        this.mPois = mBooks;
        for(Poi poi:this.mPois){
            array1.add(poi.getName());
            array2.add(Double.toString(poi.getPrice()));
            array3.add(poi.getImgName());
        }
        mInflater = (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return mPois.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(R.layout.listview,null,true);
        TextView text1 = view.findViewById(R.id.txtPoi);
        TextView text2 = view.findViewById(R.id.txtPrice);
        ImageView img = view.findViewById(R.id.imgPoi);
        text1.setText(array1.get(i));
        text2.setText(array2.get(i));
        String imgName = array3.get(i);
        int res = view.getResources().getIdentifier(imgName,"drawable",mInflater.getContext().getPackageName());
        img.setImageResource(res);
        return view;
    }
}
