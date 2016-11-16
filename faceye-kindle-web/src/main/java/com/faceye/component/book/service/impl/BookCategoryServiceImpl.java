package com.faceye.component.book.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.util.ServiceException;

import com.faceye.component.book.entity.BookCategory;
import com.faceye.component.book.repository.mongo.BookCategoryRepository;
import com.faceye.component.book.repository.mongo.customer.BookCategoryCustomerRepository;
import com.faceye.component.book.service.BookCategoryService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * 模块:电子书->com.faceye.compoent.book.service.impl<br>
 * 说明:<br>
 * 实体:分类->com.faceye.component.book.entity.entity.BookCategory 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2016-8-2 11:06:38<br>
 */
@Service
public class BookCategoryServiceImpl extends BaseMongoServiceImpl<BookCategory, Long, BookCategoryRepository> implements BookCategoryService {
    @Autowired
    private BookCategoryCustomerRepository bookCategoryCustomerRepository=null;
	@Autowired
	public BookCategoryServiceImpl(BookCategoryRepository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author haipenge <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:2016-8-2 11:06:38<br>
	*/
	@Override
	public Page<BookCategory> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<BookCategory> entityPath = resolver.createPath(entityClass);
		// PathBuilder<BookCategory> builder = new PathBuilder<BookCategory>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		//Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		//if (predicate != null) {
		//	logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		//}
		//Sort sort = new Sort(Direction.DESC, "id");
		//Page<BookCategory> res = null;
		//if (size != 0) {
		//	Pageable pageable = new PageRequest(page, size, sort);
		//	res = this.dao.findAll(predicate, pageable);
		//} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<BookCategory>("id") {
			// })
		//	List<BookCategory> items = (List) this.dao.findAll(predicate);
		//	res = new PageImpl<BookCategory>(items);

		//}
		return dao.getPage(searchParams,page,size);
	}
	
	
}/**@generate-service-source@**/
