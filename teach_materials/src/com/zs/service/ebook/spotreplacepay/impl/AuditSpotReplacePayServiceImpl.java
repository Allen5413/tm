package com.zs.service.ebook.spotreplacepay.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.ebook.spotreplacepay.SpotReplacePayDAO;
import com.zs.domain.ebook.SpotReplacePay;
import com.zs.domain.ebook.StudentEBookPay;
import com.zs.domain.sync.Spot;
import com.zs.domain.sync.Student;
import com.zs.service.ebook.spotreplacepay.AuditSpotReplacePayService;
import com.zs.service.ebook.spotreplacepaydetail.FindSpotReplacePayDetailBySrpIdService;
import com.zs.service.ebook.studentebookpay.AddStudentEbookPayService;
import com.zs.service.ebook.studentebookpay.EditStudentEbookPayForQsService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.tools.DateTools;
import com.zs.tools.HttpRequestTools;
import com.zs.tools.MD5Tools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Allen on 2018/1/5.
 */

@Service("auditSpotReplacePayService")
public class AuditSpotReplacePayServiceImpl extends EntityServiceImpl<SpotReplacePay, SpotReplacePayDAO>
    implements AuditSpotReplacePayService{

    @Resource
    private AddStudentEbookPayService addStudentEbookPayService;
    @Resource
    private FindSpotReplacePayDetailBySrpIdService findSpotReplacePayDetailBySrpIdService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private EditStudentEbookPayForQsService editStudentEbookPayForQsService;

    @Override
    public void audit(long id, int state, String arrivalTime, int payType, String remark, String loginName) throws Exception {
        SpotReplacePay spotReplacePay = super.get(id);
        spotReplacePay.setState(state);
        spotReplacePay.setVerifyer(loginName);
        spotReplacePay.setVerifyTime(DateTools.getLongNowTime());
        super.save(spotReplacePay);

        if(state == SpotReplacePay.STATE_PASS) {
            List<JSONObject> list = findSpotReplacePayDetailBySrpIdService.find(id);
            if (null != list && 0 < list.size()) {
                for (JSONObject json : list) {
                    StudentEBookPay studentEBookPay = new StudentEBookPay();
                    studentEBookPay.setStudentCode(json.get("code").toString());
                    studentEBookPay.setMoney(Long.parseLong(json.get("moneyLong").toString()));
                    studentEBookPay.setArrivalTime(DateTools.getFormatDate(arrivalTime, "yyyy-MM-dd HH:mm:ss"));
                    studentEBookPay.setCreator(loginName);
                    studentEBookPay.setPayType(payType);
                    studentEBookPay.setRemark(remark);
                    addStudentEbookPayService.add(studentEBookPay);

                    //推送数据给青书
                    Student student = findStudentByCodeService.getStudentByCode(json.get("code").toString());
                    if(null != student) {
                        Spot spot = findSpotByCodeService.getSpotByCode(student.getSpotCode());
                        String buyerPayAmount = new BigDecimal(json.get("moneyLong").toString()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                        JSONObject resultJSON = HttpRequestTools.reqQingShu(student.getCode(), student.getName(), spot.getName(), student.getIdcardType(),
                                student.getIdcardNo(), student.getMobile(), buyerPayAmount);
                        if(null != resultJSON) {
                            JSONObject data = StringUtils.isEmpty(resultJSON.get("Data").toString()) || "null".equals(resultJSON.get("Data").toString().toLowerCase()) ? null : (JSONObject) resultJSON.get("Data");
                            editStudentEbookPayForQsService.edit(student.getCode(),
                                    null == resultJSON.get("HR") ? "" : resultJSON.get("HR").toString(),
                                    null == resultJSON.get("ErrorMessage") ? "" : resultJSON.get("ErrorMessage").toString(),
                                    null == resultJSON.get("Remark") ? "" : resultJSON.get("Remark").toString(),
                                    null == data || null == data.get("QingShuOrderNum") ? "" : data.get("QingShuOrderNum").toString());
                        }
                    }
                }
            }
        }
    }
}
