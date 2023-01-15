package com.telemune.mybatis.rest.api.services;

import com.telemune.mybatis.rest.api.models.Address;
import com.telemune.mybatis.rest.api.models.Language;
import com.telemune.mybatis.rest.api.models.Person;
import java.util.List;

public interface PersonService {
    public Person createPerson(Person person);
    public List<Person> getAllPerson();
    public List<Language> getAllLangauageByPerson(Long id);
    public Person findPersonById(Long id);
    public Person findPersonByEmail(String email);
    public Address findAddessById(Long id);
    public boolean deletePersonById(Long id);
    public Person updatePerson(Person person);
}
