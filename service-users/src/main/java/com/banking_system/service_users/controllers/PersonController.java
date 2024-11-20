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
import org.springframework.web.bind.annotation.PutMapping;


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

    @PutMapping("update-name-user/{id}/{name}")
    public Person putMethodName(@PathVariable("id") int id, @PathVariable("name") String name) {
        return userService.updateNamePerson(id, name);
    }

    @PutMapping("update-prenom-user/{id}/{prenom}")
    public Person putMethodPrenom(@PathVariable("id") int id, @PathVariable("prenom") String prenom) {
        return userService.updatePrenomPerson(id, prenom);
    }

    @PutMapping("update-email-user/{id}/{email}")
    public Person putMethodEmail(@PathVariable("id") int id, @PathVariable("email") String email) {
        return userService.updateEmailPerson(id, email);
    }

    @PutMapping("update-numero_cni-user/{id}/{numero_cni}")
    public Person putMethodNumeroCni(@PathVariable("id") int id, @PathVariable("numero_cni") String numero_cni) {
        return userService.updateNumeroCniPerson(id, numero_cni);
    }

    @PutMapping("update-recto_cni-user/{id}/{recto_cni}")
    public Person putMethodRectoCni(@PathVariable("id") int id, @PathVariable("recto_cni") String recto_cni) {
        return userService.updateRectoCniPerson(id, recto_cni);
    }

    @PutMapping("update-verso_cni-user/{id}/{verso_cni}")
    public Person putMethodVersoCni(@PathVariable("id") int id, @PathVariable("verso_cni") String verso_cni) {
        return userService.updateVersonCniPerson(id, verso_cni);
    }

    @PutMapping("update-password-user/{id}/{password}")
    public Person putMethodPassword(@PathVariable("id") int id, @PathVariable("password") String password) {
        return userService.updatePasswordPerson(id, password);
    }

}
