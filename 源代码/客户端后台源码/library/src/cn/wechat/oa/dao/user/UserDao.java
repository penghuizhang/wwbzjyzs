
package cn.wechat.oa.dao.user;

import cn.wechat.oa.domain.User;

public interface UserDao {
	int addUser(User user); // 添加用户

	int login(String telephone, String pwd); // 用户登录

	int checkExist(String telephone); // 检查用户是否存在

	String getPasswordByTelephone(String telephone); // 通过用户手机号查找登录密码

	User getUserByUid(String uid); // 通过用户Id查询用户
	
	String getUidByTelephoneAndPwd(String telephone,String pwd); //通过用户的手机账号和密码获取用户的id
}
