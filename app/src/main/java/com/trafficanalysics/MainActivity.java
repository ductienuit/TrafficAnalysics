package com.trafficanalysics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ActionBar toolbar;
    FrameLayout contentContainer;
    ChatbotFragment chatbot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();

        toolbar.setTitle("Lịch thi đấu");
        loadFragment(new NewsFragment());
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void addControl() {
        toolbar = getSupportActionBar();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        if (bottomNavigationView != null) {
            bottomNavigationView.isWithText(true);
            //bottomNavigationView.activateTabletMode();
            bottomNavigationView.isColoredBackground(false);
            bottomNavigationView.setTextActiveSize(getResources().getDimension(R.dimen._9sdp));
            bottomNavigationView.setTextInactiveSize(getResources().getDimension(R.dimen._9sdp));
            bottomNavigationView.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.activeItem));
            //bottomNavigationView.setFont(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/"));

        }

        contentContainer = findViewById(R.id.contentContainer);

        addNavItem();

        addEvent();
    }
    private void addEvent() {
        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                TransitionSet set = new TransitionSet()
                        .addTransition(new Scale(0.7f))
                        .addTransition(new Fade())
                        .setInterpolator(new LinearOutSlowInInterpolator());

                switch (index) {
                    case 0:
                        TransitionManager.beginDelayedTransition(contentContainer, set);
                        toolbar.setTitle("Tíc Tíc");
                        loadFragment(new NewsFragment());
                        break;
                    case 1:
                        TransitionManager.beginDelayedTransition(contentContainer, set);
                        toolbar.setTitle("Chatbot");
                        chatbot = ChatbotFragment.getmInstance();
                        loadFragment(chatbot);
                        break;
                    case 2:
                        TransitionManager.beginDelayedTransition(contentContainer, set);
                        toolbar.setTitle("Bản đồ");
                        loadFragment(new MapFragment());
                        break;
                    case 3:
                        TransitionManager.beginDelayedTransition(contentContainer, set);
                        toolbar.setTitle("Voice");
                        loadFragment(new VoiceFragment());
                        break;
                }
            }
        });
    }

    private void addNavItem() {
        BottomNavigationItem newItem = new BottomNavigationItem
                ("Tíc Tíc", ContextCompat.getColor(this, R.color.colorAccent), R.drawable.ic_news);
        BottomNavigationItem channelItem = new BottomNavigationItem
                ("Chatbot", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_messages);
        BottomNavigationItem mapItem = new BottomNavigationItem
                ("Bản đồ", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_maps);
        BottomNavigationItem audioItem = new BottomNavigationItem
                ("Voice", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_record_voice_over);

        bottomNavigationView.addTab(newItem);
        bottomNavigationView.addTab(channelItem);
        bottomNavigationView.addTab(mapItem);
        bottomNavigationView.addTab(audioItem);
    }
}
