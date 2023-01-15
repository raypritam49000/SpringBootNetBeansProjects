package com.telemune.role.mangement.system.controllers;

import com.telemune.role.mangement.system.common.Constants;
import com.telemune.role.mangement.system.common.Response;
import com.telemune.role.mangement.system.dto.HttpLinksDto;
import com.telemune.role.mangement.system.service.HttpLinksService;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/https")
public class HttpsController {

    private static final Logger logger = LogManager.getLogger(HttpsController.class);
    @Autowired
    private HttpLinksService httpLinksService;

    @SuppressWarnings("unused")
    @GetMapping("/all")
    public Response findAllHttpsLinks() {

        try {
            logger.info("inside findAllHttpsLinks() method of HttpsController class");
            List<HttpLinksDto> httpLinksVOlst;
            httpLinksVOlst = httpLinksService.findAllHttpLinks();

            logger.debug(httpLinksVOlst.toString());
            if (null != httpLinksVOlst) {
                return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, httpLinksVOlst, "",
                        Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);
            } else {
                logger.info("httpLinksVOlst ==>" + httpLinksVOlst.toString());
                return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                        "data not found", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
            }
        } catch (Exception exception) {
            logger.error(exception.toString());
            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                    exception.toString(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        }

    }

    @PostMapping("/createHttpLink")
    public Response createHttpLinks(@RequestBody HttpLinksDto httpLinksDto) {
        try {
            logger.info("inside createHttpLinks() method of HttpsController class");
            logger.info("user input ==> httpLinksDto--" + httpLinksDto.toString());

            if (null != httpLinksDto) {
                HttpLinksDto httpLinksDtodb = httpLinksService.createHttpLinks(httpLinksDto);

                logger.debug(httpLinksDtodb.toString());

                if (null != httpLinksDtodb && null != httpLinksDtodb.getLinkId()) {
                    logger.info("Exit createHttpLinks() method of HttpsController class");

                    return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, new ArrayList<>(),
                            "HttpLink created", Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);
                } else {
                    logger.info("Exit createHttpLinks() method of HttpsController class");
                    return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST,
                            new ArrayList<>(), "Error while  creating the user", Constants.STATUS_FAILURE,
                            Constants.STATUS_FAILURE_MESSAGE);
                }

            }

        } catch (Exception exception) {
            logger.error(exception.toString());
            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                    exception.toString(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        }
        logger.info("Exit createHttpLinks() method of HttpsController class");
        return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                "Error while  creating the user", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
    }

    @GetMapping("/find/{linkId}")
    public Response findByHttpLinkId(@PathVariable(name = "linkId") Integer linkId) {

        logger.info("Inside findByHttpLinkId() method of HttpsController class");
        logger.info("user input ==> linkId--" + linkId);

        try {
            List<HttpLinksDto> httpLinksDtos = new ArrayList<>();
            HttpLinksDto httpLinksDto  = null;
            
            if (null != linkId) {
                httpLinksDto = httpLinksService.findByHttpLinksId(linkId);
                logger.debug( httpLinksDto.toString());
            }
            
            if (null !=  httpLinksDto &&  httpLinksDto.getLinkId() != null) {
               
                httpLinksDtos.add(httpLinksDto);
                logger.info("Exit findByHttpLinkId() method of HttpsController class");

                return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, httpLinksDtos, "http details",
                        Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);

            } else {
                logger.info("Exit findByHttpLinkId() method of HttpsController class");
                return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, httpLinksDtos,
                        "Not found value", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
            }

        } catch (Exception exception) {
            logger.error(exception.toString());
            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                    exception.getMessage(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        }

    }
    
    @PutMapping("/updateHttpLink")
     public Response updateHttpLinks(@RequestBody HttpLinksDto httpLinksDto){
          try {
            logger.info("Inside updateHttpLinks() method of HttpsController class");
            logger.info("user input ==> httpLinksDto--" + httpLinksDto.toString());
            
            if (null != httpLinksDto && null != httpLinksDto.getLinkId()) {
                
                HttpLinksDto httpLinksDtoDb = httpLinksService.updateHttpLinks(httpLinksDto);
                logger.debug(httpLinksDtoDb .toString());
                
                if (null != httpLinksDtoDb  && httpLinksDtoDb.getLinkId() != null) {
                    logger.info("Exit updateHttpLinks() method of HttpsController class");
                    
                    return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, new ArrayList<>(),
                            "Plan created", Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);

                } else {
                    logger.info("Exit updateHttpLinks() method of HttpsController class");
                    
                    return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST,
                            new ArrayList<>(), "Error while Updating the httpLink", Constants.STATUS_FAILURE,
                            Constants.STATUS_FAILURE_MESSAGE);
                    
                }
            }
            return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                    "object is null", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
            
        } catch (Exception exception) {
            logger.error(exception.toString());
            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                    exception.getMessage(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        }
     }

}
