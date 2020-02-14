package com.newdesignFree.desarrollo.contadord_mx;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Modificar extends AppCompatActivity {

    private EditText etTotal,et1000,et500,et200,et100,et50,et20,etm20,et10,et5,et2,et1,et05;
    private TextView lbl1000,lbl500,lbl200,lbl100,lbl50,lbl20,lblm20,lbl10,lbl5,lbl2,lbl1,lbl05;
    int clave;

    String total, b1000, b500, b200, b100, b50, b20, m20, m10, m5, m2, m1, m05;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        //Metodo para cargar anuncios
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9495452629125064/2143826070");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //recibir datos a modificar
        Bundle b= getIntent().getExtras();

        if(b!=null){
           clave = b.getInt("Id");
           total = b.getString("Total");
           b1000 = b.getString("B1000");
           b500 = b.getString("B500");
           b200 = b.getString("B200");
           b100 = b.getString("B100");
           b50 = b.getString("B50");
           b20 = b.getString("B20");
           m20 = b.getString("M20");
           m10 = b.getString("M10");
           m5 = b.getString("M5");
           m2 = b.getString("M2");
           m1 = b.getString("M1");
           m05 = b.getString("M05");


        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Declaracion de componentes
        et1000 = findViewById(R.id.et1000);
        et500 = findViewById(R.id.et500);
        et200 = findViewById(R.id.et200);
        et100 = findViewById(R.id.et100);
        et50 = findViewById(R.id.et50);
        et20 = findViewById(R.id.et20);
        etm20 = findViewById(R.id.etm20);
        et10 = findViewById(R.id.et10);
        et5 =  findViewById(R.id.et5);
        et2 =  findViewById(R.id.et2);
        et1 = findViewById(R.id.et1);
        et05 = findViewById(R.id.et05);

        etTotal = findViewById(R.id.etTotal);

        //final EditText etTotal = (EditText)findViewById(R.id.etTotal);

        lbl1000 = findViewById(R.id.lbl1000);
        lbl500 = findViewById(R.id.lbl500);
        lbl200 = findViewById(R.id.lbl200);
        lbl100 = findViewById(R.id.lbl100);
        lbl50 = findViewById(R.id.lbl50);
        lbl20 = findViewById(R.id.lbl20);
        lblm20 = findViewById(R.id.lblm20);
        lbl10 = findViewById(R.id.lbl10);
        lbl5 = findViewById(R.id.lbl5);
        lbl2 = findViewById(R.id.lbl2);
        lbl1 = findViewById(R.id.lbl1);
        lbl05 = findViewById(R.id.lbl05);

        Button btnBorrar = (Button)findViewById(R.id.btnBorrar);
        Button btnGuardar = (Button)findViewById(R.id.btnguardar);


        //Asignacion de valor por default
        lbl1000.setText("0");
        lbl500.setText("0");
        lbl200.setText("0");
        lbl100.setText("0");
        lbl50.setText("0");
        lbl20.setText("0");
        lblm20.setText("0");
        lbl10.setText("0");
        lbl5.setText("0");
        lbl2.setText("0");
        lbl1.setText("0");
        lbl05.setText("0");

        //Borrar toda la informacion
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etTotal.getText().toString().trim().equals("0.0") || etTotal.getText().toString().trim().equals("") ){

                    Toast.makeText(Modificar.this, "Sin datos", Toast.LENGTH_LONG).show();

                }else{

                    //Despues de limpiar los campos, cargamos el anuncio
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }

                    LimpiarDatos();

                    Toast.makeText(Modificar.this, "Datos eliminados", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modificar(clave,etTotal.getText().toString(),et1000.getText().toString(),et500.getText().toString()
                        ,et200.getText().toString(),et100.getText().toString(),et50.getText().toString(),et20.getText().toString()
                        ,etm20.getText().toString(),et10.getText().toString(),et5.getText().toString(),et2.getText().toString()
                        ,et1.getText().toString(),et05.getText().toString());


            }
        });

        //Set informacion obtenida para modificar
        etTotal.setText(total);
        et1000.setText(b1000);
        et500.setText(b500);
        et200.setText(b200);
        et100.setText(b100);
        et50.setText(b50);
        et20.setText(b20);
        etm20.setText(m20);
        et10.setText(m10);
        et5.setText(m5);
        et2.setText(m2);
        et1.setText(m1);
        et05.setText(m05);


        et1000.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_1000, mul_1000;

                if(et1000.getText().toString().trim().equals("")){

                    lbl1000.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_1000 = Integer.parseInt(et1000.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_1000 = valor_1000 * 1000.0;
                    lbl1000.setText(""+mul_1000);

                    SumaTotal();


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et500.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_500, mul_500;

                if(et500.getText().toString().trim().equals("")){

                    lbl500.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_500 = Integer.parseInt(et500.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_500 = valor_500 * 500.0;
                    lbl500.setText(""+mul_500);

                    SumaTotal();

                }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et200.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_200, mul_200;

                if(et200.getText().toString().trim().equals("")){

                    lbl200.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_200 = Integer.parseInt(et200.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_200 = valor_200 * 200.0;
                    lbl200.setText(""+mul_200);

                    SumaTotal();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et100.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_100, mul_100;

                if(et100.getText().toString().trim().equals("")){

                    lbl100.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_100 = Integer.parseInt(et100.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_100 = valor_100 * 100.0;
                    lbl100.setText(""+mul_100);

                    SumaTotal();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et50.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_50, mul_50;

                if(et50.getText().toString().trim().equals("")){

                    lbl50.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_50 = Integer.parseInt(et50.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_50 = valor_50 * 50.0;
                    lbl50.setText(""+mul_50);

                    SumaTotal();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_20, mul_20;

                if(et20.getText().toString().trim().equals("")){

                    lbl20.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_20 = Integer.parseInt(et20.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_20 = valor_20 * 20.0;
                    lbl20.setText(""+mul_20);

                    SumaTotal();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etm20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_m20, mul_m20;

                if(etm20.getText().toString().trim().equals("")){

                    lblm20.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_m20 = Integer.parseInt(etm20.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_m20 = valor_m20 * 20.0;
                    lblm20.setText(""+mul_m20);

                    SumaTotal();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_10, mul_10;

                if(et10.getText().toString().trim().equals("")){

                    lbl10.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_10 = Integer.parseInt(et10.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_10 = valor_10 * 10.0;
                    lbl10.setText(""+mul_10);

                    SumaTotal();

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_5, mul_5;

                if(et5.getText().toString().trim().equals("")){

                    lbl5.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_5 = Integer.parseInt(et5.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_5 = valor_5 * 5.0;
                    lbl5.setText(""+mul_5);

                    SumaTotal();

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_2, mul_2;

                if(et2.getText().toString().trim().equals("")){

                    lbl2.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_2 = Integer.parseInt(et2.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_2 = valor_2 * 2.0;
                    lbl2.setText(""+mul_2);

                    SumaTotal();

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_1, mul_1;

                if(et1.getText().toString().trim().equals("")){

                    lbl1.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_1 = Integer.parseInt(et1.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_1 = valor_1 * 1.0;
                    lbl1.setText(""+mul_1);

                    SumaTotal();

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et05.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Declaro variable
                double valor_05, mul_05;

                if(et05.getText().toString().trim().equals("")){

                    lbl05.setText("0");

                    SumaTotal();

                }else{

                    //Convierto
                    valor_05 = Integer.parseInt(et05.getText().toString());
                    //Multiplico el numero ingresado * 1000
                    mul_05 = valor_05 * 0.5;
                    lbl05.setText(""+mul_05);

                    SumaTotal();

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void LimpiarDatos(){
        et1000.setText("");
        et500.setText("");
        et200.setText("");
        et100.setText("");
        et50.setText("");
        et20.setText("");
        etm20.setText("");
        et10.setText("");
        et5.setText("");
        et2.setText("");
        et1.setText("");
        et05.setText("");
    }

    //Metodo para realizar la suma de todos los TextView
    private void SumaTotal(){
        //realizo suma del total
        Double t1000 = Double.parseDouble(lbl1000.getText().toString());
        Double t500 = Double.parseDouble(lbl500.getText().toString());
        Double t200 = Double.parseDouble(lbl200.getText().toString());
        Double t100 = Double.parseDouble(lbl100.getText().toString());
        Double t50 = Double.parseDouble(lbl50.getText().toString());
        Double t20 = Double.parseDouble(lbl20.getText().toString());
        Double tm20 = Double.parseDouble(lblm20.getText().toString());
        Double t10 = Double.parseDouble(lbl10.getText().toString());
        Double t5 = Double.parseDouble(lbl5.getText().toString());
        Double t2 = Double.parseDouble(lbl2.getText().toString());
        Double t1 = Double.parseDouble(lbl1.getText().toString());
        Double t05 = Double.parseDouble(lbl05.getText().toString());

        Double suma = t1000 + t500 +t200 +t100 + t50 + t20 +tm20 +t10 +t5 +t2 +t1 +t05;

        String resultado = String.valueOf(suma);

        etTotal.setText(resultado);
    }

    //Boton modificar
    private void Modificar(int Id, String Total, String B1000, String B500, String B200, String B100, String B50, String B20, String M20,
                           String M10,  String M5,  String M2,  String M1,  String M05){

        if(etTotal.getText().toString().trim().equals("0.0") || etTotal.getText().toString().trim().equals("") ){

            Toast.makeText(this,"Ingrese información a guardar.",Toast.LENGTH_SHORT).show();

        }else{
            BaseHelper helper = new BaseHelper(this,"Demo",null,1);
            SQLiteDatabase db = helper.getWritableDatabase();
            try{
                String sql="update Bitacoras set Total='" + Total +"', B1000='" + B1000 +"', B500='" + B500 +"', B200='" + B200 +
                        "', B100='" + B100 +"', B50='" + B50 +"', B20='" + B20 +"', M20='" + M20 +
                        "', M10='" + M10 +"', M5='" + M5 +"', M2='" + M2 +"', M1='" + M1 +"', M05='" + M05 +"' where Id="+Id;
                db.execSQL(sql);
                db.close();
                Toast.makeText(this,"Se modifico correctamente",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Modificar.this, Bitacora.class);  //your class
                startActivity(i);
                finish();

                //Despues de limpiar los campos, cargamos el anuncio
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

            }catch (Exception e){
                Toast.makeText(this,"Error:" + e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }

    //Instancia de la clase bitacora
    private static Bitacora  bitacora;

    //Boton Eliminar
    private void Eliminar(int Id){
        BaseHelper helper = new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql="delete from Bitacoras where Id="+Id;
        db.execSQL(sql);
        db.close();

        //Despues de limpiar los campos, cargamos el anuncio
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }


    protected void onRestart() {

         super.onRestart();
        Intent i = new Intent(Modificar.this, Bitacora.class);  //your class
        startActivity(i);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modificar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (item.getItemId()==android.R.id.home){
            Intent intent = new Intent(this, Bitacora.class);
            startActivity(intent);
            finish();
        }

        if (id == R.id.modificar){
            //Modificar(clave,etTotal.getText().toString());
            //onBackPressed();
        }

        if (id == R.id.eliminar){
            Eliminar(clave);

            onRestart();

        }
        return super.onOptionsItemSelected(item);
    }

}



