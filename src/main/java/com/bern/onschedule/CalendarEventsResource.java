/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bern.onschedule;

import com.bern.onschedule.calendarevents.entity.CalendarEvent;
import com.bern.onschedule.calendarevents.busniess.CalendarEventsManager;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author unknown
 */
@Stateless
@Path("events")
public class CalendarEventsResource {
    
    @Inject
    CalendarEventsManager manager;
    
    @GET
    public List<CalendarEvent> getAll(){
        return manager.getAll();
    }
    
    @GET
    @Path("date/{date}")
    public List<CalendarEvent> findEventsByDate(@PathParam("date") String date){
        return manager.getEventsByDate(date);
    }
    
    @GET
    @Path("date/{date}/time/{time}")
    public List<CalendarEvent> findEventsByDateAndTime(@PathParam("date") String date, @PathParam("time") int time){
        return manager.getEventsByDateAndTime(date,time);
    }
    
    @POST
    public Response createEvent(@Valid CalendarEvent event, @Context UriInfo uriInfo){
        CalendarEvent newEvent = this.manager.save(event);
        long newEventId = newEvent.getId();
        URI newUri = uriInfo.getAbsolutePathBuilder().path("/" + newEventId).build();
        return Response.created(newUri).build();
    }
    
    @Path("{id}")
    public CalendarEventResource find(@PathParam("id") long id){
        return new CalendarEventResource(id, manager);
    }
    
    
}
