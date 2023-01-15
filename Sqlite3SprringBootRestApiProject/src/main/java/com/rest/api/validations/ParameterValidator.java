package com.rest.api.validations;

import com.rest.api.dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class ParameterValidator {

    public boolean validate(PersonDto personDto) {
        return personDto != null && personDto.getName() != null && !personDto.getName().equals("")
                && personDto.getCity() != null && !personDto.getCity().equals("") && personDto.getSalary() != null
                && !personDto.getSalary().equals("");
    }
}
