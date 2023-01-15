package com.parent.child.rest.api.controllers;

import com.parent.child.rest.api.response.ApiResponse;
import com.parent.child.rest.api.entity.UserParent;
import com.parent.child.rest.api.dto.UserParentDTO;
import com.parent.child.rest.api.repository.UserParentRepository;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/person")
public class UserParentController {

    @Autowired
    private UserParentRepository userParentRepository;


    @GetMapping("/{id}")
    public ResponseEntity<UserParentDTO> getAllDetails(@PathVariable("id") Long id) {
        return userParentRepository.findById(id).map(mapToPersonDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/siblings")
    public ResponseEntity<Set<UserParentDTO>> getAllSiblings(@PathVariable("id") Long id) {
        return userParentRepository.findById(id).map(findSiblings).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse> getAllDetailsById(@PathVariable("id") Long id) {
        try {
              return userParentRepository.findById(id).map(mapToPersonDTO)
                .map((data)->ResponseEntity.status(200).body(new ApiResponse(200,"SUCCESS","UserParent Deatils",data,true)))
                .orElse(ResponseEntity.status(404).body(new ApiResponse(404,"NOT_FOUND","UserParent Not Deatils",Arrays.asList(),false)));
        } catch (Exception e) {
             return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private final Function<UserParent, Set<UserParentDTO>> findSiblings = person -> person.getParent().getChildren().stream()
            .map(p -> UserParentDTO.builder().id(p.getId()).username(p.getUsername()).build()).collect(Collectors.toSet());

    private final Function<UserParent, UserParentDTO> mapToPersonDTO = p -> 
            UserParentDTO.builder().id(p.getId()).username(p.getUsername()).parent(p.getParent()).children(p.getChildren()).build();

}

