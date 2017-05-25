package com.example.lenovo.inequalitysign.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.lenovo.inequalitysign.R;
import com.example.lenovo.inequalitysign.entity.Dining;
import com.example.lenovo.inequalitysign.view.HomeFragment;
import com.example.lenovo.inequalitysign.view.NearbyFragment;
import com.example.lenovo.inequalitysign.view.SquareFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static LinearLayout ll;

    private HomeFragment hf;
    private NearbyFragment nf;
    private SquareFragment sf;

    private BHFragment bh;
    private List<Dining> ls;

    private LinearLayout liner_home;
    private LinearLayout liner_find;
    private LinearLayout liner_mes;

    private ImageButton image_home;
    private ImageButton image_find;
    private ImageButton image_mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //获取界面控件
        getViews();
        //注册事件监听器
        setListener();
        //设置默认页面
        setDefaultPage();
//        setfriendlistener();
    }
    /**
     * 获取控件
     */

    private void setListener() {
        MyListener mylistener = new MyListener();
        liner_home.setOnClickListener(mylistener);
        liner_find.setOnClickListener(mylistener);
        liner_mes.setOnClickListener(mylistener);
    }
    //she值事件监听器

    private void ResetTabsImg() {
        image_home.setImageResource(R.drawable.home1);
        image_find.setImageResource(R.drawable.guangchang1);
        image_mes.setImageResource(R.drawable.fujin1);

    }
    private void SetTabsSelectedImg(int i) {
        ResetTabsImg();
        switch (i) {
            case 0:
                image_home.setImageResource(R.drawable.home2);
                break;
            case 1:
                image_find.setImageResource(R.drawable.guangchang2);
                break;
            case 2:
                image_mes.setImageResource(R.drawable.fujin2);
                break;
        }
    }
    //
    private void setDefaultPage(){
        android.app.FragmentManager fm = getFragmentManager();
        //获取fragmentTranSaction
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        //默认页面
        hf = new HomeFragment();
        transaction.replace(R.id.container,hf);
        ResetTabsImg();
        SetTabsSelectedImg(0);
        //执行更改
        transaction.commit();
    }

    //获取控件
    private void getViews(){
        ll =(LinearLayout)findViewById(R.id.ll);
        liner_home = (LinearLayout)findViewById(R.id.liner_home);
        liner_find = (LinearLayout)findViewById(R.id.liner_find);
        liner_mes = (LinearLayout)findViewById(R.id.liner_mes);

        image_home = (ImageButton)findViewById(R.id.image_home);
        image_find = (ImageButton)findViewById(R.id.image_find);
        image_mes = (ImageButton)findViewById(R.id.image_mes);

    }



//    private View.OnClickListener mListener =new View.OnClickListener() {
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
//            android.app.FragmentManager fm = getFragmentManager();
//            FragmentTransaction ft =fm.beginTransaction();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            switch (view.getId()) {
                case R.id.liner_home:
                    if (hf == null) {
                        hf = new HomeFragment();
                    }
                    ft.replace(R.id.container, hf);
//                    setDefaultPage();
                    SetTabsSelectedImg(0);
                    break;
                case R.id.liner_find://广场
                    if (sf == null) {
                        sf = new SquareFragment();

                    }
                    ft.replace(R.id.container, sf);
//                    setSquare();
                    SetTabsSelectedImg(1);
                    break;
                case R.id.liner_mes:
                    if (nf == null) {
                        nf = new NearbyFragment();
                    }
                    ft.replace(R.id.container, nf);
//                    setNear();
                    SetTabsSelectedImg(2);
                    break;
            }
            ft.commit();
            ll.invalidate();
        }
    };

    public void setChangeBH() {
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (bh == null) {
            bh = new BHFragment();
        }
        ft.replace(R.id.container, bh);
        ft.commit();
        ll.invalidate();
    }

    public void setChangeDn() {
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (sf == null) {
            sf = new SquareFragment();
        }
        ft.replace(R.id.container, sf);
        ft.commit();
        ll.invalidate();
    }

    /*扫二维码功能*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            final String scanResult = bundle.getString("result");
//            resultTextView.setText(scanResult);
            Log.e("--------石航琪",scanResult);
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, DiningInformationActivity.class);
            intent.putExtra("Context","HomeFragment");
            intent.putExtra("Id",scanResult);
            startActivity(intent);
        }
    }
}
