package mx.com.platzigram.platzigram.Intro_deslizante;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import mx.com.platzigram.platzigram.R;
import mx.com.platzigram.platzigram.view.LoginActivity;

/**
 * Created by Personal on 02/10/2016.
 */

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private PrefManager prefManager;
    public ViewPager instantiateItem;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);





        //Comprobación para el primer inicio - antes de llamar setContentView ()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()){
            launchHomeScreen();
            finish();
        }

        //Haciendo barra de notificaciones transparente
        if (Build.VERSION.SDK_INT >= 24) {
            getWindow().getDecorView().setSystemUiVisibility((View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN));
        }
            setContentView(R.layout.activity_welcome);

            viewPager = (ViewPager) findViewById(R.id.view_pager);
            dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);

            //  diseños de toda las  diapositivas
            //añadir algunos diseños más si quieres

            layouts = new int[]{
                    R.layout.welcome_slide1,
                    R.layout.welcome_slide2,
                    R.layout.welcome_slide3,
                    R.layout.welcome_slide4,
                    R.layout.welcome_slide5};

            // añadiendo puntos inferiores
            addBottomDots(0);

            // hacer transparente la barra de notificación
            changeStatusBarColor();

            myViewPagerAdapter = new MyViewPagerAdapter();
            viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);



    }




    private void addBottomDots(int currentPage){


        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);

    }

    private int getItem(int i){
        return  viewPager.getCurrentItem()+ i;

    }

    private void launchHomeScreen(){
        prefManager.setFirstTimeLaunch(false);
        //startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    //ViewPager cambio oyente
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageSelected(int position){
            addBottomDots(position);

            //cambiando el texto "Next" / "GOT IT"


        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2){
        }

        @Override
        public void onPageScrollStateChanged(int arg0){

        }
    };

    /**
     * Haciendo barra de notificaciones transparente
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    /**
     * Vista pager adapter
     */

    public class MyViewPagerAdapter extends PagerAdapter{
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter(){

        }
         //Aqui se agrega la posición de los elementos para colocar objectos en la vista en el intro
        @Override
        public Object  instantiateItem(ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            View view = layoutInflater.inflate(layouts[position],container, false );
            container.addView(view);

            if(position == 4){
                Button Ingre = (Button)view.findViewById(R.id.BotonUsuario);

                Ingre.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent Go = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(Go);
                    }

                });

            }


            return view;
        }


        @Override
        public int getCount(){
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object){
            View view = (View) object;
            container.removeView(view);
        }
    }
}

