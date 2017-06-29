package cn.wechat.oa.service.shopcar.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wechat.oa.dao.shopcar.ShopCarOrderDao;
import cn.wechat.oa.domain.ShopCarOrder;
import cn.wechat.oa.service.shopcar.ShopCarService;
@Service
public class ShopCarServiceImpl implements ShopCarService{

	@Resource
	ShopCarOrderDao shopCarOrderDao;

	@Override
	public int addShopCarOrder(ShopCarOrder shopCarOrder) {
		// TODO Auto-generated method stub
		int count = shopCarOrderDao.addShopCarOrder(shopCarOrder);
		return count;
	}


	@Override
	public List<ShopCarOrder> getShopCarOrderByUid(String uid) {
		// TODO Auto-generated method stub
		List<ShopCarOrder> list = shopCarOrderDao.getShopCarOrderByUid(uid);
		return list;
	}

	@Override
	public ShopCarOrder getShopCarOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		ShopCarOrder shopCarOrder = shopCarOrderDao.getShopCarOrderByOrderId(orderId);
		return shopCarOrder;
	}


	@Override
	public int deleteShopCarOrderByOrderId(String orderId) {
		shopCarOrderDao.deleteShopCarOrderByOrderId(orderId);
		return 0;
	}
	
	
}
