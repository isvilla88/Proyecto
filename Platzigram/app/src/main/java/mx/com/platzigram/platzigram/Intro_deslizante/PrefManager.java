package mx.com.platzigram.platzigram.Intro_deslizante;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Personal on 02/10/2016.
 * El control deslizante de bienvenida / introducción se debe mostrar
 * sólo una vez cuando se inicia la aplicación por primera vez.
 * Si el usuario inicia la aplicación en el segundo tiempo, debe ir
 * directamente a la pantalla principal. Para lograr esto, utilizamos
 * SharedPreferences para almacenar un valor booleano que indica el
 * primer inicio.
 */

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    //pref modo compartido
    int PRIVATE_MODE = 0;

    //preferencias compartidas nombre del archivo
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirsTimeLaunch";

    public PrefManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }


}
