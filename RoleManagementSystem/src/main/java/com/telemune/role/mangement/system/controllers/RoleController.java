package com.telemune.role.mangement.system.controllers;

import com.telemune.role.mangement.system.common.Constants;
import com.telemune.role.mangement.system.common.Response;
import com.telemune.role.mangement.system.dto.RoleDto;
import com.telemune.role.mangement.system.service.HttpLinksService;
import com.telemune.role.mangement.system.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/role")
public class RoleController {

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private HttpLinksService httpLinksService;

    @PostMapping()
    public Response createRole(@RequestBody RoleDto roleVO) {
        try {
            logger.info("inside createRole() method of RoleController class");
            logger.info("user input ==> RoleVO--" + roleVO.toString());
            RoleDto roleVOdb = roleService.createRole(roleVO);
            logger.debug(roleVOdb.toString());
            if (null != roleVOdb && roleVOdb.getRoleId() != null) {

                logger.info("exit createRole() method of RoleController class");
                return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, new ArrayList<>(),
                        "Role created", Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);

            } else {
                logger.info("exit createRole() method of RoleController class");
                return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                        "Error while creating the role", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
            }

        } catch (Exception exception) {
            logger.error(exception.toString());
            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(), "",
                    Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        }

    }

    @PutMapping()
    public Response updateRole(@RequestBody RoleDto roleVO) {
        try {

            logger.info("inside updateRole() method of RoleController class");
            logger.info("user input ==> RoleVO--" + roleVO.toString());
            if (null != roleVO && null != roleVO.getRoleId()) {
                RoleDto roleVOdb = roleService.updateRole(roleVO);
                logger.debug(roleVOdb.toString());
                if (null != roleVOdb && roleVOdb.getRoleId() != null) {
                    logger.info("exit updateRole() method of RoleController class");
                    return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, new ArrayList<>(),
                            "Role is Updated", Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);

                } else {
                    logger.info("exit updateRole() method of RoleController class");
                    return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST,
                            new ArrayList<>(), "Error while Updating the role", Constants.STATUS_FAILURE,
                            Constants.STATUS_FAILURE_MESSAGE);
                }
            }
            logger.info("exit updateRole() method of RoleController class");
            return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                    "Object is null", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        } catch (Exception exception) {
            logger.error(exception.toString());
            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(), "",
                    Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        }
    }

    @GetMapping("/all")
    public Response findAllRole() {

        try {
            logger.info("Inside findAllRole() method of RoleController class");
            List<RoleDto> roleVOlst = roleService.findAllRole();
            logger.debug(roleVOlst.toString());
            if (null != roleVOlst && !roleVOlst.isEmpty()) {
                logger.info("Exit findAllRole() method of RoleController class");
                return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, roleVOlst, "Role List",
                        Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);
            } else {
                logger.info("Exit findAllRole() method of RoleController class");
                return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, roleVOlst, "list is empoty",
                        Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);
            }
        } catch (Exception exception) {
            logger.error(exception.toString());
            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                    exception.getMessage(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);

        }
    }

    @GetMapping("/find/{roleId}")
    public Response findByRoleId(@PathVariable(name = "roleId") Integer roleId) {

        try {
            logger.info("Inside findAllRole() method of RoleController class");
            logger.info("user input ===> roleId" + roleId);
            RoleDto roleVOdb = null;
            List<RoleDto> roleVOlst = new ArrayList<>();
            if (null != roleId) {
                roleVOdb = roleService.findById(roleId);
                logger.debug(roleVOdb);
                roleVOlst.add(roleVOdb);
                logger.debug(roleVOlst.toString());
            }
            if (null != roleVOlst && !roleVOlst.isEmpty()) {
                logger.info("Inside findByRoleId() method of  RoleController class");
                return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, roleVOlst, "role detailt",
                        Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);

            } else {
                logger.info("Inside findByRoleId() method of RoleController class");
                return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, roleVOlst,
                        "Not found value", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
            }

        } catch (Exception exception) {
            logger.error(exception.toString());
            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(), "",
                    Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        }

    }

    @DeleteMapping("/{roleId}")
    public Response deleteByPackId(@PathVariable(name = "roleId") Integer roleId) {

        try {
            logger.info("Inside deleteByPackId() method of RoleController class");
            logger.info("user input ===> roleId" + roleId);

            if (null != roleId) {
                boolean isRoleDeleted = roleService.roleDeleteById(roleId);
                logger.debug(" isRoleDeleted ==>" + isRoleDeleted);

                if (isRoleDeleted) {
                    logger.info(isRoleDeleted);
                    logger.info("Exit deleteByPackId() method of RoleController class");
                    return new Response(HttpStatus.NO_CONTENT, Constants.HTTP_STATUS_CODE_NO_CONTACT, new ArrayList<>(),
                            "Role deleted", Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);
                } else {
                    logger.info(isRoleDeleted);
                    logger.info("Exit deleteByPackId() method of RoleController class");
                    return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST,
                            new ArrayList<>(), "Role is not deleted", Constants.STATUS_FAILURE,
                            Constants.STATUS_FAILURE_MESSAGE);
                }

            } else {
                logger.info("Exit deleteByPackId() method of RoleController class");
                return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                        "Role is not deleted", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
            }
        } catch (Exception exception) {
            logger.error(exception.toString());
            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                    exception.toString(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        }

    }

}
