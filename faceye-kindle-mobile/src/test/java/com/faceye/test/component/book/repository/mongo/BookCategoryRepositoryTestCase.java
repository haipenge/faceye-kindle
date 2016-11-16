package com.faceye.test.component.book.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.book.entity.BookCategory;
import com.faceye.component.book.repository.mongo.BookCategoryRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * BookCategory Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2016-8-2 11:06:38<br>
 */
public class BookCategoryRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private BookCategoryRepository bookCategoryRepository = null;

	@Before
	public void before() throws Exception {
		//this.bookCategoryRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		BookCategory bookCategory = new BookCategory();
		this.bookCategoryRepository.save(bookCategory);
		Iterable<BookCategory> bookCategorys = this.bookCategoryRepository.findAll();
		Assert.isTrue(bookCategorys.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		BookCategory bookCategory = new BookCategory();
		this.bookCategoryRepository.save(bookCategory);
        this.bookCategoryRepository.delete(bookCategory.getId());
        Iterable<BookCategory> bookCategorys = this.bookCategoryRepository.findAll();
		Assert.isTrue(!bookCategorys.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		BookCategory bookCategory = new BookCategory();
		this.bookCategoryRepository.save(bookCategory);
		bookCategory=this.bookCategoryRepository.findOne(bookCategory.getId());
		Assert.isTrue(bookCategory!=null);
	}

	
}
