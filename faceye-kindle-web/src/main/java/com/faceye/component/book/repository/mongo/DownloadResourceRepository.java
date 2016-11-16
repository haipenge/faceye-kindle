package com.faceye.component.book.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.book.entity.DownloadResource;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
import com.faceye.component.book.repository.mongo.customer.DownloadResourceCustomerRepository;
import com.faceye.component.book.repository.mongo.gen.DownloadResourceGenRepository;
/**
 * 模块:电子书->com.faceye.compoent.book.repository.mongo<br>
 * 说明:<br>
 * 实体:下载资源->com.faceye.component.book.entity.entity.DownloadResource 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2016-8-2 11:06:39<br>
* DownloadResourceCustomerRepository,
 */
public interface DownloadResourceRepository extends DownloadResourceGenRepository {
	
	
}/**@generate-repository-source@**/
