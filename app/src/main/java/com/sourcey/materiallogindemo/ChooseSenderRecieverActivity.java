package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ChooseSenderRecieverActivity extends AppCompatActivity {
Button btnSender;
    Button btnReciver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sender_reciever);
        btnSender = (Button)findViewById(R.id.btn_sender) ;
        btnSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefsUtils.setStringPreference(ChooseSenderRecieverActivity.this, "deviceType","sender");
                Intent intent = new Intent(ChooseSenderRecieverActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        btnReciver = (Button)findViewById(R.id.btn_reciever) ;
        btnReciver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefsUtils.setStringPreference(ChooseSenderRecieverActivity.this, "deviceType","reciever");
                Intent intent = new Intent(ChooseSenderRecieverActivity.this, BluetoothActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
