package com.ucab.fin.finucab.webservice;

/**
 * Created by Junior on 17/05/2017.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Recepcion extends AsyncTask<String,Void,String>{
    private Activity getJSONactivity;
    private ProgressDialog status;
    private static String response="";

    public Recepcion(Activity principalMJson) {
        this.getJSONactivity=principalMJson;

    }

    /**
     * Metodo que se encarga de mostrar un feedback del proceso de carga de datos al usuario
     */
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        status= new ProgressDialog(getJSONactivity); //Creo el process dialog
        status.setMessage("Cargando...");
        status.setIndeterminate(false);
        status.setCancelable(false);
        status.show();
    }

    /**
     * Metodo que se encarga de realizar la consula al servicio web y obtener la data necesaria
     * en forma de objeto Json.
     * @param url
     * @return
     */

    @Override
    protected String doInBackground(String... url) {

        try{
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url[0]); //Consulto mediante HTTP
            HttpResponse respuesta = client.execute(get); // obtengo la informacion
            HttpEntity entity = respuesta.getEntity();
            StatusLine statusline = respuesta.getStatusLine();
            if ((respuesta != null) &(statusline.getStatusCode()==200))
            {
                Log.i("Statusline",""+statusline.getStatusCode());
                response = EntityUtils.toString(entity);

            }else
            {
                Log.i("Statusline","Algo salio mal "+ statusline.getStatusCode());
            }
        }catch(UnsupportedEncodingException e){
            Log.e("UnsupportedEException",e.getMessage());

        }catch(ClientProtocolException e){
            Log.e("ClientProtocolException",e.getMessage());
        }catch(IOException e){
            Log.e("IOException",e.getMessage());
        }


        return null;
    }


    /**
     * Este metodo se encarga de devolver en formato de cadena de caracteres, la informacion que
     * devuelve el servicio web, con el fin de que pueda ser convertido en objeto JSON
     * y pueda consumirse la informacion.
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (this.status.isShowing()){
            this.status.dismiss();
        }
        if(!s.equals(null))
        {
            convertJson(s);
        }
    }

    private void convertJson(String s){
        Parametros.setRespuesta(s);
    }




}
