package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.R.id.list;


/**
 * Created by mariangel on 13/5/2017.
 */

public class ExportarPresupuesto_Controller extends AsyncTask<String ,String, String> {


//////ESTE CODIGO ES UNA PRUEBA PARA EL EXPORTAR DEL MODULO 3 DE PRESUPUESTOS, SE EXPORTAN ARCHIVOS CSV.////

    public static ArrayList<Presupuesto> listaPresupuestos = new ArrayList<>();
    public static Activity activity;
    public static ArrayList<Presupuesto> presupuesto = new ArrayList<>();



    protected String doInBackground(final String... args) {

        File exportDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), ""); //CONSEGUIR LA RUTA DEL SDCARD
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        String format = s.format(new Date());
        File fileCSV = new File(exportDir, "CSVPresupuesto"+format+".csv");   //DECLARAR UN ARCHIVO CSV
        File fileEXCEL = new File(exportDir, "ExcelPresupuesto"+format+".xls");//DECLARAR UN ARCHIVO XML
        try {
            fileCSV.createNewFile();   //CREAR EL ARCHIVO
            FileWriter writerCSV = new FileWriter(fileCSV); //HABILITAR LA FUNCION DE ESCRIBIR EL ARCHIVO

            writerCSV.write("Nombre del Presupuesto;Categoria;Monto;Clasificacion;Duracion;Tipo;\n"); // ESCRIBO EN EL ARCHIVO EL HEADER
            for(Presupuesto p : presupuesto){ //ITERACION DEL ARRAY
                writerCSV.write(p.get_nombre()+";"+p.get_categoria()+";"+p.get_monto().toString()+";"+p.get_clasificacion()
                        +";"+p.get_duracion().toString()+";"+p.get_tipo()+";"+"\n"); //LLENANDO LA FILA

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
            sheet1 = wb.createSheet("Mi Presupuesto");

            //***** GENERANDO COLUMNAS*****//
            Row row0 = sheet1.createRow(0);  // CREO LA FILA HEADER

            c = row0.createCell(0); // LE ASIGNO EL NUMERO DE LA CELDA
            c.setCellValue("Nombre del Presupuesto"); // VALOR DE LA CELDA
            c.setCellStyle(cs); // ESTABLEZCO EL ESTILO

            c = row0.createCell(1);
            c.setCellValue("Categoria");
            c.setCellStyle(cs);

            c = row0.createCell(2);
            c.setCellValue("Monto");
            c.setCellStyle(cs);

            c = row0.createCell(3);
            c.setCellValue("Clasificacion");
            c.setCellStyle(cs);

            c = row0.createCell(4);
            c.setCellValue("Duracion");
            c.setCellStyle(cs);

            c = row0.createCell(5);
            c.setCellValue("Tipo");
            c.setCellStyle(cs);

            int count = presupuesto.size(); //OBTENGO LA LONGITUD DEL ARRAY

            for(int contadorFilas=0 ; contadorFilas<count; contadorFilas++){   // ITERANDO PARA COLOCAR LAS FILAS
                Row row = sheet1.createRow(contadorFilas+1);  // CREO UNA FILA

                Presupuesto p = presupuesto.get(contadorFilas);

                c = row.createCell(0); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(p.get_nombre()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(1); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(p.get_categoria()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(2); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(p.get_monto()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(3); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(p.get_clasificacion()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(4); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(p.get_duracion()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(5); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(p.get_tipo()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

            }

            // CREAR UNA RUTA DONDE SE VA A GUARDAR NUESTRO OBJETO EN LA SDCARD
            FileOutputStream os = new FileOutputStream(fileEXCEL); // DECLARO UN OBJETO DE TIPO FILEOUTPUTSTREAM
            wb.write(os); // ESCRIBO EL ARCHIVO
            os.close(); // CIERRO EL ARCCHIVO
            listaPresupuestos = new ArrayList<>();
            return "";
        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage(), e);
            return "";

        }
    }
    public static ArrayList<Presupuesto> obtenerPresupuestos ( Activity actividad ) {

        Parametros.setMetodo("Modulo3/ListaPresupuestoExportar");
        //Parametros.setMetodo("Modulo3/ListaPresupuestoExportar?idUsuario="+ ControlDatos.getUsuario().getIdusuario());
        new Recepcion(actividad).execute("GET");
        JSONObject jObject = null;
        try {
            JSONArray mJsonArray = new JSONArray(Parametros.respuesta);
            int count = mJsonArray.length();

            for (int i = 0; i < count; i++) {   // iterate through jsonArray
                jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                Presupuesto pre = new Presupuesto();
                pre.set_nombre((String) jObject.get("Nombre"));
                pre.set_categoria((String) jObject.get("Categoria"));
                pre.set_monto(Float.parseFloat((String) jObject.get("Monto")));
                pre.set_clasificacion((String) jObject.get("Clasificacion"));
                pre.set_duracion(Integer.parseInt((String) jObject.get("Duracion")));
                if((jObject.get("Tipo")).equals("t")){
                    pre.set_tipo("Ganancia");
                }else{
                    pre.set_tipo("Gasto");
                }
                listaPresupuestos.add(pre);
            }
            for (Presupuesto p: listaPresupuestos){
                System.out.println(p.get_nombre());}

            return listaPresupuestos;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
