package com.zs.dao.finance.studentexpense.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.finance.studentexpense.CenterPayDao;
import com.zs.tools.StringTools;

/**
 * 
 * @author yanghaosen
 *
 */
@Service("centerPayDao")
public class CenterPayDaoImpl extends BaseQueryDao implements CenterPayDao{

	@Override
	public PageInfo queryCenterPay(PageInfo pageInfo,
			Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
		
		//StringBuffer sql = new StringBuffer("from sync_province syp,sync_spot syt,sync_spot_province ssp,semester sem,spot_expense_oth seo");
		StringBuffer sql = new StringBuffer("from sync_spot syt,semester sem,spot_expense_oth seo");
		String field = "concat(sem.year,'年',case sem.quarter when 0 then '春季' else '秋季' end) as stuFie,'-' as proName,syt.code as potCode,syt.name as potName,IFNULL(seo.buy, 0) as buyTot,IFNULL(seo.pay, 0) as payTol,seo.clear_time as clearTime,seo.id as spotOwnId,IFNULL(seo.stu_own_tot, 0) as stuOwnTot,IFNULL(seo.stu_acc_tot, 0) as stuAccTot,sem.id ";
		
		//param
		//省中心ID
		String provinceId = paramsMap.get("provinceId");
		//学习中心ID
		String spotId = paramsMap.get("spotId");
		//学期
		String semesterId = paramsMap.get("semesterId");
		
		//where
		sql.append(" where seo.semester_id = sem.id and syt.code = seo.spot_code");
		
		List<Object> param = new ArrayList<Object>();
		
//		if(!StringUtils.isEmpty(provinceId)){
//			sql.append(" and syp.code = ?");
//			param.add(Integer.parseInt(provinceId));
//		}
		
		if(!StringUtils.isEmpty(spotId)){
			sql.append(" and syt.code = ?");
			param.add(Integer.parseInt(spotId));
		}
			
		if(!StringUtils.isEmpty(semesterId)){
			sql.append(" and sem.id = ?");
			param.add(Integer.parseInt(semesterId));
		}
		
		sql.append(" order by seo.spot_code,seo.semester_id");
		return super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
	}
	
	public List<Object[]> quryCenterPaySum(Map<String, String> paramsMap){
		
		//StringBuffer sql = new StringBuffer("from spot_expense_oth seo,sync_province syp,sync_spot syt,semester sem,sync_spot_province ssp");
		StringBuffer sql = new StringBuffer("from spot_expense_oth seo,sync_spot syt,semester sem");
		String field = "ifnull(sum(seo.pay), 0) as sumPay,ifnull(sum(seo.buy), 0) as sumBuy,ifnull(sum(seo.stu_own_tot), 0) as stuOwnTot,ifnull(sum(seo.stu_acc_tot), 0) as stuAccTot";
		sql.append(" where seo.semester_id = sem.id and syt.code = seo.spot_code");
		//param
		//省中心ID
		String provinceId = paramsMap.get("provinceId");
		//学习中心ID
		String spotId = paramsMap.get("spotId");
		//学期
		String semesterId = paramsMap.get("semesterId");
		
		List<Object> param = new ArrayList<Object>();
//		if(!StringUtils.isEmpty(provinceId)){
//			sql.append(" and syp.code = ?");
//			param.add(Integer.parseInt(provinceId));
//		}
		
		if(!StringUtils.isEmpty(spotId)){
			sql.append(" and syt.code = ?");
			param.add(Integer.parseInt(spotId));
		}
			
		if(!StringUtils.isEmpty(semesterId)){
			sql.append(" and sem.id = ?");
			param.add(Integer.parseInt(semesterId));
		}
		PageInfo pagInfo = new PageInfo();
		pagInfo.setCountOfCurrentPage(999999);
		pagInfo.setCurrentPage(1);
	    pagInfo = super.pageSqlQueryByNativeSql(pagInfo, sql.toString(), field, param.toArray());
	    
	    return pagInfo.getPageResults();
	}
	
