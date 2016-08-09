package com.example.krzysiek.bazapostgres;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

/**
 * Created by krzysiek on 2016-08-02.
 */
public class DialogBrakPolaczenia extends Dialog {

    public DialogBrakPolaczenia(Context context) {
        super(context);
        setContentView(R.layout.dialog_brak_polaczenia);

        Button btn_OK = (Button) findViewById(R.id.button_OK);
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }
}
