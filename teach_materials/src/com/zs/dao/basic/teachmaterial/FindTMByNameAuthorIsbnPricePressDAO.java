package com.zs.dao.basic.teachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterial;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/9/7.
 */
public interface FindTMByNameAuthorIsbnPricePressDAO extends EntityJpaDao<TeachMaterial, Long> {
    @Query(nativeQuery = true, value="select tm.* " +
            "from teach_material tm, press p " +
            "where tm.press_id = p.id and tm.state = 0 and tm.name = ?1 and tm.author = ?2 and tm.price = ?3 and tm.isbn = ?4 and p.name = ?5")
    public List<TeachMaterial> find(String tmName, String author, double price, String isbn, String pressName);
}
