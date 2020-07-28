package com.integration.aepssdk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import payworld.com.aeps_lib.AepsHome;
import payworld.com.aeps_lib.Utility;

public class MainActivity extends AppCompatActivity {


    int REQ_CODE_AEPS = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnClick = (Button) findViewById(R.id.btn);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, AepsHome.class);
                Utility utility = Utility.getInstance();
                intent.putExtra("header", utility.encryptHeader(getHeaderJson(), "bRuD5WYw5wd0rdHR9yLlM6wt2vteuiniQBqE70nAuhU="));
                intent.putExtra("body", utility.encryptBody(getBodyJson(), "G7knrNLLUTk44VJ6lZBkvV1m6KW48Kr1hywKAtOt4BI="));
                intent.putExtra("receipt", true);
                startActivityForResult(intent, REQ_CODE_AEPS);
            }
        });
    }

    private String getHeaderJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("merchantId", "674629");
            jsonObject.put("Timestamp", Utility.getCurrentTimeStamp());
            jsonObject.put("merchantKey", "f7IenvVbyeT/Y+iZbnxnxV6wDGTQSnXTgFS/HDvY1X4=");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    private String getBodyJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("AgentId", "AR50087");
            jsonObject.put("merchantService", "AEPS");
            jsonObject.put("Version", "1.0");
            jsonObject.put("Mobile", "9523591370");
            jsonObject.put("Email", "ritesharyan80@gmail.com");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_AEPS) {
            if(resultCode == RESULT_OK) {

                Log.e("success`", "asd"+data.getDataString());
                //Success Case
            } else {
                Log.e("failed`", "failed");
                //Failed case
            }
        }
    }
}