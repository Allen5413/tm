package com.zs.dao.orderbook.teachmaterialratio;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.orderbook.TeachMaterialRatio;
import org.springframework.data.jpa.repository.Query;


/**
 * 统计自动生成的购书单的总价格
 * Created by Allen on 2015/5/12.
 */
public interface FindTotalPriceBySemesterDAO extends EntityJpaDao<TeachMaterialRatio, Long> {
    @Query(nativeQuery = true, value = "select cast(sum(tm.price * potm.teach_material_count) as char) as total_price from purchase_order po, purchase_order_teach_material potm, teach_material tm where po.code = potm.code and potm.teach_material_id = tm.id and po.is_auto = 0 and potm.semester_id = ?1")
    public String getTotalPriceBySemester(Long semesterId);
}
