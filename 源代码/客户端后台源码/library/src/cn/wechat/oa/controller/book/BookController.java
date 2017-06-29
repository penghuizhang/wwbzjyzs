package cn.wechat.oa.controller.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wechat.oa.domain.Book;
import cn.wechat.oa.service.book.BookService;

@Controller
public class BookController {
	@Resource
	private BookService bookService;
	
	@RequestMapping("/getBooksByCategory")
	public void getBooksByCategory(HttpServletResponse response, String category, int begin){
		System.out.println(category+""+begin);
		try {
			List<Book> booklist = bookService.getBooksByCategory(category, begin);
			ObjectMapper ob = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("booklist",booklist);
			String str = ob.writeValueAsString(map);
			outPrintf(response, str);
		
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getBookById")
	public void getBookById(HttpServletResponse response,String id){
		try {
			Book book = bookService.getBookById(id);
			
			System.out.println(book.toString());
			
			ObjectMapper ob = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("book",book);
			String str = ob.writeValueAsString(map);
			outPrintf(response, str);
		
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getBooksBySearchKey")
	public void getBooksBySearchKey(HttpServletResponse response, @Param("key") String key) throws JsonGenerationException, JsonMappingException, IOException{
		List<Book> list = bookService.getBooksBySearchKey(key);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(key);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("booklist",list);
		String str = ob.writeValueAsString(map);
		outPrintf(response, str);
		
	}
	@RequestMapping("/getBookByIsbn")
	public void getBookByIsbn(HttpServletResponse response,@Param("isbn")String isbn) throws JsonGenerationException, JsonMappingException, IOException{
		Book book = bookService.getBookByIsbn(isbn);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(isbn);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("booklist",book);
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
