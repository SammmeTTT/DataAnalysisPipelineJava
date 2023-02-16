/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerritdataanalysispipelinejava;

/**
 *
 * @author samme
 */
public class JsonContent {
    private int id;
    private int userId;
    private String title;
    private String body;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

     public String getBody() {
        return body;
    }
     
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "JsonContent{" + "id=" + id + ", title=" + title + '}';
    }
 
}
