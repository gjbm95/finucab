package com.ucab.fin.finucab.controllers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.ucab.fin.finucab.domain.Categoria;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;

import static com.ucab.fin.finucab.controllers.ExportarPresupuesto_Controller.activity;


/**
 *Modulo 4 - Modulo de  Gestion de Categorias
 *Desarrolladores:
 *@author Juan Ariza / Augusto Cordero / Manuel Gonzalez
 *Descripción de la clase:
 * Esta clase se encarga de exportar las categorias en formato Excel o Cvs
 */

public class ExportarCategoria_Controller extends AsyncTask<String ,String, String> {

    public ArrayList<Categoria> listaCategoria ;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public ExportarCategoria_Controller(ArrayList<Categoria> listaCategoria) {

        this.listaCategoria = listaCategoria;
    }

    protected String doInBackground(final String... args) {

        File root = new File(Environment.getExternalStorageDirectory(), "FinUCAB");
        if (!root.exists()) {
            root.mkdirs();
        }

        Log.d("State", Environment.getExternalStorageState() );
        exportarCSV(root);
        exportarExcel(root);

        return "";
    }

    public void exportarCSV(File exportDir){
        try {
            File fileCSV = new File(exportDir, "CSVCategoria.csv");   //DECLARAR UN ARCHIVO CSV
            fileCSV.createNewFile();   //CREAR EL ARCHIVO
            FileWriter writerCSV = new FileWriter(fileCSV); //HABILITAR LA FUNCION DE ESCRIBIR EL ARCHIVO

            writerCSV.write("Nombre de la Categoria;Descripcion;Habilitado;Ingreso;\n"); // ESCRIBO EN EL ARCHIVO EL HEADER
            for(Categoria c : listaCategoria){ //ITERACION DEL ARRAY
                writerCSV.write(c.getNombre()+";"+c.getDescripcion()+";"+c.isEstaHabilitado()+";"+c.isIngreso()
                        +";"+"\n"); //LLENANDO LA FILA

            }
            writerCSV.flush();
            writerCSV.close();//CERRAR EL ESCRIBIR

        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage(), e);


        }
    }


    public void exportarExcel(File exportDir){

        try {

            File fileEXCEL = new File(exportDir, "ExcelCategoria.xls");//DECLARAR UN ARCHIVO XLS
            fileEXCEL.createNewFile(); //CREANDO EL ARCHIVO
            Workbook wb = new HSSFWorkbook(); //CREANDO UN WORKBOOK

            //Cell style for header row
            CellStyle cs = wb.createCellStyle();
            CellStyle as = wb.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.SKY_BLUE.index); // DECLARO EL COLOR DE FONDO DE LA HOJA
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            as.setFillForegroundColor(HSSFColor.PALE_BLUE.index); // DECLARO EL COLOR DE FONDO DE LA HOJA
            as.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            //*****GENERANDO LA PAGINA******////
            Sheet sheet1 = wb.createSheet("Mis Categorias");

            //***** GENERANDO COLUMNAS*****//
            Row row0 = sheet1.createRow(0);  // CREO LA FILA HEADER

            Cell c = row0.createCell(0); // LE ASIGNO EL NUMERO DE LA CELDA
            c.setCellValue("Nombre de la Categoria"); // VALOR DE LA CELDA
            c.setCellStyle(cs); // ESTABLEZCO EL ESTILO

            c = row0.createCell(1);
            c.setCellValue("Descripcion");
            c.setCellStyle(cs);

            c = row0.createCell(2);
            c.setCellValue("Estado");
            c.setCellStyle(cs);

            c = row0.createCell(3);
            c.setCellValue("Tipo");
            c.setCellStyle(cs);

            int count = listaCategoria.size(); //OBTENGO LA LONGITUD DEL ARRAY

            for(int contadorFilas=0 ; contadorFilas<count; contadorFilas++){   // ITERANDO PARA COLOCAR LAS FILAS
                Row row = sheet1.createRow(contadorFilas+1);  // CREO UNA FILA

                Categoria ca = listaCategoria.get(contadorFilas);

                c = row.createCell(0); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(ca.getNombre()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(1); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(ca.getDescripcion()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(2); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(ca.isEstaHabilitado()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(3); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(ca.isIngreso()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

            }

            // CREAR UNA RUTA DONDE SE VA A GUARDAR NUESTRO OBJETO EN LA SDCARD
            FileOutputStream os = new FileOutputStream(fileEXCEL); // DECLARO UN OBJETO DE TIPO FILEOUTPUTSTREAM
            wb.write(os); // ESCRIBO EL ARCHIVO
            os.close(); // CIERRO EL ARCCHIVO

        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage(), e);


        }
    }

}
