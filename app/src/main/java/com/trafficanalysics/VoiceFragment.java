package com.trafficanalysics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoiceFragment extends Fragment {
    RippleBackground rippleRadio;
    RippleBackground rippleRecord;

    public VoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_voice, container, false);

        rippleRadio=(RippleBackground)view.findViewById(R.id.radiocontent);
        ImageView imageRadio=(ImageView)view.findViewById(R.id.radioImage);
        imageRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = rippleRadio.isRippleAnimationRunning();
                if(check){
                    rippleRadio.stopRippleAnimation();
                }
                else rippleRadio.startRippleAnimation();
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
                }
                else rippleRecord.startRippleAnimation();
            }
        });

        return view;
    }
}
