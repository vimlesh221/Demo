package com.sourcey.materiallogindemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button button ;
    Button btn_login4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            int permissionCheck = ContextCompat.checkSelfPermission(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
            }

        }

        button = (Button)findViewById(R.id.btn_login3) ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();

            }
        });
        btn_login4 = (Button)findViewById(R.id.btn_login4) ;

        btn_login4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFileChooser() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    7);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        switch(requestCode) {

            case 7:

                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_SEND);
                    i.setType("*/*");
                    i.putExtra(Intent.EXTRA_STREAM, uri);
                    PackageManager pm = getPackageManager();
                    List<ResolveInfo> list = pm.queryIntentActivities(i, 0);
                    if (list.size() > 0) {
                        String packageName = null;
                        String className = null;
                        boolean found = false;

                        for (ResolveInfo info : list) {
                            packageName = info.activityInfo.packageName;
                            if (packageName.equals("com.android.bluetooth")) {
                                className = info.activityInfo.name;
                                found = true;
                                break;
                            }
                        }
                        //CHECK BLUETOOTH available or not------------------------------------------------
                        if (!found) {
                            Toast.makeText(this, "Bluetooth not been found", Toast.LENGTH_LONG).show();
                        } else {
                            i.setClassName(packageName, className);
                            startActivity(i);
                        }
                    }
                }

                break;
        }}
}
