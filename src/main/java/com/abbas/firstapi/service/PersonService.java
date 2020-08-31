package com.abbas.firstapi.service;

import com.abbas.firstapi.entity.Person;
import com.abbas.firstapi.exception.NotFoundException;
import com.abbas.firstapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person addPerson(Person person){
        personRepository.save(person);
        return person;
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Person findPersonById(int id){
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()){
            throw new NotFoundException("Person with id:"+id+" does not Exist!");
        }
        return person.get();
    }

    public Person updatePerson(Person person, int id){
        Optional<Person> opperson = personRepository.findById(id);
        if (!opperson.isPresent()){
            throw new NotFoundException("Person with id:"+id+" does not Exist!");
        }
        person.setId(id);
        personRepository.save(person);
        return person;
    }

    public void deletePerson(int id){
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()){
            throw new NotFoundException("Person with id:"+id+" does not Exist!");
        }
        personRepository.delete(person.get());
    }
}
