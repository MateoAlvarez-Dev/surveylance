package com.riwi.surveylance.util.message;

public class ErrorMessages {
    
    public static String idNotFound(String entity){
       final String message = "No hay registros en la entidad %s con el id suministrado";
       return String.format(message, entity);
    }

}