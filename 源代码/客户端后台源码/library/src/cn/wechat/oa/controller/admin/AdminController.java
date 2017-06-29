package cn.wechat.oa.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wechat.oa.domain.Admin;
import cn.wechat.oa.service.admin.AdminService;

@Controller
public class AdminController {
	@Resource
	private AdminService adminService;
	
	/*
	 * 添加管理员
	 */
	@RequestMapping("/saveAdmin")
	public void saveAdmin(HttpServletResponse response,Admin admin) throws JsonGenerationException, JsonMappingException, IOException{
		adminService.saveAdmin(admin);
		
		ObjectMapper ob = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text","success");
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	
	/*
	 * 用户登录url接口
	 */
	@RequestMapping("/loginAdmin")
	public void loginAdmin(HttpServletResponse response,@Param("adminAccount")String adminAccount, @Param("adminPwd")String adminPwd) throws JsonGenerationException, JsonMappingException, IOException{
		System.out.println(adminAccount);
		System.out.println(adminPwd);
		int count = adminService.getAdminByAccountAndPwd(adminAccount, adminPwd);
		String flag = "fail";
		if(count == 1){
			flag="success";
		}
		ObjectMapper ob = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag",flag);
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	
	/*
	 * 通过管理员的账号获取管理员的身份信息
	 */
	@RequestMapping("/getAdminById")
	public void getAdminByAdminId(HttpServletResponse response, String adminId) throws JsonGenerationException, JsonMappingException, IOException{
		Admin admin = adminService.getAdminByAdminId(adminId);
		
		ObjectMapper ob = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("admin",admin);
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	
	
	private void outPrintf(HttpServletResponse response, String str) {
		// 输出到前台
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");// 指定返回值的编码方式，必须放在out声明之前
		response.setContentType("application/json;charset=UTF-8");
		try {
			out = response.getWriter();	// 获取输入流
			out.print(str);				// 输出str信息
			out.flush(); 				// 刷新
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
