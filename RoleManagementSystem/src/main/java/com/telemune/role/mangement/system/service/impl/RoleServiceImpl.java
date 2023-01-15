package com.telemune.role.mangement.system.service.impl;

import com.telemune.role.mangement.system.dto.HttpLinksDto;
import com.telemune.role.mangement.system.dto.RoleDto;
import com.telemune.role.mangement.system.entity.HttpLinks;
import com.telemune.role.mangement.system.entity.Roles;
import com.telemune.role.mangement.system.repository.HttpLinksRepository;
import com.telemune.role.mangement.system.repository.RolesRepository;
import com.telemune.role.mangement.system.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(HttpLinksServiceImpl.class);

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private HttpLinksRepository httpLinksRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDto createRole(RoleDto roleDto) {

        logger.info("Inside createRole() method of RoleServiceImpl class");
        Roles roles = this.modelMapper.map(roleDto, Roles.class);

        List<HttpLinks> httplst = new ArrayList<>();

        for (HttpLinksDto hl : roleDto.getHttpLinkslst()) {
            HttpLinks httpdb = httpLinksRepository.findByLinkId(hl.getLinkId());
            httplst.add(httpdb);
        }
        roles.setHttpLinks(httplst);

        Roles rolesdb = rolesRepository.save(roles);
        logger.info("Exit createRole() method of RoleServiceImpl class" + rolesdb.toString());

        return this.modelMapper.map(rolesdb, RoleDto.class);
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        logger.info("Inside updateRole() method of RoleServiceImpl class");

        try {
            Roles marketPlaceRoles = rolesRepository.findByRoleId(roleDto.getRoleId());

            if (marketPlaceRoles != null) {

                Optional.ofNullable(roleDto.getDescription()).ifPresent(marketPlaceRoles::setDescription);
                Optional.ofNullable(roleDto.getRoleName()).ifPresent(marketPlaceRoles::setRoleName);

                List<HttpLinks> httplst = new ArrayList<>();
                for (HttpLinksDto hl : roleDto.getHttpLinkslst()) {
                    HttpLinks httpdb = httpLinksRepository.findByLinkId(hl.getLinkId());
                    httplst.add(httpdb);
                }
                marketPlaceRoles.setHttpLinks(httplst);
                Roles marketPlaceRolesdb = rolesRepository.save(marketPlaceRoles);

                logger.debug(marketPlaceRolesdb.toString());

                logger.info("Exit updateRole() method of RoleServiceImpl class");
                return this.modelMapper.map(marketPlaceRolesdb, RoleDto.class);
            } else {
                logger.info("Exit updateRole() method of RoleServiceImpl class");
            }
            return new RoleDto();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        logger.info("Exit updateRole() method of RoleServiceImpl class");
        return new RoleDto();

    }

    @Override
    public List<RoleDto> findAllRole() {
        logger.info("Inside findAllRole() method of RoleServiceImpl class");
        List<Roles> roleslist = rolesRepository.findAll();

        List<RoleDto> roleDtolist = roleslist.stream().map(role
                -> this.modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
        logger.info("Exit findAllRole() method of RoleServiceImpl class");

        return roleDtolist;
    }

    @Override
    public RoleDto findById(Integer id) {
        logger.info("Inside findById() method of RoleServiceImpl class");
        Roles roles = rolesRepository.findByRoleId(id);
        logger.debug(roles.toString());
        RoleDto roleDto = null;
        if (roles != null) {
            roleDto = this.modelMapper.map(roles, RoleDto.class);
            List<HttpLinksDto> httplst = new ArrayList<>();

            for (HttpLinks httpLinks : roles.getHttpLinks()) {
                HttpLinksDto httpLinksDto = this.modelMapper.map(httpLinks, HttpLinksDto.class);
                httplst.add(httpLinksDto);
            }
            logger.info("Exit findById() method of RoleServiceImpl class");
            roleDto.setHttpLinkslst(httplst);

        }
        return roleDto;
    }

    @Override
    public boolean roleDeleteById(Integer roleId) {
        Roles roles = rolesRepository.findByRoleId(roleId);
        logger.info("Inside roleDeleteById() method of RoleServiceImpl class : " + roles.toString());

        try {
            if (roles != null) {
                rolesRepository.deleteByRoleId(roleId);
                logger.info("Exit roleDeleteById() method of RoleServiceImpl class");
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.info("Exit roleDeleteById() method of RoleServiceImpl class");
        return false;

    }

}
