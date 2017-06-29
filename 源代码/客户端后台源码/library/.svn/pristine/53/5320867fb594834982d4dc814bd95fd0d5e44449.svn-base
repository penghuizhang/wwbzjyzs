package cn.wechat.oa.service.book.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cn.wechat.oa.dao.book.BookDao;
import cn.wechat.oa.domain.Book;
import cn.wechat.oa.service.book.BookService;

/**
 * 
 * @Description TODO
 * @author hwchao
 * @time 2017年5月5日 下午6:05:34
 * @vision 1.0
 */
@Service
public class BookServiceImpl implements BookService{

	@Resource
	private BookDao bookDao;
	
	@Override
	public List<Book> getBooksByCategory(String category, int begin) {
		// TODO Auto-generated method stub
		int end = begin + 20;
		List<Book> booklist = bookDao.getBooksByCategory(category,begin,end);
		return booklist;
	}

	@Override
	public Book getBookById(String id) {
		// TODO Auto-generated method stub
		Book book= bookDao.getBookById(id);
		return book;
	}

	@Override
	public List<Book> getBooksBySearchKey( @Param("key") String key) {
		// TODO Auto-generated method stub
		List<Book> list = bookDao.getBooksBySearchKey(key);
		return list;
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		// TODO Auto-generated method stub
		Book book = bookDao.getBookByIsbn(isbn);
		return book;
	}

	@Override
	public void updateBookStorage(String id, int count) {
		// TODO Auto-generated method stub
		bookDao.updateBookStorage(id, count);
	}

	@Override
	public void updateCategoryBookStorage(String id, int count) {
		// TODO Auto-generated method stub
		bookDao.updateCategoryBookStorage(id, count);
	}

}
