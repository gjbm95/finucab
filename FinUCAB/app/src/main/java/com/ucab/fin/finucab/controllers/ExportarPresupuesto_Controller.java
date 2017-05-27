package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

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


/**Modulo 3 - Modulo de Presupuestos
 *Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 *Esta clase se encarga del proceso de exportar la lista de presupuestos de un usuario
 **/

public class ExportarPresupuesto_Controller extends AsyncTask<String ,String, String> {


    public static ArrayList<Presupuesto> listaPresupuestos = new ArrayList<>();
    public static Activity activity;
    public static ResponseWebServiceInterface interfaz;


    /**
     * @param args
     * @return
     * Este método se encarga de crear el archivo .csv y .xls y luego generarlo
     **/
    protected String doInBackground(final String... args) {
        exportarCSV();
        exportarExcel();
        listaPresupuestos = new ArrayList<>();
        return "";
    }
    /**
     * Este método se encarga de crear el archivo .xls y luego generarlo
     **/

    public void exportarExcel(){
        File exportDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        String format = s.format(new Date());
        File fileEXCEL = new File(exportDir, "ExcelPresupuesto"+format+".xls");
        try {
            fileEXCEL.createNewFile();
            Workbook wb = new HSSFWorkbook();
            Cell c= null;

            CellStyle cs = wb.createCellStyle();
            CellStyle as = wb.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            as.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
            as.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            Sheet sheet1 = null;
            sheet1 = wb.createSheet("Mi Presupuesto");

            Row row0 = sheet1.createRow(0);

            c = row0.createCell(0);
            c.setCellValue("Nombre del Presupuesto");
            c.setCellStyle(cs);

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

            int count = listaPresupuestos.size();

            for(int contadorFilas=0 ; contadorFilas<count; contadorFilas++){
                Row row = sheet1.createRow(contadorFilas+1);

                Presupuesto p = listaPresupuestos.get(contadorFilas);

                c = row.createCell(0);
                c.setCellValue(p.get_nombre());
                c.setCellStyle(as);

                c = row.createCell(1);
                c.setCellValue(p.get_categoria());
                c.setCellStyle(as);

                c = row.createCell(2);
                c.setCellValue(p.get_monto());
                c.setCellStyle(as);

                c = row.createCell(3);
                c.setCellValue(p.get_clasificacion());
                c.setCellStyle(as);

                c = row.createCell(4);
                c.setCellValue(p.get_duracion());
                c.setCellStyle(as);

                c = row.createCell(5);
                c.setCellValue(p.get_tipo());
                c.setCellStyle(as);

            }

            FileOutputStream os = new FileOutputStream(fileEXCEL);
            wb.write(os);
            os.close();

        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage(), e);


        }
    }

    /**
     * Este método se encarga de crear el archivo .csv y luego generarlo
     **/

    public void exportarCSV(){
        File exportDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        String format = s.format(new Date());
        File fileCSV = new File(exportDir, "CSVPresupuesto"+format+".csv");

        try {
            fileCSV.createNewFile();
            FileWriter writerCSV = new FileWriter(fileCSV);

            writerCSV.write("Nombre del Presupuesto;Categoria;Monto;Clasificacion;Duracion;Tipo;\n");
            for(Presupuesto p : listaPresupuestos){
                writerCSV.write(p.get_nombre()+";"+p.get_categoria()+";"+p.get_monto().toString()+";"+p.get_clasificacion()
                        +";"+p.get_duracion().toString()+";"+p.get_tipo()+";"+"\n");

            }
            writerCSV.flush();
            writerCSV.close();

        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage(), e);

        }
    }



    /**
     *
     * @param actividad
     * Este método se encarga de hacer la llamada al web service y devolver todos los presupuestos
     * para el proceso de exportacion
     */
    public static void obtenerPresupuestos ( Activity actividad ) {

        System.out.println("ESTOY EN OBTENERPRESUPUESTO");
        Parametros.setMetodo("Modulo3/ListaPresupuestoExportar?idUsuario="+ ControlDatos.getUsuario().getUsuario());
        new Recepcion(actividad,interfaz).execute("GET");

    }

    /**
     * @return
     * Este método se encarga de llenar la lista de presupuestos que se utilizará para exportar
     */
    public static ArrayList<Presupuesto> utilizarPresupuesto(){
        System.out.println("Estoy en utilizarPresupuesto(): la respuesta es: "+Parametros.getRespuesta());
        JSONObject jObject = null;
        try {
            JSONArray mJsonArray = new JSONArray(Parametros.respuesta);
            int count = mJsonArray.length();

            for (int i = 0; i < count; i++) {   // iterate through jsonArray
                jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                Presupuesto pre = new Presupuesto();
                System.out.println("NOMbre en json: "+(String) jObject.get("Nombre"));
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
                ExportarPresupuesto_Controller.listaPresupuestos.add(pre);
            }
            for (Presupuesto p: ExportarPresupuesto_Controller.listaPresupuestos){
                System.out.println("Nombre: "+p.get_nombre());
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
