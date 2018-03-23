package com.faceye.test.component.book.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.book.entity.Author;
import com.faceye.component.book.repository.mongo.AuthorRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Author Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2016-8-2 11:06:40<br>
 */
public class AuthorRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private AuthorRepository authorRepository = null;

	@Before
	public void before() throws Exception {
		//this.authorRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Author author = new Author();
		this.authorRepository.save(author);
		Iterable<Author> authors = this.authorRepository.findAll();
		Assert.assertTrue(authors.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Author author = new Author();
		this.authorRepository.save(author);
        this.authorRepository.deleteById(author.getId());
        Iterable<Author> authors = this.authorRepository.findAll();
		Assert.assertTrue(!authors.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Author author = new Author();
		this.authorRepository.save(author);
		author=this.authorRepository.findById(author.getId()).get();
		Assert.assertTrue(author!=null);
	}

	
}
