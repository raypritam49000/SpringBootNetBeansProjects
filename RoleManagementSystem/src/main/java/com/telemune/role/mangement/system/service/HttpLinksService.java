package com.telemune.role.mangement.system.service;

import com.telemune.role.mangement.system.dto.HttpLinksDto;
import java.util.List;

public interface HttpLinksService {
     public HttpLinksDto createHttpLinks(HttpLinksDto httpLinksVO);
     public HttpLinksDto updateHttpLinks(HttpLinksDto httpLinksVO);
     public List<HttpLinksDto> findAllHttpLinks();
     public HttpLinksDto findByHttpLinksId(Integer id);
}
