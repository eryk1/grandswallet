package yastas.gentera.hackathon;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundTaskRegistro extends AsyncTask<String, Void, String> {
    private String registroPaso2 = "https://schema.getpostman.com/json/collection/v2.1.0/collection.json";
    Context contexto;
    Activity activity;

    public BackgroundTaskRegistro(Context contexto) {
        this.contexto = contexto;
        activity = (Activity)contexto;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        if(method.equals("registro_comercio")){
            try {
                URL url = new URL(registroPaso2);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String primer_nombre = params[1];
                String segundo_nombre = params[2];
                String apellido_paterno = params[3];
                String apellido_materno = params[4];
                String birthdate = params[5];
                String curp = params[6];
                String telephone = params[7];
                String email = params[8];
                String codigo_postal = params[9];
                String estado = params[10];
                String delegacion_municipio = params[11];
                String colonia = params[12];
                String calle = params[13];
                String numero_exterior = params[14];
                String numero_interior = params[15];
                String foto_perfil = params[16];
                String foto_ine = params[17];
                String clave_elector = params[18];
                String password = params[19];

                String data = URLEncoder.encode("primer_nombre", "UTF-8")+"="+URLEncoder.encode(primer_nombre, "UTF-8")+"&"+
                        URLEncoder.encode("segundo_nombre", "UTF-8")+"="+URLEncoder.encode(segundo_nombre, "UTF-8")+"&"+
                        URLEncoder.encode("apellido_paterno", "UTF-8")+"="+URLEncoder.encode(apellido_paterno, "UTF-8")+"&"+
                        URLEncoder.encode("apellido_materno", "UTF-8")+"="+URLEncoder.encode(apellido_materno, "UTF-8")+"&"+
                        URLEncoder.encode("birthdate", "UTF-8")+"="+URLEncoder.encode(birthdate, "UTF-8")+"&"+
                        URLEncoder.encode("curp", "UTF-8")+"="+URLEncoder.encode(curp, "UTF-8")+"&"+
                        URLEncoder.encode("telephone", "UTF-8")+"="+URLEncoder.encode(telephone, "UTF-8")+"&"+
                        URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"+
                        URLEncoder.encode("codigo_postal", "UTF-8")+"="+URLEncoder.encode(codigo_postal, "UTF-8")+"&"+
                        URLEncoder.encode("estado", "UTF-8")+"="+URLEncoder.encode(estado, "UTF-8")+"&"+
                        URLEncoder.encode("delegacion_municipio", "UTF-8")+"="+URLEncoder.encode(delegacion_municipio, "UTF-8")+"&"+
                        URLEncoder.encode("colonia", "UTF-8")+"="+URLEncoder.encode(colonia, "UTF-8")+"&"+
                        URLEncoder.encode("calle", "UTF-8")+"="+URLEncoder.encode(calle, "UTF-8")+"&"+
                        URLEncoder.encode("numero_exterior", "UTF-8")+"="+URLEncoder.encode(numero_exterior, "UTF-8")+"&"+
                        URLEncoder.encode("numero_interior", "UTF-8")+"="+URLEncoder.encode(numero_interior, "UTF-8")+"&"+
                        URLEncoder.encode("foto_perfil", "UTF-8")+"="+URLEncoder.encode(foto_perfil, "UTF-8")+"&"+
                        URLEncoder.encode("foto_ine", "UTF-8")+"="+URLEncoder.encode(foto_ine, "UTF-8")+"&"+
                        URLEncoder.encode("clave_elector", "UTF-8")+"="+URLEncoder.encode(clave_elector, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line=bufferedReader.readLine()) != null){
                    stringBuilder.append(line+"\n");
                }
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject JO = jsonArray.getJSONObject(0);
            String code = JO.getString("code");
            String message = JO.getString("message");
            if(code.equals("reg_true")){
                showDialog("Registro exitoso", message, code);
            } else if (code.equals("reg_false")){
                showDialog("Error en el registro", message, code);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showDialog(String title, String message, String code){

    }
}