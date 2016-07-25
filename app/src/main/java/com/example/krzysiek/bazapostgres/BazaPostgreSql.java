package com.example.krzysiek.bazapostgres;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BazaPostgreSql extends Activity {

    private TextView id_aparatury;
    private TextView id_nazwa_aparatury;
    private TextView text_typ;
    private TextView text_sn;
    private TextView text_jednostka_org;
    private TextView text_uwagi;
    private TextView text_ost_przeglad;

    //  EditText edit_podaj_id;

    Button btn_wczytaj;

    String ET;

    EditText edit_podaj_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        DialogLogin dialogLogin = new DialogLogin(this);
        dialogLogin.show();

        id_aparatury = (TextView) findViewById(R.id.id_aparatury);
        id_nazwa_aparatury = (TextView) findViewById(R.id.text_id_nazwa_aparatury);
        text_typ = (TextView) findViewById(R.id.text_typ);
        text_sn = (TextView) findViewById(R.id.text_nr_fabryczny);
        text_jednostka_org = (TextView) findViewById(R.id.text_jednostka_organizacyjna);
        text_uwagi = (TextView) findViewById(R.id.text_uwagi);
        text_ost_przeglad = (TextView) findViewById(R.id.text_ostatni_przeglad);



        btn_wczytaj = (Button) findViewById(R.id.button_wczytaj);
        edit_podaj_id = (EditText) findViewById(R.id.edit_podaj_id);



        btn_wczytaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ET = edit_podaj_id.getText().toString();
                new MojeZapytanie().execute(ET);
            }
        });

    }

    public class MojeZapytanie extends AsyncTask<String, Void, Void> {
        private String id = "";
        private String nazwa = "";
        private String typ = "";
        private String sn = "";
        private String jedn_org;
        private String uwagi;
        private String ost_przeglad;

        @Override
        protected Void doInBackground(String... params) {

            Connection polaczenie = null;
            try {
                Class.forName("org.postgresql.Driver");

                String url =  "jdbc:postgresql://10.0.0.9:5432/dhs_all";

                String numer = params[0];



                polaczenie = DriverManager.getConnection(url, "read", "read");
                Statement st = polaczenie.createStatement();

                String zapytanie1 = "select * from ses_wykaz_aparatury where id_aparatury="+numer+";";
                final ResultSet rs1 = st.executeQuery(zapytanie1);
                rs1.next();

                id = rs1.getString(1);
                typ = rs1.getString(5);
                sn = rs1.getString(6);
                uwagi = rs1.getString(10);

                String id_nazwa = rs1.getString(2);
                String id_jednostki_organizacyjnej = rs1.getString(11);


                String zapytanie2 = "select * from ses_aparatura where id_nazwa_aparatury="+id_nazwa+";";
                final ResultSet rs2 = st.executeQuery(zapytanie2);
                rs2.next();

                nazwa = rs2.getString(2);


                String zapytanie3 = "select * from all_jednostki_org where id_jedn_org="+id_jednostki_organizacyjnej+";";
                final ResultSet rs3 = st.executeQuery(zapytanie3);
                rs3.next();

                jedn_org = rs3.getString(2);


                String zapytanie4 = "select * from ses_odbyte_kontrole where id_aparatury="+numer+";";
                final ResultSet rs4 = st.executeQuery(zapytanie4);
                rs4.next();

                while (rs4.next()) {

                    ost_przeglad = rs4.getString(3);

                }


                polaczenie.close();


            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
                System.err.println("Error: Cant connect!");
                polaczenie = null;
            }

            return null;
        }

        protected void onPostExecute(Void result) {

            id_aparatury.setText(id);
            id_nazwa_aparatury.setText(nazwa);
            text_typ.setText(typ);
            text_sn.setText(sn);
            text_jednostka_org.setText(jedn_org);
            text_uwagi.setText(uwagi);
            text_ost_przeglad.setText(ost_przeglad);



            super.onPostExecute(result);
        }


    }


}
