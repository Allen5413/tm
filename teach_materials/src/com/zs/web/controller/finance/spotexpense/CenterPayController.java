package com.zs.web.controller.finance.spotexpense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.service.finance.spotexpenseoth.FindSpotExpenseOthForCountService;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.config.UserTypeEnum;
import com.zs.dao.finance.studentexpense.SpotPayPolTempDao;
import com.zs.domain.basic.Semester;
import com.zs.domain.finance.SpotPayPolTemp;
import com.zs.domain.sync.Province;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import com.zs.service.finance.spotexpense.SpotExpenseOthService;
import com.zs.service.sync.level.FindLevelService;
import com.zs.service.sync.province.FindProvinceByCodeService;
import com.zs.service.sync.spec.FindSpecService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotByProvCodeService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.service.sync.student.FindStudentListByWhereService;
import com.zs.tools.UpLoadFileTools;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;

/**
 *
 * @author yanghaosen
 *
 */
@Controller
@RequestMapping(value = "/centerPay")
public class CenterPayController extends LoggerController{

	private static Logger log = Logger.getLogger(CenterPayController.class);

	@Resource
	private SpotExpenseOthService spotExpenseOthService;
	@Resource
	private FindProvinceByCodeService findProvinceByCodeService;
	@Resource
	private FindSpotByCodeService findSpotByCodeService;
	@Resource
	private FindSpotService findSpotService;
	@Resource
	private FindSpotByProvCodeService findSpotByProvCodeService;
	@Resource
	private FindSpotExpenseOthForCountService findSpotExpenseOthForCountService;
	@Resource
	private FindSemesterPageByWhereService findSemesterPageByWhereService;
	@Resource
	private FindStudentListByWhereService findStudentListByWhereService;
	@Resource
	private FindSpecService findSpecService;
	@Resource
	private FindLevelService findLevelService;
	@Resource
	private SpotPayPolTempDao spotPayPolTempDao;

	@RequestMapping(value = "open")
	public String open(HttpServletRequest request){
		return setSearchData(request, "");
	}

	@RequestMapping(value = "querySpotPay")
	public String querySpotPay(HttpServletRequest request){
		try {
			String returnPath = setSearchData(request, "");

			String provinceId = request.getParameter("provinceId");
			String spotId = request.getParameter("spotId");
			String semesterId = request.getParameter("semesterId");
			String method = request.getParameter("method");
			if("search".equals(method)) {
				PageInfo pagInfo = this.getPageInfo(request);
				Map<String, String> params = new HashMap<String, String>();
				params.put("provinceId", StringUtils.isEmpty(provinceId) ? UserTools.getLoginUserForProvCode(request) : provinceId);
				params.put("spotId", StringUtils.isEmpty(spotId) ? UserTools.getLoginUserForSpotCode(request) : spotId);
				params.put("semesterId", semesterId);
				//该方法是以前直接查询spot_expense_oth表来获取数据，现在改用从学生财务表统计查询数据 2016-03-25
				//pagInfo = spotExpenseOthService.querySpotExpenseOth(pagInfo, params);
				JSONObject json = findSpotExpenseOthForCountService.findPageByWhere(pagInfo, params);
				request.setAttribute("pageInfo", json.get("pageInfo"));
				request.setAttribute("sumTotalPay", json.get("sumTotalPay"));
				request.setAttribute("sumTotalBuy", json.get("sumTotalBuy"));
				request.setAttribute("sumAcc", json.get("sumAcc"));
				request.setAttribute("sumOwn", json.get("sumOwn"));
			}
			return returnPath;
		}catch (Exception e){
			super.outputException(request, e, log, "查询学生账户消费统计");
			return "error";
		}
	}

