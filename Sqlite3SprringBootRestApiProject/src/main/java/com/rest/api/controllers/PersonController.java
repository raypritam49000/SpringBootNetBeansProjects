package com.rest.api.controllers;

import com.rest.api.dto.PersonDto;
import com.rest.api.response.ApiResponse;
import com.rest.api.service.PersonService;
import com.rest.api.validations.ParameterValidator;
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
@RequestMapping("/api/v1/persons")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private ParameterValidator parameterValidator;
    
    
    @GetMapping
    public ResponseEntity<ApiResponse> getAllPersons() {
        try {
            List<PersonDto> personList = personService.getAllPersons();

            if (personList != null && !personList.isEmpty() && personList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Persons Details", true, personList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Person Details Not Found", false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPerson(@PathVariable("id") long id) {
        try {

            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }

            PersonDto personDto  = personService.getPerson(id);
            Boolean isValid = parameterValidator.validate(personDto);
            if (isValid) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Preson Details", true, personDto), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Persons Details Not Found", false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiResponse(404, "NOT_FOUND", "Persons Detail Not Found", false, Arrays.asList()), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse> createPerson(@RequestBody PersonDto personDto) {
        try {
            Boolean isValid = parameterValidator.validate(personDto);
            if (isValid) {
               PersonDto createPersonDto = this.personService.createPerson(personDto);
                return new ResponseEntity<>(new ApiResponse(201, "CREATED", "Person Created", true, createPersonDto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "All Parameter are required", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePerson(@PathVariable("id") long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }

            Boolean isDeleted = personService.deletePerson(id);

            if (isDeleted) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Person Detail Deleted", true, Arrays.asList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Error Occur While Delete Person Details", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePerson(@PathVariable("id") long id, @RequestBody PersonDto personDto) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }
            Boolean isValid = parameterValidator.validate(personDto);
            
            if (isValid) {
                PersonDto updatePersonDto = personService.updatePerson(id, personDto);
                return new ResponseEntity<>(new ApiResponse(203, "UPDATED", "Persons Details updated", true, updatePersonDto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "All Parameter are required", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
