/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        JSONArray expResult = null;
        JSONArray result = FetchData.accessJsonFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeJsonToFile method, of class FetchData.
     */
    @Test
    public void testWriteJsonToFile() throws Exception {
        System.out.println("writeJsonToFile");
        JSONArray jsonArray = null;
        FetchData.writeJsonToFile(jsonArray);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of apiRequest method, of class FetchData.
     */
    @Test
    public void testApiRequest() throws Exception {
        System.out.println("apiRequest");
        String startDate = "";
        String endDate = "";
        JSONArray expResult = null;
        JSONArray result = FetchData.apiRequest(startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class FetchData.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        FetchData.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
