package com.banking_system.service_users.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.banking_system.service_users.models.Person;


@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Optional<Person> findByTel(String tel);
}
