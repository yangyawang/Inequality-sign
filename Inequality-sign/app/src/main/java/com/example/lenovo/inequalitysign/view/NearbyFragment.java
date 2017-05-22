package com.example.lenovo.inequalitysign.view;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.example.lenovo.inequalitysign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment  {
    private View view;
    private  TextureMapView mMapView = null;
    private BaiduMap mBaiduMap=null;
    // 百度地图 UI 控制器
    private UiSettings mUiSettings = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SDKInitializer.initialize(getActivity().getApplicationContext());
        view = inflater.inflate(R.layout.fragment_nearby, container, false);
        initBaiduMap();
        return view;
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        SDKInitializer.initialize(getActivity().getApplicationContext());
//        initBaiduMap();
//
//    }



    /**
     * 初始化百度地图
     */
    private void initBaiduMap() {
        // 获取地图视图
        mMapView = (TextureMapView) view.findViewById(R.id.bmapView);
        // 获取地图控制器
        mBaiduMap = mMapView.getMap();
        // 设置比例尺为 500M
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
        // 获取地图 UI 控制器
        mUiSettings = mBaiduMap.getUiSettings();
        // 隐藏指南针
        mUiSettings.setCompassEnabled(true);
        // 设置标注覆盖物的监听
        setMarkerLister();
    }
    //设置覆盖监听
    private void setMarkerLister() {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getActivity(),
                        marker.getTitle(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    public void onResume(){
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
        mMapView.onPause();
    }

}



