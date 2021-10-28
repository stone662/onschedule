/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bern.onschedule;

import javax.ejb.Stateless;
import com.bern.onschedule.calendarevents.busniess.CalendarEventsManager;
import com.bern.onschedule.calendarevents.entity.CalendarEvent;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;
/**
 *
 * @author unknown
 */

public class CalendarEventResource {
    long id;
    CalendarEventsManager manager;

    public CalendarEventResource() {
    }

    public CalendarEventResource(long id, CalendarEventsManager manager) {
        this.id = id;
        this.manager = manager;
    }
    
    @GET
    public CalendarEvent getById(){
        System.out.println("Returned Event " + id);
        return this.manager.findById(id);
    }
    
    @DELETE
    public void delete(){
        this.manager.delete(id);
    }
    
    @PUT
    public CalendarEvent updateEventContent(CalendarEvent event){
        event.setId(id);
        return this.manager.save(event);
    }
    
    
    
    
}
