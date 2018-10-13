package com.trafficanalysics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebChromeClient;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    WebView webview;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_map, container, false);
        // Inflate the layout for this fragment
        webview=view.findViewById(R.id.webview);
        //WebSettings websietting=webview.getSettings();
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setHorizontalFadingEdgeEnabled(false);
        webview.setWebChromeClient(new WebChromeClient());
        //webview.setWebViewClient(new WebViewClient());
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.loadUrl("https://www.google.com/maps/d/u/0/viewer?mid=1D5mU8Xb00hAH67JBT6y-KYEXyJbXifPR&ll=10.777824951677136%2C106.68820279988381&z=14");
        return view;
    }
//    @Override
//    public void onBackPressed() {
//        if(webview.canGoBack()) {
//            webview.goBack();
//        } else {
//            super.onBackPressed();
//        }
//    }
}
