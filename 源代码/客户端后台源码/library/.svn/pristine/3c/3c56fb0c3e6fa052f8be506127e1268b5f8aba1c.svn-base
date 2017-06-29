/**
 * 
 */
package cn.wechat.oa.service.user.impl;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cn.wechat.oa.dao.user.UserDao;
import cn.wechat.oa.domain.User;
import cn.wechat.oa.service.user.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource 
	UserDao userDao;

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		int count = userDao.addUser(user);
		return count;
	}

	@Override
	public int login(@Param("telephone") String telephone,@Param("pwd") String pwd) {
		// TODO Auto-generated method stub
		int count = userDao.login(telephone, pwd);
		return count;
	}

	@Override
	public User getUserByUid(String uid) {
		// TODO Auto-generated method stub
		User user = userDao.getUserByUid(uid);
		return user;
	}

	@Override
	public int checkExist(String telephone) {
		// TODO Auto-generated method stub
		int count = userDao.checkExist(telephone);
		return count;
	}

	@Override
	public String getPasswordByTelephone(String telephone) {
		// TODO Auto-generated method stub
		String password = userDao.getPasswordByTelephone(telephone);
		return password;
	}

	@Override
	public String getUidByTelephoneAndPwd(@Param("telephone") String telephone,@Param("pwd") String pwd) {
		// TODO Auto-generated method stub
		String uid = userDao.getUidByTelephoneAndPwd(telephone, pwd);
		return uid;
	}
	
	
}
