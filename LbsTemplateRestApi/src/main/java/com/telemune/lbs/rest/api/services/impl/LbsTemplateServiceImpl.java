package com.telemune.lbs.rest.api.services.impl;

import com.telemune.lbs.rest.api.dto.LbsTemplateDto;
import com.telemune.lbs.rest.api.entity.LbsTemplate;
import com.telemune.lbs.rest.api.repository.LbsTemplateRepository;
import com.telemune.lbs.rest.api.services.ILbsTemplateService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LbsTemplateServiceImpl implements ILbsTemplateService {

    @Autowired
    private LbsTemplateRepository lbsTemplateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LbsTemplateDto> findByTemplateId(Integer id) {
        List<LbsTemplate> tempdblst = lbsTemplateRepository.findByLbsTemplateIdsTemplateId(id);

        if (tempdblst != null) {
            List<LbsTemplateDto> ltlst = tempdblst.stream()
                    .map(lbsTemplate -> modelMapper.map(lbsTemplate, LbsTemplateDto.class)).collect(Collectors.toList());
            return ltlst;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean templateDeleteById(Integer id) {

        List<LbsTemplate> tempdblst = lbsTemplateRepository.findByLbsTemplateIdsTemplateId(id);
        if (tempdblst != null && !tempdblst.isEmpty()) {
            lbsTemplateRepository.deleteAllByLbsTemplateIdsTemplateId(id);
            return true;
        }
        return false;
    }

}
