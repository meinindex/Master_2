package com.techacademy.reversegeocoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.CoderMalfunctionError;

/**
 * Created by Claudi on 27.12.2016.
 */

public class HttpDataHandler {

    public HttpDataHandler()
    {

    }

    public String GetHTTPData(String requestUrl)
    {
        URL url;
        String response = "";
        try {
            url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-uncoded");
            conn.setDoInput(true);

            int responseCode = conn.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (( line = br.readLine()) != null)
                {
                    response +=line;
                }
            }
            else response = "";

        }
        catch(ProtocolException e)
        {
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return  response;
    }
}
