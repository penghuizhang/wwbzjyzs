package cn.wechat.oa.controller.shopcar;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wechat.oa.domain.Book;
import cn.wechat.oa.domain.BorrowRecord;
import cn.wechat.oa.domain.DetailShopCarOrder;
import cn.wechat.oa.domain.ShopCarOrder;
import cn.wechat.oa.service.book.BookService;
import cn.wechat.oa.service.borrowrecord.BorrowOrderService;
import cn.wechat.oa.service.shopcar.ShopCarService;

@Controller
public class ShopCarController {
	@Resource
	private ShopCarService shopCarService;
	@Resource
	private BookService bookService;
	@Resource
	private BorrowOrderService borrowOrderService;
	
	/*
	 * 通过购物车订单查询购物车记录
	 */
	@RequestMapping("/getShopCarOrdersByOrderIds")
	public void getShopCarOrdersByOrderIds(HttpServletResponse response,String orderIds) throws JsonGenerationException, JsonMappingException, IOException{
		System.out.println(orderIds);
		String[] orderId = orderIds.split(",");
		List<Book> books = new ArrayList<Book>();
		int len = orderId.length;
		Book book;
		ShopCarOrder shopCarOrder;  //购物车订单
		BorrowRecord borrowRecord;  //借阅订单
		
		String operate = null;
		if("borrow".equals(orderId[0])){
			operate = "借阅图书";
			for(int i=1; i<len; i++){
				shopCarOrder = shopCarService.getShopCarOrderByOrderId(orderId[i]);
				book = bookService.getBookById(shopCarOrder.getId());
				books.add(book);
			}
		}
		else if("return".equals(orderId[0])){
			operate = "归还图书";
			for(int i=1; i<len; i++){
				borrowRecord = borrowOrderService.getBorrowRecordByOrderId(orderId[i]);
				book = bookService.getBookById(borrowRecord.getId());
				books.add(book);
			}
		}
		ObjectMapper ob = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list",books);
		map.put("operate", operate);
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	
	/*
	 * 添加购物车记录
	 */
	@RequestMapping("/addShopCarOrder")
	public void addShopCarOrder(HttpServletResponse response,String uid,String id) {
		ShopCarOrder shopCarOrder = new ShopCarOrder();
		shopCarOrder.setId(id);
		shopCarOrder.setUid(uid);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String  orderId= sdf.format(date) + System.currentTimeMillis();
		shopCarOrder.setOrderId(orderId);
		shopCarOrder.setAddTime(date);
		shopCarService.addShopCarOrder(shopCarOrder);
	} 
	
	 /*
	  * 通过订单ID删除购物车记录
	  */
	@RequestMapping("/deleteShopCarOrderByOrderId")
	public void deleteShopCarOrderById(HttpServletResponse response, String orderIds) {
		System.out.println(orderIds);
		String[] orderIds1 = orderIds.split(",");
		for(String orderId:orderIds1){
			System.out.println(orderId);
			shopCarService.deleteShopCarOrderByOrderId(orderId);
		}
	} 
	
	/*
	 * 通过用户的uid查找用户的购物车记录
	 */
	@RequestMapping("/getShopCarOrderByUid")
	public void getShopCarOrderByUid(HttpServletResponse response,String uid) throws JsonGenerationException, JsonMappingException, IOException {
		List<ShopCarOrder> list = shopCarService.getShopCarOrderByUid(uid);
		Iterator<ShopCarOrder> iterator = list.iterator();
		List<DetailShopCarOrder> books = new ArrayList<DetailShopCarOrder>();
		DetailShopCarOrder detailShopCarOrder;
		while(iterator.hasNext()){
			ShopCarOrder shopCarOrder = iterator.next();
			String id = shopCarOrder.getId();
			Book book = bookService.getBookById(id);
			detailShopCarOrder = new DetailShopCarOrder();
			detailShopCarOrder.setBook(book);
			detailShopCarOrder.setShopCarOrder(shopCarOrder);
			books.add(detailShopCarOrder);
		}
		ObjectMapper ob = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list",books);
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}  
	
	/*
	 * 向前台输出数据
	 */
	private void outPrintf(HttpServletResponse response, String str) {
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
