package com.example.krzysiek.bazapostgres;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Surykatka on 2016-07-25.
 */
public class DialogLogin  extends Dialog {
    private String haslo_rot13;

    public DialogLogin(Context context) {
        super(context);
        setContentView(R.layout.activity_dialog_login);
        setTitle("Logowanie do SES");

        Button btn_login = (Button) findViewById(R.id.btn_login);
        final EditText editText_haslo = (EditText) findViewById(R.id.editText_haslo);
        final EditText editText_login = (EditText) findViewById(R.id.editText_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String haslo = editText_haslo.getText().toString();
                Rot13 rot13 = new Rot13();
                String haslo_rot13 = rot13.rot13(haslo);
                editText_login.setText(haslo_rot13);



                //Intent cel = new Intent(getContext(), BazaPostgreSql.class);
                //cel.putExtras("haslo_rot13", haslo_rot13);





            }
        });



    }
}
