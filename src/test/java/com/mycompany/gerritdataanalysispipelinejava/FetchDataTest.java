/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import static com.mycompany.gerritdataanalysispipelinejava.AnalyseData.analyzeJsonData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author samme
 */
public class FetchDataTest {

    public FetchDataTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of accessJsonFile method, of class FetchData.
     */
    @Test
    public void testAccessJsonFile() throws Exception {
        System.out.println("accessJsonFile");

        JSONArray result = FetchData.accessJsonFile();
        assertTrue(!result.isEmpty());
        assertTrue(result.get(0) != null);

    }

    /**
     * Test of writeJsonToFile method, of class FetchData.
     */
    @Test
    public void testWriteJsonToFile() throws Exception {
        System.out.println("writeJsonToFile");
        JSONObject object = new JSONObject();
        object.put("Kalle", 56);
        object.put("Kajsa", "married");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(object);
        assertTrue(!jsonArray.isEmpty());
        assertEquals(56, object.get("Kalle"));
        assertEquals("gravid", object.get("Kajsa"));

        assertEquals(object, jsonArray.get(0));

    }

    /**
     * Test of apiRequest method, of class FetchData.
     */
    @Test
    public void testApiRequest() throws Exception {
        System.out.println("apiRequest");
        String startDate = "2023-01-10";
        String endDate = "2023-01-29";
        JSONArray result = FetchData.apiRequest(startDate, endDate);
        assertEquals("2023-01-10", startDate);
        assertEquals("2023-01-29", endDate);

        assertTrue(startDate.length() == 10);
        assertTrue(endDate.length() == 10);

        assertTrue(result != null);
        assertTrue(startDate instanceof String);
        assertTrue(endDate instanceof String);
        assertTrue(!result.isEmpty());

    }

    /**
     * Test of main method, of class FetchData.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        FetchData.main(args);

    }

}
