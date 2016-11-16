package com.faceye.component.book.controller;

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

import com.faceye.component.book.entity.Author;
import com.faceye.component.book.service.AuthorService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:电子书->com.faceye.compoent.book.controller<br>
 * 说明:<br>
 * 实体:作者:com.faceye.component.book.entity.entity.Author<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2016-8-2 11:06:40<br>
 */
//@Controller
@Scope("prototype")
@RequestMapping("/book/author")
public class AuthorController extends BaseController<Author, Long, AuthorService> {
	@Autowired
	public AuthorController(AuthorService service) {
		super(service);
	}
	
	
	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br> 
	 * haipenge@gmail.com <br>
	 * 创建日期2016-8-2 11:06:40<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<Author> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("book.author"));
		model.addAttribute("global",global);
		return "book.author.home";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:40<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			Author author=this.service.get(id);
			beforeInput(author,model,request);
			model.addAttribute("author", author);
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("book.author.edit"));
		model.addAttribute("global",global);
		return "book.author.update";
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:40<br>
	 */
	@RequestMapping(value="/input")
	public String input(Author author, Model model,HttpServletRequest request){
		beforeInput(author,model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("book.author.add"));
		model.addAttribute("global",global);
		return "book.author.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:40<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Author author,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(author,model,request);
		  return "book.author.update";
		}else{
		  this.beforeSave(author,request);
	      this.service.save(author);
	      this.afterSave(author,request);
		  return "redirect:/book/author/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:40<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,RedirectAttributesModelMap model) {
		if(id!=null){
			Author author=this.service.get(id);
				if(author!=null){
					if(beforeRemove(author,model)){
						this.service.remove(author);		
						//MessageBuilder.getInstance().setMessage(model,author.getName()+" "+ this.getI18N("global.remove.success"));
					}
				}
		}
		return "redirect:/book/author/home";
	}
	
	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:40<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes,Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				Author author=this.service.get(Long.parseLong(id));
				if(author!=null){
					if(beforeRemove(author,model)){
						this.service.remove(author);
						//MessageBuilder.getInstance().setMessage(model,author.getName()+" "+ this.getI18N("global.remove.success"));		
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
	 * 创建日期:2016-8-2 11:06:40<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			Author author=this.service.get(id);
			model.addAttribute("author", author);
		}
		return "book.author.detail";
	}
	///////////////////////////////////////////////以下为回调函数///////////////////////////////////////////////

	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-2 11:06:40<br>
	 */
	protected void beforeInput(Author author,Model model,HttpServletRequest request){
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param author
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-2 11:06:40
	 */
	protected void beforeSave(Author author,HttpServletRequest request){
	}
	
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Author author,Model model){
		boolean res=true;
		
		return res;
	}
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param author
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-2 11:06:40
	 */
	protected void afterSave(Author author,HttpServletRequest request){
	   
	}
	

}
