package com.zs.dao.sync.student.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.sync.Student;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
@Service("findStudentPageByWhereDAO")
public class FindStudentPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {

    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<Student> studentPageInfo = new PageInfo<Student>();
        studentPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        studentPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        //StringBuilder sql = new StringBuilder("FROM sync_student t1,sync_province t2,sync_spot t3,sync_spot_province t6");
        StringBuilder sql = new StringBuilder("FROM sync_student t1,sync_spot t3");
        String field = "t3.name as spoName,concat(t1.study_enter_year,case t1.study_quarter when 0 then '春季' else '秋季' end) as toStuFie,t1.code as stuCode,t1.name as stuName,t1.spec_code as speCode,t1.level_code as levelCode,t1.is_forever_sned_tm,t1.id";
        
        sql.append(" where t1.spot_code = t3.code");
        
        List<Object> param = new ArrayList<Object>();
        //查询条件,入学年
        try{
        	param.add(Integer.parseInt(paramsMap.get("toStuYear")));
        	sql.append(" and t1.study_enter_year = ?");
        }catch(Exception e){}
        
        //查询条件,入学季
        try{
        	param.add(Integer.parseInt(paramsMap.get("toStuQuarter")));
        	sql.append(" and t1.study_quarter = ?");
        }catch(Exception e){}
       
        //查询条件,专业
        String spec = paramsMap.get("spec");
        if(!StringUtils.isEmpty(spec)){
        	sql.append(" and t1.spec_code = ?");
        	param.add(spec);
        	
        }
        
        //查询条件,层次
        String level = paramsMap.get("level");
        if(!StringUtils.isEmpty(level)){
        	sql.append(" and t1.level_code = ?");
        	param.add(level);
        	
        }
        
        //查询条件，学号
        String code = paramsMap.get("stuCode");
        if(!StringUtils.isEmpty(code)){
        	sql.append(" and ? like concat('%',t1.code,'%')");
        	param.add(code);
        	
        }
        
        //查询条件，姓名
        String stuName = paramsMap.get("name");
        if(!StringUtils.isEmpty(stuName)){
        	sql.append(" and t1.name like concat('%',?,'%')");
        	param.add(stuName);
        	
        }
        
        //查询条件，中心
        String spotCode = paramsMap.get("spotCode");
        if(!StringUtils.isEmpty(spotCode)){
        	sql.append(" and t3.code = ?");
        	param.add(spotCode);
        	
        }
        
        //sql.append(" order by t1.study_enter_year, t1.code ");
        return super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
    }
}
