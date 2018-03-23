package com.faceye.component.book.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.book.entity.Author;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
import com.faceye.component.book.repository.mongo.customer.AuthorCustomerRepository;
import com.faceye.component.book.repository.mongo.gen.AuthorGenRepository;
/**
 * 模块:电子书->com.faceye.compoent.book.repository.mongo<br>
 * 说明:<br>
 * 实体:作者->com.faceye.component.book.entity.entity.Author 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2016-8-2 11:06:40<br>
* AuthorCustomerRepository,
 */
public interface AuthorRepository extends AuthorGenRepository {
	
	
}/**@generate-repository-source@**/
