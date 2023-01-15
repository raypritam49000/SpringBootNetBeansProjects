package com.telemune.role.mangement.system.service;

import com.telemune.role.mangement.system.dto.RoleDto;
import java.util.List;

public interface RoleService {
    public RoleDto createRole(RoleDto roleDto);
    public RoleDto updateRole(RoleDto roleDto);
    public List<RoleDto> findAllRole();
    public RoleDto findById(Integer id);
    public boolean roleDeleteById(Integer roleId);
}
