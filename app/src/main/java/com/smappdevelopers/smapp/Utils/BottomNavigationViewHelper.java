package com.smappdevelopers.smapp.Utils;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.smappdevelopers.smapp.HomeActivity;
import com.smappdevelopers.smapp.NewFrontActivity;
import com.smappdevelopers.smapp.R;

/**
 * Created by Catt on 24/11/2017.
 */

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationActivity");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
        bottomNavigationViewEx.setIconSize(25, 25);
        bottomNavigationViewEx.setTextVisibility(true);
        bottomNavigationViewEx.setSmallTextSize(14);
        bottomNavigationViewEx.setLargeTextSize(14);


    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);

                switch (item.getItemId()) {
                    case R.id.ic_house:
                        //Intent homeIntent = new Intent(context, HomeActivity.class);
                        //context.startActivity(homeIntent);


                        Toast.makeText(context, "House", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.ic_fav:
                        Toast.makeText(context, "Fav", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.ic_alert:
                        Toast.makeText(context, "Alert", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.ic_settings:
                        Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });
    }
}
