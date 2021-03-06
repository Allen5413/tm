package com.zs.domain.finance;

import com.feinno.framework.common.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 学习中心费用
 * Created by LihongZhang on 2015/5/14.
 */
@Entity
@Table(name = "spot_expense")
public class SpotExpense extends AbstractEntity {

    public static final int STATE_YES = 0;   //已结算状态
    public static final int STATE_NO = 1;    //未结算状态

    private Long id;        //主键id
    private Long semesterId;    //学期id
    private String spotCode;    //学习中心编号
    private Double pay;          //已支付金额
    private Double buy;      //消费金额
    private Integer state;          //状态为，是否结算 【0：已结算，1：未结算】
    private String creator;     //创建人
    private Date createTime = new Date();    //创建时间
    private String operator;    //操作人
    private Date operateTime = new Date();   //操作时间
    private Date clearTime;    //结清时间
    private Integer version;        //版本号
    private Integer discount;       //折扣（默认是100，不打折）


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

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
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

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Date getClearTime() {
        return clearTime;
    }

    public void setClearTime(Date clearTime) {
        this.clearTime = clearTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
