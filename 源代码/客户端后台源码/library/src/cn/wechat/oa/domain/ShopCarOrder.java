package cn.wechat.oa.domain;

import java.util.Date;

public class ShopCarOrder {
	private String orderId; // 订单号
	private String uid; //用户Id
	private String id; //书籍Id
	private Date addTime; //添加时间
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
}
