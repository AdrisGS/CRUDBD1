package com.example.adrisgs.crudbd;

import android.provider.BaseColumns;

public class EstructuraBD {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private EstructuraBD() {}

    /* Inner class that defines the table contents */
    // public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "datos_personales";
        public static final String NOMBRE_COLUMNA1= "id";
        public static final String NOMBRE_COLUMNA2= "nombre";
        public static final String NOMBRE_COLUMNA3= "apellido";
    //}

    private static final String TEXT_TYPE =" TEXT";
    private static final String COMMA_SEP =",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EstructuraBD.TABLE_NAME + " (" +
                    EstructuraBD.NOMBRE_COLUMNA1 + " INTEGER PRIMARY KEY," +
                    EstructuraBD.NOMBRE_COLUMNA2 + TEXT_TYPE +COMMA_SEP+
                    EstructuraBD.NOMBRE_COLUMNA3+  TEXT_TYPE+ " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EstructuraBD.TABLE_NAME;
}
