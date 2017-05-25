package com.ucab.fin.finucab.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanificacionFragment extends Fragment {


    FloatingActionButton fab;
    MainActivity parentActivity;
    RecyclerView recycle;

    public PlanificacionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_planificacion, container, false);
        parentActivity = (MainActivity) getActivity();



        parentActivity.getSupportActionBar().setTitle("Planificacion de pagos");

        recycle = (RecyclerView) rootView.findViewById(R.id.listaPlanificacion);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(myLayoutManager);

        fab = (FloatingActionButton) rootView.findViewById(R.id.addPlanificacionFloatingBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("curda","anis");

                String val = bundle.getString("curda");
                parentActivity.changeFragment(new PlanificacionFragment(), true);
            }

        });


        return rootView;
    }


    private void irTareaJson() {


       /* class HttpGetTask extends AsyncTask<String, Void, String> {


            private ProgressDialog status;
            private String response = "";
            //private Util util;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                status = new ProgressDialog(MainActivity.this);
                status.setMessage("Cargando...");
                status.setIndeterminate(false);
                status.setCancelable(false);
                status.show();
            }

            @Override
            protected String doInBackground(String... url) {

                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet get = new HttpGet(Util.GET_URL);
                    HttpResponse respuesta = client.execute(get);
                    HttpEntity entity = respuesta.getEntity();
                    StatusLine statusLine = respuesta.getStatusLine();
                    if (respuesta != null && statusLine.getStatusCode() == 200) {

                        Log.i("StatusLine", "" + statusLine.getStatusCode());
                        response = EntityUtils.toString(entity);
                    } else {
                        Log.e("Statusline", "Algo salio mal" + statusLine.getStatusCode());
                    }

                } catch (UnsupportedEncodingException e) {
                    Log.e("EncodingException", e.getMessage());
                } catch (ClientProtocolException e) {
                    Log.e("ClientProtocolException", e.getMessage());
                } catch (IOException e) {
                    Log.e("IOException", e.getMessage());
                }


                return response;


            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (this.status.isShowing()) {
                    this.status.dismiss();
                }

                if (!s.equals(null)) {
                    MainActivity.main = true;
                    convertToJson(s);
                }
            }



            /*private void volver() {
                Intent regresar = new Intent(getJsonActivity, MainActivity.class);
                getJsonActivity.startActivity(regresar);
            }
        }

        HttpGetTask gt = new HttpGetTask();
        gt.execute();*/}
    }




