package com.integration.aepssdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import payworld.com.aeps_lib.AepsHome;
import payworld.com.aeps_lib.Utility;

public class WebViewActivity extends AppCompatActivity {
    int REQ_CODE_AEPS = 1001;
    private WebView mWebview;
     Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebview = (WebView) findViewById(R.id.webView);
       // WebPayInterface.AepsDetails();

        activity = this;
        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {

            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

       // mWebview.loadUrl("https://www.payezy.net/user/public/login");
       // mWebview.loadUrl("https://www.ruralpays.in/user/public/login");
      // mWebview.loadUrl(" https://www.payseve.com/user/public/login");
        //  mWebview.loadUrl(" https://www.payindia.technology/user/public/login");
      // mWebview.loadUrl(" https://www.apnaseva.co.in/user/public/login");
      //  mWebview.loadUrl(" https://www.payezy.net/user/public/login");
        mWebview.loadUrl("https://www.meseva.in/user/public/login");
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
        mWebview.addJavascriptInterface(new WebAppInterface(this), "Android");


    }

    private String getHeaderJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("merchantId", "610328456");
            jsonObject.put("Timestamp", Utility.getCurrentTimeStamp());
            jsonObject.put("merchantKey", "qkoUAPlP2h4rLyHFt3ACGjgRwqzfiRdL+SbECd0jgCo=");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    private String getBodyJson(String agentId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("AgentId", agentId);
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
    private class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void showToast(String agentId) {

            Toast.makeText(mContext, "AgentId"+ agentId, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity, AepsHome.class);
            Utility utility = Utility.getInstance();
            intent.putExtra("header", utility.encryptHeader(getHeaderJson(), "1Vx1IGJMp/8Y7oMQtJcr0gj3gMsIEUy0SyDMkousZ0c="));
            intent.putExtra("body", utility.encryptBody(getBodyJson(agentId), "UcaotUvbA5gXB2aeak69eEfUT4FEn7o1f2Q0jE4gb6k="));
            intent.putExtra("receipt", true);
            startActivityForResult(intent, REQ_CODE_AEPS);
            finish();
        }
    }

}