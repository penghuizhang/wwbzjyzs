/**
 * 
 */
package cn.wechat.oa.service.user;

import org.apache.ibatis.annotations.Param;

import cn.wechat.oa.domain.User;

/**
 * @author kylin
 * @description:
 * @date 2017-4-28
 */
public interface UserService {
	int addUser(User user);

	int login(@Param("telephone") String telephone, @Param("pwd") String pwd);

	User getUserByUid(String uid);

	int checkExist(String telephone);
	
	String getUidByTelephoneAndPwd(@Param("telephone") String telephone, @Param("pwd") String pwd);

	String getPasswordByTelephone(String telephone);
	
	

}
