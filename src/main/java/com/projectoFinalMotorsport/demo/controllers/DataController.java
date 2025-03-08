package com.projectoFinalMotorsport.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectoFinalMotorsport.demo.model.User;
import com.projectoFinalMotorsport.demo.service.DataService;
import com.projectoFinalMotorsport.demo.utils.ApiResponse;

@RestController
@RequestMapping("/users")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.ok().body(new ApiResponse("Ok", dataService.getUsers(), ""));
    }

    /* 
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long idLong) {
        return ResponseEntity.ok().body(new ApiResponse("Ok", dataService.getUserById(), ""));
    }
    */
}
 