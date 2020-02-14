package com.newdesignFree.desarrollo.contadord_mx;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class Bitacora extends AppCompatActivity {

    private AdView mAdView;
    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    String total, b1000, b500, b200, b100, b50, b20, m20, m10, m5, m2, m1, m05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Initialize the Mobile Ads SDK.
        setContentView(R.layout.activity_bitacora);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        //Instancia a la base de datos
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        final SQLiteDatabase db = helper.getWritableDatabase();

        super.onCreate(savedInstanceState);

        listView = (ListView) findViewById(R.id.listview);
        CargarListado();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int clave = Integer.parseInt(listado.get(position).split(" Total: ")[0]);

                //String sql="Select * from Bitacoras where Id="+clave;

                //Consultamos los datos que corresponden al id de la lista
                Cursor c = db.rawQuery("SELECT * FROM Bitacoras WHERE Id=" + clave, null);

                if (c != null) {
                    c.moveToFirst();
                    do {
                        //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                        total = c.getString(c.getColumnIndex("TOTAL"));
                        b1000 = c.getString(c.getColumnIndex("B1000"));
                        b500 = c.getString(c.getColumnIndex("B500"));
                        b200 = c.getString(c.getColumnIndex("B200"));
                        b100 = c.getString(c.getColumnIndex("B100"));
                        b50 = c.getString(c.getColumnIndex("B50"));
                        b20 = c.getString(c.getColumnIndex("B20"));
                        m20 = c.getString(c.getColumnIndex("M20"));
                        m10 = c.getString(c.getColumnIndex("M10"));
                        m5 = c.getString(c.getColumnIndex("M5"));
                        m2 = c.getString(c.getColumnIndex("M2"));
                        m1 = c.getString(c.getColumnIndex("M1"));
                        m05 = c.getString(c.getColumnIndex("M05"));

                    } while (c.moveToNext());
                }

                //Cerramos el cursor y la conexion con la base de datos
                c.close();
                db.close();

                Intent intent = new Intent(Bitacora.this, Modificar.class);
                intent.putExtra("Id", clave);
                intent.putExtra("Total", total);
                intent.putExtra("B1000", b1000);
                intent.putExtra("B500", b500);
                intent.putExtra("B200", b200);
                intent.putExtra("B100", b100);
                intent.putExtra("B50", b50);
                intent.putExtra("B20", b20);
                intent.putExtra("M20", m20);
                intent.putExtra("M10", m10);
                intent.putExtra("M5", m5);
                intent.putExtra("M2", m2);
                intent.putExtra("M1", m1);
                intent.putExtra("M05", m05);

                startActivity(intent);
                finish();
            }
        });


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }


    /** Called when leaving the activity *//*
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    *//** Called when returning to the activity *//*
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    *//** Called before the activity is destroyed *//*
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }*/



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void CargarListado(){
        listado = ListaBitacora();

        if(listado.size()>0){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
            listView.setAdapter(adapter);
        }else{
            ListView lv = (ListView)findViewById(R.id.listview);
            lv.setEmptyView(findViewById(R.id.emptyListView2));
            lv.setEmptyView(findViewById(R.id.emptyListView));
        }

    }

    private ArrayList<String>ListaBitacora(){
        ArrayList<String> datos = new ArrayList<String>();
        BaseHelper helper = new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select Id, Total from Bitacoras";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){
            do{
                String linea = c.getInt(0) +" Total: " + "$"+c.getString(1);
                datos.add(linea);
            }while (c.moveToNext());
        }
        db.close();
        return  datos;
    }

}
