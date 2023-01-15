package com.telemune.role.mangement.system.service;

import com.telemune.role.mangement.system.dto.UserDto;
import java.util.List;

public interface UserService {
    public UserDto findByUsername(String username);
    public UserDto createUser(UserDto userDto) throws Exception;
    public UserDto updateUser(UserDto userDto);
    public List<UserDto> findAllUser();
    public boolean userDeleteByUsername(String username);
}
