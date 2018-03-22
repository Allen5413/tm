package com.zs.service.sync.beginSchedule.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.beginschedule.BeginScheduleDAO;
import com.zs.dao.sync.level.LevelDAO;
import com.zs.dao.sync.spec.SpecDAO;
import com.zs.domain.sync.BeginSchedule;
import com.zs.domain.sync.BeginScheduleBean;
import com.zs.domain.sync.Level;
import com.zs.domain.sync.Spec;
import com.zs.service.sync.beginSchedule.BeginScheduleService;

@Service("beginScheduleService")
public class BeginScheduleServiceImpl extends EntityServiceImpl<BeginSchedule,BeginScheduleDAO> implements BeginScheduleService{
	
	@Resource
	private BeginScheduleDAO beginScheduleDAO;
	
	@Resource
	private LevelDAO levelDAO;
	
	@Resource
	private SpecDAO specDAO;

	@Override
	public List<BeginScheduleBean> queryBeginScheduleOnGroup(Map<String,String> paramMap) {
		List<BeginSchedule> beginSchList = beginScheduleDAO.queryBeginScheduleOnGroup(Integer.parseInt(paramMap.get("enYear")),Integer.parseInt(paramMap.get("enQuarter")),paramMap.get("specCode"),paramMap.get("levelCode"));
		List<BeginScheduleBean> resultList = null;
		if(null != beginSchList && beginSchList.size() > 0){
			resultList = new ArrayList<BeginScheduleBean>();
			for(BeginSchedule beginSchedule : beginSchList){
				BeginScheduleBean beginScheduleBean = new BeginScheduleBean();
				beginScheduleBean.setAcademicYear(beginSchedule.getAcademicYear());
				beginScheduleBean.setEnterYear(beginSchedule.getEnterYear());
				beginScheduleBean.setLevelCode(beginSchedule.getLevelCode());
				Level level = levelDAO.queryLevelByCode(beginSchedule.getLevelCode());
				beginScheduleBean.setLevelName(null != level ? level.getName() : "");
				beginScheduleBean.setQuarter(beginSchedule.getQuarter());
				beginScheduleBean.setSpecCode(beginSchedule.getSpecCode());
				Spec spec = specDAO.querySpecByCode(beginSchedule.getSpecCode());
				beginScheduleBean.setSpecName(null != spec ? spec.getName() : "");
				beginScheduleBean.setTerm(beginSchedule.getTerm());
				
				resultList.add(beginScheduleBean);
			}
		}
		
		return resultList;
	}

}
