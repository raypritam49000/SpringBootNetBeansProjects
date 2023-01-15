package com.rest.api.service;

import com.rest.api.dto.PersonDto;
import java.util.List;

public interface PersonService {
    public PersonDto createPerson(PersonDto personDto);
    public PersonDto updatePerson(Long id,PersonDto personDto);
    public PersonDto getPerson(Long id);
    public List<PersonDto> getAllPersons();
    public Boolean deletePerson(Long id);  
}
