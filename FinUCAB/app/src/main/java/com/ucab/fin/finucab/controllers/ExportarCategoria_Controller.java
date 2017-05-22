package com.ucab.fin.finucab.controllers;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

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


/**
 * Created by juan on 21/5/2017.
 */

public class ExportarCategoria_Controller extends AsyncTask<String ,String, String> {



    protected String doInBackground(final String... args) {
        File exportDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), ""); //CONSEGUIR LA RUTA DEL SDCARD
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File fileCSV = new File(exportDir, "CSVCategoria.csv");   //DECLARAR UN ARCHIVO CSV
        File fileEXCEL = new File(exportDir, "ExcelCategoria.xls");//DECLARAR UN ARCHIVO XML

        try {
            fileCSV.createNewFile();   //CREAR EL ARCHIVO
            FileWriter writerCSV = new FileWriter(fileCSV); //HABILITAR LA FUNCION DE ESCRIBIR EL ARCHIVO
            writerCSV.write("Esto;es;el;csv;y;funciona;\n"); // ESCRIBO EN EL ARCHIVO
            writerCSV.write("Esto;tambien;funciona"); // ESCRIBO UNA SEGUNDA LINEA
            writerCSV.flush();
            writerCSV.close();//CERRAR EL ESCRIBIR

            //***********PARA EXPORTAR A EXCEL*********//
            fileEXCEL.createNewFile();
            //New Workbook
            Workbook wb = new HSSFWorkbook();
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

            //***** GENERANDO COLUMNAS*****/
            Row row = sheet1.createRow(0);  // CREO UNA FILA
            c = row.createCell(0); // LE ASIGNO EL NUMERO DE LA CELDA
            c.setCellValue("Nombre de la Categoria"); // VALOR DE LA CELDA
            c.setCellStyle(cs); // ESTABLEZCO EL ESTILO

            c = row.createCell(1);
            c.setCellValue("Nombre");
            c.setCellStyle(cs);

            c = row.createCell(2);
            c.setCellValue("Descripcion");
            c.setCellStyle(cs);

            c = row.createCell(3);
            c.setCellValue("Estado");
            c.setCellStyle(cs);

            c = row.createCell(4);
            c.setCellValue("Tipo");
            c.setCellStyle(cs);

            ////****OTRA FILA DE PRUEBA******///
            Row rownext = sheet1.createRow(1);
            c = rownext.createCell(0);
            c.setCellValue("Comida");
            c.setCellStyle(as);

            c = rownext.createCell(1);
            c.setCellValue("Comida del mes");
            c.setCellStyle(as);

            c = rownext.createCell(2);
            c.setCellValue("Habilitado");
            c.setCellStyle(as);

            c = rownext.createCell(3);
            c.setCellValue("Ingreso");
            c.setCellStyle(as);

            // Create a path where we will place our List of objects on external storage
            FileOutputStream os = new FileOutputStream(fileEXCEL); // DECLARO UN OBJETO DE TIPO FILEOUTPUTSTREAM
            wb.write(os); // ESCRIBO EL ARCHIVO
            os.close(); // CIERRO EL ARCCHIVO
            return "";
        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage(), e);
            return "";

        }
    }

}
