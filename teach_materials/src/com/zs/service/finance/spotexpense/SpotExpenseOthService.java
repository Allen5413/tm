package com.zs.service.finance.spotexpense;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.finance.SpotPayTemp;
import net.sf.json.JSONObject;

/**
 * 
 * @author yanghaosen
 *
 */
public interface SpotExpenseOthService {
	
	public PageInfo querySpotExpenseOth(PageInfo pageInfo, Map<String, String> map);
	
	public JSONObject querySpotStuOwn(Map<String,String> paramsMap);

	public String downSpotStuOwn(Map<String, String> map, String fileName)throws Exception;
	
	public void saveTempPay(String subStr,String spotOwnId,String userLoginName,String sumPay,String imagUrl,String spotCode,String payWay,String remark);
	
	public PageInfo querySpotPayPolTemp(PageInfo pageInfo,Map<String, String> paramsMap);
	
	public List<SpotPayTemp> querySpotPayTempByPolId(Long polId);
	
	public PageInfo querySpotTemp(Map<String,String> paramsMap);

	public String downSpotTemp(Map<String,String> paramsMap, String fileName)throws Exception;
	
	public void verifySure(Long spotPolId, String userLoginName, String loginName, String arrivalTime, String payType, String remark) throws Exception;
	
	public void verifyNotSure(Long spotPolId,String userLoginName) throws Exception;

	public String downStudentOrderTm(String codeMoneys, String fileName)throws Exception;
}
