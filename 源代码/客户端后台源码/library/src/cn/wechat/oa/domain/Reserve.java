package cn.wechat.oa.domain;

import java.util.Date;

public class Reserve {
	
	private String reserveId; //预订单号
	private String uid; //用户ID
	private String id; //预定书籍的ID
	private Date reserveTime; //预定时间
	private ReserveState state; //预定状态

	public String getReserveId() {
		return reserveId;
	}
	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
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
	public Date getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}
	public ReserveState getState() {
		return state;
	}
	public void setState(ReserveState state) {
		this.state = state;
	}
	
	
}
