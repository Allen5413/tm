package com.zs.domain.finance;

import com.feinno.framework.common.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 学习中心入账明细
 * Created by LihongZhang on 2015/5/14.
 */
@Entity
@Table(name = "spot_expense_pay")
public class SpotExpensePay extends AbstractEntity{

    public final static int PAY_TYPE_ONLINE_BANK= 0;
    public final static int PAY_TYPE_ONLINE_TRANSFER= 1;
    public final static int PAY_TYPE_ONLINE_CASH = 2;
    public final static int PAY_TYPE_ONLINE_OTHER= 3;

    private Long id;                //主键id
    private String spotCode;        //学习中心编号
    private Float money;            //支付金额
    private Long invoiceId;         //发票id
    private String creator;         //创建人——不能修改
    private Date createTime = new Date();        //创建时间——不能修改
    private Date arrivalTime;       //银行到账时间
    private int payType;            //缴费类型[0:网银，1:银行转账，2:现金，3:其它]
    private String remark;          //备注

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getSpotCode() {
        return spotCode;
    }

    public void setSpotCode(String spotCode) {
        this.spotCode = spotCode;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
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
}
