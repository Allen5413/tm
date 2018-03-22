package com.zs.dao.orderbook.teachmaterialratio.impl;

import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.BaseQueryDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/11.
 */
@Service("countNewStudentChannelTMtypeTMCountForAddDAO")
public class CountNewStudentChannelTMtypeTMCountForAddDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        int year = Integer.parseInt(paramsMap.get("year"));
        int quarter = Integer.parseInt(paramsMap.get("quarter"));
        int enterYear = Integer.parseInt(paramsMap.get("enterYear"));
        int enterQuarter = Integer.parseInt(paramsMap.get("enterQuarter"));

        StringBuilder sql = new StringBuilder();
        //统计教材的
        sql.append("select ir.issue_channel_id, tm.teach_material_type_id, bs.course_code, tm.id, count(*) as count ");
        sql.append("from sync_begin_schedule bs, sync_student s, issue_range ir, teach_material_course tmc, teach_material tm ");
        sql.append("where bs.spec_code = s.spec_code and bs.level_code = s.level_code and s.spot_code = ir.spot_code and bs.course_code = tmc.course_code and tmc.teach_material_id = tm.id ");
        sql.append("and tm.teach_material_type_id is not null and tm.state = 0 and ir.is_issue = 0 and s.state = 0 and s.spot_code between '001' and '400' and s.state = 0 and bs.enter_year = ? and bs.quarter = ? and bs.academic_year = ? and bs.term = ? and s.study_enter_year = ? and s.study_quarter = ? ");
        sql.append("group by ir.issue_channel_id, tm.teach_material_type_id, bs.course_code, tm.id ");
        //sql合并
        sql.append("union all ");
        //统计套教材的
        sql.append("select ir.issue_channel_id, tm.teach_material_type_id, bs.course_code, tm.id, count(*) as count ");
        sql.append("from sync_begin_schedule bs, sync_student s, issue_range ir, set_teach_material stm, set_teach_material_tm stmtm, teach_material tm ");
        sql.append("where bs.spec_code = s.spec_code and bs.level_code = s.level_code and s.spot_code = ir.spot_code and bs.course_code = stm.buy_course_code and stm.id = stmtm.set_teach_material_id and stmtm.teach_material_id = tm.id ");
        sql.append("and tm.teach_material_type_id is not null and tm.state = 0 and ir.is_issue = 0 and s.state = 0 and s.spot_code between '001' and '400' and s.state = 0 and bs.enter_year = ? and bs.quarter = ? and bs.academic_year = ? and bs.term = ? and s.study_enter_year = ? and s.study_quarter = ? ");
        sql.append("group by ir.issue_channel_id, tm.teach_material_type_id, bs.course_code, tm.id");

        Object[] param = {year, quarter, year, quarter, enterYear, enterQuarter, year, quarter, year, quarter, enterYear, enterQuarter};

        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), param);
        return list;
    }
}
