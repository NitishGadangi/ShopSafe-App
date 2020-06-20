package com.nitish.shopsafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.tapadoo.alerter.Alerter;

import mehdi.sakout.fancybuttons.FancyButton;

public class Home extends AppCompatActivity {

    public void joinQueue(View view){
        FancyButton btn = (FancyButton) view;
        btn.setBackgroundColor(Color.parseColor("#FF7272"));
        btn.setText("LEAVE QUEUE");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaveQueue(view);
            }
        });
//        Toast.makeText(this, "Successfully Joined the Queue", Toast.LENGTH_SHORT).show();
        Alerter.create(this)
       .setTitle("You are in Queue")
                .setText("You will be notified when your spot appears")
                .setBackgroundColorInt(Color.parseColor("#21A87D"))
                .setDuration(10000)
                .show();
    }

    public void leaveQueue(View view){
        FancyButton btn = (FancyButton) view;
        btn.setBackgroundColor(Color.parseColor("#21A87D"));
        btn.setText("JOIN QUEUE");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinQueue(view);
            }
        });

//        Toast.makeText(this, "Left from the Queue", Toast.LENGTH_SHORT).show();
        Alerter.create(this)
                .setTitle("You left the Queue")
                .setText("Get back to the line when required")
                .setBackgroundColorInt(Color.parseColor("#FF7272"))
                .setDuration(1000)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startBottomNavigation();
    }

    public void startBottomNavigation(){
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_notifications_black_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_home_black_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_account_circle_black_24dp, R.color.colorPrimary);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

// Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#002651"));

// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

//// Enable the translation of the FloatingActionButton
//        bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

// Display color under navigation bar (API 21+)
// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
//        bottomNavigation.setTranslucentNavigationEnabled(true);

// Manage titles
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

// Set current item programmatically
        bottomNavigation.setCurrentItem(1);


//// Enable / disable item & set disable color
//        bottomNavigation.enableItemAtPosition(2);
//        bottomNavigation.disableItemAtPosition(2);
//        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));

// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if(position==0){
                    startActivity(new Intent(getApplicationContext(),Notifications.class));
                }
                if(position==2){
                    startActivity(new Intent(getApplicationContext(),Profile.class));
                }
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
    }


}
