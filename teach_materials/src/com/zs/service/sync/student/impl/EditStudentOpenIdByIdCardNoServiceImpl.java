package com.zs.service.sync.student.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.student.FindStudentByIdCardNoDAO;
import com.zs.dao.sync.student.StudentDAO;
import com.zs.domain.sync.Student;
import com.zs.service.sync.student.EditStudentOpenIdByIdCardNoService;
import com.zs.service.sync.student.FindStudentByOpenIdService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created by Allen on 2016/5/24.
 */
@Service("editStudentOpenIdByIdCardNoService")
public class EditStudentOpenIdByIdCardNoServiceImpl extends EntityServiceImpl<Student, StudentDAO> implements EditStudentOpenIdByIdCardNoService {

    @Resource
    private FindStudentByIdCardNoDAO findStudentByIdCardNoDAO;
    @Resource
    private FindStudentByOpenIdService findStudentByOpenIdService;

    @Override
    @Transactional
    public synchronized JSONObject edit(String idCardNo, String openId, Timestamp wxBoundTime) throws Exception {
        int flag = 0;
        String msg = "";
        if(StringUtils.isEmpty(idCardNo)){
            flag = 1;
            msg = "没有传入证件号码";
        }
        if(StringUtils.isEmpty(openId)){
            flag = 1;
            msg = "没有传入openId";
        }
        Student student2 = findStudentByOpenIdService.find(openId);
        if(null != student2){
            flag = 2;
            msg = "您已经绑定了，如需重新绑定请先在更多服务里面解除绑定。";
        }else{
            Student student = findStudentByIdCardNoDAO.find(idCardNo);
            if(null == student){
                flag = 1;
                msg = "没有找到用户信息";
            }else{
                if(!StringUtils.isEmpty(student.getOpenId())){
                    flag = 1;
                    msg = "该证件号码已经被绑定了";
                }else {
                    student.setOpenId(openId);
                    student.setWxBoundTime(wxBoundTime);
                    super.update(student);
                    msg = student.getName();
                }
            }
        }
        JSONObject json = new JSONObject();
        json.put("flag", flag);
        json.put("msg", msg);
        return json;
    }

    @Override
    public JSONObject cancelBound(String openId) throws Exception {
        JSONObject json = new JSONObject();
        int flag = 0;
        String msg = "";
        if(StringUtils.isEmpty(openId)){
            flag = 1;
            msg = "没有传入openId";
        }
        Student student = findStudentByOpenIdService.find(openId);
        if(null == student){
            flag = 1;
            msg = "你目前还没有绑定该公众号";
        }else{
            msg = "解除绑定";
            String time = null == student.getWxBoundTime() ? "" : student.getWxBoundTime().toString();
            String name = student.getName();
            student.setOpenId("");
            student.setWxBoundTime(null);
            super.update(student);

            json.put("time", time);
            json.put("name", name);
        }

        json.put("flag", flag);
        json.put("msg", msg);
        return json;
    }
}
