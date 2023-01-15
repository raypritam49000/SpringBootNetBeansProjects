package com.telemune.role.mangement.system.service;

import com.telemune.role.mangement.system.dto.UserParentDTO;
import java.util.Optional;
import java.util.Set;

public interface UserParentService {
    public Optional<UserParentDTO> getAllDetailsById(Long id);
    public Optional<Set<UserParentDTO>> getAllSiblings(Long id);
}
