/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gerritdataanalysispipelinejava;

import java.io.IOException;
import static java.lang.Runtime.Version.parse;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    
    static StringBuilder accessAPI(URL url) throws MalformedURLException, IOException{
        url = new URL("https://android-review.googlesource.com/c/platform/libcore"); 
        StringBuilder informationString = new StringBuilder();
        
        try {
    
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Check if connection is made
            int connectionCode = urlConnection.getResponseCode();
            int connectionSucess = 200;
            

            if(connectionCode != connectionSucess){
                throw new RuntimeException("HttpResponceCode: " + connectionCode);
            } else{
                Scanner scanner = new Scanner(url.openStream());

                while(scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }
                //close scanner
                scanner.close();

                System.out.println(informationString);               
            }
            
        } catch(Exception e){
            System.out.println("Something went wrong");
        }
        return informationString;
    }
   
        static void saveToJson(StringBuilder informationString) throws ParseException{
            JSONParser parseToJson = new JSONParser();
            JSONArray objectToParse = (JSONArray) parseToJson.parse(String.valueOf(informationString));
        
            System.out.println(objectToParse.get(0));
        
            JSONObject countryData = (JSONObject) objectToParse.get(0);
        
            System.out.println(countryData.get("location_type"));
        }
        
    
    public static void main(String[] args) {
        
        System.out.println("Hello World!");    
    }
    
    
    
    
    
}
