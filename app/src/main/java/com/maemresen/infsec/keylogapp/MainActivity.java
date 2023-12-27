package com.maemresen.infsec.keylogapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.maemresen.infsec.keylogapp.util.Helper;

import java.io.DataOutputStream;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission_group.CAMERA;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = Helper.getLogTag(MainActivity.class);

    private Button button;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tvTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "aaa", Toast.LENGTH_LONG).show();
            }
        });

        MyTextView myTextView = findViewById(R.id.tvTest2);
        myTextView.setText("abcdefghijk123");

        findViewById(R.id.tvTest2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "bbbb", Toast.LENGTH_LONG).show();
            }
        });

        // 隐藏的view不会被KeyLogger监测到
        findViewById(R.id.tvHide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
//                Toast.makeText(MainActivity.this, "cccc", Toast.LENGTH_LONG).show();
                if(count == 2) {
                    findViewById(R.id.tvHide).setVisibility(View.GONE);
                }
            }
        });

        button = findViewById(R.id.btn_settings);
        button.setOnClickListener(v -> {
            Intent openSettings = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            openSettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(openSettings);
        });
    }
}
