package com.faceye.component.book.service;

import com.faceye.component.book.entity.BookTag;
import com.faceye.feature.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
 
/**
 * 模块:电子书->com.faceye.compoent.book.service<br>
 * 说明:<br>
 * 实体:标签->com.faceye.component.book.entity.entity.BookTag 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2016-8-2 11:06:40<br>
 */
public interface BookTagService extends BaseService<BookTag,Long>{

	
}/**@generate-service-source@**/
