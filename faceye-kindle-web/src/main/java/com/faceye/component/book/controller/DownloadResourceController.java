package com.faceye.component.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.MapUtils;
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
import com.faceye.component.book.entity.BookCategory;
import com.faceye.component.book.entity.DownloadResource;
import com.faceye.component.book.service.BookCategoryService;
import com.faceye.component.book.service.BookService;
import com.faceye.component.book.service.DownloadResourceService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:电子书->com.faceye.compoent.book.controller<br>
 * 说明:<br>
 * 实体:下载资源:com.faceye.component.book.entity.entity.DownloadResource<br>
 * 
 * @author haipenge <br>
 *         haipenge@gmail.com<br>
 *         创建日期:2016-8-2 11:06:39<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/book/downloadResource")
public class DownloadResourceController extends BaseController<DownloadResource, Long, DownloadResourceService> {
	@Autowired
	private BookService bookService=null;
	@Autowired
	private BookCategoryService bookCategoryService=null;
	@Autowired
	public DownloadResourceController(DownloadResourceService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge@gmail.com <br>
	 *                      创建日期2016-8-2 11:06:39<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Page<DownloadResource> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("book.downloadResource"));
		model.addAttribute("global", global);
		return "book.downloadResource.home";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:39<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			DownloadResource downloadResource = this.service.get(id);
			beforeInput(downloadResource, model, request);
			model.addAttribute("downloadResource", downloadResource);
		}
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("book.downloadResource.edit"));
		model.addAttribute("global", global);
		return "book.downloadResource.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:39<br>
	 */
	@RequestMapping(value = "/input")
	public String input(DownloadResource downloadResource, Model model, HttpServletRequest request) {
		beforeInput(downloadResource, model, request);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("book.downloadResource.add"));
		model.addAttribute("global", global);
		return "book.downloadResource.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:39<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid DownloadResource downloadResource, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(downloadResource, model, request);
			return "book.downloadResource.update";
		} else {
			this.beforeSave(downloadResource, request);
			this.service.save(downloadResource);
			this.afterSave(downloadResource, request);
			return "redirect:/book/downloadResource/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:39<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, RedirectAttributesModelMap model) {
		if (id != null) {
			DownloadResource downloadResource = this.service.get(id);
			if (downloadResource != null) {
				if (beforeRemove(downloadResource, model)) {
					this.service.remove(downloadResource);
					// MessageBuilder.getInstance().setMessage(model,downloadResource.getName()+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/book/downloadResource/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:39<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes, Model model) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				DownloadResource downloadResource = this.service.get(Long.parseLong(id));
				if (downloadResource != null) {
					if (beforeRemove(downloadResource, model)) {
						this.service.remove(downloadResource);
						// MessageBuilder.getInstance().setMessage(model,downloadResource.getName()+" "+ this.getI18N("global.remove.success"));
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
	 * 
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:39<br>
	 */
	@RequestMapping("/detail/{id}.html")
	public String detail(@PathVariable Long id, Model model) {
		this.getBookCategories(model);
		if (id != null) {
			GlobalEntity global = new GlobalEntity();
			DownloadResource downloadResource = this.service.get(id);
			if (downloadResource != null) {
				Book book = downloadResource.getBook();
				model.addAttribute("book", book);
				model.addAttribute("downloadResource", downloadResource);
				global.setTitle(downloadResource.getBook().getName() + "." + downloadResource.getFormat() + "下载");
				global.setKeywords(downloadResource.getBook().getName() + "." + downloadResource.getFormat() + "下载" + "," + downloadResource.getBook().getName());
				global.setDesc(downloadResource.getBook().getName() + "." + downloadResource.getFormat() + "下载" + "," + downloadResource.getBook().getName());
				model.addAttribute("global",global);
				// 其它相关下载
				Map params = new HashMap();
				params.put("EQ|book.$id", downloadResource.getBook().getId());
				List<DownloadResource> resources = this.service.getPage(params, 1, 0).getContent();
				model.addAllAttributes(resources);
				//同类搜索
				params = new HashMap();
				BookCategory bookCategory = book.getBookCategory();
				if (bookCategory != null) {
					params.put("EQ|bookCategory.$id", bookCategory.getId());
					List<Book> books=this.bookService.getPage(params, 1, 3).getContent();
					model.addAttribute("books", books);
				}

			}
		}
		return "book.downloadResource.detail";
	}
	
	/**
	 * 显示下载地址
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月13日 下午6:39:06
	 */
	@RequestMapping("/showDownloadUrl")
	@ResponseBody
	public String showDownloadUrl(HttpServletRequest request){
		Map params=HttpUtil.getRequestParams(request);
		String res="";
		Long id=MapUtils.getLong(params, "id");
		DownloadResource downloadResource=this.service.get(id);
		if(downloadResource!=null){
			Book book=downloadResource.getBook();
			book.setDownloadCount(book.getDownloadCount()+1);
			this.bookService.save(book);
			String url=downloadResource.getUrl();
			res="{\"url\":\""+url+"\"}";
		}
		return res;
	}
	/////////////////////////////////////////////// 以下为回调函数///////////////////////////////////////////////

	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:39<br>
	 */
	protected void beforeInput(DownloadResource downloadResource, Model model, HttpServletRequest request) {
	}

	/**
	 * 保存前的数据回调
	 * 
	 * @todo
	 * @param downloadResource
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-2 11:06:39
	 */
	protected void beforeSave(DownloadResource downloadResource, HttpServletRequest request) {
	}

	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(DownloadResource downloadResource, Model model) {
		boolean res = true;

		return res;
	}

	/**
	 * 保存后的数据回调
	 * 
	 * @todo
	 * @param downloadResource
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-2 11:06:39
	 */
	protected void afterSave(DownloadResource downloadResource, HttpServletRequest request) {

	}
	
	
	/**
	 * 获取图书分类
	 * 
	 * @param model
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月3日 下午3:24:31
	 */
	private void getBookCategories(Model model) {
		List<BookCategory> bookCategories = this.bookCategoryService.getAll();
		model.addAttribute("bookCategories", bookCategories);
	}

}
