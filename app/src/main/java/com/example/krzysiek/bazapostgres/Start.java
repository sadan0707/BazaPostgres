package com.example.krzysiek.bazapostgres;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Surykatka on 2016-08-15.
 */
public class Start extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_glowny);

    }

    public void MySQL(View view) {
        //Intent intent = new Intent(this, MySQL_str_glowna.class);
        Intent intent = new Intent(this, BazaMySQL.class);
        startActivity(intent);

    }

    public void MAS(View view) {
        Intent intent = new Intent(this,Mas_str_glowna.class);
        startActivity(intent);
    }
}
