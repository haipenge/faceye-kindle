package com.faceye.component.book.controller;

import java.util.HashMap;
import java.util.List;
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
import com.faceye.component.book.entity.Book;
import com.faceye.component.book.entity.BookCategory;
import com.faceye.component.book.entity.DownloadResource;
import com.faceye.component.book.service.AuthorService;
import com.faceye.component.book.service.BookCategoryService;
import com.faceye.component.book.service.BookService;
import com.faceye.component.book.service.DownloadResourceService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.RandUtil;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:电子书->com.faceye.compoent.book.controller<br>
 * 说明:<br>
 * 实体:电子书:com.faceye.component.book.entity.entity.Book<br>
 * 
 * @author haipenge <br>
 *         haipenge@gmail.com<br>
 *         创建日期:2016-8-2 11:06:41<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/book/book")
public class BookController extends BaseController<Book, Long, BookService> {
	@Autowired
	protected BookCategoryService bookCategoryService = null;
	@Autowired
	protected AuthorService authorService = null;
	@Autowired
	private DownloadResourceService downloadResourceService = null;

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
	 * 						haipenge@gmail.com <br>
	 *                      创建日期2016-8-2 11:06:41<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Page<Book> page = this.service.getPage(searchParams, getPage(searchParams), 24);
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global = new GlobalEntity();
		global.setTitle("FaceYe电子书库-kindle电子书库-FaceYe");
		global.setKeywords("电子书库,kindle电子书库,kindle电子书下载,kindle电子书mobi下载");
		global.setDesc("电子书库,kindle电子书库,kindle电子书下载,kindle电子书mobi下载");
		model.addAttribute("global", global);
		this.getBookCategories(model);
		this.getHotBooks(request, model);
		return "book.book.home";
	}

	/**
	 * 按分类
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月9日 下午4:31:07
	 */
	@RequestMapping("/home/{id}.html")
	public String home(@PathVariable Long id, HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		searchParams.put("EQ|bookCategory.$id", id);
		BookCategory bookCategory = this.bookCategoryService.get(id);
		model.addAttribute(bookCategory);
		Page<Book> page = this.service.getPage(searchParams, getPage(searchParams), 24);
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(bookCategory.getName() + "-kindle电子书库-FaceYe");
		global.setKeywords(bookCategory.getName() + "-电子书库,kindle电子书库,kindle电子书下载,kindle电子书mobi下载");
		global.setDesc(bookCategory.getName() + "-电子书库,kindle电子书库,kindle电子书下载,kindle电子书mobi下载");
		model.addAttribute("global", global);
		this.getBookCategories(model);
		this.getHotBooks(request, model);
		return "book.book.home";
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

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:41<br>
	 */
	//@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			Book book = this.service.get(id);
			beforeInput(book, model, request);
			model.addAttribute("book", book);
		}
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("book.book.edit"));
		model.addAttribute("global", global);
		return "book.book.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:41<br>
	 */
	//@RequestMapping(value = "/input")
	public String input(Book book, Model model, HttpServletRequest request) {
		beforeInput(book, model, request);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("book.book.add"));
		model.addAttribute("global", global);
		return "book.book.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:41<br>
	 */
	//@RequestMapping("/save")
	public String save(@Valid Book book, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(book, model, request);
			return "book.book.update";
		} else {
			this.beforeSave(book, request);
			this.service.save(book);
			this.afterSave(book, request);
			return "redirect:/book/book/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:41<br>
	 */
	//@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, RedirectAttributesModelMap model) {
		if (id != null) {
			Book book = this.service.get(id);
			if (book != null) {
				if (beforeRemove(book, model)) {
					this.service.remove(book);
					// MessageBuilder.getInstance().setMessage(model,book.getName()+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/book/book/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-2 11:06:41<br>
	 */
	//@RequestMapping("/multiRemove")
	//@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes, Model model) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				Book book = this.service.get(Long.parseLong(id));
				if (book != null) {
					if (beforeRemove(book, model)) {
						this.service.remove(book);
						// MessageBuilder.getInstance().setMessage(model,book.getName()+" "+ this.getI18N("global.remove.success"));
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
	 *                      创建日期:2016-8-2 11:06:41<br>
	 */
	@RequestMapping("/detail/{id}.html")
	public String detail(@PathVariable Long id, Model model) {
		this.getBookCategories(model);
		if (id != null) {
			Book book = this.service.get(id);
			if (book != null) {
				book.setAccessCount(book.getAccessCount()+1);
				this.service.save(book);
				model.addAttribute("book", book);
				Map params = new HashMap();
				params.put("EQ|book.$id", id);
				List<DownloadResource> resources = this.downloadResourceService.getPage(params, 1, 0).getContent();
				model.addAttribute("downloadResources", resources);
				// 查找同类好书
				params = new HashMap();
				BookCategory bookCategory = book.getBookCategory();
				if (bookCategory != null) {
					params.put("EQ|bookCategory.$id", bookCategory.getId());
					List<Book> books = this.service.getPage(params, 1, 3).getContent();
					model.addAttribute("books", books);
				}
				GlobalEntity global = new GlobalEntity();
				global.setTitle(book.getName() + "Kindle电子书下载");
				global.setKeywords(book.getName() + "," + book.getName() + "Kindle电子书" + "," + book.getName() + ".mobi下载");
				global.setDesc(book.getName() + "," + book.getName() + "Kindle电子书" + "," + book.getName() + ".mobi");
				model.addAttribute("global", global);
			}
		}
		return "book.book.detail";
	}
	
	/**
	 * 取得热门图书
	 * @param request
	 * @param model
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月14日 上午8:29:16
	 */
	private void getHotBooks(HttpServletRequest request,Model model){
		int page=RandUtil.getRandInt(1, 250);
		Page<Book> hotBooks=this.service.getPage(null, page, 10);
		model.addAttribute("hotBooks",hotBooks);
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
	 *                      创建日期:2016-8-2 11:06:41<br>
	 */
	protected void beforeInput(Book book, Model model, HttpServletRequest request) {
		List<BookCategory> bookCategorys = this.bookCategoryService.getAll();
		model.addAttribute("bookCategorys", bookCategorys);
		List<Author> authors = this.authorService.getAll();
		model.addAttribute("authors", authors);
	}

	/**
	 * 保存前的数据回调
	 * 
	 * @todo
	 * @param book
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-2 11:06:41
	 */
	protected void beforeSave(Book book, HttpServletRequest request) {
	}

	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Book book, Model model) {
		boolean res = true;

		return res;
	}

	/**
	 * 保存后的数据回调
	 * 
	 * @todo
	 * @param book
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-2 11:06:41
	 */
	protected void afterSave(Book book, HttpServletRequest request) {

	}
	
	

}