	@RequestMapping(value = "toAddStu")
	public String toAddStu(HttpServletRequest request){

		String spotCode = request.getParameter("spotCode");
		String spotOwnId = request.getParameter("spotOwnId");
		request.setAttribute("spotCode", spotCode);
		request.setAttribute("isOpenDig", request.getParameter("isOpenDig"));
		request.setAttribute("spotOwnId", spotOwnId);

		return "finance/spotexpense/cpAddStu";
	}

	@RequestMapping(value = "openPayStu")
	public String openPayStu(HttpServletRequest request){
		//得到所有学期
		List<Semester> semesterList = findSemesterPageByWhereService.getAll();
		request.setAttribute("semesterList", semesterList);
		return "finance/spotexpense/cpPayStu";
	}

	@RequestMapping(value = "toPayStu")
	public String toPayStu(HttpServletRequest request){
		String spotCode = request.getParameter("spotCode");
		String spotOwnId = request.getParameter("spotOwnId");
		String semesterId = request.getParameter("semesterId");

		String toStuYear = request.getParameter("toStuYear");
		String toStuQuarter = request.getParameter("toStuQuarter");
		String spec = request.getParameter("spec");
		String level = request.getParameter("level");
		String stuCode = request.getParameter("stuCode");
		String stuName = request.getParameter("stuName");

		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("spoCode", spotCode);
		paramsMap.put("semesterId", semesterId);

		paramsMap.put("toStuYear", toStuYear);
		paramsMap.put("toStuQuarter", toStuQuarter);
		paramsMap.put("spec", spec);
		paramsMap.put("level", level);
		paramsMap.put("stuCode", stuCode);
		paramsMap.put("stuName", stuName);

		JSONObject jsonObject = this.spotExpenseOthService.querySpotStuOwn(paramsMap);
		request.setAttribute("jsonObject", jsonObject);
		request.setAttribute("spotCode", spotCode);
		request.setAttribute("spotOwnId", spotOwnId);
		request.setAttribute("speList", findSpecService.getAll());
		request.setAttribute("levelList",findLevelService.getAll());
		return "finance/spotexpense/cpPayStu";
	}

	@RequestMapping(value = "toFindPayStu")
	@ResponseBody
	public JSONObject toFindPayStu(HttpServletRequest request){
		String spotCode = request.getParameter("spotCode");
		String semesterId = request.getParameter("semesterId");
		String toStuYear = request.getParameter("toStuYear");
		String toStuQuarter = request.getParameter("toStuQuarter");
		String spec = request.getParameter("spec");
		String level = request.getParameter("level");
		String stuCode = request.getParameter("stuCode");
		String stuName = request.getParameter("stuName");

		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("spoCode", spotCode);
		paramsMap.put("semesterId", semesterId);
		paramsMap.put("toStuYear", toStuYear);
		paramsMap.put("toStuQuarter", toStuQuarter);
		paramsMap.put("spec", spec);
		paramsMap.put("level", level);
		paramsMap.put("stuCode", stuCode);
		paramsMap.put("stuName", stuName);

		JSONObject jsonObject = this.spotExpenseOthService.querySpotStuOwn(paramsMap);
		return jsonObject;
	}



	@RequestMapping(value = "downPayStu")
	@ResponseBody
	public String downPayStu(HttpServletRequest request){
		try {
			String spotCode = request.getParameter("spotCode");
			String semesterId = request.getParameter("semesterId");
			String toStuYear = request.getParameter("toStuYear");
			String toStuQuarter = request.getParameter("toStuQuarter");
			String spec = request.getParameter("spec");
			String level = request.getParameter("level");
			String stuCode = request.getParameter("stuCode");
			String stuName = request.getParameter("stuName");
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("spoCode", spotCode);
			paramsMap.put("semesterId", semesterId);
			paramsMap.put("toStuYear", toStuYear);
			paramsMap.put("toStuQuarter", toStuQuarter);
			paramsMap.put("spec", spec);
			paramsMap.put("level", level);
			paramsMap.put("stuCode", stuCode);
			paramsMap.put("stuName", stuName);
			String downUrl = "/excelDown/" + spotCode + ".xls";
			spotExpenseOthService.downSpotStuOwn(paramsMap, request.getRealPath("") + downUrl);
			return downUrl;
		}catch(Exception e){
			super.outputException(request, e, log, "下载老生欠款明细");
			return "error";
		}
	}

