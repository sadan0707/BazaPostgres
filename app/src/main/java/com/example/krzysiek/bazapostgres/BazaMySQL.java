package com.example.krzysiek.bazapostgres;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Surykatka on 2016-08-15.
 */
public class BazaMySQL extends Activity {
    private TextView text_nazwa, text_id, text_model, text_sn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql);

        text_nazwa = (TextView) findViewById(R.id.text_nazwa);
        text_model = (TextView) findViewById(R.id.text_model);
        text_sn = (TextView) findViewById(R.id.text_sn);

        final EditText editText_wczytaj = (EditText) findViewById(R.id.editText_wczytaj);
        Button button_wczytaj = (Button) findViewById(R.id.button_wczytaj);

        button_wczytaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = editText_wczytaj.getText().toString();
                new ZapytanieMySQL().execute(ID);
            }
        });




    }

    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mysql, menu);

        return  super.onCreateOptionsMenu(menu);
    }

    public void DialogWstaw(View view) {

        Intent intent = new Intent(this, DialogWstaw.class);
        startActivity(intent);
    }


    public class ZapytanieMySQL extends AsyncTask<String,Void,Void> {

        int id;
        String  nazwa, model, sn;
        String URL ="jdbc:mysql://10.0.2.15:3306/sprzety";
        String USER = "krzysiek";
        String HASLO = "Ks1234";


        @Override
        protected Void doInBackground(String... params) {

            Connection polaczenie;

            try {
                Class.forName("com.mysql.jdbc.Driver");

                polaczenie = (Connection) DriverManager.getConnection(URL, USER, HASLO);

                String ID = params[0];

                Statement statement = (Statement) polaczenie.createStatement();

                ResultSet rezultat = (ResultSet) statement.executeQuery("select * from wykaz_sprzety where id_aparatury="+ID+";");

                rezultat.next();

                    id = rezultat.getInt(1);
                    nazwa = rezultat.getString(2);
                    model = rezultat.getString(3);
                    sn = rezultat.getString(4);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void result) {

            //text_id.setText(""+id);
            text_nazwa.setText(nazwa);
            text_model.setText(model);
            text_sn.setText(sn);

            super.onPostExecute(result);
        }
    }
}
