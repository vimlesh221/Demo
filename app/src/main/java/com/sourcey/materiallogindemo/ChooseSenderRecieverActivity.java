package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseSenderRecieverActivity extends AppCompatActivity {
Button btnSender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sender_reciever);
        btnSender = (Button)findViewById(R.id.btn_sender) ;

        btnSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseSenderRecieverActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
