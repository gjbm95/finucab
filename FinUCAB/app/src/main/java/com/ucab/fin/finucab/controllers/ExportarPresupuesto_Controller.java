package com.ucab.fin.finucab.controllers;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Created by mariangel on 13/5/2017.
 */

public class ExportarPresupuesto_Controller extends AsyncTask<String ,String, String> {

//////ESTE CODIGO ES UNA PRUEBA PARA EL EXPORTAR DEL MODULO 3 DE PRESUPUESTOS, SE ESPORTAN ARCHIVOS CSV.////

    protected String doInBackground(final String... args){
        File exportDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), ""); //CONSEGUIR LA RUTA DEL SDCARD
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "ExcelPresupuesto.csv");   //DECLARAR UN ARCHIVO CON EL NOMBRE EXCELFILE
        try {
            file.createNewFile();   //CREAR EL ARCHIVO
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file),','); // DECLARAR UN OBJETO CSVWRITE PARA EMPEZAR A ESCRIBIR

            //data
            ArrayList<String> listdata= new ArrayList<String>(); //ARRAY DE PRUEBA (LUEGO DEBE SER CAMBIADO POR CODIGO DE BASE DE DATOS)
            listdata.add("Gastos Restaurant");
            listdata.add("Restaurant");
            listdata.add("5000");
            listdata.add("Unico");
            listdata.add("0");

            String arrStr1[] ={"Nombre del Presupuesto", "Categoria", "Monto", "Tipo","Recurrencia"}; //HEADER DEL ARCHIVO
            csvWrite.writeNext(arrStr1); //ESCRIBIR EL HEADER EN EL ARCHIVO

            String arrStr[] ={listdata.get(0), listdata.get(1), listdata.get(2), listdata.get(3),listdata.get(4)}; //OBTENER EL ARRAY
            csvWrite.writeNext(arrStr); //ESCRIBIR EL ARRAY EN EL ARCHIVO

            csvWrite.close(); //CERRAR EL ESCRIBIR
            return "";
        }
        catch (IOException e){
            Log.e("MainActivity", e.getMessage(), e);
            return "";
        }
    }

}
