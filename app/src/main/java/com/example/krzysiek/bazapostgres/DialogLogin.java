package com.example.krzysiek.bazapostgres;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Surykatka on 2016-07-25.
 */
public class DialogLogin  extends Activity {
    private String haslo_rot13;
    //Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_login);
        //setTitle("Logowanie do SES");

        Button btn_login = (Button) findViewById(R.id.btn_login);


        }


    public void Login(View view) {

        final EditText editText_haslo = (EditText) findViewById(R.id.editText_haslo);
        final EditText editText_login = (EditText) findViewById(R.id.editText_login);

        String haslo = editText_haslo.getText().toString();
        Rot13 rot13 = new Rot13();
        String haslo_rot13 = rot13.rot13(haslo);

        String login = editText_login.getText().toString();

        Connection polaczenie = null;
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://10.0.0.9:5432/dhs_all";

            polaczenie = DriverManager.getConnection(url, login, haslo_rot13);


            Intent cel = new Intent(this, BazaPostgreSql.class);
            cel.putExtra("haslo_rot13", haslo_rot13);
            cel.putExtra("login", login);
            startActivity(cel);

        //} catch (ClassNotFoundException e) {
          //  e.printStackTrace();
            //Toast.makeText(this, "brak", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(this, "brak", Toast.LENGTH_LONG).show();
        }
    }
}
