package com.sourcey.materiallogindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends AppCompatActivity {
    @BindView(R.id.input_oldpassword)
    EditText input_oldpassword;
    @BindView(R.id.input_newpassword)
    EditText input_newpassword;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    return;
                }
                String newpassword = input_newpassword.getText().toString();
                SharedPrefsUtils.setStringPreference(ChangePasswordActivity.this,"password",newpassword);
                finish();
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String oldpassword = input_oldpassword.getText().toString();
        String newpassword = input_newpassword.getText().toString();

        if (oldpassword.isEmpty() || oldpassword.length() < 4 || oldpassword.length() > 10) {
            input_oldpassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            input_oldpassword.setError(null);
        }
        if (!oldpassword.equals(SharedPrefsUtils.getStringPreference(ChangePasswordActivity.this,"password"))) {
            input_oldpassword.setError("Your old password is wrong");
            valid = false;
        } else {
            input_oldpassword.setError(null);
        }
        if (newpassword.isEmpty() || newpassword.length() < 4 || newpassword.length() > 10) {
            input_newpassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            input_newpassword.setError(null);
        }

        return valid;
    }
}
