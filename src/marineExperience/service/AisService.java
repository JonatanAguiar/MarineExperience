package marineExperience.service;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import marineExperience.model.Barco;
import marineExperience.uteis.MapPoints;
import org.apache.commons.io.IOUtils;

public class AisService {

    MapPoints mapPoints;
    
    public AisService(MapPoints mapPoints) {
        this.mapPoints = mapPoints;
    }
    
    
    //requisição Post
    public void Post_JSON(String message) {

        new Thread(() -> {
            Barco boat = new Barco();
            String query_url = "http://ais.tbsalling.dk/decode";
            String json = "{ \"nmea\" : \"" + message + "\"}";
            String latidude, longitude, trueHeading;
            try {
                URL url = new URL(query_url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                //headers
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                
                OutputStream os = conn.getOutputStream();
                //manda string json para api
                os.write(json.getBytes("UTF-8"));
                os.close();
                
                // ler a resposta
                InputStream in = new BufferedInputStream(conn.getInputStream());
                String result = IOUtils.toString(in, "UTF-8");
                
                System.out.println(result);
                
                //se a resposta da api voltar vazia retorna pra fora
                if(result.equals("[]")){
                    return;
                }
                String[] props = result.split(",");
                //System.out.println(props[22]);
                
                System.out.println(props[30]);
                String msiCon = props[22];
                int indexIni = msiCon.indexOf("{");
                int indexFin = msiCon.indexOf("}");
                String msi = msiCon.substring(indexIni + 1, indexFin);
                latidude=props[27];
                if(props[27].contains("\"longitude\"")){
                    latidude = props[26];
                }
                longitude=props[28];
                if(props[28].contains("\"courseOverGround\"")){
                    longitude = props[27];
                }
                trueHeading=props[30];
                if(props[30].contains("\"second\"")){
                    trueHeading = props[29];
                }
                //
                if (props[27] == null) {
                    props[27] = "0";
                }
                System.out.println(props[27]);
                if (props[28] == null) {
                    props[28] = "0";
                }
                System.out.println(props[28]);
                if (props[30] == null) {
                    props[30] = "0";
                }
                
                String barcoCompleto = "{"
                        + props[9] + ","
                        + msi + ","
                        + latidude + ","
                        + longitude + ","
                        + trueHeading + "}";
                Gson gson = new Gson();
                boat = gson.fromJson(barcoCompleto, Barco.class);
                mapPoints.addWaypoint(boat);
                
                //System.out.println(boat);
                in.close();
                conn.disconnect();
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();

    }
}
