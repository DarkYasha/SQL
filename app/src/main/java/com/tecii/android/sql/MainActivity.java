package com.tecii.android.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView et1, et2, et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (TextView)findViewById(R.id.et1);
        et2 = (TextView)findViewById(R.id.et2);
        et3 = (TextView)findViewById(R.id.et3);
    }

    public void alta(View v){
        AdminSQLiteHepler admin =  new AdminSQLiteHepler(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        bd.insert("articulos", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        Toast.makeText(this, "se cargaron los datos del articulo", Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v){
        AdminSQLiteHepler admin =  new AdminSQLiteHepler(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        Cursor file = bd.rawQuery("select descripcion, precio from articulos where codigo" + cod + "´", null);
        if (file.moveToFirst()) {
            et2.setText(file.getString(0));
            et3.setText(file.getString(1));
        }
        else
            Toast.makeText(this, "No existe articulo con dicho codigo", Toast.LENGTH_SHORT).show();
    }

    public void consultapordescripcion(View v){
        AdminSQLiteHepler admin =  new AdminSQLiteHepler(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String descri = et2.getText().toString();
        Cursor file = bd.rawQuery("select descripcion, precio from articulos where descripcion" + descri + "´", null);
        if (file.moveToFirst()) {
            et1.setText(file.getString(0));
            et3.setText(file.getString(1));
        }
        else
            Toast.makeText(this, "No existe articulo con dicha descripcion", Toast.LENGTH_SHORT).show();
    }

    public void bajaporcodigo(View v){
        AdminSQLiteHepler admin =  new AdminSQLiteHepler(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        int cant = bd.delete("articulos", "codigo =" + cod, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        if (cant == 1)
            Toast.makeText(this, "se borro el articulo con dicho codigos", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe articulo con dicho codigo", Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v){
        AdminSQLiteHepler admin =  new AdminSQLiteHepler(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        int cant = bd.update("articulos", registro, "codigo =" + cod, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificsron los datos", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe articulo con dicho codigo", Toast.LENGTH_SHORT).show();
    }
}

