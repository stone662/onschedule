/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bern.onschedule.calendarevents.busniess;

import com.bern.onschedule.calendarevents.entity.CalendarEvent;
import com.bern.onschedule.calendarevents.entity.CalendarEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

/**
 *
 * @author unknown
 */
@Stateless
public class CalendarEventsManager {
    
    @PersistenceContext
    EntityManager em;
    
    public List<CalendarEvent> getAll(){
        return this.em.createNamedQuery(CalendarEvent.FIND_ALL, CalendarEvent.class).getResultList();
    }
    
    public CalendarEvent findById(long id){
        return this.em.find(CalendarEvent.class, id);
    }
    
    public CalendarEvent save(CalendarEvent newCalendarEvent){
        return this.em.merge(newCalendarEvent);
    }
    
    public void delete(long id){
        try{
            CalendarEvent reference = this.em.getReference(CalendarEvent.class, id);
            this.em.remove(reference);
             System.out.println("deleted = " + id);
        } catch(EntityNotFoundException e){
        }
    }

    public List<CalendarEvent> getEventsByDate(String date) {
        String formatedDate = formatDate(date);
        return this.em.createNamedQuery(CalendarEvent.FIND_BY_DATE, CalendarEvent.class).setParameter("date", formatedDate).getResultList();
    }
    
    public List<CalendarEvent> getEventsByDateAndTime(String date, int time){
        String formatedDate = formatDate(date);
        int lowerLimit = time - 30;
        int upperLimit = time + 30;
        
        return this.em.createNamedQuery(CalendarEvent.FIND_BY_DATE_AND_TIME, CalendarEvent.class)
                    .setParameter("date", formatedDate)
                    .setParameter("lowerTime", lowerLimit)
                    .setParameter("upperTime", upperLimit)
                    .getResultList();
    }
    
    private String formatDate(String date){
        return date.replace('-', '/');
    }
    
 
}
