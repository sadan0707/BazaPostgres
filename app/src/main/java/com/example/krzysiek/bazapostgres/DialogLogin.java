package com.example.krzysiek.bazapostgres;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

        new Weryfikacja().execute(login,haslo_rot13);

        /*

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
        }*/



    }

    protected class Weryfikacja extends AsyncTask<String, Void, Void> {

        private String login1;
        private String haslo1;

        Connection polaczenie = null;

        @Override
        protected Void doInBackground(String... params) {

           // Connection polaczenie = null;
            try {
                Class.forName("org.postgresql.Driver");


                String url = "jdbc:postgresql://10.0.0.9:5432/dhs_all";



                login1 = params[0];
                haslo1 = params[1];

                polaczenie = DriverManager.getConnection(url, login1, haslo1);


                    Intent cel = new Intent(getApplicationContext(), BazaPostgreSql.class);
                    cel.putExtra("haslo_rot13", haslo1);
                    cel.putExtra("login", login1);
                    startActivity(cel);

                    //Toast.makeText(DialogLogin.this, "BRAK POLACZENIA", Toast.LENGTH_LONG).show();


            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            } catch (SQLException e) {


                e.printStackTrace();
               //Toast.makeText(getApplication(), "BRAK POLACZENIA", Toast.LENGTH_LONG).show();
                //new DialogBrakPolaczenia(DialogLogin.this).show();
                Void result;


        }


            return null;
        }

        protected void onPostExecute(Void result){
            super.onPostExecute(result);

                Toast.makeText(getApplication(), "BRAK POLACZENIA", Toast.LENGTH_LONG).show();



        }
    }

}
