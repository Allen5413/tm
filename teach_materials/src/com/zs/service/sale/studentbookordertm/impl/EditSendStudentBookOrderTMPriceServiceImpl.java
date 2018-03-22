package com.zs.service.sale.studentbookordertm.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.basic.teachmaterial.TeachMaterialDAO;
import com.zs.dao.sale.studentbookordertm.StudentBookOrderTmDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.finance.StudentExpenseBuy;
import com.zs.domain.sale.StudentBookOrderTM;
import com.zs.service.finance.studentexpensebuy.AddStudentExpenseBuyService;
import com.zs.service.sale.studentbookorder.CountSendStudentBookOrderByTMIdService;
import com.zs.service.sale.studentbookordertm.EditSendStudentBookOrderTMPriceService;
import com.zs.tools.DateTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by Allen on 2015/9/5.
 */
@Service("editSendStudentBookOrderTMPriceService")
public class EditSendStudentBookOrderTMPriceServiceImpl extends EntityServiceImpl implements EditSendStudentBookOrderTMPriceService {

    @Resource
    private CountSendStudentBookOrderByTMIdService countSendStudentBookOrderByTMIdService;
    @Resource
    private StudentBookOrderTmDAO studentBookOrderTmDAO;
    @Resource
    private AddStudentExpenseBuyService addStudentExpenseBuyService;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private TeachMaterialDAO teachMaterialDAO;

    @Override
    @Transactional
    public void editSendStudentBookOrderTMPrice(Map<String, String> paramsMap, Map<String, Boolean> sortMap, String loginName) throws Exception {
        JSONArray jsonArray = countSendStudentBookOrderByTMIdService.countSendStudentBookOrderByTMId(paramsMap, sortMap);
        if(null != jsonArray && 0 < jsonArray.size()){
            //获取教材信息
            TeachMaterial teachMaterial = teachMaterialDAO.get(Long.parseLong(paramsMap.get("tmId")));
            if(null == teachMaterial){
                throw new BusinessException("没有找到教材信息");
            }
            //获取当前学期
            Semester semester = findNowSemesterDAO.getNowSemester();
            float newPrice = Float.valueOf(paramsMap.get("newPrice"));
            Timestamp operateTime = DateTools.getLongNowTime();
            for (int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                long sbotmId = Long.parseLong(jsonObject.get("sbotmId").toString());
                StudentBookOrderTM studentBookOrderTM = studentBookOrderTmDAO.get(sbotmId);
                if(null != studentBookOrderTM){
                    //修改教材明细里面的价格
                    studentBookOrderTM.setPrice(newPrice);
                    studentBookOrderTM.setOperator(loginName);
                    studentBookOrderTM.setOperateTime(operateTime);
                    studentBookOrderTmDAO.update(studentBookOrderTM);

                    //追加学生的消费记录
                    String studentCode = jsonObject.get("code").toString();
                    String tmName = teachMaterial.getName();
                    float oldPrice = Float.parseFloat(jsonObject.get("price").toString());
                    float money = new BigDecimal(newPrice).subtract(new BigDecimal(oldPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                    if(0 != money) {
                        StudentExpenseBuy studentExpenseBuy = new StudentExpenseBuy();
                        studentExpenseBuy.setStudentCode(studentCode);
                        studentExpenseBuy.setSemester(semester);
                        studentExpenseBuy.setType(StudentExpenseBuy.TYPE_TM_UPDATE_PRICE);
                        studentExpenseBuy.setMoney(new BigDecimal(money).multiply(new BigDecimal(studentBookOrderTM.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
                        studentExpenseBuy.setDetail("教材 [" + tmName + "] 价格由" + oldPrice + ", 变为" + newPrice + "，产生的差额");
                        studentExpenseBuy.setCreator(loginName);
                        studentExpenseBuy.setCreateTime(operateTime);
                        addStudentExpenseBuyService.addStudentExpenseBuy(studentExpenseBuy, loginName);
                    }
                }
            }
        }
    }
}
