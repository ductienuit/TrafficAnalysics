package com.trafficanalysics;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.util.Map;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapsInitializer;

import com.trafficanalysics.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                //googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                //Ngã tư Hàng Xanh
                LatLng Hangxanh = new LatLng(10.801484, 106.711453);
                googleMap.addMarker(new MarkerOptions().position(Hangxanh).title("Hàng Xanh").snippet("Quận Bình Thạnh, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(Hangxanh)
                        .radius(400.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(Hangxanh).zoom(14).build();
//Ngã sáu Công trường Dân Chủ
                LatLng CongtruongDanchu = new LatLng(10.7778189 ,106.6815522);
                googleMap.addMarker(new MarkerOptions().position(CongtruongDanchu).title("Công trường Dân Chủ").snippet("Quận 3, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(CongtruongDanchu)
                        .radius(500.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(CongtruongDanchu).zoom(14).build();
//Đường Thành Thái & Ba Tháng Hai
                LatLng ThThaiBTHai = new LatLng(10.7680441, 106.6672899);
                googleMap.addMarker(new MarkerOptions().position(ThThaiBTHai).title("Thành Thái & Ba Tháng Hai").snippet("Quận 10, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(ThThaiBTHai)
                        .radius(410.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(ThThaiBTHai).zoom(14).build();
//Giao lộ Cộng Hòa - Hoàng Hoa Thám
                LatLng CHoaHHTham = new LatLng(10.8004697, 106.6607957);
                googleMap.addMarker(new MarkerOptions().position(CHoaHHTham).title("Cộng Hòa - Hoàng Hoa Thám").snippet("Quận Tân Bình, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(CHoaHHTham)
                        .radius(450.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(CHoaHHTham).zoom(14).build();
//Vòng xoay Phạm Văn Đồng - Nguyễn Thái Sơn - Bạch Đằng - Hoàng Minh Giám - Nguyễn Kiệm
                LatLng VXoayPVD = new LatLng(10.8142761, 106.6781924);
                googleMap.addMarker(new MarkerOptions().position(VXoayPVD).title("Vòng xoay Phạm Văn Đồng - Nguyễn Thái Sơn - Bạch Đằng - Hoàng Minh Giám - Nguyễn Kiệm").snippet("Quận Gò Vấp, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(VXoayPVD)
                        .radius(470.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(VXoayPVD).zoom(14).build();
//Trường Chinh (đoạn từ đường Âu Cơ đến đường Tân Kỳ Tân Quý)
                LatLng TrCAUTanKy = new LatLng(10.8034869, 106.6352065);
                googleMap.addMarker(new MarkerOptions().position(TrCAUTanKy).title("Trường Chinh - Âu Cơ - Tân Kỳ Tân Quý").snippet("Quận Tân Bình, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(TrCAUTanKy)
                        .radius(380.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(TrCAUTanKy).zoom(14).build();
//Nguyễn Thị Định (từ vòng xoay Mỹ Thủy đến cảng Cát Lái)
                LatLng VXMyThuy = new LatLng(10.7696584, 106.7728108);
                googleMap.addMarker(new MarkerOptions().position(VXMyThuy).title("Vòng xoay Mỹ Thủy").snippet("Quận 2, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(VXMyThuy)
                        .radius(420.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(VXMyThuy).zoom(14).build();
//Ngã tư Thủ Đức
                LatLng VXThuDuc = new LatLng(10.84906, 106.7739138);
                googleMap.addMarker(new MarkerOptions().position(VXThuDuc).title("Ngã tư Thủ Đức").snippet("Quận Thủ Đức, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(VXThuDuc)
                        .radius(310.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(VXThuDuc).zoom(14).build();
//Giao lộ Đinh Bộ Lĩnh - Bạch Đằng
                LatLng GLDiBoLi = new LatLng(10.8133729, 106.7094257);
                googleMap.addMarker(new MarkerOptions().position(GLDiBoLi).title("Giao lộ Đinh Bộ Lĩnh - Bạch Đằng").snippet("Quận Bình Thạnh, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(GLDiBoLi)
                        .radius(370.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(GLDiBoLi).zoom(14).build();
//Xô Viết Nghệ Tĩnh (từ Bạch Đằng đến ngã năm Đài Liệt sỹ)
                LatLng XVNTi = new LatLng(10.8101741, 106.7122637);
                googleMap.addMarker(new MarkerOptions().position(XVNTi).title("Xô Viết Nghệ Tĩnh").snippet("Quận Bình Thạnh, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(XVNTi)
                        .radius(460.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(XVNTi).zoom(14).build();
//Nút giao An Phú
                LatLng NGAnPh = new LatLng(10.6921106, 106.5958739);
                googleMap.addMarker(new MarkerOptions().position(NGAnPh).title("Nút giao An Phú").snippet("Quận Bình Chánh, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(NGAnPh)
                        .radius(330.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(NGAnPh).zoom(14).build();
//Giao lộ Nguyễn Văn Cừ - Trần Hưng Đạo
                LatLng GLNgVanCu= new LatLng(10.7533343, 106.6866075);
                googleMap.addMarker(new MarkerOptions().position(GLNgVanCu).title("Giao lộ Nguyễn Văn Cừ - Trần Hưng Đạo").snippet("Quận 1, Hồ Chí Minh, Việt Nam"));
                googleMap.addCircle(new CircleOptions()
                        .center(GLNgVanCu)
                        .radius(430.0)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70,50,30,50)));
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(GLNgVanC).zoom(14).build();


                //CameraPosition cameraPosition = new CameraPosition.Builder().target(Hangxanh).zoom(14).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

//                LatLng Hutech =new LatLng(10.802037, 106.714577);
//                googleMap.addMarker(new MarkerOptions().position(Hutech).title("Hutech").snippet("Đại học Hutech"));



            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

//    WebView webview;
//
//    public MapFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view =inflater.inflate(R.layout.fragment_map, container, false);
//        // Inflate the layout for this fragment
//        webview=view.findViewById(R.id.webview);
//        //WebSettings websietting=webview.getSettings();
//        webview.getSettings().setLoadsImagesAutomatically(true);
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.setHorizontalFadingEdgeEnabled(false);
//        webview.setWebChromeClient(new WebChromeClient());
//        //webview.setWebViewClient(new WebViewClient());
//        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webview.loadUrl("https://www.google.com/maps/@10.8020971,106.7134238,18z");
//        return view;
//    }
////    @Override
////    public void onBackPressed() {
////        if(webview.canGoBack()) {
////            webview.goBack();
////        } else {
////            super.onBackPressed();
////        }
////    }
}
