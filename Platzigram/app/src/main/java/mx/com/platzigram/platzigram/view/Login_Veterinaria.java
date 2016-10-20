package mx.com.platzigram.platzigram.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mx.com.platzigram.platzigram.R;

/**
 * Created by Personal on 15/10/2016.
 */

public class Login_Veterinaria extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_v);
    }

    public void goCreate (View view){

        Intent intent = new Intent(this, RegistrodeCuentaActivity.class);
        startActivity(intent);

    }
}
