/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.imageio.ImageIO;
//import javafx.embed.swing.SwingFXUtils;


public class VisualizeData{
    
    public static void createTable(String[] data){
        String startDate = data[0];
        String endDate = data[1];
        String numberOfOpenReviews = data[2];
        
        
        String[][] tableContent={
            {startDate,endDate, numberOfOpenReviews}
        };
        
        String[] headers = {"Start Date", "End Date", "Number of Open Reviews"};

        JTable dataTable= new JTable(tableContent,headers);
            
        JFrame frame = new JFrame("Analyzed Review Data Visualization");
        frame.add(new JScrollPane(dataTable));
        
        
        //frame.add(JFreeChart);
        
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);       
        
    }
    
    public static void createPieChart(String[] pieChartData){
        Stage primaryStage = null;//probably this causing the error, Stage should be initiated
        String startDate = pieChartData[0];
        String endDate = pieChartData[1];
        String stringOfOpenReviews = pieChartData[2];
        String stringOfClosedReviews = pieChartData[3];
        
        double numberOfOpenReviews= Double.parseDouble(stringOfOpenReviews);
        double numberOfClosedReviews= Double.parseDouble(stringOfClosedReviews);
        System.out.println(numberOfOpenReviews);
        System.out.println(numberOfClosedReviews);
        
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
           new PieChart.Data("Closed Reviews",numberOfClosedReviews),
           new PieChart.Data("Open Reviews",numberOfOpenReviews)
//           new PieChart.Data("",0),
//           new PieChart.Data("",0),


        );
        
        PieChart pieChart = new PieChart(pieData); //something crashes wrongly initialized
        pieChart.setTitle("PieChart of Opened and Closed data between: " + startDate + " and " + endDate);
                
        Group baseGroup = new Group(pieChart);
        Scene scene = new Scene(baseGroup,600,400);
        
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
//        
        
    } 
    
    public static void createPDF(){
        JFileChooser pdfFile = new JFileChooser();
        pdfFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int saveStatus = pdfFile.showSaveDialog(pdfFile);
        String path = "";
    
        if(saveStatus == JFileChooser.APPROVE_OPTION){
            path = pdfFile.getSelectedFile().getPath();
        }
        
        Document doc = new Document();
        try{
            PdfWriter.getInstance(doc, new FileOutputStream(path+"gerritDataVisualization.pdf"));
            doc.open();
            
            //Pdf pdfTable = new PdfTable(3); // ev vanlig table istället
            
            
            
            
        }
        catch(Exception e){
            System.out.println("");
        }
        
    }
    
    
 public static void main(String[] args){
     //String[] tableData = {"2023-02-10","2023-02-13","1500"};
     //createTable(tableData);
     
     
     
 }   

 
 
 
 
 
}
