package com.zs.web.controller.finance.invoice;

import com.zs.domain.finance.Invoice;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.service.finance.invoice.EditInvoiceForMoneyService;
import com.zs.service.finance.invoice.FindInvoiceForStateOpenByStudentCodeService;
import com.zs.service.finance.invoice.FindTotalPayAndTotalInvoiceBySpotCodeService;
import com.zs.service.finance.studentexpensepay.FindStudentExpensePayByCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/5/6.
 */
@Controller
@RequestMapping(value = "/editInvoiceForMoney")
public class EditInvoiceForMoneyConlloer extends LoggerController<Invoice, EditInvoiceForMoneyService> {
    private static Logger log = Logger.getLogger(EditInvoiceForMoneyConlloer.class);

    @Resource
    private EditInvoiceForMoneyService editInvoiceForMoneyService;
    @Resource
    private FindInvoiceForStateOpenByStudentCodeService findInvoiceForStateOpenByStudentCodeService;
    @Resource
    private FindStudentExpensePayByCodeService findStudentExpensePayByCodeService;
    @Resource
    private FindTotalPayAndTotalInvoiceBySpotCodeService findTotalPayAndTotalInvoiceBySpotCodeService;


    @RequestMapping(value = "open")
    public String open(@RequestParam("id") long id,
                       HttpServletRequest request) {
        try {
            Invoice invoice = editInvoiceForMoneyService.get(id);

            double money = invoice.getMoney();
            BigDecimal openMoney = new BigDecimal(0);
            BigDecimal totalPay = new BigDecimal(0);
            BigDecimal canMoney = new BigDecimal(0);
            if(null != invoice){
                String studentCode = invoice.getStudentCode();
                //说明是开总票的
                if(StringUtils.isEmpty(studentCode)){
                    //查询该中心还能交多少钱的发票
                    JSONObject json = findTotalPayAndTotalInvoiceBySpotCodeService.find(invoice.getSpotCode());
                    double temp = new BigDecimal(json.get("payTotal").toString()).subtract(new BigDecimal(json.get("invoiceTotal").toString()))
                            .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    request.setAttribute("canMoney", new BigDecimal(money).add(new BigDecimal(temp)).setScale(2,BigDecimal.ROUND_HALF_UP));
                }else{
                    //开学生明细票

                    //查询该学生已经开过的发票
                    List<Invoice> list = findInvoiceForStateOpenByStudentCodeService.find(studentCode);
                    if(null != list && 0 < list.size()){
                        for(Invoice oldInvoice : list){
                            openMoney = openMoney.add(new BigDecimal(oldInvoice.getMoney()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                    }

                    //查询学生一共交的费用
                    Map<String, String> params = new HashMap<String, String>();
                    List<StudentExpensePay> studentExpensePayList = findStudentExpensePayByCodeService.getStudentExpensePayByCode(studentCode);
                    if(null != studentExpensePayList && 0 < studentExpensePayList.size()){
                        for(StudentExpensePay studentExpensePay : studentExpensePayList){
                            totalPay = totalPay.add(new BigDecimal(studentExpensePay.getMoney()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                    }

                    //查询该中心还能交多少钱的发票
                    JSONObject json = findTotalPayAndTotalInvoiceBySpotCodeService.find(invoice.getSpotCode());
                    double temp = new BigDecimal(json.get("payTotal").toString()).subtract(new BigDecimal(json.get("invoiceTotal").toString()))
                            .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();


                    canMoney = totalPay.subtract(openMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
                    if(canMoney.doubleValue() <= temp) {
                        request.setAttribute("canMoney", canMoney);
                    }else{
                        request.setAttribute("canMoney", new BigDecimal(temp).add(new BigDecimal(money)));
                    }
                    request.setAttribute("money", money);
                }
                request.setAttribute("money", money);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "finance/invoiceForMoneyEdit";
    }

    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request,
                          @RequestParam("id") long id,
                          @RequestParam("money") double money){
        JSONObject jsonObject = new JSONObject();
        try{
            editInvoiceForMoneyService.edit(id, money, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改发票金额");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
