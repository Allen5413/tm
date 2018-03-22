package com.zs.domain.finance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.feinno.framework.common.domain.AbstractEntity;
/**
 * 
 * @author yanghaosen
 *
 */
@Entity
@Table(name = "spot_pay_pol_temp")
public class SpotPayPolTemp extends AbstractEntity{

	public static final int IS_SPOT_NOT = 0;
	public static final int IS_SPOT_YES = 1;

	public static final String IS_SURE_WAIT = "0";
	public static final String IS_SURE_PASS = "1";
	public static final String IS_SURE_NOT = "2";

	private Long id;
	
	private String spotCode;		//学习中心编号
	
	private Long ownId;			//学生账户消费统计id
	
	private Float spotMoney;		//学习中心一共交的钱
	
	private String imagUrl;		//上传凭证
	
	private String isSure;		//审核状态 0：未审核；1：审核通过；2：审核不通过
	
	private String verifyer;		//审核人
	
	private Date verifyTime;		//审核时间
	
	private String payWay;		//汇款方式

	private int isSpot;	//是否为学习中心缴费， 0：不是（为学生）；1：是

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String remark;		//备注
	
	private String creator;
	
	private Date createTime = new Date();
	
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

	public Long getOwnId() {
		return ownId;
	}

	public void setOwnId(Long ownId) {
		this.ownId = ownId;
	}

	public Float getSpotMoney() {
		return spotMoney;
	}

	public void setSpotMoney(Float spotMoney) {
		this.spotMoney = spotMoney;
	}

	public String getImagUrl() {
		return imagUrl;
	}

	public void setImagUrl(String imagUrl) {
		this.imagUrl = imagUrl;
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

	public String getIsSure() {
		return isSure;
	}

	public void setIsSure(String isSure) {
		this.isSure = isSure;
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


	public int getIsSpot() {
		return isSpot;
	}

	public void setIsSpot(int isSpot) {
		this.isSpot = isSpot;
	}
}
