/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VisualizeData{
    
    public static void createTable(String[] data){
        String startDate = data[0];
        String endDate = data[1];
        String numberOfOpenReviews = data[2];
        
        
        String[][] tableContent={
            {startDate,endDate,numberOfOpenReviews}
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
    
    public static void createPieChart(){
        
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
           new PieChart.Data("",0),
           new PieChart.Data("",0),
           new PieChart.Data("",0),
           new PieChart.Data("",0),
           new PieChart.Data("",0),
           new PieChart.Data("",0)

        );
        
        PieChart pieChart = new PieChart(pieData);
        pieChart.setTitle("PieChart");
                
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
     String[] tableData = {"2023-02-10","2023-02-13","1500"};
     createTable(tableData);
 
 }   
}
