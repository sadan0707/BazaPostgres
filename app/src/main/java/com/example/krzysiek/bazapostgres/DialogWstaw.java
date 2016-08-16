package com.example.krzysiek.bazapostgres;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by krzysiek on 2016-08-16.
 */
public class DialogWstaw extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wstaw);

    }

    public void PrzyciskWstaw(View view) {

        final EditText editT_wstaw_nazwa = (EditText) findViewById(R.id.editt_wstaw_nazwa);
        final EditText editT_wstaw_model = (EditText) findViewById(R.id.editT_wstaw_model);
        final EditText editT_wstaw_sn = (EditText) findViewById(R.id.editT_wstaw_sn);
        final EditText editT_wstaw_prod = (EditText) findViewById(R.id.editT_wstaw_prod);

        String WSTAW_NAZWA = editT_wstaw_nazwa.getText().toString();
        String WSATW_MODEL = editT_wstaw_model.getText().toString();
        String WSTAW_SN = editT_wstaw_sn.getText().toString();
        String WSTAW_PROD = editT_wstaw_prod.getText().toString();

        new ZapiszAsync().execute(WSTAW_NAZWA, WSATW_MODEL, WSTAW_SN, WSTAW_PROD);

    }

    public class ZapiszAsync extends AsyncTask <String, Void, Void> {
        int ID,id;
        String nazwa, model, sn, prod;

        @Override
        protected Void doInBackground(String... params) {


            nazwa = params[0];
            model = params[1];
            sn = params[2];
            prod = params[3];

            String URL = "jdbc:mysql://10.0.2.15:3306/sprzety";
            String USER = "krzysiek";
            String HASLO = "Ks1234";

            String ZAPYTANIE = "select * from wykaz_sprzety";

            Connection polaczenie;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                polaczenie = DriverManager.getConnection(URL, USER,HASLO);

                Statement statement = polaczenie.createStatement();
                ResultSet zapytanie = statement.executeQuery(ZAPYTANIE);

                while (zapytanie.next()) {
                    ID = zapytanie.getInt(1);

                }
                int id = ID+1;

                statement.executeUpdate("insert into wykaz_sprzety (id_aparatury, nazwa, model, sn, producent) values ("+id+", '"+nazwa+"', '"+model+"', '"+sn+"', '"+prod+"')");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
              }

        public void onPostExecute(Void result) {

        }
    }
}
