package com.zs.dao.ebook.spotreplacepaydetail;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.ebook.SpotReplacePayDetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2018/1/3.
 */
public interface FindSpotReplacePayDetailBySrpIdDAO extends EntityJpaDao<SpotReplacePayDetail, Long> {
    @Query(nativeQuery = true, value = "SELECT srpd.id, s.code, s.name, ROUND(srpd.money/100, 2), srpd.money FROM spot_replace_pay_detail srpd, sync_student s " +
            "where srpd.student_code = s.code and srpd.srp_id = ?1 " +
            "order by s.code")
    public List<Object[]> find(long srpId)throws Exception;
}
