package cn.wechat.oa.service.borrowrecord.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wechat.oa.dao.borrowrecord.BorrowRecordDao;
import cn.wechat.oa.domain.BorrowRecord;
import cn.wechat.oa.service.borrowrecord.BorrowOrderService;
@Service
public class BorrowOrderServiceImpl implements BorrowOrderService{
	
	@Resource
	BorrowRecordDao borrowRecordDao;

	@Override
	public void addBorrowRecord(BorrowRecord borrowRecord) {
		// TODO Auto-generated method stub
		borrowRecordDao.addBorrowRecord(borrowRecord);
	}

	@Override
	public void alterBorrowRecord(BorrowRecord borrowRecorder) {
		// TODO Auto-generated method stub
		borrowRecordDao.alterBorrowRecord(borrowRecorder);
	}

	@Override
	public void addBorrowRecordToHistory(BorrowRecord borrowRecord) {
		// TODO Auto-generated method stub
		borrowRecordDao.addBorrowRecordToHistory(borrowRecord);
	}

	@Override
	public void deleteBorrowRecordByOrderId(String orderId) {
		// TODO Auto-generated method stub
		borrowRecordDao.deleteBorrowRecordByOrderId(orderId);
	}

	@Override
	public void deleteBorrowRecordFromHistoryByOrderId(String orderId) {
		// TODO Auto-generated method stub
		borrowRecordDao.deleteBorrowRecordFromHistoryByOrderId(orderId);
	}

	@Override
	public List<BorrowRecord> getBorrowRecordHistoryByUid(String uid) {
		// TODO Auto-generated method stub
		List<BorrowRecord> list = borrowRecordDao.getBorrowRecordHistoryByUid(uid);
		return list;
	}

	@Override
	public List<BorrowRecord> getBorrowedRecordByUid(String uid) {
		// TODO Auto-generated method stub
		List<BorrowRecord> list = borrowRecordDao.getBorrowedRecordByUid(uid);
		return list;
	}

	@Override
	public BorrowRecord getBorrowRecordByOrderId(String orderId) {
		// TODO Auto-generated method stub
		BorrowRecord borrowRecord = borrowRecordDao.getBorroeRecordByOrderId(orderId);
		return borrowRecord;
	}

}
