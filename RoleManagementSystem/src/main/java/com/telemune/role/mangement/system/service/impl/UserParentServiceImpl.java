package com.telemune.role.mangement.system.service.impl;

import com.telemune.role.mangement.system.dto.UserParentDTO;
import com.telemune.role.mangement.system.entity.UserParent;
import com.telemune.role.mangement.system.repository.UserParentRepository;
import com.telemune.role.mangement.system.service.UserParentService;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserParentServiceImpl implements UserParentService{
    
    @Autowired
    private UserParentRepository userParentRepository;

    @Override
    public Optional<UserParentDTO> getAllDetailsById(Long id) {
       return userParentRepository.findById(id).map(mapToPersonDTO);
    }

    @Override
    public Optional<Set<UserParentDTO>> getAllSiblings(Long id) {
       Optional<Set<UserParentDTO>> optionalAllSiblings =  userParentRepository.findById(id).map(findSiblings);
       if(optionalAllSiblings.isPresent()){
           return optionalAllSiblings;
       }
       else{
           throw new IllegalArgumentException("Data not found with "+id);
       }
    }
    
    
     private final Function<UserParent, Set<UserParentDTO>> findSiblings = person -> person.getParent().getChildren().stream()
            .map(p -> UserParentDTO.builder().id(p.getId()).username(p.getUsername()).build()).collect(Collectors.toSet());

    private final Function<UserParent, UserParentDTO> mapToPersonDTO = p -> 
            UserParentDTO.builder().id(p.getId()).username(p.getUsername()).parent(p.getParent()).children(p.getChildren()).build();
    
}
