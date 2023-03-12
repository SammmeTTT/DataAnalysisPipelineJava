/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.mycompany.gerritdataanalysispipelinejava.FetchData;
import java.util.ArrayList;
import java.util.List;

public class AnalyseData {
    
    public static String[] analyzeJsonData(JSONArray jsonObjArray, String[] inputDates){
        int totalNrOfReviews = jsonObjArray.size();
        int nrOfOpenReviews = 0;
        int nrOfClosedReviews = 0;
        int datesOpenPerDay = 0;
        int datesClosedPerDay = 0;
        
        
        List<String> dateArray = new ArrayList<String>();
        List<String> dateOpenArray = new ArrayList<String>();
        List<String> dateClosedArray = new ArrayList<String>();
        
        String startDate = inputDates[0];
        String endDate = inputDates[1];
        String tempDate = endDate;
        
//        String[] myArray = new String[a.size()];
//        a.toArray(myArray);
        
        //String status;

        if (jsonObjArray != null){
                
                for(int i = 0; i < jsonObjArray.size(); i++){
                    JSONObject objects = (JSONObject) jsonObjArray.get(i);
                    
                    String status = (String) objects.get("status");
                    String updateDates = (String) objects.get("updated");
                    String dates = updateDates.substring(0, 10);
                    
                   if(dates.equals(tempDate)){
                        
                        if(status.equals("MERGED") || status.equals("ABANDONED")){//lägg bara till om den är opened
                            nrOfClosedReviews++;
                            datesClosedPerDay++;
                        }
                        else{
                            nrOfOpenReviews++;
                            datesOpenPerDay++;
                        }
                        //dates and opened, and dates and closed in an other array
                   }
                   else{
                        String tempClosedPerDay = Integer.toString(datesClosedPerDay);
                        dateClosedArray.add(tempClosedPerDay);
                        datesClosedPerDay = 0; //resetting because its a new date
                        
                        String tempOpenPerDay = Integer.toString(datesOpenPerDay);
                        dateOpenArray.add(tempOpenPerDay);
                        datesOpenPerDay = 0; //resetting because its a new date
                        dateArray.add(tempDate);
                        tempDate = dates;
                    
                        if(status.equals("MERGED") || status.equals("ABANDONED")){//lägg bara till om den är opened
                            datesClosedPerDay++; //adding 1 for the new date
                        }
                        else{
                            
                            datesOpenPerDay++; //adding 1 for the new date
                        }
                   }
                   
                }
                System.out.println("\n"+ "Total Number of Reviews " + totalNrOfReviews);
                System.out.println("\n"+ "nr of reviews open: " + nrOfOpenReviews);
                System.out.println("\n"+ "nr of reviews closed: " + nrOfClosedReviews);
                System.out.println("\n"+ "array: " + jsonObjArray);
                System.out.println("date array = " + dateArray);
                System.out.println("date closed array = " + dateClosedArray);                
                System.out.println("date open array = " + dateOpenArray);
        }else{
            System.out.println("The json file is empty or something went wrong, make sure there is data in the json file before you trying to read it");
        }
        String strOpenReviews = String.valueOf(nrOfOpenReviews);
        String strClosedReviews = String.valueOf(nrOfClosedReviews);

        //String[] output = {strOpenReviews, strClosedReviews}; 
        dateArray.addAll(dateOpenArray);
        dateArray.addAll(dateClosedArray);
        
        System.out.println("dates and closed and then opened: " + dateArray);
        
        String[] output = new String[dateArray.size()];
        dateArray.toArray(output);
        
        return output;
    }
   
}
