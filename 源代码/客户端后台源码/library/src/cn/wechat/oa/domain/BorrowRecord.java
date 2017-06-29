package cn.wechat.oa.domain;

import java.util.Date;

public class BorrowRecord {
	
	private String borrowRecordId; // 借阅流水单号
	private String id; // 图书Id
	private String uid; // 用户Id
	private Date borrowTime; // 借阅时间
	private Date returnTime; // 归还时间
	private int exceedTime; // 超期时间
	private double exceedCost; // 超期费用
	private String adminAccount; //管理员工号

	
	
	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getBorrowRecordId() {
		return borrowRecordId;
	}

	public void setBorrowRecordId(String borrowRecordId) {
		this.borrowRecordId = borrowRecordId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public int getExceedTime() {
		return exceedTime;
	}

	public void setExceedTime(int exceedTime) {
		this.exceedTime = exceedTime;
	}

	public double getExceedCost() {
		return exceedCost;
	}

	public void setExceedCost(double exceedCost) {
		this.exceedCost = exceedCost;
	}

}
