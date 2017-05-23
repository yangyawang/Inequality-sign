package com.example.lenovo.inequalitysign.view;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.example.lenovo.inequalitysign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment  {
    private View view;
    private  TextureMapView mMapView = null;
    // 百度地图 UI 控制器
    private UiSettings mUiSettings = null;

    /* 地图实例 */
    private BaiduMap mBaiduMap = null;
    /* 定位的客户端 */
    private LocationClient mLocationClient = null;
    /* 定位的监听器 */
//    public MyLocationListener mMyLocationListener;
    /* 当前定位的模式 */
    private MyLocationConfiguration.LocationMode mCurrentMode
            = MyLocationConfiguration.LocationMode.NORMAL;
    /* 是否是第一次定位 */
    private volatile boolean isFristLocation = true;
    /* 最新一次的经纬度*/
    private double mCurrentLantitude;
    private double mCurrentLongitude;
    /* 周边雷达管理器 */
    private RadarSearchManager mRadarSearchManager = null;
    /* 定位的监听器 */
    public MyLocationListener mMyLocationListener = null;
    /* 周边雷达的监听器 */
//    MyRadarSearchListener mRadarSerchListener = null;
    private String mUserID = "蝙蝠侠";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SDKInitializer.initialize(getActivity().getApplicationContext());
        view = inflater.inflate(R.layout.fragment_nearby, container, false);
        initBaiduMap();
        initLocation();
        center2MyLoc();

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
    //-------------------------定位并移动到当前位置-------------------------------------------------
    private void initLocation() {
        //定位SDK初始化
        mLocationClient =new LocationClient(getActivity().getApplicationContext());
        //设置定位的相关配置
        LocationClientOption option=new LocationClientOption();
        option.setOpenGps(true);//打开GPS
        option.setCoorType("bd09ll");//设置坐标位置
        option.setScanSpan(1000);//自动定位间隔
        option.setIsNeedAddress(true);//是否需要地址
        option.setIsNeedLocationPoiList(true);
        //定位模式
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //根据配置信息对定位客户端进行设置
        mLocationClient.setLocOption(option);
        //注册监听
        mMyLocationListener =new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        //设置定位图标
        // 构建Marker图标
        int n;
        if(mUserID.equals("钢铁侠"))
            n = R.drawable.gangtiexia;
        else if(mUserID.equals("蝙蝠侠"))
            n = R.drawable.login;
        else if(mUserID.equals("闪电侠"))
            n = R.drawable.shandianxia;
        else
            n = R.drawable.sishen;
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(n);

        // 设置百度地图定位图层显示模式
        MyLocationConfiguration config = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL,
                true,
                bitmap);
        mBaiduMap.setMyLocationConfigeration(config);
//        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.login);
//        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode,true,mCurrentMarker);
//        mBaiduMap.setMyLocationConfigeration(config);
    }


    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView销毁后不再处理新接收的位置
            if (location ==null||mMapView == null){
                return;
            }

            //构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();

            StringBuffer buf = new StringBuffer(256);
            buf.append(location.getLatitude());
            buf.append("  ");
            buf.append(location.getLongitude());

            Log.i("test", location.toString() + " " + buf.toString());

            //设置BaiduMap的定位数据
            mBaiduMap.setMyLocationData(locData);
            //记录位置信息
            mCurrentLantitude=location.getLatitude();
            mCurrentLongitude= location.getLongitude();
            //第一次定位是，将地图位置移动到当前位置
            if(isFristLocation){
                isFristLocation =false;
                center2MyLoc();
            }
        }
    }
    //定义center2nyLoc函数来实现BaiduMap移动到定位位置。
    public void center2MyLoc(){
        LatLng ll = new LatLng(mCurrentLantitude,mCurrentLongitude);
        //设置当前定位位置为BaiduMap的中心点，并移动到定位位置
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);
        addMarker();
    }
    private void addMarker(){
        // 定义Maker坐标点
        LatLng point = new LatLng(mCurrentLantitude, mCurrentLongitude);//114.527956,38.001957
        // 构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.marker);
        // 构建MarkerOption，用于在地图上添加
        OverlayOptions option = new MarkerOptions()
                .position(point)// 设置marker的位置
                .title("石狮子")//title
                .draggable(true)//准许拖拽
                .icon(bitmap);// 必须设置marker图标
        // 在地图上添加Marker，并显示
        Marker marker = (Marker)mBaiduMap.addOverlay(option);
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



