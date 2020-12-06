package marineExperience.service;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import marineExperience.model.Ais;
import marineExperience.model.Barco;
import marineExperience.uteis.MapPoints;
import org.apache.commons.io.IOUtils;

public class AisService {

    private MapPoints mapPoints;
    private List<Ais> aiss;

    public AisService(MapPoints mapPoints, List<Ais> aiss) {
        this.mapPoints = mapPoints;
        this.aiss = aiss;
    }

    //requisição Post
    public void Post_JSON() {
        for (int i = 0; i < aiss.size(); i++) {
            Barco boat = new Barco();
            String query_url = "http://ais.tbsalling.dk/decode";
            String json = "{ \"nmea\" : \"" + aiss.get(i).getMsg() + "\"}";
            String radioCanal, latidude, longitude, trueHeading;
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
                if (result.equals("[]")) {
                    return;
                }
                String[] props = result.split(",");
                System.out.println(props.length);
                radioCanal = props[9];
                if (props[9].contains("\"checksum\"")) {
                    radioCanal = props[10];
                }
                System.out.println(radioCanal);
                String msiCon = props[22];
                System.out.println(msiCon);
                int indexIni = msiCon.indexOf("{");
                int indexFin = msiCon.indexOf("}");
                String msi = msiCon.substring(indexIni + 1, indexFin);
                latidude = props[27];
                if (props[27].contains("\"longitude\"")) {
                    latidude = props[26];
                }
                System.out.println(latidude);
                longitude = props[28];
                if (props[28].contains("\"courseOverGround\"")) {
                    longitude = props[27];
                }
                System.out.println(longitude);
                trueHeading = props[30];
                if (props[30].contains("\"second\"")) {
                    trueHeading = props[29];
                }
                if (latidude.contains("null")) {
                    return;
                }
                if (longitude.contains("null")) {
                    return;
                }
                if (trueHeading.contains("null")) {
                    return;
                }

                String barcoCompleto = "{"
                        + radioCanal + ","
                        + msi + ","
                        + latidude + ","
                        + longitude + ","
                        + trueHeading + "}";
                Gson gson = new Gson();
                boat = gson.fromJson(barcoCompleto, Barco.class);
                System.out.println(boat);
                mapPoints.addWaypoint(boat);
                in.close();
                conn.disconnect();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
