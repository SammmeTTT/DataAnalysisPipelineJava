/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.gerritdataanalysispipelinejava;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mycompany.gerritdataanalysispipelinejava.JsonContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Runtime.Version.parse;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static org.json.simple.JSONValue.parse;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author samme
 */
public class FetchData {

    //trying to accessJson file
    public static void accessJsonFile(){
        String path = System.getProperty("." + File.separator + "output.json");
        System.out.println("Successfully get file: " + path);
    }
    
    //write jsondata to a json file
    public static void writeJsonToFile(JSONObject jsonObject) throws IOException{
        try (FileWriter jsonFile = new FileWriter("output.json")) {//GerritDataAnalysisPipelineJava/scr/main/java/com/mycompany/gerritdataanalysispipelinejava/
            jsonFile.write(jsonObject.toJSONString());
            jsonFile.close();
        }
        catch(IOException e){
            System.out.println("Couldn't write json string to json file");
        }
        accessJsonFile();
    }
    
    
    //sends api request and get a string response
    public static String apiRequest() throws IOException, InterruptedException {
        String requestUrl = "https://android-review.googlesource.com/changes/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(requestUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        String jsonData = response.body();
        jsonData = jsonData.substring(4, jsonData.length()-1);
        //jsonData = gson.fronJson(response.body(), JsonContent.class); // import gson
        
        //json string to json objects    
        //ObjectMapper mapper = new ObjectMapper();
        //List<JsonContent> jsonContent = mapper.readValue(jsonData, new TypeReference < List < JsonContent >> ()  {});
        //jsonContent.forEach(x -> System.out.println(x));
        return jsonData;
    }

    //makes an jsonobject of the request string
    static JSONObject toJsonObject(String informationString) throws ParseException {
        JSONParser parseToJson = new JSONParser();
        JSONArray objectToParse = (JSONArray) parseToJson.parse(String.valueOf(informationString));
        JSONObject jsonData = (JSONObject) objectToParse.get(0);
        return jsonData;
        //System.out.println(countryData.get("_number"));
    }

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        String client = apiRequest();
        JSONObject object = toJsonObject(client);
        writeJsonToFile(object);

    }

}
