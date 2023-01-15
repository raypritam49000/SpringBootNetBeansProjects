package com.rest.api.service.impl;

import com.rest.api.dto.PersonDto;
import com.rest.api.entity.Person;
import com.rest.api.repository.PersonRepository;
import com.rest.api.service.PersonService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = this.modelMapper.map(personDto, Person.class);
        Person createPerson = this.personRepository.save(person);
        return this.modelMapper.map(createPerson, PersonDto.class);
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto) {
        Optional<Person> optionalPerson = this.personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            optionalPerson.get().setCity(personDto.getCity());
            optionalPerson.get().setName(personDto.getName());
            optionalPerson.get().setSalary(personDto.getSalary());
            Person updatePerson = this.personRepository.save(optionalPerson.get());
            return this.modelMapper.map(updatePerson, PersonDto.class);
        } else {
            throw new IllegalArgumentException("Person Not Found with " + id);
        }
    }

    @Override
    public PersonDto getPerson(Long id) {
        Optional<Person> optionalPerson = this.personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            return this.modelMapper.map(optionalPerson.get(), PersonDto.class);
        } else {
            throw new IllegalArgumentException("Person Not Found with " + id);
        }
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<Person> persons = this.personRepository.findAll();
        List<PersonDto> personDtoList = persons.stream().map(person
                -> {
            return this.modelMapper.map(person, PersonDto.class);
        }).collect(Collectors.toList());
        return personDtoList;
    }

    @Override
    public Boolean deletePerson(Long id) {
        Optional<Person> optionalPerson = this.personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            this.personRepository.delete(optionalPerson.get());
            return true;
        } else {
            return false;
        }
    }

}
