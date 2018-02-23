package com.smappdevelopers.smapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.smappdevelopers.smapp.Utils.BottomNavigationViewHelper;

public class NewFrontActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    //GridView gridView;

    String[] values = {
            "DATOS ÚTILES",
            "TIENDAS Y PROFESIONALES",
            "PRODUCTOS Y SERVICIOS",
            "TURISMO",
            "COMUNICADOS INSTITUCIONALES",
            "GASTRONOMÍA",
            "HOY SE SALE",
            "¿QUÉ HACER?",
            "¡GANA PREMIOS!"
    };

    int[] images = {
            R.drawable.menu_datos_utiles,
            R.drawable.menu_tiendas_y_profesionales,
            R.drawable.menu_productos_y_servicios,
            R.drawable.menu_turismo,
            R.drawable.menu_comunicados_institucionales,
            R.drawable.menu_gastronomia,
            R.drawable.menu_hoy_se_sale,
            R.drawable.menu_que_hacer,
            R.drawable.menu_gana_premios
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_front_layout);

        setupBottomNavigationView();

        //gridView = (GridView) findViewById(R.id.menu_gridview);
        //GridAdapter gridAdapter = new GridAdapter(this, values, images);

        //gridView.setAdapter(gridAdapter);
    }

    private void setupBottomNavigationView(){

        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(NewFrontActivity.this, bottomNavigationViewEx);

    }
}
