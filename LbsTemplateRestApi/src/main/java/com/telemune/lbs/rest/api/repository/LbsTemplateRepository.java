package com.telemune.lbs.rest.api.repository;

import com.telemune.lbs.rest.api.embedded.LbsTemplateIds;
import com.telemune.lbs.rest.api.entity.LbsTemplate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LbsTemplateRepository extends JpaRepository<LbsTemplate, LbsTemplateIds> {

    Optional<LbsTemplate> findByLbsTemplateIds(LbsTemplateIds lbsTemplateIds);

    List<LbsTemplate> findByLbsTemplateIdsTemplateId(Integer id);

    void deleteAllByLbsTemplateIdsTemplateId(Integer id);

    List<LbsTemplate> findByLbsTemplateIdsLanguageId(Integer id);

    @Query(value = "SELECT max(t.lbsTemplateIds.templateId) FROM LbsTemplate t")
    public int maxTemplateId();

}
