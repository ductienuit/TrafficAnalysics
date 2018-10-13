package com.trafficanalysics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ActionBar toolbar;
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
            // bottomNavigationView.activateTabletMode();
            bottomNavigationView.isColoredBackground(false);
            bottomNavigationView.setTextActiveSize(getResources().getDimension(R.dimen._9sdp));
            bottomNavigationView.setTextInactiveSize(getResources().getDimension(R.dimen._9sdp));
            //bottomNavigationView.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.activeItem));
            //bottomNavigationView.setFont(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/"));

        }

        addNavItem();

        addEvent();
    }
    private void addEvent() {
        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                switch (index) {
                    case 0:
                        toolbar.setTitle("News");
                        loadFragment(new ChatbotFragment());
                        break;
                    case 1:
                        toolbar.setTitle("Traffic Message");
                        loadFragment(new ChatbotFragment());
                        break;
                    case 2:
                        toolbar.setTitle("Map Traffic");
                        loadFragment(new MapFragment());
                        break;
                }
            }
        });
    }

    private void addNavItem() {
        BottomNavigationItem dateItem = new BottomNavigationItem
                ("News", ContextCompat.getColor(this, R.color.colorAccent), R.drawable.ic_match);
        BottomNavigationItem channelItem = new BottomNavigationItem
                ("Traffic Message", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_live_tv);
        BottomNavigationItem newItem = new BottomNavigationItem
                ("Map Traffic", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_news);

        bottomNavigationView.addTab(dateItem);
        bottomNavigationView.addTab(channelItem);
        bottomNavigationView.addTab(newItem);
    }
}
