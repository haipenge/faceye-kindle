package com.faceye.test.component.book.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.book.entity.BookTag;
import com.faceye.component.book.repository.mongo.BookTagRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * BookTag Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2016-8-2 11:06:39<br>
 */
public class BookTagRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private BookTagRepository bookTagRepository = null;

	@Before
	public void before() throws Exception {
		//this.bookTagRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		BookTag bookTag = new BookTag();
		this.bookTagRepository.save(bookTag);
		Iterable<BookTag> bookTags = this.bookTagRepository.findAll();
		Assert.isTrue(bookTags.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		BookTag bookTag = new BookTag();
		this.bookTagRepository.save(bookTag);
        this.bookTagRepository.delete(bookTag.getId());
        Iterable<BookTag> bookTags = this.bookTagRepository.findAll();
		Assert.isTrue(!bookTags.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		BookTag bookTag = new BookTag();
		this.bookTagRepository.save(bookTag);
		bookTag=this.bookTagRepository.findOne(bookTag.getId());
		Assert.isTrue(bookTag!=null);
	}

	
}
