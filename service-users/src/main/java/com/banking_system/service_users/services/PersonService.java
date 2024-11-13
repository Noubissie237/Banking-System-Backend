package com.banking_system.service_users.services;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking_system.service_users.models.Person;
import com.banking_system.service_users.repositories.PersonRepository;

@Service
public class PersonService {
    
    @Autowired
    PersonRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    public Person updateNamePerson(int id, String newValue) {
        Person person = getUserById(id);

        person.setNom(newValue);
        userRepository.save(person);
        return person;
    }

    public Person updatePrenomPerson(int id, String newValue) {
        Person person = getUserById(id);

        person.setPrenom(newValue);
        userRepository.save(person);
        return person;
    }

    public Person updateEmailPerson(int id, String newValue) {
        Person person = getUserById(id);

        person.setEmail(newValue);
        userRepository.save(person);
        return person;
    }

    public Person updateNumeroCniPerson(int id, String newValue) {
        Person person = getUserById(id);

        person.setNumero_cni(newValue);
        userRepository.save(person);
        return person;
    }

    public Person updateRectoCniPerson(int id, String newValue) {
        Person person = getUserById(id);

        person.setRecto_cni(newValue);
        userRepository.save(person);
        return person;
    }

    public Person updateVersonCniPerson(int id, String newValue) {
        Person person = getUserById(id);

        person.setVerso_cni(newValue);
        userRepository.save(person);
        return person;
    }

    public Person updatePasswordPerson(int id, String newValue) {
        Person person = getUserById(id);
        
        String encodedPassword = passwordEncoder.encode(newValue);
        person.setPassword(encodedPassword);
        userRepository.save(person);
        return person;
    }
} 