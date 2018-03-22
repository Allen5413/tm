package com.zs.domain.finance;

import com.feinno.framework.common.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 退款批次关联的学生
 * Created by Allen on 2016/1/4.
 */
@Entity
@Table(name = "refund_student")
public class RefundStudent extends AbstractEntity {

    public final static int STATE_WAIT = 0;
    public final static int STATE_PASS = 1;
    public final static int STATE_NOT_PASS = 2;

    private Long id;                        //主键id
    private String code;                    //退款批次编号
    private String studentCode;             //学号
    private double money;                   //金额
    private int state;                      //状态 【0：待审核，1：通过，2：未通过】
    private String refundDetail;            //退款说明
    private String auditDetail;             //审核说明
    private String creator;                 //创建人
    private Date createTime = new Date();   //创建时间
    private String operator;                //操作人
    private Date operateTime = new Date();  //操作时间
    private Integer version;                //版本号

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRefundDetail() {
        return refundDetail;
    }

    public void setRefundDetail(String refundDetail) {
        this.refundDetail = refundDetail;
    }

    public String getAuditDetail() {
        return auditDetail;
    }

    public void setAuditDetail(String auditDetail) {
        this.auditDetail = auditDetail;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
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

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
