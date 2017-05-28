package com.ucab.fin.finucab.controllers;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.ucab.fin.finucab.domain.Pago;

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


/**
 *Modulo 5 - Modulo de  Gestion de Pagos
 *Desarrolladores:
 *@author Maria Jose Perez / Luis Manuel Rojas / Jeffrey Soares
 *Descripción de la clase:
 * Esta clase se encarga de exportar las pagos en formato Excel o Cvs
 */

public class ExportarPago_Controller extends AsyncTask<String ,String, String> {

    public ArrayList<Pago> listaPago ;

    public ExportarPago_Controller(ArrayList<Pago> listaPago) {

        this.listaPago = listaPago;
    }

    protected String doInBackground(final String... args) {

        File exportDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), ""); //CONSEGUIR LA RUTA DEL SDCARD
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        exportarCSV(exportDir);
        exportarExcel(exportDir);

        return "";
    }

    public void exportarCSV(File exportDir){
        try {
            File fileCSV = new File(exportDir, "CSVPago.csv");   //DECLARAR UN ARCHIVO CSV
            fileCSV.createNewFile();   //CREAR EL ARCHIVO
            FileWriter writerCSV = new FileWriter(fileCSV); //HABILITAR LA FUNCION DE ESCRIBIR EL ARCHIVO

            writerCSV.write("Nombre del Pago;Descripcion;Habilitado;Ingreso;\n"); // ESCRIBO EN EL ARCHIVO EL HEADER
            for(Pago c : listaPago){ //ITERACION DEL ARRAY
                writerCSV.write(c.getTotal()+";"+c.getTipo()+";"+c.getCategoria()+";"+c.getDescripcion()
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

            File fileEXCEL = new File(exportDir, "ExcelPago.xls");//DECLARAR UN ARCHIVO XLS
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
            Sheet sheet1 = wb.createSheet("Mis Pagos");

            //***** GENERANDO COLUMNAS*****//
            Row row0 = sheet1.createRow(0);  // CREO LA FILA HEADER

            Cell c = row0.createCell(0); // LE ASIGNO EL NUMERO DE LA CELDA
            c.setCellValue("Monto"); // VALOR DE LA CELDA
            c.setCellStyle(cs); // ESTABLEZCO EL ESTILO

            c = row0.createCell(1);
            c.setCellValue("Tipo Transaccion");
            c.setCellStyle(cs);

            c = row0.createCell(2);
            c.setCellValue("Categoria");
            c.setCellStyle(cs);

            c = row0.createCell(3);
            c.setCellValue("Descripcion");
            c.setCellStyle(cs);

            int count = listaPago.size(); //OBTENGO LA LONGITUD DEL ARRAY

            for(int contadorFilas=0 ; contadorFilas<count; contadorFilas++){   // ITERANDO PARA COLOCAR LAS FILAS
                Row row = sheet1.createRow(contadorFilas+1);  // CREO UNA FILA

                Pago pg = listaPago.get(contadorFilas);

                c = row.createCell(0); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(pg.getTotal()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(1); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(pg.getTipo()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(2); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(pg.getCategoria()); // VALOR DE LA CELDA
                c.setCellStyle(as); // ESTABLEZCO EL ESTILO

                c = row.createCell(3); // LE ASIGNO EL NUMERO DE LA CELDA
                c.setCellValue(pg.getDescripcion()); // VALOR DE LA CELDA
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
