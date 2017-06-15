package com.example.lenovo.inequalitysign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.inequalitysign.R;
import com.example.lenovo.inequalitysign.entity.Friend;
import com.example.lenovo.inequalitysign.ui.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李泽 on 2017/6/13.
 */
public class Myadapter extends BaseAdapter{
    private Context context;
    private List<Friend> data = new ArrayList<>();

    public Myadapter(List<Friend> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (LoginActivity.isLogin){
            return data.size();
        }else {
            return data.size()-1;
        }
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item,null);
        }
        ImageView iv = (ImageView) view.findViewById(R.id.tu);
        TextView tv = (TextView) view.findViewById(R.id.paihaojilu);

        iv.setImageResource(data.get(i).getImg());
        tv.setText(data.get(i).getName().toString());

        return view;
    }
}
