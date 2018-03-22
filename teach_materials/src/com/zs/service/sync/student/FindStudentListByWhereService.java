package com.zs.service.sync.student;

import java.util.Map;

import com.feinno.framework.common.dao.support.PageInfo;

public interface FindStudentListByWhereService {
	
	public PageInfo findStudentList(Map<String,String> paramMap)throws Exception;
}
