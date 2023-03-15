/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.gerritdataanalysispipelinejava;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import static com.mycompany.gerritdataanalysispipelinejava.AnalyseData.analyzeJsonData;
import static com.mycompany.gerritdataanalysispipelinejava.VisualizeData.createLineGraph;
import static com.mycompany.gerritdataanalysispipelinejava.VisualizeData.frameToPdf;
import static com.mycompany.gerritdataanalysispipelinejava.VisualizeData.createTable;
//import static com.mycompany.gerritdataanalysispipelinejava.VisualizeData.graphToImage;
import java.awt.image.BufferedImage;

import java.lang.Object;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.Optional;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.ws.rs.core.UriBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FetchData {

    //trying to access Json file
    public static JSONArray accessJsonFile() throws FileNotFoundException, IOException, ParseException {
        //access json file in current directory
        FileReader readJsonFile;
        Object obj;
        JSONArray jsonObjArray = null;
        try {
            JSONParser parser = new JSONParser();
            readJsonFile = new FileReader("output.json"); //read from current directory
            obj = parser.parse(readJsonFile);
            jsonObjArray = (JSONArray) obj;
        } catch (IOException | ParseException e) {
            System.out.println("Couldn't access the json file");
        }
        return jsonObjArray;
    }

    //write jsondata to a json file
    public static void writeJsonToFile(JSONArray jsonArray) throws IOException {
        //write json array to a json file
        try (FileWriter writeToJson = new FileWriter("output.json")) {
            if (jsonArray != null) {
                writeToJson.write(jsonArray.toJSONString());
                writeToJson.close();
            } else {
                System.out.println("There are no data to write to the json file, look at the API request and see whats wrong and if you got nay data");
            }
        } catch (IOException e) {
            System.out.println("Couldn't write json string to json file");
        }
    }

    //sends api request and get a string response
    public static JSONArray apiRequest(String startDate, String endDate) throws IOException, InterruptedException, ParseException {

        String jsonData;
        String jsonResponseData = "";
        boolean sendRequest = true;
        HttpResponse<String> response = null;
        JSONParser parseToJson = new JSONParser();
        JSONArray arrayToParse = null;
        HttpRequest request = null;

        try {
            int start = 0;
            int limit = 2000;
            int counter = 0;

            while (sendRequest == true) {
                //creates url for request
                URI url = UriBuilder.fromUri("https://android-review.googlesource.com/changes/?q=after:" + startDate + "+before:" + endDate + "&start=" + start)
                        .build();

                //sends request and gets more than 2000 reviews
                HttpClient client = HttpClient.newHttpClient();

                request = HttpRequest.newBuilder()
                        .GET()
                        .header("accept", "application/json")
                        .uri(url)
                        .build();
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                jsonData = response.body();
                jsonResponseData = jsonData.substring(5, jsonData.length() - 1);

                if (counter == 0) {
                    arrayToParse = (JSONArray) parseToJson.parse(jsonResponseData);
                } else {
                    JSONArray tempArray = (JSONArray) parseToJson.parse(jsonResponseData);
                    arrayToParse.addAll(tempArray);
                }
                counter++;

                start = start + 2000;
                if (start == 20000) {
                    sendRequest = false;

                }
                int arraySize = arrayToParse.size();

                if (arraySize < start && arraySize > (start - 2000)) {
                    sendRequest = false;
                }
            }

        } catch (IOException | IllegalArgumentException | InterruptedException e) {
            System.out.println("Something is wrong with the request url, please change it and try again!");
        }

        return arrayToParse;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ParseException, FileNotFoundException, DocumentException {
        //Fix start and end Date here instead

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write a start date in format (2023-02-13): ");
        String tempDate = scanner.next();
        int tempDay = Integer.parseInt(tempDate.substring(9, 10));
        String correctDay = String.valueOf(tempDay - 1);
        String startDate = tempDate.replace(tempDate.substring(9, 10), correctDay);
        System.out.println("Write a end date in format (2023-02-15): ");
        String endDate = scanner.next();

        JSONArray array = apiRequest(startDate, endDate);
        //JSONArray array = toJsonArray(client);

        writeJsonToFile(array);
        JSONArray jsonData = accessJsonFile();
        String[] dataInput = {startDate, endDate};
        String[] pieChartData = analyzeJsonData(jsonData, dataInput);
        // String[] visualizationInput= {startDate, endDate, pieChartData[0],pieChartData[1]};
        //createTable(visualizationInput);
        JPanel graphPanel = createLineGraph(pieChartData);
        //  BufferedImage image = graphToImage(graphPanel);
        frameToPdf(graphPanel);
    }

}
