package com.trafficanalysics;


import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.IOException;

import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoiceFragment extends Fragment {
    RippleBackground rippleRadio;
    RippleBackground rippleRecord;

    SimpleExoPlayer player;
    DataSource.Factory dataSourceFactory;
    MediaSource mediaSource;

    String filePath;

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
                    //rippleRecord.stopRippleAnimation();
                }
                else {
                    rippleRecord.startRippleAnimation();
                    record();
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

    void record()
    {
        filePath = Environment.getExternalStorageDirectory() + "/recorded_audio.wav";
        int color = getResources().getColor(R.color.colorPrimaryDark);
        int requestCode = 0;
        AndroidAudioRecorder.with(this)
                // Required
                .setFilePath(filePath)
                .setColor(color)
                .setRequestCode(requestCode)

                // Optional
                .setSource(AudioSource.MIC)
                .setChannel(AudioChannel.STEREO)
                .setSampleRate(AudioSampleRate.HZ_48000)
                .setAutoStart(true)
                .setKeepDisplayOn(true)

                // Start recording
                .recordFromFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                thanksAudio();

            } else if (resultCode == RESULT_CANCELED) {
                // Oops! User has canceled the recording
                rippleRecord.stopRippleAnimation();
            }
        }
    }
    void thanksAudio(){
        AssetFileDescriptor afd = null;
        try {
            afd = getContext().getAssets().openFd("thanks.mp3");
            MediaPlayer playerThanks = new MediaPlayer();
            playerThanks.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            playerThanks.prepare();
            playerThanks.start();
            playerThanks.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    rippleRecord.stopRippleAnimation();
                }
            });
            playerThanks.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    rippleRecord.stopRippleAnimation();
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