	public PageInfo querySpotStuOwn(PageInfo pageInfo,Map<String, String> paramsMap){
		
		StringBuffer sql = new StringBuffer("from sync_student sst,student_expense ste");
		String field = "concat(sst.study_enter_year,case sst.study_quarter when 0 then '春季' else '秋季' end) as toStuYear,sst.code as stuCode,sst.name as stuName,sum(ifnull(ste.buy, 0) - ifnull(ste.pay, 0)) as stuOwn";
		
		sql.append(" where sst.code = ste.student_code and ifnull(ste.pay,0) < ifnull(ste.buy,0)");
		
		List<Object> param = new ArrayList<Object>();
		
		String spoCode = paramsMap.get("spoCode");
		if(!StringUtils.isEmpty(spoCode)){
			sql.append(" and sst.spot_code = ?");
			param.add(spoCode);
		}
		String semesterId = paramsMap.get("semesterId");
		if(!StringUtils.isEmpty(semesterId)){
			sql.append(" and ste.semester_id = ?");
			param.add(Long.parseLong(semesterId));
		}

		String toStuYear = paramsMap.get("toStuYear");
		if(!StringUtils.isEmpty(toStuYear)){
			sql.append(" and sst.study_enter_year = ?");
			param.add(Integer.parseInt(toStuYear));
		}
		String toStuQuarter = paramsMap.get("toStuQuarter");
		if(!StringUtils.isEmpty(toStuQuarter)){
			sql.append(" and sst.study_quarter = ?");
			param.add(Integer.parseInt(toStuQuarter));
		}
		String spec = paramsMap.get("spec");
		if(!StringUtils.isEmpty(spec)){
			sql.append(" and sst.spec_code = ?");
			param.add(spec);
		}
		String level = paramsMap.get("level");
		if(!StringUtils.isEmpty(level)){
			sql.append(" and sst.level_code = ?");
			param.add(level);
		}
		String stuCode = paramsMap.get("stuCode");
		if(!StringUtils.isEmpty(stuCode)){
			sql.append(" and sst.code = ?");
			param.add(stuCode);
		}
		String stuName = paramsMap.get("stuName");
		if(!StringUtils.isEmpty(stuName)){
			sql.append(" and sst.name = ?");
			param.add(stuName);
		}
		
		sql.append(" group by sst.code");
		
		return super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
	}
	
	public PageInfo querySpotPayPolTemp(PageInfo pageInfo,Map<String, String> paramsMap){
		
		StringBuffer sql = new StringBuffer("from spot_pay_pol_temp t1,sync_spot t2");
		String field = "t1.spot_code,t2.name,t1.spot_money,t1.creator,t1.create_time as createTime,t1.verifyer,t1.verify_time as verifyTime,t1.is_sure,t1.id as polId,t1.pay_way as payWay,t1.is_spot,t1.remark";
		sql.append(" where t1.spot_code = t2.code");
		
		List<Object> param = new ArrayList<Object>();
		String spoCode = paramsMap.get("spotCode");
		String state = paramsMap.get("state");
		if(!StringUtils.isEmpty(spoCode)){
			sql.append(" and t1.spot_code = ?");
			param.add(spoCode);
		}
		if(!StringUtils.isEmpty(state)){
			sql.append(" and t1.is_sure = ?");
			param.add(Integer.parseInt(state));
		}
		sql.append(" order by t1.is_sure asc,t1.create_time desc");
		return super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
	}

	@Override
	public PageInfo querySpotTemp(PageInfo pageInfo,Map<String, String> paramsMap) {
		
		pageInfo.setCountOfCurrentPage(999999);
		pageInfo.setCurrentPage(1);
		
		StringBuffer sql = new StringBuffer("from spot_pay_temp t1,sync_student t2,spot_pay_pol_temp t3");
		String field = "t1.stu_code,t2.name,t1.stu_money,t1.id";
		sql.append(" where t1.stu_code = t2.code");
		
		List<Object> param = new ArrayList<Object>();
		String polId = paramsMap.get("polId");
		if(!StringUtils.isEmpty(polId)){
			sql.append(" and t1.pol_id = t3.id");
			sql.append(" and t3.id = ?");
			param.add(polId);
		}
		
		return super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field,param.toArray());
	}
	
	
}
