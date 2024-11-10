package com.banking_system.service_users.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_users.models.Person;
import com.banking_system.service_users.services.PersonService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    PersonService userService;

    @PostMapping("/add-user")
    public List<Person> addUserController(@RequestBody Person user){
        userService.addUser(user);
        return userService.getAllUsers();
    }

    @GetMapping("/get-persons")
    public List<Person> getpersons() {
        return userService.getAllUsers();
    }

    @GetMapping("/delete-user/{id}")
    public List<Person> deleteUserController(@PathVariable("id") int id){
        return userService.deleteUser(id);
    }

    @GetMapping("/get-user/{number}")
    public Optional<Person> getUserController(@PathVariable("number") String number){
        return userService.getUserByNumber(number);
    }
}
