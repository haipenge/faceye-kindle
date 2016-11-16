package com.faceye.test.component.book.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.book.entity.Book;
import com.faceye.component.book.repository.mongo.BookRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Book Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2016-8-2 11:06:41<br>
 */
public class BookRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private BookRepository bookRepository = null;

	@Before
	public void before() throws Exception {
		//this.bookRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Book book = new Book();
		this.bookRepository.save(book);
		Iterable<Book> books = this.bookRepository.findAll();
		Assert.isTrue(books.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Book book = new Book();
		this.bookRepository.save(book);
        this.bookRepository.delete(book.getId());
        Iterable<Book> books = this.bookRepository.findAll();
		Assert.isTrue(!books.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Book book = new Book();
		this.bookRepository.save(book);
		book=this.bookRepository.findOne(book.getId());
		Assert.isTrue(book!=null);
	}

	
}
