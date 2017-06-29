package cn.wechat.oa.service.borrowrecord;

import java.util.List;

import cn.wechat.oa.domain.BorrowRecord;

public interface BorrowOrderService {
	
	void addBorrowRecord(BorrowRecord borrowRecord); // 添加借阅记录

	void alterBorrowRecord(BorrowRecord borrowRecorder); //修改借阅信息
	
	BorrowRecord getBorrowRecordByOrderId(String orderId); // 通过借阅流水单号查找借阅记录

	void addBorrowRecordToHistory(BorrowRecord borrowRecord); //添加借阅信息到历史表中

	void deleteBorrowRecordByOrderId(String orderId); //通过流水单号删除历史信息

	void deleteBorrowRecordFromHistoryByOrderId(String orderId); //从历史信息表中删除借阅记录

	List<BorrowRecord> getBorrowRecordHistoryByUid(String uid); // 获取借阅的历史信息

	List<BorrowRecord> getBorrowedRecordByUid(String uid); // 获取已借的书籍信息
}

