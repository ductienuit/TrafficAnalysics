package com.trafficanalysics;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.skyfishjy.library.RippleBackground;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoiceFragment extends Fragment {
    RippleBackground rippleRadio;
    RippleBackground rippleRecord;

    SimpleExoPlayer player;
    DataSource.Factory dataSourceFactory;
    MediaSource mediaSource;

    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    private  ExtractorsFactory extractorsFactory= new DefaultExtractorsFactory();
    private   TrackSelection.Factory trackSelectionFactory= new AdaptiveTrackSelection.Factory(bandwidthMeter);
    private   TrackSelector trackSelector = new DefaultTrackSelector(trackSelectionFactory);
    private   DefaultBandwidthMeter defaultBandwidthMeter= new DefaultBandwidthMeter();

    public VoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_voice, container, false);


        addExo();

        rippleRadio=(RippleBackground)view.findViewById(R.id.radiocontent);
        ImageView imageRadio=(ImageView)view.findViewById(R.id.radioImage);
        imageRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = rippleRadio.isRippleAnimationRunning();
                if(check){
                    rippleRadio.stopRippleAnimation();
                    player.setPlayWhenReady(false);
                }
                else {
                    rippleRadio.startRippleAnimation();
                    player.setPlayWhenReady(true);
                }
            }
        });

        rippleRecord=(RippleBackground)view.findViewById(R.id.recordContent);
        ImageView imageRecord=(ImageView)view.findViewById(R.id.recordImage);
        imageRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = rippleRecord.isRippleAnimationRunning();
                if(check){
                    rippleRecord.stopRippleAnimation();
                    player.setPlayWhenReady(false);
                }
                else {
                    rippleRecord.startRippleAnimation();
                    player.setPlayWhenReady(true);
                }

            }
        });
        return view;
    }

    private void addExo() {
        dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "mediaPlayerSample"), defaultBandwidthMeter);

        mediaSource = new HlsMediaSource(Uri.parse("https://5a6872aace0ce.streamlock.net/vovgt+hcm/vovgt+hcm.stream_aac/chunklist_w348808222.m3u8"),
                dataSourceFactory, null, null);
        player = ExoPlayerFactory.newSimpleInstance( getContext(), trackSelector);
        player.prepare(mediaSource);
    }
}
