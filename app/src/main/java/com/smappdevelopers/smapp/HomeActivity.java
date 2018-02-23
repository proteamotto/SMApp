package com.smappdevelopers.smapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.smappdevelopers.smapp.Utils.BottomNavigationViewHelper;

import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG, "onCreate: starting");

        setupBottomNavigationView();

        String url = "http://www.smapp.com.ar/";
        Intent intent = getIntent();


        if (intent.getExtras() != null){
            url = intent.getDataString();
        }

        webview = (WebView) this.findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUserAgentString("com.smappdevelopers.smapp:2");
        if (isConnected()) {
            webview.loadUrl(url);
            webview.setWebViewClient(new MyWebViewClient());
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Sin conexión a internet", Toast.LENGTH_LONG).show();
        }
    }

    private void setupBottomNavigationView(){

        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(HomeActivity.this, bottomNavigationViewEx);

    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(intent);
                return true;
            }
            else if (url.startsWith("mailto:")) {
                url = url.substring(7);
                String body = "";
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setType("application/octet-stream");
                mail.putExtra(Intent.EXTRA_EMAIL, new String[] { url });
                mail.putExtra(Intent.EXTRA_SUBJECT, "");
                mail.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(mail);
                return true;
            }
            else if (url.startsWith("share:")) {
                url = url.substring(6);
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                i.putExtra(Intent.EXTRA_TEXT, url);
                startActivity(Intent.createChooser(i, "Share URL"));
                return true;
            }
            else if (url.startsWith("geo:")) {
                url = url.substring(4);
                String map = "http://maps.google.com/maps?z=8&q=" + url;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                startActivity(intent);
                return true;
            }
            else if (url.startsWith("route:")) {
                url = url.substring(6);
                String map = "google.navigation:q="+ url;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                startActivity(intent);
                return true;
            }
            else if (Uri.parse(url).getHost().equals("www.smapp.com.ar")) {
                if (isConnected()) {
                    return false;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sin conexión a internet", Toast.LENGTH_LONG).show();
                    return true;
                }
            }
            else {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webview.canGoBack()) {
                        webview.goBack();
                    }
                    else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
