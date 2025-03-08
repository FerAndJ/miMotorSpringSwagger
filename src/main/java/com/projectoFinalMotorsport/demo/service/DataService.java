package com.projectoFinalMotorsport.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectoFinalMotorsport.demo.utils.DataRestApi;

@Service
public class DataService {

    @Autowired
    private DataRestApi dataRestApi;

    public List<?> getUsers() {
        return dataRestApi.getUsers();
    }

    /* 
    public Object getUserById(String id) {
        return dataRestApi.getUserById(id);
    }

    */
}
