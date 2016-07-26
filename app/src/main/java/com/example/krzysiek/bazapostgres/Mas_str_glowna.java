package com.example.krzysiek.bazapostgres;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Mas_str_glowna extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_str_glowna);

        Intent intent = new Intent(this, DialogLogin.class);
        startActivity(intent);





    }

}
