package com.sofi.sofidemoproject.Services;

import com.sofi.sofidemoproject.Models.Data;
import com.sofi.sofidemoproject.Models.Gif;
import com.sofi.sofidemoproject.SofiDemoConfigProperties;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class GiphyService {

    private final SofiDemoConfigProperties sofiConfig;

    // Default controller with Configuration
    public GiphyService(SofiDemoConfigProperties sofiConfig) {
        this.sofiConfig = sofiConfig;
    }

    // Build up the Http connection
    private HttpURLConnection getConnection(URL inputURL) throws IOException {
        // Open the connection
        HttpURLConnection con = (HttpURLConnection) inputURL.openConnection();
        con.setRequestMethod("GET");
        con.addRequestProperty("Content-Type", "application/json");

        int status = con.getResponseCode();
        if(status<200 || status>=300) {
            throw new IOException("Error in reading data with status:"+status);
        }
        return con;
    }
    // Get search response
    public Data getSearchResponse(String searchKeyWord, int maxReturn) throws MalformedURLException, IOException, ProtocolException {

        System.out.printf(" URL: %s and maxReturn: %d%n", sofiConfig.urlSearch(), maxReturn);
        // Build search URL
        // Keyword must be cast to UTF_8
        searchKeyWord = URLEncoder.encode(searchKeyWord, StandardCharsets.UTF_8);
        URL urlSearch = new URL(sofiConfig.urlSearch()+"?api_key="+sofiConfig.key() + "&q="+searchKeyWord + "&limit="+maxReturn);

        // Build response string
        String response;
        StringBuilder stringBuilder = new StringBuilder();

        try{
            // Create connection
            HttpURLConnection connection = getConnection(urlSearch);
            // Use Buffered Reader to store the input InputStream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((response = bufferedReader.readLine())!=null) {
                stringBuilder.append(response);
            }
            bufferedReader.close();
            connection.disconnect();
        }catch (IOException ex){
            ex.printStackTrace();
        }


        // Parse response string to Json
        JsonObject jsonResponse = new Gson().fromJson(stringBuilder.toString(), JsonObject.class);
        // put it in Json array
        JsonArray data = jsonResponse.getAsJsonArray("data");

        Gif[] gifs = new Gif[data.size()];
        if(data.size()>=5){
            for (int i=0;i<data.size();i++){
                // Get the JsonObject from response data and parse to GifObject
                JsonObject result =  data.get(i).getAsJsonObject();
                gifs[i] = new Gif(result.get("id").getAsString(),result.get("url").getAsString());
            }
        }

        return new Data(gifs);
    }





}
