package com.zs.dao.finance.studentexpense;

import java.util.List;
import java.util.Map;

import com.feinno.framework.common.dao.support.PageInfo;

/**
 * 
 * @author yanghaosen
 *
 */
public interface CenterPayDao {
	
	public PageInfo queryCenterPay(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap);
	
	public PageInfo querySpotStuOwn(PageInfo pageInfo,Map<String, String> paramsMap);
	
	public PageInfo querySpotPayPolTemp(PageInfo pageInfo,Map<String, String> paramsMap);
	
	public PageInfo querySpotTemp(PageInfo pageInfo,Map<String,String> paramsMap);
	
	public List<Object[]> quryCenterPaySum(Map<String, String> paramsMap);
}
