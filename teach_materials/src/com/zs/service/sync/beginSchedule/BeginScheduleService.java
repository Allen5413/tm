package com.zs.service.sync.beginSchedule;

import java.util.List;
import java.util.Map;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.BeginSchedule;
import com.zs.domain.sync.BeginScheduleBean;

public interface BeginScheduleService extends EntityService<BeginSchedule>{
	
	public List<BeginScheduleBean> queryBeginScheduleOnGroup(Map<String,String> paramMap);
}
