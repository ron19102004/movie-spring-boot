package com.movie.app.exceptions;

import org.hibernate.tool.schema.spi.ExceptionHandler;

public class ServiceException extends RuntimeException {
   public ServiceException(String message){
       super(message);
   }
}
