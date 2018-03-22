package com.zs.domain.sync;

import com.feinno.framework.common.domain.AbstractEntity;
import com.zs.tools.DateTools;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Allen on 2016/9/8.
 */
@Entity
@Table(name = "spot_wx")
public class SpotWx  extends AbstractEntity {

    private Long id;            //主键
    private String code;        //编号
    private String idcard;      //身份证号
    private String adminName;   //管理员姓名
    private String openId;     //openId
    private Date boundTime;     //绑定时间
    private String operator;    //操作人
    private Date operateTime = new Date();  //操作时间
    private String operateTimeStr;
    private String boundTimeStr;
    private boolean isBound;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdcard() {
        return null == idcard ? "" : idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAdminName() {
        return null == adminName ? "" : adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getOpenId() {
        return null == openId ? "" : openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getBoundTime() {
        return boundTime;
    }

    public void setBoundTime(Date boundTime) {
        this.boundTime = boundTime;
    }

    public String getOperator() {
        return null == operator ? "" : operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Transient
    public String getOperateTimeStr() {
        return DateTools.getFormattedString(getOperateTime(), DateTools.longDatePattern);
    }

    @Transient
    public String getBoundTimeStr() {
        if(null == getBoundTime()){
            return "";
        }else {
            return DateTools.getFormattedString(getBoundTime(), DateTools.longDatePattern);
        }
    }

    @Transient
    public boolean getIsBound() {
        return StringUtils.isEmpty(getOpenId()) ? false : true;
    }
}
