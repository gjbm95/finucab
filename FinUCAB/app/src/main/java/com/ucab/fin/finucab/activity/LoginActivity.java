package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ucab.fin.finucab.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Colocando el icono en la parte superior izquierda:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logoh);
        actionBar.setTitle("");
        //------------------------------------------------------------------------------------------
//        BIND VIEES
        signInButton = (Button) findViewById(R.id.signInButton);

//        SET LISTENERS
        signInButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.signInButton:
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                break;
        }

    }
}
