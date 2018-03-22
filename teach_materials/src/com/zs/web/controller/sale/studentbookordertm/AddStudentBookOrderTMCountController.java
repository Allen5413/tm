package com.zs.web.controller.sale.studentbookordertm;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.IssueRange;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderTM;
import com.zs.domain.sync.Student;
import com.zs.service.basic.issuerange.FindIssueRangeBySpotCodeService;
import com.zs.service.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderByCodeService;
import com.zs.service.sale.studentbookordertm.StudentBookOrderTMService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 增加学生订单明细教材数量
 * Created by Allen on 2015/7/13.
 */
@Controller
@RequestMapping(value = "/addStudentBookOrderTMCount")
public class AddStudentBookOrderTMCountController  extends
        LoggerController<StudentBookOrderTM, StudentBookOrderTMService> {

    private static Logger log = Logger.getLogger(AddStudentBookOrderTMCountController.class);

    @Resource
    private StudentBookOrderTMService studentBookOrderTMService;
    @Resource
    private FindStudentBookOrderByCodeService findStudentBookOrderByCodeService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private FindIssueRangeBySpotCodeService findIssueRangeBySpotCodeService;
    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdService findTeachMaterialStockBytmIdAndChannelIdService;


    /**
     * 新增教材数量
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(@RequestParam("id") long id,
            @RequestParam("count") int count,
            HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            StudentBookOrderTM studentBookOrderTM = studentBookOrderTMService.get(id);
            if(null == studentBookOrderTM){
                throw new BusinessException("没有找到信息记录");
            }
            //查询订单信息
            StudentBookOrder studentBookOrder = findStudentBookOrderByCodeService.findStudentBookOrderByCode(studentBookOrderTM.getOrderCode());
            if(null == studentBookOrder){
                throw new BusinessException("没有找到该明细的订单信息记录");
            }
            //查询学生信息
            Student student = findStudentByCodeService.getStudentByCode(studentBookOrder.getStudentCode());
            if(null != student){
                IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(student.getSpotCode());
                if(null != issueRange){
                    TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdService.findTeachMaterialStockBytmIdAndChannelId(studentBookOrderTM.getTeachMaterialId(), issueRange.getIssueChannelId());
                    if((null == teachMaterialStock || null == teachMaterialStock.getStock() || count > teachMaterialStock.getStock()) && 0 < count){
                        throw new BusinessException("该教材库存不足，不能在添加了");
                    }
                }
            }
            if(1 < studentBookOrderTM.getCount() + count){
                throw new BusinessException("教材数量不能大于1");
            }
            if(0 > studentBookOrderTM.getCount() + count){
                throw new BusinessException("教材数量不能小于0");
            }
            studentBookOrderTM.setCount(studentBookOrderTM.getCount() + count);
            studentBookOrderTM.setOperator(UserTools.getLoginUserForName(request));
            studentBookOrderTMService.update(studentBookOrderTM);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改数量");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
