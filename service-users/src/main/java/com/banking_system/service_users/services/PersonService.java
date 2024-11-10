package com.banking_system.service_users.services;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_users.models.Person;
import com.banking_system.service_users.repositories.PersonRepository;

@Service
public class PersonService {
    
    @Autowired
    PersonRepository userRepository;

    // Ajout d'un utilisateur
    public void addUser(Person user){
        userRepository.save(user);
    }

    // Suppression d'un utilisateur
    public List<Person> deleteUser(int id){
        userRepository.deleteById(id);
        return userRepository.findAll();
    }

    // Liste de tous les utilisateurs
    public List<Person> getAllUsers(){
        return userRepository.findAll();
    }

    // Rechercher un utilisateur par l'id
    public Person getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public Optional<Person> getUserByNumber(String number) {
        return userRepository.findByTel(number);
    }
} 