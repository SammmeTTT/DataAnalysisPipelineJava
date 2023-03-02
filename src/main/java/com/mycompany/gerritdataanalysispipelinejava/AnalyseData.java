/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.mycompany.gerritdataanalysispipelinejava.FetchData;

public class AnalyseData {
    
    public static String[] analyzeJsonData(JSONArray jsonObjArray){
        int totalNrOfReviews = jsonObjArray.size();
        int nrOfOpenReviews = 0;
        int nrOfClosedReviews = 0;
        
         //String status;
        //JSONArray arrayData = jsonObjArray.get(0);
        if (jsonObjArray != null){
                System.out.println("\n"+ "size: " + jsonObjArray.size());

                for(int i = 0; i < jsonObjArray.size(); i++){
                    JSONObject objects = (JSONObject) jsonObjArray.get(i);
                    
                    String status = (String) objects.get("status");
                   
                    if(status.equals("MERGED") || status.equals("ABANDONED")){//lägg bara till om den är opened
                        nrOfClosedReviews++;
                    }
                    else{
                        nrOfOpenReviews++;
                    }
                }
                System.out.println("\n"+ "nr of reviews open: " + nrOfOpenReviews);
                System.out.println("\n"+ "nr of reviews closed: " + nrOfClosedReviews);
                System.out.println("\n"+ "array: " + jsonObjArray);
            
        }else{
            System.out.println("The json file is empty or something went wrong, make sure there is data in the json file before you trying to read it");
        }
        String strOpenReviews = String.valueOf(nrOfOpenReviews);
        String strClosedReviews = String.valueOf(nrOfClosedReviews);

        String[] output = {"2023-01-10","2023-02-10",strOpenReviews, strClosedReviews};    
        return output;
    }
    
    
    
    
    
}
