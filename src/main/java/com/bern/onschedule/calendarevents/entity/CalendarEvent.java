/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bern.onschedule.calendarevents.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author unknown
 */
@Entity
@NamedQuery(name = CalendarEvent.FIND_ALL, query = "SELECT t FROM CalendarEvent t")
@NamedQuery(name = CalendarEvent.FIND_BY_DATE, query = "SELECT t FROM CalendarEvent t WHERE t.date LIKE :date")
@NamedQuery(name = CalendarEvent.FIND_BY_DATE_AND_TIME, query = "SELECT t FROM CalendarEvent t WHERE t.date LIKE :date AND (t.time BETWEEN :lowerTime AND :upperTime)")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CalendarEvent {

    public static final String PREFIX = "calendarevents.entity.CalendarEvent.";
    public static final String FIND_ALL = PREFIX + "findAll";
    public static final String FIND_BY_DATE = PREFIX + "findByDate";
    public static final String FIND_BY_DATE_AND_TIME = PREFIX + "findByDateAndTime";

    @Id
    @GeneratedValue
    private long id;
   
    @NotNull
    @Size(min = 6, max = 10)
    private String date;
    
    @NotNull
    @Size(min = 2, max = 350)
    private String description;
    
    @NotNull
    @Max(2359)
    private int time;
    
    @Version
    private int version;
    
    private String location;
    
    

    public CalendarEvent() {
    }

    public CalendarEvent(String date, String description, String location, int time) {
        this.date = date;
        this.description = description;
        this.location = location;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public int getTime() {
        return time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
