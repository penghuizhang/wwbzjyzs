package cn.wechat.oa.service.admin.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wechat.oa.dao.admin.AdminDao;
import cn.wechat.oa.domain.Admin;
import cn.wechat.oa.service.admin.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Resource 
	private AdminDao adminDao;
	
	@Override
	public void saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.saveAdmin(admin);
	}

	@Override
	public int getAdminByAccountAndPwd(String adminAccount, String adminPwd) {
		// TODO Auto-generated method stub
		int count = adminDao.getAdminByAccountAndPwd(adminAccount, adminPwd);
		return count;
	}

	@Override
	public Admin getAdminByAdminId(String adminId) {
		// TODO Auto-generated method stub
		Admin admin = adminDao.getAdminByAdminId(adminId);
		return admin;
	}

}
