package com.zs.dao.basic.setteachmaterialtm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SetTeachMaterialTM;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/11/22.
 */
public interface FindSetTeachMaterialTMByCourseCodeDAO extends EntityJpaDao<SetTeachMaterialTM, Long> {
    @Query("select stmtm from SetTeachMaterial stm, SetTeachMaterialTM stmtm where stm.id = stmtm.setTeachMaterialId and stm.buyCourseCode = ?1")
    public List<SetTeachMaterialTM> find(String courseCode);
}
