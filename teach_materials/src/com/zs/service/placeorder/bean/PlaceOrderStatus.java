package com.zs.service.placeorder.bean;
/**
 * 
 * @author yanghaosen
 *
 */
public enum PlaceOrderStatus {
	
	CREATED("1","已生成"),
	
	DOING("2","处理中"),

	DISPATCH("3","分拣中"),

	PACKGED("4","已打包"),

	SENDED("5","已发出"),

	SIGNED("6","已签收");
	
	private PlaceOrderStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	private String code;
	
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
