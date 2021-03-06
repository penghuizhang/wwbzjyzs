package cn.wechat.oa.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wechat.oa.domain.User;
import cn.wechat.oa.service.user.UserService;
import cn.wechat.oa.wx.OpenId;
import cn.wechat.oa.wx.wxUtils;

@Controller
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/getOpenId")
	public void getOpenId(HttpServletResponse response, String code) throws JsonGenerationException, JsonMappingException, IOException{
		OpenId openId = wxUtils.getOpenId(code);
		System.out.println(code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openId", openId);
		ObjectMapper ob = new ObjectMapper();
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	
	//检验该手机号是否已经注册
	@RequestMapping("/checkExist")
	public void checkExist(String telephone,  HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException, NoSuchAlgorithmException {
		int count = userService.checkExist(telephone);
		Map<String, Object> map = new HashMap<String, Object>();
		if (count == 1) {
			map.put("message", "该手机号已注册");
			map.put("flag", "false");
			ObjectMapper ob = new ObjectMapper();
			String str = ob.writeValueAsString(map);
			outPrintf(response, str);
		} else {
			map.put("message", "允许注册");
			map.put("flag", "true");
			ObjectMapper ob = new ObjectMapper();
			String str = ob.writeValueAsString(map);
			outPrintf(response, str);
		}
	}
	
	//注册用户
	@RequestMapping("/register")
	public void register(HttpServletResponse response,User user, Model model) throws NoSuchAlgorithmException, JsonGenerationException, JsonMappingException, IOException{
		//获取当前的时间戳
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uid = sdf.format(date) + System.currentTimeMillis();
		
		user.toString(); //打印后台接受的用户注册信息
		
		user.setUid(uid); 
		userService.addUser(user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "注册成功");
		map.put("flag", "success");
		ObjectMapper ob = new ObjectMapper();
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	
	//通过用户的id 返回用户的信息
	@RequestMapping("/getUserByUid")
	public void getUserByUid(HttpServletResponse response, String uid)
			throws JsonGenerationException, JsonMappingException, IOException {
		User user = userService.getUserByUid(uid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		ObjectMapper ob = new ObjectMapper();
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	
	//用户登录， 验证用户是否存在
	@RequestMapping("/loginUser")
	public void loginUser(HttpServletResponse response,@Param("telephone") String telephone,@Param("pwd") String pwd)
			throws NoSuchAlgorithmException, IOException {
		
		System.out.println(telephone + " " +pwd);
		//从数据库中取出已存储的密码
		String oldpassword = userService.getPasswordByTelephone(telephone);
		
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper ob = new ObjectMapper();
		if (pwd.equals(oldpassword)) {
			    String uid = userService.getUidByTelephoneAndPwd(telephone,oldpassword);
				map.put("flag", "success");
				map.put("uid", uid);
				String str = ob.writeValueAsString(map);
				outPrintf(response, str);
		}
	}

	private void outPrintf(HttpServletResponse response, String str) {
		// 输出到前台
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");// 指定返回值的编码方式，必须放在out声明之前
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();// 获取输入流
			out.print(str);// 输出str信息
			out.flush();// 刷新
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
