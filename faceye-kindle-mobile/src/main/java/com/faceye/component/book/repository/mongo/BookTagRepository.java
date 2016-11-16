package com.faceye.component.book.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.book.entity.BookTag;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
import com.faceye.component.book.repository.mongo.customer.BookTagCustomerRepository;
import com.faceye.component.book.repository.mongo.gen.BookTagGenRepository;
/**
 * 模块:电子书->com.faceye.compoent.book.repository.mongo<br>
 * 说明:<br>
 * 实体:标签->com.faceye.component.book.entity.entity.BookTag 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2016-8-2 11:06:40<br>
* BookTagCustomerRepository,
 */
public interface BookTagRepository extends BookTagGenRepository {
	
	
}/**@generate-repository-source@**/
