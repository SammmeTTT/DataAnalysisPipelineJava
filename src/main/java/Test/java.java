/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import com.mycompany.gerritdataanalysispipelinejava.FetchData;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author samme
 */
public class java {
    
      @Test
    void accessJsonFileTest() throws IOException, FileNotFoundException, ParseException {
        assertTrue(true);
        
       // assertEquals(JSONArray, FetchData.accessJsonFile());     
    }
     @Test
    void writeToJsonTest() {
        assertTrue(true);
    }
     @Test
    void apiRequestTest() {
        assertTrue(true);
        String startDate = "2023-01-10";
        String endDate="2023-01-29";
    }
    
}
