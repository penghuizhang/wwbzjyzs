package cn.wechat.oa.service.admin;

import cn.wechat.oa.domain.Admin;

public interface AdminService {
	// 注册管理员
	void saveAdmin(Admin admin);
	
	//管理员登录
	int getAdminByAccountAndPwd(String adminAccount, String adminPwd);
	
	//通过管理员的账号获取管理员的身份信息
	Admin getAdminByAdminId(String adminId);
}
