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


import com.faceye.component.book.entity.BookCategory;
import com.faceye.component.book.service.BookCategoryService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.view.MessageBuilder;
import com.faceye.feature.util.GlobalEntity;

/**
 * 模块:电子书->com.faceye.compoent.book.controller<br>
 * 说明:<br>
 * 实体:分类:com.faceye.component.book.entity.entity.BookCategory<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2016-8-2 11:06:38<br>
 */
//@Controller
@Scope("prototype")
@RequestMapping("/book/bookCategory")
public class BookCategoryController extends BaseController<BookCategory, Long, BookCategoryService> {
	@Autowired
	public BookCategoryController(BookCategoryService service) {
		super(service);
	}
	
	
	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br> 
	 * haipenge@gmail.com <br>
	 * 创建日期2016-8-2 11:06:38<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<BookCategory> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("book.bookCategory"));
		model.addAttribute("global",global);
		return "book.bookCategory.home";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:38<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			BookCategory bookCategory=this.service.get(id);
			beforeInput(bookCategory,model,request);
			model.addAttribute("bookCategory", bookCategory);
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("book.bookCategory.edit"));
		model.addAttribute("global",global);
		return "book.bookCategory.update";
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:38<br>
	 */
	@RequestMapping(value="/input")
	public String input(BookCategory bookCategory, Model model,HttpServletRequest request){
		beforeInput(bookCategory,model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("book.bookCategory.add"));
		model.addAttribute("global",global);
		return "book.bookCategory.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:38<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid BookCategory bookCategory,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(bookCategory,model,request);
		  return "book.bookCategory.update";
		}else{
		  this.beforeSave(bookCategory,request);
	      this.service.save(bookCategory);
	      this.afterSave(bookCategory,request);
		  return "redirect:/book/bookCategory/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:38<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,RedirectAttributesModelMap model) {
		if(id!=null){
			BookCategory bookCategory=this.service.get(id);
				if(bookCategory!=null){
					if(beforeRemove(bookCategory,model)){
						this.service.remove(bookCategory);		
						//MessageBuilder.getInstance().setMessage(model,bookCategory.getName()+" "+ this.getI18N("global.remove.success"));
					}
				}
		}
		return "redirect:/book/bookCategory/home";
	}
	
	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:38<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes,Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				BookCategory bookCategory=this.service.get(Long.parseLong(id));
				if(bookCategory!=null){
					if(beforeRemove(bookCategory,model)){
						this.service.remove(bookCategory);
						//MessageBuilder.getInstance().setMessage(model,bookCategory.getName()+" "+ this.getI18N("global.remove.success"));		
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
	 * 创建日期:2016-8-2 11:06:38<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			BookCategory bookCategory=this.service.get(id);
			model.addAttribute("bookCategory", bookCategory);
		}
		return "book.bookCategory.detail";
	}
	///////////////////////////////////////////////以下为回调函数///////////////////////////////////////////////

	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:38<br>
	 */
	protected void beforeInput(BookCategory bookCategory,Model model,HttpServletRequest request){
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param bookCategory
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-2 11:06:38
	 */
	protected void beforeSave(BookCategory bookCategory,HttpServletRequest request){
	}
	
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(BookCategory bookCategory,Model model){
		boolean res=true;
		
		return res;
	}
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param bookCategory
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-2 11:06:38
	 */
	protected void afterSave(BookCategory bookCategory,HttpServletRequest request){
	   
	}
	

}
