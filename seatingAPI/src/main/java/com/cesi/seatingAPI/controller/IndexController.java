package com.cesi.seatingAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the Seating Plan API. "
        		+ " - You can create a new Member by making a POST request to /api/members endpoint."
        		+ " - You can create a new Room by making a POST request to /api/rooms endpoint.";
    }
}

