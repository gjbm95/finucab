package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 *Modulo 4 - Modulo de  Gestion de Categorias
 *Desarrolladores:
 *@author Juan Ariza / Augusto Cordero / Manuel Gonzalez
 *Descripción de la clase:
 * Esta clase se encarga de exportar las categorias en formato Excel o Cvs
 */

public class ExportarCategoria_Controller extends AsyncTask<String ,String, String> {

//////ESTE CODIGO ES UNA PRUEBA PARA EL EXPORTAR DEL MODULO 3 DE PRESUPUESTOS, SE EXPORTAN ARCHIVOS CSV.////

    public static ArrayList<Categoria> listaCategoria = new ArrayList<>();
    public static Activity activity;
    public static ArrayList<Categoria> categoria = new ArrayList<>();



    protected String doInBackground(final String... args) {

        File exportDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), ""); //CONSEGUIR LA RUTA DEL SDCARD
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File fileCSV = new File(exportDir, "CSVPresupuesto.csv");   //DECLARAR UN ARCHIVO CSV
        File fileEXCEL = new File(exportDir, "ExcelPresupuesto.xls");//DECLARAR UN ARCHIVO XML
        try {
            fileCSV.createNewFile();   //CREAR EL ARCHIVO
            FileWriter writerCSV = new FileWriter(fileCSV); //HABILITAR LA FUNCION DE ESCRIBIR EL ARCHIVO

            writerCSV.write("Nombre de la Categoria;Descripcion;Estado;Tipo;\n"); // ESCRIBO EN EL ARCHIVO EL HEADER
            for(Categoria c : categoria){ //ITERACION DEL ARRAY
                writerCSV.write(c.getNombre()+";"+c.getDescripcion()+";"+c.isEstaHabilitado()+";"+c.isIngreso()
                        +";"+"\n"); //LLENANDO LA FILA

            }
            writerCSV.flush();
            writerCSV.close();//CERRAR EL ESCRIBIR

            //***********PARA EXPORTAR A EXCEL*********//

            fileEXCEL.createNewFile(); //CREANDO EL ARCHIVO
            Workbook wb = new HSSFWorkbook(); //CREANDO UN WORKBOOK
            Cell c= null;

            //Cell style for header row
            CellStyle cs = wb.createCellStyle();
            CellStyle as = wb.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.SKY_BLUE.index); // DECLARO EL COLOR DE FONDO DE LA HOJA
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            as.setFillForegroundColor(HSSFColor.PALE_BLUE.index); // DECLARO EL COLOR DE FONDO DE LA HOJA
            as.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            //*****GENERANDO LA PAGINA******////
            Sheet sheet1 = null;
            sheet1 = wb.createSheet("Mis Categorias");

            //***** GENERANDO COLUMNAS*****//
            Row row0 = sheet1.createRow(0);  // CREO LA FILA HEADER

            c = row0.createCell(0); // LE ASIGNO EL NUMERO DE LA CELDA
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

            int count = categoria.size(); //OBTENGO LA LONGITUD DEL ARRAY

            for(int contadorFilas=0 ; contadorFilas<count; contadorFilas++){   // ITERANDO PARA COLOCAR LAS FILAS
                Row row = sheet1.createRow(contadorFilas+1);  // CREO UNA FILA

                Categoria ca = categoria.get(contadorFilas);

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
            return "";
        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage(), e);
            return "";

        }
    }
    public static ArrayList<Categoria> obtenerPresupuestos ( Activity actividad ) {

        Parametros.setMetodo("Modulo4/ListaCategoriaExportar");
        //Parametros.setMetodo("Modulo3/ListaPresupuestoExportar?idUsuario="+ ControlDatos.getUsuario().getIdusuario());
        new Recepcion(actividad).execute("GET");
        JSONObject jObject = null;
        try {
            JSONArray mJsonArray = new JSONArray(Parametros.respuesta);
            int count = mJsonArray.length();
            for (int i = 0; i < count; i++) {   // iterate through jsonArray
                jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                Categoria ca = new Categoria();
                ca.setNombre((String) jObject.get("Nombre"));
                ca.setDescripcion((String) jObject.get("Descripcion"));
                ca.setEstaHabilitado(Boolean.parseBoolean((String) jObject.get("Estado")));
                ca.setEsIngreso(Boolean.parseBoolean((String) jObject.get("Tipo")));

                listaCategoria.add(ca);
            }


            return listaCategoria;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
