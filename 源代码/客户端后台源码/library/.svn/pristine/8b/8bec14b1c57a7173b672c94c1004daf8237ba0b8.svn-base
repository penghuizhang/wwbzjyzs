package cn.wechat.oa.controller.borroworder;

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
import cn.wechat.oa.domain.DetailBorrowRecorder;
import cn.wechat.oa.domain.ShopCarOrder;
import cn.wechat.oa.service.book.BookService;
import cn.wechat.oa.service.borrowrecord.BorrowOrderService;
import cn.wechat.oa.service.shopcar.ShopCarService;

@Controller
public class BorrowOrderController {
	
	@Resource
	private BorrowOrderService borrowOrderService;
	@Resource
	private BookService bookService;
	@Resource
	private ShopCarService shopCarService;

	/*
	 * 管理员扫码后，允许借阅时调用该接口
	 */
	@RequestMapping("/borrowbook")
	public void borrowBook(HttpServletResponse response, String orderInfo, String adminAccount) throws JsonGenerationException, JsonMappingException, IOException{
		System.out.println("管理员扫码获得信息："+orderInfo);
		
		String[] strings = orderInfo.split(",");
		int len = strings.length;
		ShopCarOrder shopCarOrder;
		BorrowRecord borrowRecord;
		for(int i=1;i<len;i++){
			shopCarOrder = shopCarService.getShopCarOrderByOrderId(strings[i]);
			String uid = shopCarOrder.getUid();
			String id = shopCarOrder.getId();
			/*
			 * 添加借阅记录
			 */
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String  borrowRecordId= sdf.format(date) + System.currentTimeMillis();  //借阅记录编号
			borrowRecord = new BorrowRecord();
			borrowRecord.setBorrowRecordId(borrowRecordId);
			borrowRecord.setUid(uid);
			borrowRecord.setId(id);
			borrowRecord.setBorrowTime(date);
			borrowRecord.setAdminAccount(adminAccount);
			borrowOrderService.addBorrowRecord(borrowRecord);
			/*
			 * 修改图书库存信息
			 */
			int count = bookService.getBookById(id).getCount()-1;
			bookService.updateBookStorage(id, count);
			bookService.updateCategoryBookStorage(id, count);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "success");
		ObjectMapper ob = new ObjectMapper();
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	
	/*
	 * 管理员扫描用户的还书二维码，确定归还时调用该接口
	 */
	@RequestMapping("/returnbook")
	public void returnBook(HttpServletResponse response, String orderInfo, String adminAccount) throws JsonGenerationException, JsonMappingException, IOException{
		/*
		 * orderInfo是归还图书的记录ID
		 */
		System.out.println("管理员扫码获得信息："+orderInfo);
		
		String[] strings = orderInfo.split(",");
		int len = strings.length;
		BorrowRecord borrowRecord;
		for(int i=1;i<len;i++){
			/*
			 * 将借阅记录从借阅表中删除，
			 * 并放入历史借阅表中
			 */
			Date returnDate = new Date();
			borrowRecord = borrowOrderService.getBorrowRecordByOrderId(strings[i]);
			String id = borrowRecord.getId();
			borrowOrderService.deleteBorrowRecordByOrderId(strings[i]);
			borrowRecord.setReturnTime(returnDate);
			borrowOrderService.addBorrowRecordToHistory(borrowRecord);
			/*
			 * 修改图书库存信息
			 */
			int count = bookService.getBookById(id).getCount()+1;
			bookService.updateBookStorage(id, count);
			bookService.updateCategoryBookStorage(id, count);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "success");
		ObjectMapper ob = new ObjectMapper();
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
 	}
	/*
	 * 用户查看当前正在借阅的书籍信息，返回用户借阅的书籍信息
	 */
	@RequestMapping("/getBorrowBooksByUid")
	public void getBorrowBooksByUid(HttpServletResponse response, String uid) throws JsonGenerationException, JsonMappingException, IOException{
		System.out.println(uid);
		List<BorrowRecord> borrowRecords = borrowOrderService.getBorrowedRecordByUid(uid);
		
		Iterator<BorrowRecord> iterator = borrowRecords.iterator();
		ArrayList<DetailBorrowRecorder> borrowlist = new ArrayList<DetailBorrowRecorder>();
		BorrowRecord borrowRecord;
		Book book;
		DetailBorrowRecorder detailBorrowRecorder;
		while(iterator.hasNext()){
			borrowRecord = iterator.next();
			book = bookService.getBookById(borrowRecord.getId());
			/*
			 * 组装详细借阅信息
			 */
			detailBorrowRecorder = new DetailBorrowRecorder();
			detailBorrowRecorder.setBorrowRecord(borrowRecord);
			detailBorrowRecorder.setBook(book);
			
			borrowlist.add(detailBorrowRecorder);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", borrowlist);
		ObjectMapper ob = new ObjectMapper();
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	/*
	 * 
	 */
	@RequestMapping("/getBorrowHistiryByUid")
	public void getBorrowHistiryByUid(HttpServletResponse response, String uid) throws JsonGenerationException, JsonMappingException, IOException{
		List<BorrowRecord> borrowRecords = borrowOrderService.getBorrowRecordHistoryByUid(uid);
		Iterator<BorrowRecord> iterator = borrowRecords.iterator();
		ArrayList<DetailBorrowRecorder> borrowlist = new ArrayList<DetailBorrowRecorder>();
		BorrowRecord borrowRecord;
		Book book;
		DetailBorrowRecorder detailBorrowRecorder;
		while(iterator.hasNext()){
			borrowRecord = iterator.next();
			book = bookService.getBookById(borrowRecord.getId());
			/*
			 * 组装详细借阅信息
			 */
			detailBorrowRecorder = new DetailBorrowRecorder();
			detailBorrowRecorder.setBorrowRecord(borrowRecord);
			detailBorrowRecorder.setBook(book);
			borrowlist.add(detailBorrowRecorder);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", borrowlist);
		ObjectMapper ob = new ObjectMapper();
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
	}
	
	private void outPrintf(HttpServletResponse response, String str) {
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

