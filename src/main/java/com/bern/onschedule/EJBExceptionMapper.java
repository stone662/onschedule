/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bern.onschedule;

import javax.ejb.EJBException;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author unknown
 */
@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(EJBException e) {
        Throwable cause = e.getCause();
        if(cause instanceof OptimisticLockException){
            return Response.status(Response.Status.CONFLICT).header("cause", "conflict occured: " + cause).build();
        }
        else{
            return Response.serverError().header("cause","Unknown Error " + cause).build();
        }
    }
    
}
