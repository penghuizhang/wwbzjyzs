package cn.wechat.oa.dao.book;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wechat.oa.domain.Book;

/**
 * 
 * @Description TODO
 * @author hwchao
 * @time 2017年5月5日 下午6:05:08
 * @vision 1.0
 */
public interface BookDao {
	
	Book getBookByIsbn(String isbn);

	Book getBookById(String id);
	
	void updateBookStorage(String id,int count);
	
	void updateCategoryBookStorage(String id,int count);
	
	List<Book> getBooksBySearchKey(@Param("key")String key);
	
	/*
	 * category 书籍的类别
	 * begin 是在查询数据库是的起始地址
	 * end 是查询的结束地址
	 */
	List<Book> getBooksByCategory(String category,int begin, int end);
	
}
