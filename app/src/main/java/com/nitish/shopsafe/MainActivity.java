package com.nitish.shopsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    private Auth0 auth0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        findViewById(R.id.btn_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });

        findViewById(R.id.btn_shopkeeper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),StoreManager.class));
            }
        });

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });

        findViewById(R.id.forgot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
            }
        });

        auth0 = new Auth0(this);
        auth0.setOIDCConformant(true);

        auth0.setLoggingEnabled(true);



        findViewById(R.id.social_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login() {
        WebAuthProvider.login(auth0)
                .withScheme("demo")
                .withAudience("https://dev-lh2wrih3.us.auth0.com/userinfo")
                .start(this, new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull final Dialog dialog) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(final AuthenticationException exception) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onSuccess(@NonNull final Credentials credentials) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, Home.class);
//                                intent.putExtra(EXTRA_ACCESS_TOKEN, credentials.getAccessToken());
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
    }

}
