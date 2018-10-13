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
        webview.loadUrl("https://www.google.com/maps/@10.7756514,106.6673812,16z");
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
