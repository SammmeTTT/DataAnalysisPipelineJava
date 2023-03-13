/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import com.mycompany.gerritdataanalysispipelinejava.PieGraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
//import javafx.embed.swing.SwingFXUtils;


public class VisualizeData{
    
    public static void createTable(String[] data){
//        int dataSize = data.length;
//        int indexRange = dataSize/3;
        String nrOpenReviews = data[0];
        String startDate = data[1];
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
    
    public static JFrame createLineGraph(String[] input){
        
        int dataSize = input.length;
        int index = dataSize/3;
        int indexDatesBound = index;
        
        int indexOpenLower = index;
        int indexOpenUpper = index*2;
        int indexClosedLower = indexOpenUpper;
        //int indexClosedUpper = dataSize;
        int counterOpen = 0;
        int counterClosed = 0;
        int counterDate = 0;
        
        String[] dates = new String[index];
        int[] openReviews = new int[index];
        int[] closedReviews = new int[index];
        
        String startYear = "";
        String endYear = "";
        String startMonth = "";
        String endMonth = "";


        for(int i = dataSize-1; i >= 0; i--){
            String elemAtIndex = input[i];
            if(indexClosedLower <= i){
                int nrOfClosed = Integer.parseInt(elemAtIndex);
                closedReviews[counterClosed] = nrOfClosed;
                counterClosed++;
            }
            else if(indexOpenLower <= i && i < indexClosedLower){
                int nrOfOpen = Integer.parseInt(elemAtIndex);
                openReviews[counterOpen] = nrOfOpen;
                counterOpen++;
            }
            else if(i>=0 && i<indexDatesBound){
                String day = elemAtIndex.substring(8, 10);
                if(i==indexDatesBound-1){
                    startYear = elemAtIndex.substring(0, 4);
                    startMonth = elemAtIndex.substring(5, 7);
                }else if(i==0){
                    endYear = elemAtIndex.substring(0, 4);
                    endMonth = elemAtIndex.substring(5, 7);
                }
                dates[counterDate] = day;
                counterDate++;
            }
                 
        }
        
        DefaultCategoryDataset dataOpened = new DefaultCategoryDataset();
        DefaultCategoryDataset dataClosed = new DefaultCategoryDataset();

    for (int j = 0; j < index; j++){
        dataOpened.setValue(openReviews[j], "Amount", dates[j]);
        dataClosed.setValue(closedReviews[j], "Amount", dates[j]);
    } 
 //Days in Month \n Start Date: "+ "startDate" + "and End Date: " + "endDate"
        JFreeChart graphOpen = ChartFactory.createLineChart("Code Review Graph","Start Year: "+startYear+" End Year: "+endYear+" Start Month: "+startMonth+" End Month: "+ endMonth +" Days in Month: (see graph)","Number of Opened Reviews",dataOpened,PlotOrientation.VERTICAL,false,true,false);
        JFreeChart graphClosed = ChartFactory.createLineChart("Code Review Graph","Start Year: "+startYear+" End Year: "+endYear+" Start Month: "+startMonth+" End Month: "+endMonth + " Days in Month: (see graph)","Number of Closed Reviews",dataClosed,PlotOrientation.VERTICAL,false,true,false);
        
        //Create plot
        CategoryPlot lineCategoryPlotOpen = graphOpen.getCategoryPlot();
        CategoryPlot lineCategoryPlotClosed = graphClosed.getCategoryPlot();
        
        lineCategoryPlotOpen.setBackgroundPaint(Color.white);
        lineCategoryPlotClosed.setBackgroundPaint(Color.white);

        //Adds costumization to the linegraph
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlotOpen.getRenderer();
        Color lineChartColor = new Color(0,100,200);
        lineRenderer.setSeriesPaint(0,lineChartColor);
        
        //Create new content frame
        JFrame frame = new JFrame("Analyzed Review Data Visualization");        
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
        //create panel to display graph
        JPanel mainPanel = new JPanel();
       
        ChartPanel graphPanelOpen = new ChartPanel(graphOpen);
        ChartPanel graphPanelClosed = new ChartPanel(graphClosed);
        
        graphPanelOpen.removeAll();
        graphPanelClosed.removeAll();
       // mainPanel.removeAll();
        //  graphPanelOpen.add(graphPanel,BorderLayout.CENTER);
        //  graphPanelClosed.add(graphPanelClosed,BorderLayout.EAST);  
        graphPanelOpen.validate();
        graphPanelClosed.validate();
        mainPanel.validate();
//        frame.add(new JScrollPane(graphPanelOpen));
//        frame.add(new JScrollPane(graphPanelClosed));
        mainPanel.add(graphPanelOpen, BorderLayout.NORTH);
        mainPanel.add(graphPanelClosed, BorderLayout.SOUTH);
        //frame.add(new ChartPanel(graphOpen), BorderLayout.EAST);
        //frame.add(new ChartPanel(graphClosed), BorderLayout.CENTER);

        frame.add(new JScrollPane(mainPanel));
        frame.pack();
        frame.setVisible(true); 
        
        return frame;
    }
    
//    public static void createPDF(JFrame graph){
//        JFileChooser pdfFile = new JFileChooser();
//        pdfFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        
//        int saveStatus = pdfFile.showSaveDialog(pdfFile);
//    
//        if(saveStatus == JFileChooser.APPROVE_OPTION){
//            path = pdfFile.getSelectedFile().getPath();
//        }
//        
//        
//        try{
//            Document doc = new Document();
//            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("gerritDataVisualization.pdf"));
//            doc.open();
//            PdfContentByte contentByte = writer.getDirectContent();
//            PdfTemplate template = contentByte.createTemplate(500, 500);
//            Graphics2D g2 = template.createGraphics(500, 500);
//            mainPanel.print(g2);
//            g2.dispose();
//            contentByte.addTemplate(template, 30, 800);
//            //Pdf pdfTable = new PdfTable(3); // ev vanlig table istället   
//        }
//        catch(Exception e){
//            System.out.println("");
//        }
//        
//        
//        //doc.add(new Paragraph("Hello this is a pdf file from project tabelle"));
//       
//        if(doc.isOpen()){
//            doc.close();
//            }
//        
//    }
    
    
    public static void frameToPdf(JFrame graph){
        String file = "gerritDataVisualization.pdf";
        try{
            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file));
            doc.open();
            
            PdfContentByte bytes = writer.getDirectContent();
            PdfTemplate pdfTemplate =bytes.createTemplate(PageSize.A4.getWidth(),PageSize.A4.getHeight()); 
            bytes.addTemplate(pdfTemplate,0,0);
            
            Graphics2D graphics = pdfTemplate.createGraphics(PageSize.A4.getWidth(),PageSize.A4.getHeight());
            graphics.scale(0.4, 0.4);
            
            for(int i = 0; i< graph.getContentPane().getComponents().length; i++){
                Component comp = graph.getContentPane().getComponent(i);
                if(comp instanceof JLabel || comp instanceof JScrollPane){
                    graphics.translate(comp.getBounds().x, comp.getBounds().y);
                    if(comp instanceof JScrollPane){
                    comp.setBounds(0, 0, (int)PageSize.A4.getWidth()*2,(int)PageSize.A4.getHeight()*2);
                    }
                    comp.paintAll(graphics);
                    comp.addNotify();
                }
            }
            graphics.dispose();
            doc.close();
        } catch(Exception e){
            System.out.println("Error to create PDF: " + e.toString());
        }
    
    }
    
 public static void main(String[] args) throws Exception{
     String[] tableData = {"2023-02-10","2023-02-13","200","1500"};
     //createTable(tableData);
     //createPieChart(tableData);
     createLineGraph(tableData);
     //createPDF();
     //Stage stage = new Stage();
     //PieGraph.start(stage);
     
 }   

 
 
 
 
 
}
