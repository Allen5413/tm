package com.zs.domain.ebook;

import com.feinno.framework.common.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Allen on 2018/1/3.
 */
@Entity
@Table(name = "spot_replace_pay")
public class SpotReplacePay extends AbstractEntity {

    public static final int STATE_WAIT = 0;
    public static final int STATE_PASS = 1;
    public static final int STATE_NOT = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String spotCode;
    private long money;
    private String imagUrl;		//上传凭证
    private int state;		    //审核状态 0：未审核；1：审核通过；2：审核不通过
    private String verifyer;		//审核人
    private Date verifyTime;		//审核时间
    private String payWay;		//汇款方式
    private String remark;		//备注
    private String creator;
    private Date createTime = new Date();

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

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getVerifyer() {
        return verifyer;
    }

    public void setVerifyer(String verifyer) {
        this.verifyer = verifyer;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
