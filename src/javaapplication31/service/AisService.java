/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication31.service;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author jonat
 */
public class AisService {

    public static String makeRequest() {
        HttpURLConnection urlConnection;
        String url;
        String data ="nmea"+":"+"!AIVDM,1,1,,A,137l5P001uLLLbQfuB0A9hrh00Sj,0*73";
        String result = null;
        try {
            //Connect 
            urlConnection = (HttpURLConnection) ((new URL("http://ais.tbsalling.dk/decode").openConnection()));
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("nmea", "!AIVDM,1,1,,A,137l5P001uLLLbQfuB0A9hrh00Sj,0*73");
            urlConnection.setDoInput(true);
            urlConnection.connect();

            Gson jsonObject=new Gson();
            jsonObject.("nmea","!AIVDM,1,1,,A,137l5P001uLLLbQfuB0A9hrh00Sj,0*73");


            DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.write(jsonObject.toString().getBytes("UTF-8"));

            int code = urlConnection.getResponseCode();
            if (code == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuffer stringBuffer = new StringBuffer();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                object =  new JSONObject(stringBuffer.toString());
                //   array = new JSONArray(stringBuffer.toString());
                array = object.getJSONArray("response");

            }
//            //Write
//            OutputStream outputStream = urlConnection.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//            writer.write(data);
//            writer.close();
//            outputStream.close();
//
//            //Read
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
//
//            String line = null;
//            StringBuilder sb = new StringBuilder();
//
//            while ((line = bufferedReader.readLine()) != null) {
//                sb.append(line);
//            }
//
//            bufferedReader.close();
//            result = sb.toString();
//
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(makeRequest());
    }
}
