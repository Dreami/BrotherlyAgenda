/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brotherlyagendamaven;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private String id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private final static String DATE_PATTERN1 = "MM/dd/yyyy";
    private final static String DATE_PATTERN2 = "M/d/yyyy";
    
    public Task(String n, String d, String dd_text, String id) {
        this.id = id;
        this.name = n;
        this.description = d;
        this.dueDate = convertStringDateToLocalDate(dd_text);
    }
    
    public Task(String n, String d, String dd_text) {
        Random rand = new Random();
        this.id = n + rand.nextInt(90000) + 100;
        this.name = n;
        this.description = d;
        this.dueDate = convertStringDateToLocalDate(dd_text);
    }
    
    public Task(String n, String d, LocalDate dd) {
        Random rand = new Random();
        this.id = n + rand.nextInt(90000) + 100;
        this.name = n;
        this.description = d;
        this.dueDate = dd;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDateFromString(String dueDate) {
        this.dueDate = convertStringDateToLocalDate(dueDate);
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    private LocalDate convertStringDateToLocalDate(String dueDate) {
        LocalDate dd;
        Pattern p = Pattern.compile("([0-9]{2})/([0-9]{2})/([0-9]{4})");
        Matcher m = p.matcher(dueDate);
        
        if(m.matches()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN1);
            dd = LocalDate.parse(dueDate, formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN2);
            dd = LocalDate.parse(dueDate, formatter);
            
        }
        return dd;
    }
}
