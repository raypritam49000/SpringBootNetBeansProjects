package com.telemune.role.mangement.system.service.impl;

import com.telemune.role.mangement.system.dto.UserDto;
import com.telemune.role.mangement.system.entity.AdminUser;
import com.telemune.role.mangement.system.entity.Roles;
import com.telemune.role.mangement.system.repository.AdminUserRepository;
import com.telemune.role.mangement.system.repository.RolesRepository;
import com.telemune.role.mangement.system.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private AdminUserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto findByUsername(String username) {
        logger.info("Inside findByUsername() method function of UserServiceImpl class");
        Optional<AdminUser> adminUser = userRepository.findByUsername(username);
        System.out.println(adminUser.get());
       
        if (adminUser.isPresent()) {
            logger.info("Exit from findByUsername() method function of UserServiceImpl class");
            return modelMapper.map(adminUser.get(), UserDto.class);
        }
        logger.info("Exit from findByUsername() method function of UserServiceImpl class");
        return new UserDto();
    }

    @Override
    public UserDto createUser(UserDto userDto) throws Exception {
        try {
            logger.info("Inside createUser() method function of UserServiceImpl class");
            Optional<AdminUser> adminUser = userRepository.findByUsername(userDto.getUsername());

            if (adminUser.isPresent()) {
                logger.info("user Already Exit with name===" + adminUser.get().getUsername());
                throw new Exception("Username Already Exit");
            } else {

                AdminUser user = modelMapper.map(userDto, AdminUser.class);
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                
                Roles roles = rolesRepository.findByRoleId(userDto.getRoles().getRoleId());

                user.setCreatedBy("NA");
                user.setRoles(roles);
                user.setFirstLogin("0");
                user.setUserType(String.valueOf(userDto.getRoles().getRoleId()));

                AdminUser adminUserdb = userRepository.save(user);

                logger.info("Exit from createUser() method function of UserServiceImpl class");

                return modelMapper.map(adminUserdb, UserDto.class);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.info("Exit from createUser() method function of IUserServiceImpl class");
        return new UserDto();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        logger.info("Inside userUpdate() method function of UserServiceImpl class");
        Optional<AdminUser> adminUser = userRepository.findByUsername(userDto.getUsername());

        if (adminUser.isPresent()) {

            adminUser.get().setEmail(userDto.getEmail());
            adminUser.get().setFirstLogin(userDto.getFirstLogin());
            adminUser.get().setMobileNumber(userDto.getMobileNumber());
            adminUser.get().setCreatedBy(userDto.getCreatedBy());
            adminUser.get().setPassword(userDto.getPassword());
            adminUser.get().setUsername(userDto.getUsername());
            adminUser.get().setUserType(userDto.getUserType());
            adminUser.get().setPassword(passwordEncoder.encode(userDto.getPassword()));

            Roles roles = rolesRepository.findByRoleId(userDto.getRoles().getRoleId());
            adminUser.get().setRoles(roles);

            AdminUser adminUserdb = userRepository.save(adminUser.get());

            logger.info("Exit from userUpdate() method function of UserServiceImpl class");

            return modelMapper.map(adminUserdb, UserDto.class);

        } else {
            return new UserDto();
        }
    }

    @Override
    public List<UserDto> findAllUser() {

        logger.info("Inside findAllUser() method function of UserServiceImpl class");
        List<UserDto> finalUserVO = new ArrayList<>();
        List<AdminUser> userdbList = userRepository.findAll();

        for (AdminUser mpAdminUser : userdbList) {
            UserDto userDto = modelMapper.map(mpAdminUser, UserDto.class);
            finalUserVO.add(userDto);
        }
        logger.info("Exit from  findAllUser() method function of UserServiceImpl class");
        return finalUserVO;
    }

    @Override
    public boolean userDeleteByUsername(String username) {
        logger.info("Inside userDeleteByUsername() method function of UserServiceImpl class");
        Optional<AdminUser> marketPlaceAdminUser = userRepository.findByUsername(username);
        if (marketPlaceAdminUser.isPresent()) {

            logger.info("Inside userDeleteByUsername() method function of UserServiceImpl class");
            userRepository.deleteByUsername(username);
            return true;

        } else {
            logger.info("Inside userDeleteByUsername() method function of UserServiceImpl class");
            return false;
        }

    }

}