	@RequestMapping(value = "doAddStu")
	public String doAddStu(HttpServletRequest request)throws Exception{
		//param
		String toStuYear = request.getParameter("toStuYear");
		String toStuQuarter = request.getParameter("toStuQuarter");
		String spec = request.getParameter("spec");
		String level = request.getParameter("level");
		String stuCode = request.getParameter("stuCode");
		String stuName = request.getParameter("stuName");
		String spotCode = request.getParameter("spotCode");

		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("toStuYear",toStuYear);
		paramsMap.put("toStuQuarter",toStuQuarter);
		paramsMap.put("spec",spec);
		paramsMap.put("level",level);
		paramsMap.put("stuCode",stuCode);
		paramsMap.put("stuName",stuName);
		paramsMap.put("spotCode",spotCode);

		PageInfo pagInfo = findStudentListByWhereService.findStudentList(paramsMap);

		request.setAttribute("pagInfo", pagInfo);
		request.setAttribute("speList", findSpecService.getAll());
		request.setAttribute("levelList",findLevelService.getAll());
		request.setAttribute("spotCode",request.getParameter("spotCode"));

		return "finance/spotexpense/dataTablePage";
	}

	@RequestMapping(value = "toDoAddStu")
	public String toDoAddStu(HttpServletRequest request){

		request.setAttribute("speList", findSpecService.getAll());
		request.setAttribute("levelList",findLevelService.getAll());
		request.setAttribute("spotCode",request.getParameter("spotCode"));
		request.setAttribute("spotOwnId", request.getParameter("spotOwnId"));

		return "finance/spotexpense/doAddStu";
	}

	@RequestMapping(value = "toNextPage")
	public String toNextPage(HttpServletRequest request){

		String spotCode = request.getParameter("spotCode");

		Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
		if(null != spot){
			request.setAttribute("spoName",spot.getName());
			request.setAttribute("spotOwnId",request.getParameter("spotOwnId"));
			String sumPay = request.getParameter("sumPay");
			request.setAttribute("sumPay", request.getParameter("sumPay"));
			request.setAttribute("spotCode", spotCode);
		}
		return "finance/spotexpense/lastPayPage";
	}

	@RequestMapping(value = "sureTemPay")
	@ResponseBody
	public JSONObject sureTempPay(HttpServletRequest request,HttpServletResponse response) throws IOException{
		JSONObject jSONObject = new JSONObject();
		try {
			String subStr = request.getParameter("stuTempPay");
			String spotOwnId = request.getParameter("spotOwnId");
			String sumPay = request.getParameter("sumHid");
			String spotCode = request.getParameter("spotCode");
			String payWay = request.getParameter("payWay");
			String remark = request.getParameter("remark");

			MultipartRequest mulReu = (MultipartRequest)request;

			String[] stuPayArr = subStr.split("%");
			if(null != stuPayArr && stuPayArr.length > 0){
				for(String stuPay : stuPayArr){
					String[] payDetailArr = stuPay.split("\\$");
					if(null != payDetailArr && payDetailArr.length > 0){
						String stuCode = payDetailArr[0];
						double stuMon = Double.parseDouble(payDetailArr[2].toString());
						if(stuMon <= 0){
							throw new BusinessException("学号："+stuCode+", 金额不正确，或者小于等于0！");
						}
					}
				}
			}else{
				throw new BusinessException("请提交交费学生！");
			}

			//处理上传图片
			String imagUrl = UpLoadFileTools.uploadImg(request,mulReu.getFiles("payImg"),"jpg|png|jpeg",400,7, "/spotPayImg");
			spotExpenseOthService.saveTempPay(subStr, spotOwnId,UserTools.getLoginUserForName(request),sumPay,imagUrl,spotCode,payWay,remark);
			jSONObject.put("state", 0);
		} catch (Exception e) {
			String msg = super.outputException(request, e, log, "学生交费");
			jSONObject.put("state", 1);
			jSONObject.put("msg",msg);
		}
		return jSONObject;
	}

