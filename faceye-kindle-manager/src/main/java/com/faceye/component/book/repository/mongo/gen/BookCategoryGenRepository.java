package com.faceye.component.book.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.book.entity.BookCategory;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:电子书->com.faceye.compoent.book.repository.mongo<br>
 * 说明:<br>
 * 实体:分类->com.faceye.component.book.entity.entity.BookCategory 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2016-8-2 11:06:38<br>
 */
public interface BookCategoryGenRepository extends BaseMongoRepository<BookCategory,Long> {
	
	
}/**@generate-repository-source@**/
