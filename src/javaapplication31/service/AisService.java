package javaapplication31.service;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javaapplication31.model.Barco;
import org.apache.commons.io.IOUtils;

public class AisService {
    public Barco Post_JSON(String message) {
        Barco boat = new Barco();
        String query_url = "http://ais.tbsalling.dk/decode";
        String json = "{ \"nmea\" : \""+message+"\"}";
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            
            System.out.println(result);
            
            String[] props = result.split(",");
            if(props[9].equals(null)){
                props[9]="0";
            }
            System.out.println(props[9]);
            if(props[22].equals(null)){
                props[22]="0";
            }
            System.out.println(props[22]);
            if(props[27].equals(null)){
                props[27]="0";
            }
            System.out.println(props[27]);
            if(props[28].equals(null)){
                props[28]="0";
            }
            System.out.println(props[28]);
            if(props[30].equals(null)){
                props[30]="0";
            }
            System.out.println(props[30]);
            String msiCon = props[22];
            int indexIni = msiCon.indexOf("{");   
            int indexFin = msiCon.indexOf("}");
            String msi = msiCon.substring(indexIni + 1, indexFin);
            String barcoCompleto = "{"
                    + props[9] +","
                    + msi +","
                    + props[27] +","
                    + props[28] +","
                    + props[30] +"}";
            
            Gson gson = new Gson();
            boat = gson.fromJson(barcoCompleto, Barco.class);
            
            System.out.println(boat);
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return boat;
    }
}