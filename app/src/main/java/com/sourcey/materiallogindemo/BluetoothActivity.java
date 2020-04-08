package com.sourcey.materiallogindemo;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class BluetoothActivity extends AppCompatActivity {
    int count = 0;
    TextView text_message;
    Timer timer;
    ImageView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        text_message = (TextView)findViewById(R.id.text_message);
        logout = (ImageView) findViewById(R.id.ivLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefsUtils.clearPreference(BluetoothActivity.this,"isLogined");
                SharedPrefsUtils.clearPreference(BluetoothActivity.this,"deviceType");
                finish();
            }
        });

        try {
            File file=new File(Environment.getExternalStorageDirectory().getPath()+"/"+"bluetooth");
          //  Toast.makeText(this,"path:"+Environment.getExternalStorageDirectory().getPath()+"/"+"bluetooth",Toast.LENGTH_LONG).show();
            File[] list = file.listFiles();
            count = 0;
            for (File f: list){
                String name = f.getName();

                    count++;
                System.out.println("170 " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
          timer =new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            text_message = (TextView)findViewById(R.id.text_message);
                            File file=new File(Environment.getExternalStorageDirectory().getPath()+"/"+"Download");
                            //  Toast.makeText(this,"path:"+Environment.getExternalStorageDirectory().getPath()+"/"+"bluetooth",Toast.LENGTH_LONG).show();
                            File[] list = file.listFiles();
                            count = 0;
                            String name = null;
                            for (File f: list){
                                 name = f.getName();
                                count++;
                                System.out.println("170 " + count);
                            }
                            // change UI elements here
                            if(text_message!=null) {
                                text_message.setText(" "+name+" ");
                            }
                        }
                    });
                }
            }, 0, 5000);//put here time 1000 milliseconds=1 second

        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
        }
    }
}
