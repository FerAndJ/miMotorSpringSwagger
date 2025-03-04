package com.projectoFinalMotorsport.demo.utils;

import lombok.Data;

@Data
public class ApiResponse {

    private String status;
    private Object respuesta;
    private String error;

    public ApiResponse() {

    }

    public ApiResponse(String status, Object respuesta, String error) {
        this.status = status;
        this.respuesta = respuesta;
        this.error = error;
    }
    
}
