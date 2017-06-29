package cn.wechat.oa.dao.reserve;

import java.util.List;

import cn.wechat.oa.domain.Reserve;
import cn.wechat.oa.domain.ReserveBook;

public interface ReserveDao {
	int addReserve(Reserve reserve); //添加预定单
	
    List<ReserveBook> getReserveByUid(String uid);  // 通过用户Id查找预订记录
  
}