	@RequestMapping(value = "verifySpotPay")
	public String verifySpotPay(HttpServletRequest request){

		String spotCode = request.getParameter("spotCode");
		String state = request.getParameter("state");
		Map<String,String> paramsMap = new HashMap<String,String>();

		paramsMap.put("spotCode",spotCode);
		paramsMap.put("state",state);
		PageInfo pagInfo = this.getPageInfo(request);
		pagInfo = spotExpenseOthService.querySpotPayPolTemp(pagInfo, paramsMap);

		//获取学习中心数据
		List<Spot> spotList = null;

		//判断用户类型，如果是省中心用户，只能查看自己下面的学习中心的学生订单，如果是学习中心用户，只能查看自己中心的学生订单
		String loginType = UserTools.getLoginUserForLoginType(request);
		if(loginType.equals(UserTypeEnum.ADMIN.getValue())){
			spotList = findSpotService.getAll();
		}else if(loginType.equals(UserTypeEnum.PROVINCE.getValue())){
			String provCode = UserTools.getLoginUserForProvCode(request);
			spotList = findSpotByProvCodeService.getSpotForListByProvCode(provCode);
		}else if(loginType.equals(UserTypeEnum.SPOT.getValue())){
			Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
			if(null != spot) {
				spotList = new ArrayList<Spot>(1);
				spotList.add(spot);
			}
		}

		request.setAttribute("spotList", spotList);
		request.setAttribute("pageInfo", pagInfo);
		return "finance/spotexpense/spotPolPay";
	}

	@RequestMapping(value = "querySpotDetail")
	public String querySpotDetail(HttpServletRequest request){

		String polId = request.getParameter("polId");

		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("polId", polId);

		SpotPayPolTemp spotPayPolTemp = this.spotPayPolTempDao.get(Long.parseLong(polId));
		if(SpotPayPolTemp.IS_SPOT_NOT == spotPayPolTemp.getIsSpot()){
			PageInfo pagInfo = this.spotExpenseOthService.querySpotTemp(paramsMap);
			request.setAttribute("pagInfo",pagInfo);
		}
		if(SpotPayPolTemp.IS_SPOT_YES == spotPayPolTemp.getIsSpot()){
			Spot spot = findSpotByCodeService.getSpotByCode(spotPayPolTemp.getSpotCode());
			request.setAttribute("spot",spot);
		}

		request.setAttribute("spotPayPolTemp", spotPayPolTemp);

		return "finance/spotexpense/spotpoldetail";
	}

	@RequestMapping(value = "downSpotDetail")
	@ResponseBody
	public String downSpotDetail(HttpServletRequest request, HttpServletResponse response){

		try {
			String polId = request.getParameter("polId");

			Map<String,String> paramsMap = new HashMap<String,String>();
			paramsMap.put("polId", polId);

			String downUrl = "/excelDown/jfmx.xls";
			spotExpenseOthService.downSpotTemp(paramsMap, request.getRealPath("") + downUrl);
			return new String(downUrl.getBytes(), "ISO-8859-1");
		}catch(Exception e){
			super.outputException(request, e, log, "下载缴费明细");
			return "error";
		}
	}

	@RequestMapping(value = "downStudentOrderTm")
	@ResponseBody
	public String downStudentOrderTm(HttpServletRequest request){

		try {
			String codeMoneys = request.getParameter("codeMoneys");
			String downUrl = "/excelDown/fpmx.xls";
			spotExpenseOthService.downStudentOrderTm(codeMoneys, request.getRealPath("") + downUrl);
			return new String(downUrl.getBytes(), "ISO-8859-1");
		}catch(Exception e){
			super.outputException(request, e, log, "下载发票明细");
			return "error";
		}
	}

