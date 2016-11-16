package com.faceye.component.book.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;


import com.faceye.component.book.entity.Book;
import com.faceye.component.book.service.BookService;
import com.faceye.component.book.entity.BookCategory;
import com.faceye.component.book.service.BookCategoryService;
import com.faceye.component.book.entity.Author;
import com.faceye.component.book.service.AuthorService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.view.MessageBuilder;
import com.faceye.feature.util.GlobalEntity;

/**
 * 模块:电子书->com.faceye.compoent.book.controller<br>
 * 说明:<br>
 * 实体:电子书:com.faceye.component.book.entity.entity.Book<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2016-8-2 11:06:41<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/book/book")
public class BookController extends BaseController<Book, Long, BookService> {
	@Autowired
	protected BookCategoryService bookCategoryService=null; 
	@Autowired
	protected AuthorService authorService=null; 
	@Autowired
	public BookController(BookService service) {
		super(service);
	}
	
	
	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br> 
	 * haipenge@gmail.com <br>
	 * 创建日期2016-8-2 11:06:41<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<Book> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("book.book"));
		model.addAttribute("global",global);
		return "book.book.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:41<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			Book book=this.service.get(id);
			beforeInput(book,model,request);
			model.addAttribute("book", book);
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("book.book.edit"));
		model.addAttribute("global",global);
		return "book.book.update";
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:41<br>
	 */
	@RequestMapping(value="/input")
	public String input(Book book, Model model,HttpServletRequest request){
		beforeInput(book,model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("book.book.add"));
		model.addAttribute("global",global);
		return "book.book.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:41<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Book book,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(book,model,request);
		  return "book.book.update";
		}else{
		  this.beforeSave(book,request);
	      this.service.save(book);
	      this.afterSave(book,request);
		  return "redirect:/book/book/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:41<br>
	 */
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long id,Model model) {
		if(id!=null){
			Book book=this.service.get(id);
				if(book!=null){
					if(beforeRemove(book,model)){
						this.service.remove(book);		
						//MessageBuilder.getInstance().setMessage(model,book.getName()+" "+ this.getI18N("global.remove.success"));
					}
				}
		}
		//return "redirect:/book/book/home";
		String messages = MessageBuilder.getInstance().getMessages(model);
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
	}
	
	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:41<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes,Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				Book book=this.service.get(Long.parseLong(id));
				if(book!=null){
					if(beforeRemove(book,model)){
						this.service.remove(book);
						//MessageBuilder.getInstance().setMessage(model,book.getName()+" "+ this.getI18N("global.remove.success"));		
					}
				}
			}
		}
		String messages = MessageBuilder.getInstance().getMessages(model);
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
	}
	
	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:41<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			Book book=this.service.get(id);
			model.addAttribute("book", book);
		}
		return "book.book.detail";
	}
	///////////////////////////////////////////////以下为回调函数///////////////////////////////////////////////

	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:41<br>
	 */
	protected void beforeInput(Book book,Model model,HttpServletRequest request){
		    List<BookCategory> bookCategorys=this.bookCategoryService.getAll();
		    model.addAttribute("bookCategorys", bookCategorys);
		    List<Author> authors=this.authorService.getAll();
		    model.addAttribute("authors", authors);
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param book
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-2 11:06:41
	 */
	protected void beforeSave(Book book,HttpServletRequest request){
	}
	
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Book book,Model model){
		boolean res=true;
		
		return res;
	}
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param book
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-2 11:06:41
	 */
	protected void afterSave(Book book,HttpServletRequest request){
	   
	}
	

}
