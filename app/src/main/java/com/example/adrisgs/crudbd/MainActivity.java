package com.example.adrisgs.crudbd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnInsertar, btnbuscar, btnactualizar,btnborrar;


    EditText textoId, textoNombre, textoApellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertar=(Button)findViewById(R.id.insertar);
        btnbuscar=(Button)findViewById(R.id.buscar);
        btnactualizar=(Button)findViewById(R.id.actualizar);
        btnborrar=(Button)findViewById(R.id.borrar);

        textoId=(EditText)findViewById(R.id.idAlgo);
        textoNombre=(EditText)findViewById(R.id.nombre);
        textoApellido=(EditText)findViewById(R.id.apellido);

        final BDHelper mDbHelper = new BDHelper(this);//nos permite acceder a la base de datos

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Gets the data repository in write mode
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(EstructuraBD.NOMBRE_COLUMNA1, textoId.getText().toString());
                values.put(EstructuraBD.NOMBRE_COLUMNA2, textoNombre.getText().toString());
                values.put(EstructuraBD.NOMBRE_COLUMNA3, textoApellido.getText().toString());

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(EstructuraBD.TABLE_NAME, null, values);

                Toast.makeText(getApplicationContext(),"Se guardó el registro con id: "+newRowId,Toast.LENGTH_LONG).show();

            }
        });


        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = mDbHelper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        //EstructuraBD.NOMBRE_COLUMNA1,
                        EstructuraBD.NOMBRE_COLUMNA2,
                        EstructuraBD.NOMBRE_COLUMNA3
                };

                // Filter results WHERE "title" = 'My Title'
                String selection = EstructuraBD.NOMBRE_COLUMNA1 + " = ?";
                String[] selectionArgs = { textoId.getText().toString() };

                // How you want the results sorted in the resulting Cursor
               /* String sortOrder =
                        EstructuraBD.COLUMN_NAME_SUBTITLE + " DESC";//ordena los registros*/

               try{

                   Cursor cursor = db.query(
                           EstructuraBD.TABLE_NAME,   // The table to query
                           projection,             // The array of columns to return (pass null to get all)
                           selection,              // The columns for the WHERE clause
                           selectionArgs,          // The values for the WHERE clause
                           null,                   // don't group the rows
                           null,                   // don't filter by row groups
                           null              // The sort order
                   );
                   cursor.moveToFirst();

                   textoNombre.setText(cursor.getString(0));
                   textoApellido.setText(cursor.getString(1));


               }catch (Exception e){

                   Toast.makeText(getApplicationContext(),"No se encontro registro",Toast.LENGTH_LONG).show();
               }



            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                // New value for one colum
                ContentValues values = new ContentValues();
                values.put(EstructuraBD.NOMBRE_COLUMNA2, textoNombre.getText().toString());
                values.put(EstructuraBD.NOMBRE_COLUMNA3, textoApellido.getText().toString());

                // Which row to update, based on the title
                String selection = EstructuraBD.NOMBRE_COLUMNA1+ " LIKE ?";
                String[] selectionArgs = { textoId.getText().toString() };

                int count = db.update(
                        EstructuraBD.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);

                Toast.makeText(getApplicationContext(),"Se actualizó el registro con ID: "+textoId.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });

        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                // Define 'where' part of query.
                String selection = EstructuraBD.NOMBRE_COLUMNA1 + " LIKE ?";
                // Specify arguments in placeholder order.
                String[] selectionArgs = { textoId.getText().toString() };
                // Issue SQL statement.
                int deletedRows = db.delete(EstructuraBD.TABLE_NAME, selection, selectionArgs);

                Toast.makeText(getApplicationContext(),"Se borro el registro con ID:"+textoId.getText().toString(),Toast.LENGTH_LONG).show();

                textoId.setText("");
                textoNombre.setText("");
                textoApellido.setText("");
            }
        });


    }
}
