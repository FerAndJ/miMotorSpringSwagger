package com.projectoFinalMotorsport.demo.utils;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataRestApi {

    public final String URL = "https://jsonplaceholder.typicode.com/";

    public List<?> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL + "users", List.class);
    }

    /* 
    public Object getUserById(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = URL + "/users/" + id;
        return restTemplate.getForObject()
    }

    */

}
