package cn.wechat.oa.dao.shopcar;

import java.util.List;

import cn.wechat.oa.domain.ShopCarOrder;

public interface ShopCarOrderDao {
	int addShopCarOrder(ShopCarOrder shopCarOrder); // 添加订单
	 
	int deleteShopCarOrderByOrderId(String orderId);  //通过订单号删除订单
	
	ShopCarOrder getShopCarOrderByOrderId(String orderId); //通过订单号查找订单
	
	List<ShopCarOrder> getShopCarOrderByUid(String uid);  //通过用户Id查找订单
	
}
