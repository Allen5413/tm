package com.zs.service.sync.student.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.sync.level.LevelDAO;
import com.zs.dao.sync.spec.SpecDAO;
import com.zs.domain.sync.Level;
import com.zs.domain.sync.Spec;
import com.zs.service.sync.student.FindStudentListByWhereService;

@Service("findStudentListByWhereService")
public class FindStudentListByWhereServiceImpl implements FindStudentListByWhereService{
	
	@Resource 
	private FindPageByWhereDAO findStudentPageByWhereDAO;
	@Resource
	private SpecDAO specDAO;
	@Resource
	private LevelDAO levelDAO;

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Exception.class)
	public PageInfo findStudentList(Map<String, String> paramMap)throws Exception{
		PageInfo pagInfo = new PageInfo();
		pagInfo.setCurrentPage(1);
		pagInfo.setCountOfCurrentPage(999999);
		pagInfo = findStudentPageByWhereDAO.findPageByWhere(pagInfo, paramMap, null);
		if(null != pagInfo){
			List<Object[]> datList = pagInfo.getPageResults();
			JSONArray jsonArray = new JSONArray();
			if(null != datList && datList.size() > 0){
				for(Object[] objArr : datList){
					JSONObject jsonObj = new JSONObject();
					//jsonObj.put("proCode", objArr[0]);
					jsonObj.put("spoCode", objArr[0]);
					jsonObj.put("toStuFie", objArr[1]);
					
					String speCode = (String)objArr[4];
					Spec spec = this.specDAO.querySpecByCode(speCode);
					if(null != spec){
						jsonObj.put("spe", spec.getName());
					}else{
						jsonObj.put("spe", "");
					}
					String levelCode = (String)objArr[5];
					Level level = levelDAO.queryLevelByCode(levelCode);
					if(null != level){
						jsonObj.put("level", level.getName());
					}else{
						jsonObj.put("level", "");
					}
					
					jsonObj.put("stuCode", objArr[2]);
					jsonObj.put("stuName", objArr[3]);
					jsonArray.add(jsonObj);
				}
			}
			pagInfo.setPageResults(jsonArray);
		}
		
		return pagInfo;
	}

}
