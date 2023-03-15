/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.AnalyzeDataTest to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import com.mycompany.gerritdataanalysispipelinejava.FetchData;
import com.mycompany.gerritdataanalysispipelinejava.AnalyseData;
import static com.mycompany.gerritdataanalysispipelinejava.AnalyseData.analyzeJsonData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author samme
 */
public class AnalyzeDataTest {

    @Test
    public void testAnalyseData() throws Exception {
        System.out.println("accessJsonFile");

        String[] inputDates = {"2023-01-10", "2023-01-29"};

        String startDate = inputDates[0];
        String endDate = inputDates[1];
        String tempDate = endDate;

        JSONObject object = new JSONObject();
        object.put("status", "NEW");
        object.put("updated", "2023-01-10");

        JSONObject object1 = new JSONObject();
        object1.put("status", "NEW");
        object1.put("updated", "2023-01-12");

        JSONObject object2 = new JSONObject();
        object2.put("status", "MERGED");
        object2.put("updated", "2023-01-19");

        JSONObject object3 = new JSONObject();
        object3.put("status", "ABANDONED");
        object3.put("updated", "2023-01-16");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(object);
        jsonArray.add(object1);
        jsonArray.add(object2);
        jsonArray.add(object3);

        String[] output = analyzeJsonData(jsonArray, inputDates);

        //assertTrue();
        assertEquals("2023-01-10", startDate);
        assertEquals("2023-01-29", endDate);

        assertTrue(!jsonArray.isEmpty());
        assertTrue(object.get("status") == "NEW");
        assertTrue(object.get("updated") == "2023-01-10");

        assertTrue(jsonArray.get(0) != null);
        assertTrue(jsonArray.get(1) != null);
        assertTrue(jsonArray.get(2) != null);
        assertTrue(jsonArray.get(3) != null);
        System.out.println("length" + output.length);

        assertTrue(jsonArray.size() == 4);
        assertTrue(output.length == 12);

    }

}
