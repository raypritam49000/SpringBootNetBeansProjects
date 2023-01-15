package com.telemune.mybatis.rest.api.validation;

import com.telemune.mybatis.rest.api.models.Address;
import com.telemune.mybatis.rest.api.models.Person;

public class ParameterValidator {

    public static Boolean isValid(Person person) {
        return person != null && person.getEmail() != null && !person.getEmail().equals("") && person.getFirstName() != null
                && !person.getFirstName().equals("") && person.getLastName() != null && !person.getLastName().equals("");
    }

    public static Boolean isValidAddress(Address address) {
        return address != null && address.getCity() != null && !address.getCity().equals("") && address.getState() != null
                && !address.getState().equals("") &&  address.getCountry() != null && !address.getCountry().equals("");
    }
}
