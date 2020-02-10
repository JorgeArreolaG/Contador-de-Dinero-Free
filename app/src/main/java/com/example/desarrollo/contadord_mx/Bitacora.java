package com.example.desarrollo.contadord_mx;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLData;
import java.util.ArrayList;

public class Bitacora extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }



    String total, bmil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Instancia a la base de datos
        BaseHelper helper = new BaseHelper(this,"Demo",null,1);
        final SQLiteDatabase db = helper.getWritableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora);
        listView = (ListView)findViewById(R.id.listview);
        CargarListado();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int clave = Integer.parseInt(listado.get(position).split(" Total: ")[0]);

                //String sql="Select * from Bitacoras where Id="+clave;

                //Consultamos los datos que corresponden al id de la lista
                Cursor c = db.rawQuery("SELECT * FROM Bitacoras WHERE Id="+clave, null);

                if (c != null) {
                    c.moveToFirst();
                    do {
                        //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                        total = c.getString(c.getColumnIndex("TOTAL"));
                        bmil = c.getString(c.getColumnIndex("BMIL"));
                    } while (c.moveToNext());
                }

                //Cerramos el cursor y la conexion con la base de datos
                c.close();
                db.close();

                Intent intent = new Intent(Bitacora.this, Modificar.class);
                intent.putExtra("Id", clave);
                intent.putExtra("Total",total );
                intent.putExtra("Bmil", bmil);
                startActivity(intent);
                finish();
            }
        });


        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }



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
