package com.faceye.test.component.book.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.book.entity.DownloadResource;
import com.faceye.component.book.repository.mongo.DownloadResourceRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * DownloadResource Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2016-8-2 11:06:39<br>
 */
public class DownloadResourceRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private DownloadResourceRepository downloadResourceRepository = null;

	@Before
	public void before() throws Exception {
		//this.downloadResourceRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		DownloadResource downloadResource = new DownloadResource();
		this.downloadResourceRepository.save(downloadResource);
		Iterable<DownloadResource> downloadResources = this.downloadResourceRepository.findAll();
		Assert.isTrue(downloadResources.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		DownloadResource downloadResource = new DownloadResource();
		this.downloadResourceRepository.save(downloadResource);
        this.downloadResourceRepository.delete(downloadResource.getId());
        Iterable<DownloadResource> downloadResources = this.downloadResourceRepository.findAll();
		Assert.isTrue(!downloadResources.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		DownloadResource downloadResource = new DownloadResource();
		this.downloadResourceRepository.save(downloadResource);
		downloadResource=this.downloadResourceRepository.findOne(downloadResource.getId());
		Assert.isTrue(downloadResource!=null);
	}

	
}
