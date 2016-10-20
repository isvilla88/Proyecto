package mx.com.platzigram.platzigram.Validación;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import mx.com.platzigram.platzigram.R;

/**
 * Created by Personal on 16/10/2016.
 */

public class ValidaciónDatos extends AppCompatActivity {

    private TextInputLayout DexCorreo;
    private TextInputLayout DexName;
    private TextInputLayout DexUsuario;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_veterinaria);

        //Referencias Editex para limpiar los errores
        EditText campoEmail = (EditText) findViewById(R.id.emailV);
        EditText campoNombre = (EditText) findViewById(R.id.name);
        EditText campoUsuario = (EditText) findViewById(R.id.usuario);


        campoNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DexName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        campoEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                esCorreoValido(String.valueOf(s));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //Referencias de Dex
        DexCorreo = (TextInputLayout) findViewById(R.id.DexCorreo);
        DexName = (TextInputLayout) findViewById(R.id.DexName);
        DexUsuario = (TextInputLayout) findViewById(R.id.DexUsuario);

        //Referencia Botón
        Button botonAceptar = (Button) findViewById(R.id.joinUs_V);
        botonAceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                validarDatos();
            }
        });


    }
    private boolean validarNombre(String nombre){
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        return patron.matcher(nombre).matches() || nombre.length() > 30;
    }

    private boolean validarTelefono(String telefono){
        return Patterns.PHONE.matcher(telefono).matches();
    }

    private boolean validarCorreo(String correo){
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches();
    }

    private boolean esCorreoValido(String correo){
        if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            DexCorreo.setError("Correo electrónico invalido");
            return false;
        }else {
            DexCorreo.setError(null);
        }
        return true;
    }

    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            DexName.setError("Nombre invalido");
            return false;
        } else {
            DexName.setError(null);
        }
        return true;

    }

    private boolean esUsuarioValido(String usuario){
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if(!patron.matcher(usuario).matches() || usuario.length() > 20 ){
            DexUsuario.setError("Nombre de usuario invalido");
            return false;
        }else {
            DexUsuario.setError(null);
        }
        return true;
    }





    private void validarDatos() {
        String correo = DexCorreo.getEditText().getText().toString();
        String nombre = DexName.getEditText().getText().toString();
        String usuario = DexUsuario.getEditText().getText().toString();


        boolean a = esCorreoValido(correo);
        boolean b = esNombreValido(nombre);
        boolean c = esUsuarioValido(usuario);


        if(a && b && c ){
            //Ok, se pasa a la siguiente acción
            Toast.makeText(this,"Se guardo su registro", Toast.LENGTH_LONG).show();
        }
    }




}
