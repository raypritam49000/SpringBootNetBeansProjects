package com.telemune.mybatis.rest.api.controllers;

import com.telemune.mybatis.rest.api.models.Address;
import com.telemune.mybatis.rest.api.models.Language;
import com.telemune.mybatis.rest.api.models.Person;
import com.telemune.mybatis.rest.api.response.ApiResponse;
import com.telemune.mybatis.rest.api.services.PersonService;
import com.telemune.mybatis.rest.api.validation.ParameterValidator;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/createPerson/{id}")
    public ResponseEntity<ApiResponse> createPerson(@RequestBody Person person, @PathVariable("id") Long id) {
        try {
            Boolean isValid = ParameterValidator.isValid(person);

            if (!isValid) {
                return new ResponseEntity<>(new ApiResponse("All Parameters are required", "INTERNAL_SERVER_ERROR", 400, false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            } else {
                Address address = personService.findAddessById(id);
                person.setAddress(address);
                Person createPerson = personService.createPerson(person);
                return new ResponseEntity<>(new ApiResponse("Person Created", "CREATED", 201, true, createPerson), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), "INTERNAL_SERVER_ERROR", 501, false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getAllPersons")
    public ResponseEntity<ApiResponse> getAllPersons() {
        try {
            List<Person> personList = personService.getAllPerson();

            if (personList != null && !personList.isEmpty() && personList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse("Person Details List", "SUCCESS", 200, true, personList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse("Person Not Found", "NOT_FOUND", 404, false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), "INTERNAL_SERVER_ERROR", 501, false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPerson/{id}")
    public ResponseEntity<ApiResponse> getPerson(@PathVariable("id") Long id) {
        try {
            Person person = personService.findPersonById(id);
            Boolean isValid = ParameterValidator.isValid(person);
            if (isValid) {
                return new ResponseEntity<>(new ApiResponse("Person Details List", "SUCCESS", 200, true, person), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse("Person Not Found", "NOT_FOUND", 404, false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), "INTERNAL_SERVER_ERROR", 501, false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllLangauageByPerson/{id}")
    public ResponseEntity<ApiResponse> getAllLangauageByPerson(@PathVariable("id") Long id) {
        try {
            List<Language> languageList = personService.getAllLangauageByPerson(id);

            if (languageList != null && !languageList.isEmpty() && languageList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse("Languages List By Person", "SUCCESS", 200, true, languageList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse("Person Not Found", "NOT_FOUND", 404, false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), "INTERNAL_SERVER_ERROR", 501, false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPersonByEmail/{email}")
    public ResponseEntity<ApiResponse> getPersonByEmail(@PathVariable("email") String email) {
        try {
            Person person = personService.findPersonByEmail(email);
            Boolean isValid = ParameterValidator.isValid(person);
            if (isValid) {
                return new ResponseEntity<>(new ApiResponse("Person Details List", "SUCCESS", 200, true, person), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse("Person Not Found", "NOT_FOUND", 404, false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), "INTERNAL_SERVER_ERROR", 501, false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAddress/{id}")
    public ResponseEntity<ApiResponse> getAddressById(@PathVariable("id") Long id) {
        try {
            Address address = personService.findAddessById(id);
            Boolean isValid = ParameterValidator.isValidAddress(address);
            if (isValid) {
                return new ResponseEntity<>(new ApiResponse("Address Details List", "SUCCESS", 200, true, address), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse("Address Not Found", "NOT_FOUND", 404, false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), "INTERNAL_SERVER_ERROR", 501, false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity<ApiResponse> deletePersonById(@PathVariable("id") Long id) {
        try {
            Boolean isDeleted = personService.deletePersonById(id);
            if (isDeleted) {
                return new ResponseEntity<>(new ApiResponse("Address Details Deleted", "SUCCESS", 200, true, Arrays.asList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse("Address Not Found", "NOT_FOUND", 404, false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), "INTERNAL_SERVER_ERROR", 501, false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updatePerson/{id}")
    public ResponseEntity<ApiResponse> updatePerson(@RequestBody Person person) {
        try {
            Boolean isValid = ParameterValidator.isValid(person);

            if (!isValid) {
                return new ResponseEntity<>(new ApiResponse("All Parameters are required", "INTERNAL_SERVER_ERROR", 400, false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            } else {
                Person updatePerson = personService.updatePerson(person);
                return new ResponseEntity<>(new ApiResponse("Person Updated", "UPDATED", 201, true, updatePerson), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), "INTERNAL_SERVER_ERROR", 501, false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
