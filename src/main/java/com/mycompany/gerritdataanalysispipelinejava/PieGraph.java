/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import static com.mycompany.gerritdataanalysispipelinejava.VisualizeData.createPieChart;
import static com.mycompany.gerritdataanalysispipelinejava.VisualizeData.createTable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author samme
 */
public class PieGraph extends Application{
    public static void createPieChart(String[] pieChartData){
        //Stage primaryStage = new Stage();//probably this causing the error, Stage should be initiated
        String startDate = pieChartData[0];
        String endDate = pieChartData[1];
        String stringOfOpenReviews = pieChartData[2];
        String stringOfClosedReviews = pieChartData[3];
        
        double numberOfOpenReviews= Double.parseDouble(stringOfOpenReviews);
        double numberOfClosedReviews= Double.parseDouble(stringOfClosedReviews);
        System.out.println(numberOfOpenReviews);
        System.out.println(numberOfClosedReviews);
        
        ObservableList<javafx.scene.chart.PieChart.Data> pieData = FXCollections.observableArrayList(
           new javafx.scene.chart.PieChart.Data("Closed Reviews",numberOfClosedReviews),
           new javafx.scene.chart.PieChart.Data("Open Reviews",numberOfOpenReviews)
//           new PieChart.Data("",0),
//           new PieChart.Data("",0),


        );
        
        javafx.scene.chart.PieChart pieChart; //something crashes wrongly initialized
        pieChart = new javafx.scene.chart.PieChart(pieData);
        pieChart.setTitle("PieChart of Opened and Closed data between: " + startDate + " and " + endDate);
                
        Group baseGroup = new Group(pieChart);
        Scene scene = new Scene(baseGroup,600,400);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Gerrit Analysed Data");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        
//        WritableImage pieChartImage= scene.snapshot(null);
//        File file = new File("PieChart.png");
//        try{
//            ImageIO.writeSwingFXUtils.fromFXImage(pieChartImage,null), "png", file);2
//        
//        }
//        catch(Exception e){
//        }
//        
//        PDDocument doc = new PDDocument();
//        PDPage page = new PDPage();
 
    } 

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //Stage primaryStage = new Stage();//probably this causing the error, Stage should be initiated
        String[] pieChartData = {"2023-01-10", "2023-01-29", "1295", "16169"};
        String startDate = pieChartData[0];
        String endDate = pieChartData[1];
        String stringOfOpenReviews = pieChartData[2];
        String stringOfClosedReviews = pieChartData[3];
        
        double numberOfOpenReviews= Double.parseDouble(stringOfOpenReviews);
        double numberOfClosedReviews= Double.parseDouble(stringOfClosedReviews);
        //System.out.println(numberOfOpenReviews);
        //System.out.println(numberOfClosedReviews);
        
        ObservableList<javafx.scene.chart.PieChart.Data> pieData = FXCollections.observableArrayList(
           new javafx.scene.chart.PieChart.Data("Closed Reviews",numberOfClosedReviews),
           new javafx.scene.chart.PieChart.Data("Open Reviews",numberOfOpenReviews)
//           new PieChart.Data("",0),
//           new PieChart.Data("",0),


        );
        
        javafx.scene.chart.PieChart pieChart; //something crashes wrongly initialized
        pieChart = new javafx.scene.chart.PieChart(pieData);
        pieChart.setTitle("PieChart of Opened and Closed data between: " + startDate + " and " + endDate);
                
        Group baseGroup = new Group(pieChart);
        Scene scene = new Scene(baseGroup,600,400);
        
        primaryStage.setTitle("Gerrit Analysed Data");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void main(String[] args) throws Exception{
     //String[] tableData = {"2023-02-10","2023-02-13","200","1500"};
     //createTable(tableData);
     //createPieChart(tableData);
     Stage stage = new Stage();
     start(stage);
     
 }   
}
