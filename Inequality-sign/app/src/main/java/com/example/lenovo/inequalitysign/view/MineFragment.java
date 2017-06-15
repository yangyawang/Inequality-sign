package com.example.lenovo.inequalitysign.view;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;


import com.example.lenovo.inequalitysign.R;
import com.example.lenovo.inequalitysign.Utils.Utils;
import com.example.lenovo.inequalitysign.adapter.OrderAdapter;
import com.example.lenovo.inequalitysign.http.Httpss;
import com.example.lenovo.inequalitysign.ui.LoginActivity;
import com.example.lenovo.inequalitysign.ui.MainActivity;
import com.example.lenovo.inequalitysign.ui.MineOrderActivity;
import com.example.lenovo.inequalitysign.ui.MineProfileActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
    private String u = Utils.USER_URL+ "getmessage";
    private View view;
    private Button btn;
    private LinearLayout lineargeren;
    private LinearLayout linearjilu;
    private LinearLayout linearshezhi;
    private LinearLayout lineartuichu;



    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

//                case R.id.laoanniu:
//                    //点击头像跳转到登录界面
//                    Log.i("click","click");
//                    Intent a = new Intent();
//                    a.setClass(getActivity().getApplicationContext(), LoginActivity.class);
//                    startActivity(a);

                case R.id.imageButton:
                    //点击头像跳转到登录界面

                    Intent i = new Intent();
                    i.setClass(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    break;

                case R.id.tv_name:
                    //点击用户名跳转到登录界面

                    Intent j = new Intent();
                    j.setClass(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(j);
                    break;


                case R.id.mypageB2:
                    //如果 没登陆 调到登陆界面，登陆之后可以查看记录
                    //if(Utils.id==""){
                        Intent intent = new Intent();
                        intent.setClass(getActivity().getApplicationContext(), OrderAdapter.class);
                        startActivity(intent);
                    //}else{
                      //  Intent intent = new Intent();
                      //  intent.setClass(getActivity().getApplicationContext(), MineOrderActivity.class);
                     //   startActivity(intent);
                   // }

                    break;
//                case R.id.mypageB3:
                    //如果 没登陆 调到登陆界面，登陆之后可以设置个人资料
                    //if(Utils.id==""){
//                        Intent intent1 = new Intent();
//                        intent1.setClass(getActivity().getApplicationContext(), LoginActivity.class);
//                        startActivity(intent1);
                    //}else{
                     //   Intent intent1 = new Intent();
                     //   intent1.setClass(getActivity().getApplicationContext(), MineProfileActivity.class);
                     //   startActivity(intent1);
                    //}

//                    break;
//                case R.id.btn_exit:
//                    Utils.id = "";
//                    Utils.flag = 1;
//                    Intent intent2 = new Intent(getActivity().getApplicationContext(), MainActivity.class);
//                    startActivity(intent2);
            }
        }
    };
    private TextView tv_name;
    private Button btn_exit;
    private String name;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            btn.setVisibility(View.GONE);
            tv_name.setVisibility(View.VISIBLE);
            btn_exit.setVisibility(View.VISIBLE);
            tv_name.setText(name);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
        setOnClick();
        setContent();
    }

    private void setContent() {
        if (Utils.id == "") {

        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Httpss http = new Httpss();
                    NameValuePair pair = new BasicNameValuePair("id", Utils.id);
                    String s = http.setAndGet(u,pair);
                    try {
                        JSONObject object = new JSONObject(s);
                        name = object.getString("name");

                        Message msg =new Message();
                        mHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void setOnClick() {
        btn.setOnClickListener(mListener);
        linearjilu.setOnClickListener(mListener);
        linearshezhi.setOnClickListener(mListener);
        btn_exit.setOnClickListener(mListener);
    }

    private void findView() {
//        btn = (Button)view.findViewById(R.id.laoanniu);
        linearjilu = (LinearLayout)view.findViewById(R.id.mypageB2);//排号记录
//        linearshezhi = (LinearLayout)view.findViewById(R.id.mypageB3);//个人资料
        tv_name = (TextView)view.findViewById(R.id.tv_name);
//        btn_exit = (Button) view.findViewById(R.id.btn_exit);
    }

}
