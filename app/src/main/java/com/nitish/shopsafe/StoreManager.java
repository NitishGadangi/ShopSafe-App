package com.nitish.shopsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

public class StoreManager extends AppCompatActivity {

    @BindView(R.id.store_open)
    TextView store_open;

    public void closeStore(View view){
        FancyButton btn = (FancyButton) view;
        btn.setBackgroundColor(Color.parseColor("#FF7272"));
        btn.setText("OPEN STORE");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStore(view);
            }
        });
//        Toast.makeText(this, "Your Store is now Closed", Toast.LENGTH_SHORT).show();
        Alerter.create(this)
                .setTitle("Your Store is now closed")
                .setText("It wont be visible to users untill you get back and click open store")
                .setBackgroundColorInt(Color.parseColor("#FF7272"))
                .setDuration(10000)
                .show();
        store_open.setText("Store is Closed");
        store_open.setTextColor(Color.parseColor("#FF7272"));
    }

    public void openStore(View view){
        FancyButton btn = (FancyButton) view;
        btn.setBackgroundColor(Color.parseColor("#002651"));
        btn.setText("CLOSE STORE");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeStore(view);
            }
        });
//        Toast.makeText(this, "Store Opened", Toast.LENGTH_SHORT).show();
        Alerter.create(this)
                .setTitle("Your Store is now Open")
                .setBackgroundColorInt(Color.parseColor("#21A87D"))
                .setDuration(600)
                .show();
        store_open.setText("Store Open | 0 in queue");
        store_open.setTextColor(Color.parseColor("#21A87D"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager);

        ButterKnife.bind(this);

        findViewById(R.id.card3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PeopleInQueue.class));
            }
        });
    }
}