	@RequestMapping(value = "verifySure")
	@ResponseBody
	public JSONObject verifySure(HttpServletRequest request){
		String polId = request.getParameter("polId");
		String arrivalTime = request.getParameter("arrivalTime");
		String payType = request.getParameter("payType");
		String remark = request.getParameter("remark");
		String loginUserName = UserTools.getLoginUserForName(request);
		String loginName = UserTools.getLoginUserForLoginName(request);
		JSONObject jsonObj = new JSONObject();
		try {
			this.spotExpenseOthService.verifySure(Long.parseLong(polId), loginUserName, loginName, arrivalTime, payType, remark);
			jsonObj.put("verifyState", "1");
		}catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("verifyState", "0");
		}
		return jsonObj;
	}

	@RequestMapping(value = "verifyNotSure")
	@ResponseBody
	public JSONObject verifyNotSure(HttpServletRequest request){

		String polId = request.getParameter("polId");
		String userLoginName = UserTools.getLoginUserForName(request);
		JSONObject jsonObj = new JSONObject();
		try {
			this.spotExpenseOthService.verifyNotSure(Long.parseLong(polId), userLoginName);
			jsonObj.put("verifyState", "1");
		}catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("verifyState", "0");
		}

		return jsonObj;
	}

	private int getShowPayBtnAbt(HttpServletRequest request){
		String loginType = UserTools.getLoginUserForLoginType(request);
		return loginType.equals(UserTypeEnum.SPOT.getValue()) || loginType.equals(UserTypeEnum.ADMIN.getValue()) ? 1 : 0;
	}

	/**
	 * 根据不同的用户类型，设置页面上的查询数据。
	 * 如果是省中心用户，只能查看自己下面的学习中心的学生订单，
	 * 如果是学习中心用户，只能查看自己中心的学生订单，
	 * 管理员可以查所有的
	 * @param request
	 */
	private String setSearchData(HttpServletRequest request, String provCode){
		String returnPath = "finance/spotexpense/centerPayList";
		//获取学期数据
		List<Semester> semesterList = findSemesterPageByWhereService.getAll();
		//获取省中心数据
		List<Province> provinceList = null;
		//获取学习中心数据
		List<Spot> spotList = null;

		//判断用户类型，如果是省中心用户，只能查看自己下面的学习中心的学生订单，如果是学习中心用户，只能查看自己中心的学生订单
		String loginType = UserTools.getLoginUserForLoginType(request);
		if(loginType.equals(UserTypeEnum.ADMIN.getValue())){
			provinceList = findProvinceByCodeService.getAll();
			spotList = findSpotService.getAll();
		}
		if(loginType.equals(UserTypeEnum.PROVINCE.getValue())){
			provCode = StringUtils.isEmpty(provCode) ? UserTools.getLoginUserForProvCode(request) : provCode;
			Province province = findProvinceByCodeService.getProvinceByCode(provCode);
			if(null != province){
				provinceList = new ArrayList<Province>(1);
				provinceList.add(province);
			}
			spotList = findSpotByProvCodeService.getSpotForListByProvCode(provCode);
			returnPath = "provPages/finance/centerPayList";
		}
		if(loginType.equals(UserTypeEnum.SPOT.getValue())){
			//查询登录学习中心用户
			Spot spot = findSpotByCodeService.getSpotByCode(UserTools.getLoginUserForSpotCode(request));
			request.setAttribute("spot", spot);
			returnPath = "spotPages/finance/centerPayList";
		}

		request.setAttribute("semesterList", semesterList);
		request.setAttribute("provinceList", provinceList);
		request.setAttribute("spotList", spotList);
		request.setAttribute("isShowBtn", getShowPayBtnAbt(request));

		return returnPath;
	}
}
