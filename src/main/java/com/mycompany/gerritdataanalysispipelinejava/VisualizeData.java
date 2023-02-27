/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author samme
 */
public class VisualizeData {
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
    
    
    
}
