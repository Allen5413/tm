package com.zs.domain.ebook;

import com.feinno.framework.common.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Allen on 2018/1/3.
 */
@Entity
@Table(name = "student_ebook_pay")
public class StudentEBookPay extends AbstractEntity {

    public final static int PAY_TYPE_ONLINE_BANK = 0;
    public final static int PAY_TYPE_TRANSFER = 1;
    public final static int PAY_TYPE_CASH = 2;
    public final static int PAY_TYPE_OTHER = 3;
    public final static int PAY_TYPE_WX = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentCode;
    private long money;
    private Date arrivalTime;       //银行到账时间
    private int payType;            //缴费类型[0:网银，1:银行转账，2:现金，3:其它，4:微信]
    private String remark;          //备注
    private String hr;              //青书请求状态（0表示成功）
    private String errorMsg;        //青书请求错误原因
    private String qsRemark;        //青书错误信息描素
    private String qsOrderCode;     //青书订单号
    private String creator;
    private Date createTime = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
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

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getQsRemark() {
        return qsRemark;
    }

    public void setQsRemark(String qsRemark) {
        this.qsRemark = qsRemark;
    }

    public String getQsOrderCode() {
        return qsOrderCode;
    }

    public void setQsOrderCode(String qsOrderCode) {
        this.qsOrderCode = qsOrderCode;
    }
}
