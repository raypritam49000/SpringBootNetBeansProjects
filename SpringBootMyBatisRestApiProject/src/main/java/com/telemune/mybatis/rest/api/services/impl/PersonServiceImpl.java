package com.telemune.mybatis.rest.api.services.impl;

import com.telemune.mybatis.rest.api.models.Address;
import com.telemune.mybatis.rest.api.models.Language;
import com.telemune.mybatis.rest.api.models.Person;
import com.telemune.mybatis.rest.api.repository.PersonRepository;
import com.telemune.mybatis.rest.api.services.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person createPerson(Person person){ 
        personRepository.save(person);
        return this.personRepository.findPersonByEmail(person.getEmail());
    }

    @Override
    public List<Person> getAllPerson() {
        return this.personRepository.findAll();
    }

    @Override
    public List<Language> getAllLangauageByPerson(Long id) {
        return this.personRepository.findLanuageByPerson(id);
    }

    @Override
    public Person findPersonById(Long id) {
        return this.personRepository.findPersonById(id);
    }

    @Override
    public Person findPersonByEmail(String email) {
        return this.personRepository.findPersonByEmail(email);
    }

    @Override
    public Address findAddessById(Long id) {
        return this.personRepository.findAddessById(id);
    }

    @Override
    public boolean deletePersonById(Long id) {
       this.personRepository.deletePersonById(id);
       return true;
    }

    @Override
    public Person updatePerson(Person person) {
       this.personRepository.updatePerson(person);
       return this.personRepository.findPersonByEmail(person.getEmail());
    }

}
