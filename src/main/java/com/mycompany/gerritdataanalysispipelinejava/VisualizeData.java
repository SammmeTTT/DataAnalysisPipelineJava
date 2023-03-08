/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import com.mycompany.gerritdataanalysispipelinejava.PieGraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Color;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
//import javafx.embed.swing.SwingFXUtils;


public class VisualizeData{
    
    public static void createTable(String[] data){
        String startDate = data[0];
        String endDate = data[1];
        String numberOfOpenReviews = data[2];
        String numberOfClosedReviews = data[3];

        
        
        String[][] tableContent={
            {startDate,endDate, numberOfOpenReviews, numberOfClosedReviews}
        };
        
        String[] headers = {"Start Date", "End Date", "Number of Open Reviews", "Number of Closed Reviews"};

        JTable dataTable= new JTable(tableContent,headers);
            
        JFrame frame = new JFrame("Analyzed Review Data Visualization");
        frame.add(new JScrollPane(dataTable));
        
        
        //frame.add(JFreeChart);
        
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);       
        
    }
    
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
        
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
           new PieChart.Data("Closed Reviews",numberOfClosedReviews),
           new PieChart.Data("Open Reviews",numberOfOpenReviews)          


        );
        
        PieChart pieChart; //something crashes wrongly initialized
        pieChart = new PieChart(pieData);
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
    
    public static void createLineGraph(String[] input){
        
        //int nrOfOpenReviews = input[0];
        //int nrOfClosedReviews = input[1];
        
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        data.setValue(23, "Amount", "One");
        data.setValue(75, "Amount", "Two");
        data.setValue(50, "Amount", "Three");
        data.setValue(86, "Amount", "Four");
        data.setValue(45, "Amount", "Five");
        data.setValue(89, "Amount", "Six");
        data.setValue(75, "Amount", "Seven");
        data.setValue(204, "Amount", "Eigh");
        data.setValue(151, "Amount", "Nine");
        data.setValue(350, "Amount", "Ten");
        data.setValue(300, "Amount", "Eleven");
        data.setValue(450, "Amount", "Twelve");
        data.setValue(400, "Amount", "Thirteen");
        data.setValue(600, "Amount", "Forteen");
        data.setValue(1000, "Amount", "Fifteen");
        data.setValue(800, "Amount", "Seventeen");
        data.setValue(2000, "Amount", "Eighteen");
        data.setValue(1500, "Amount", "Nineteen");
        data.setValue(2500, "Amount", "Twenty");
        data.setValue(2000, "Amount", "TwentyOne");
        data.setValue(2250, "Amount", "TwentyTwo");
        data.setValue(2500, "Amount", "Twentythree");
        data.setValue(3000, "Amount", "TwentyFour");
        data.setValue(2750, "Amount", "TwentyFive");
        data.setValue(2300, "Amount", "TwentySix");
        data.setValue(2000, "Amount", "TwentySeven");
        data.setValue(2750, "Amount", "TwentyEight");
        data.setValue(2500, "Amount", "TwentyNine");
        data.setValue(4750, "Amount", "Thirty");
        data.setValue(4700, "Amount", "ThirtyOne");
      

        
        
        JFreeChart graph = ChartFactory.createLineChart("Code Review Graph","Days in Month","Number of Reviews",data,PlotOrientation.VERTICAL,false,true,false);
        CategoryPlot lineCategoryPlot = graph.getCategoryPlot();
        
        lineCategoryPlot.setBackgroundPaint(Color.white);
        
        JFrame frame = new JFrame("Analyzed Review Data Visualization");

        //frame.add(JFreeChart);
        
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
        //create panel to display graph
        ChartPanel graphPanel = new ChartPanel(graph);
        graphPanel.removeAll();
        //graphPanel.add(graphPanel,BorderLayout.CENTER);
        graphPanel.validate(); 
        frame.add(new JScrollPane(graphPanel));
        frame.setVisible(true); 
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
    
    
 public static void main(String[] args) throws Exception{
     String[] tableData = {"2023-02-10","2023-02-13","200","1500"};
     //createTable(tableData);
     //createPieChart(tableData);
     createLineGraph(tableData);
     //Stage stage = new Stage();
     //PieGraph.start(stage);
     
 }   

 
 
 
 
 
}
