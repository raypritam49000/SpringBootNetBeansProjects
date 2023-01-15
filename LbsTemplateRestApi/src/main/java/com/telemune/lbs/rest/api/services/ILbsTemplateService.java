package com.telemune.lbs.rest.api.services;

import com.telemune.lbs.rest.api.dto.LbsTemplateDto;
import java.util.List;

public interface ILbsTemplateService {
    public List<LbsTemplateDto> findByTemplateId(Integer id);
    public boolean templateDeleteById(Integer id);
}
