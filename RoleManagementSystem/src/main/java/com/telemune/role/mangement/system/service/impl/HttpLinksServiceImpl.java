package com.telemune.role.mangement.system.service.impl;

import com.telemune.role.mangement.system.dto.HttpLinksDto;
import com.telemune.role.mangement.system.entity.HttpLinks;
import com.telemune.role.mangement.system.repository.HttpLinksRepository;
import com.telemune.role.mangement.system.service.HttpLinksService;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpLinksServiceImpl implements HttpLinksService {

    private static final Logger logger = LogManager.getLogger(HttpLinksServiceImpl.class);

    @Autowired
    private HttpLinksRepository httpLinksRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HttpLinksDto createHttpLinks(HttpLinksDto httpLinksDto) {
        logger.info("Inside createHttpLinks() method function of HttpLinksServiceImpl class");
        HttpLinks httpLinks = this.modelMapper.map(httpLinksDto, HttpLinks.class);

        HttpLinks httpLinksdb = httpLinksRepository.save(httpLinks);
        logger.info("Exit from createHttpLinks() method function of HttpLinksServiceImpl class");

        return this.modelMapper.map(httpLinksdb, HttpLinksDto.class);
    }

    @Override
    public HttpLinksDto updateHttpLinks(HttpLinksDto httpLinksDto) {
        logger.info("Inside updateHttpLinks() method of HttpLinksServiceImpl class");
        HttpLinks httpLinks = this.modelMapper.map(httpLinksDto, HttpLinks.class);

        HttpLinks httpLinksdb = httpLinksRepository.save(httpLinks);
        logger.info("Exit updateHttpLinks() method of HttpLinksServiceImpl class");

        return this.modelMapper.map(httpLinksdb, HttpLinksDto.class);
    }

    @Override
    public List<HttpLinksDto> findAllHttpLinks() {
        logger.info("Inside findAllHttpsLinks() method of HttpLinksServiceImpl class");
        List<HttpLinks> httpLinks = httpLinksRepository.findAll();

        List<HttpLinksDto> httpLinksDtos = httpLinks.stream().map(httpLink
                -> this.modelMapper.map(httpLink, HttpLinksDto.class)).collect(Collectors.toList());
        logger.info("Exit findAllHttpsLinks() method of HttpLinksServiceImpl class");

        return httpLinksDtos;
    }

    @Override
    public HttpLinksDto findByHttpLinksId(Integer id) {

        logger.info("Inside findByHttpLinksId() method of HttpLinksServiceImpl class");
        HttpLinks httpLinks = httpLinksRepository.findByLinkId(id);

        logger.info("Exit findByHttpLinksId() method of HttpLinksServiceImpl class");
        return this.modelMapper.map(httpLinks, HttpLinksDto.class);
    }

}
