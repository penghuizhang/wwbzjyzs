package cn.wechat.oa.service.reserve.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wechat.oa.dao.reserve.ReserveDao;
import cn.wechat.oa.domain.Reserve;
import cn.wechat.oa.domain.ReserveBook;
import cn.wechat.oa.service.reserve.ReserveService;
@Service
public class ReserveServiceImpl implements ReserveService{

	
	@Resource
	ReserveDao reserveDao;
	
	@Override
	public int addReserve(Reserve reserve) {
		// TODO Auto-generated method stub
		int count = reserveDao.addReserve(reserve);
		return count;
	}

	@Override
	public List<ReserveBook> getReserveByUid(String uid) {
		// TODO Auto-generated method stub
		List<ReserveBook> list = reserveDao.getReserveByUid(uid);
		return list;
	}

}
