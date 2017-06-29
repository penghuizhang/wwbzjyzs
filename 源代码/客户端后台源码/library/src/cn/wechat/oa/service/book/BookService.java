package cn.wechat.oa.service.book;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wechat.oa.domain.Book;

/**
 * 
 * @Description TODO
 * @author hwchao
 * @time 2017年5月5日 下午6:05:27
 * @vision 1.0
 */
public interface BookService {

	Book getBookById(String id);
	
	Book getBookByIsbn(String isbn);
	
	void updateBookStorage(String id,int count);
	
	void updateCategoryBookStorage(String id,int count);
	
	List<Book> getBooksByCategory(String category,int begin);
	
	List<Book> getBooksBySearchKey( @Param("key") String key);
	
	
}
