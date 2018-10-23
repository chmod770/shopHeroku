package com.gabi.example;

import com.gabi.example.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<Person, Long>{}
