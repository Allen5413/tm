package com.zs.domain.finance;

import com.feinno.framework.common.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 退款
 * Created by Allen on 2016/1/4.
 */
@Entity
@Table(name = "refund")
public class Refund extends AbstractEntity {

    public final static int STATE_WAIT_PRINT = 0;
    public final static int STATE_WAIT_UPLOAD = 1;
    public final static int STATE_WAIT_AUDIT = 2;
    public final static int STATE_AUDIT = 3;

    private Long id;                        //主键id
    private String spotCode;                //学习中心编号
    private String code;                    //退款批次编号
    private int state;                      //状态 【0：待打印，1：待上传凭证，2：待审核，3：已审核】
    private String bankName;                //退款银行
    private String bankCode;                //银行卡号
    private String company;                 //收款单位
    private String refundApplyImg;          //退款申请凭证
    private String refundConfirmImg;        //实际退款后上传的退款凭证
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getRefundApplyImg() {
        return refundApplyImg;
    }

    public void setRefundApplyImg(String refundApplyImg) {
        this.refundApplyImg = refundApplyImg;
    }

    public String getRefundConfirmImg() {
        return refundConfirmImg;
    }

    public void setRefundConfirmImg(String refundConfirmImg) {
        this.refundConfirmImg = refundConfirmImg;
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

    public String getSpotCode() {
        return spotCode;
    }

    public void setSpotCode(String spotCode) {
        this.spotCode = spotCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
