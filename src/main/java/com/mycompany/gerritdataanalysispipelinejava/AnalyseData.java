/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.mycompany.gerritdataanalysispipelinejava.FetchData;

public class AnalyseData {
    
    public static void analyzeJsonData(JSONArray jsonObjArray){
        int nrOfOpenedReviews = 0;
         //String status;
        //JSONArray arrayData = jsonObjArray.get(0);
        if (jsonObjArray != null){
                System.out.println("\n"+ "size: " + jsonObjArray.size());


                for(int i = 0; i < jsonObjArray.size(); i++){
                    JSONObject objects = (JSONObject) jsonObjArray.get(i);
                    //for(int j = 0; j<objects.size(); j++){
                    //}
                    
                    String status = (String) objects.get("status");
                    
                    if(status != null){//lägg bara till om den är opened
                        System.out.println("\n"+ "status: " + status);
                        nrOfOpenedReviews++;
                    }
                }
                System.out.println("\n"+ "nr of reviews open: " + nrOfOpenedReviews);
                System.out.println("\n"+ "array: " + jsonObjArray);
            
        }else{
            System.out.println("The json file is empty or something went wrong, make sure there is data in the json file before you trying to read it");
        }
            
    }
}
