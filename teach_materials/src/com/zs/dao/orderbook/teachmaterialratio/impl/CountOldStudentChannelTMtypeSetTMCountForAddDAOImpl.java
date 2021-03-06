package com.zs.dao.orderbook.teachmaterialratio.impl;

import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.BaseQueryDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 自动生成订购单，统计旧生的渠道教材类型下的套教材数量，单个教材没在里面
 * Created by Allen on 2015/5/11.
 */
@Service("countOldStudentChannelTMtypeSetTMCountForAddDAO")
public class CountOldStudentChannelTMtypeSetTMCountForAddDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        String sql = "select ir.issue_channel_id, tm.teach_material_type_id, bs.course_code, tm.id, count(*) as count from sync_begin_schedule bs, sync_student s, issue_range ir, set_teach_material stm, set_teach_material_tm stmtm, teach_material tm " +
                " where bs.enter_year = s.study_enter_year and bs.quarter = s.study_quarter and bs.spec_code = s.spec_code and bs.level_code = s.level_code and s.spot_code = ir.spot_code and bs.course_code = stm.buy_course_code and stm.id = stmtm.set_teach_material_id and stmtm.teach_material_id = tm.id " +
                " and tm.teach_material_type_id is not null and tm.state = 0 and ir.is_issue = 0 and bs.academic_year = ? and term = ? group by ir.issue_channel_id, tm.teach_material_type_id, tm.id";

        int year = Integer.parseInt(paramsMap.get("year"));
        int quarter = Integer.parseInt(paramsMap.get("quarter"));

        Object[] param = {year, quarter};

        List<Object[]> list = super.sqlQueryByNativeSql(sql, param);
        return list;
    }
}
