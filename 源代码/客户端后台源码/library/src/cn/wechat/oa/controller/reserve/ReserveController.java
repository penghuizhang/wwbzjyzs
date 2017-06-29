package cn.wechat.oa.controller.reserve;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wechat.oa.domain.Reserve;
import cn.wechat.oa.domain.ReserveBook;
import cn.wechat.oa.domain.ReserveState;
import cn.wechat.oa.service.book.BookService;
import cn.wechat.oa.service.reserve.ReserveService;

@Controller
public class ReserveController {

	@Resource
	ReserveService reserveService;
	
	@Resource
	BookService bookService;
	
	//添加预定单
	@RequestMapping("/addReserve")
	public void addReserve(HttpServletResponse response, String uid, String id){
		
		Reserve reserve = new Reserve();
		reserve.setId(id);
		reserve.setUid(uid);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String  reserveId= sdf.format(date) + System.currentTimeMillis();
		reserve.setReserveId(reserveId);
		reserve.setState(ReserveState.WAIT);
		reserve.setReserveTime(date);
		reserveService.addReserve(reserve);
	}
	
	// 通过用户Id查找预订记录
	@RequestMapping("/getReserveByUid")
    public void getReserveByUid(HttpServletResponse response,String uid) throws JsonGenerationException, JsonMappingException, IOException{
		System.out.println(uid);
	
		List<ReserveBook> list = reserveService.getReserveByUid(uid);
		ObjectMapper ob = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reservelist",list);
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	
	private void outPrintf(HttpServletResponse response, String str) {
		// 输出到前台
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");// 指定返回值的编码方式，必须放在out声明之前
		response.setContentType("text/html;charset=UTF-8");
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
