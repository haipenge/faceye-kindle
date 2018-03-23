package com.faceye.component.book.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.faceye.feature.service.PropertyService;
import com.faceye.feature.util.bean.BeanContextUtil;

/**
 * 模块:电子书->book->Book<br>
 * 说明:<br>
 * 实体:电子书->com.faceye.component.book.entity.Book Mongo 对像<br>
 * mongo数据集:book_book<br>
 * 
 * @author haipenge <br>
 *         联系:haipenge@gmail.com<br>
 *         创建日期:2016-8-2 11:06:41<br>
 *         spring-data-mongo支持的注释类型<br>
 * @Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。<br>
 * @Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。<br>
 * @DBRef - 声明类似于关系数据库的关联关系。ps：暂不支持级联的保存功能，当你在本实例中修改了DERef对象里面的值时，单独保存本实例并不能保存DERef引用的对象，它要另外保存<br>
 * @Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。<br>
 * @CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。<br>
 * @GeoSpatialIndexed - 声明该字段为地理信息的索引。<br>
 * @Transient - 映射忽略的字段，该字段不会保存到<br>
 * @PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据。<br>
 * @CompoundIndexes({ @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}") })
 * @Indexed(unique = true)
 */

@Document(collection = "faceye_11_book_book")
public class Book implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	@Indexed(direction = IndexDirection.DESCENDING)
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 说明:书摘<br>
	 * 属性名: digest<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:digest<br>
	 * 
	 * @author haipenge<br>
	 */
	private String digest = "";

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	/**
	 * 说明:目录<br>
	 * 属性名: catalog<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:catalog<br>
	 * 
	 * @author haipenge<br>
	 */
	private String catalog = "";

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	/**
	 * 说明:内容简介<br>
	 * 属性名: contentValidity<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:content_validity<br>
	 * 
	 * @author haipenge<br>
	 */
	private String contentValidity = "";

	public String getContentValidity() {
		return contentValidity;
	}

	public void setContentValidity(String contentValidity) {
		this.contentValidity = contentValidity;
	}

	/**
	 * 说明:下载次数 <br>
	 * 属性名: downloadCount<br>
	 * 类型: java.lang.Integer<br>
	 * 数据库字段:download_count<br>
	 * 
	 * @author haipenge<br>
	 */
	private Integer downloadCount = 0;

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	/**
	 * 说明:访问次数<br>
	 * 属性名: accessCount<br>
	 * 类型: java.lang.Integer<br>
	 * 数据库字段:access_count<br>
	 * 
	 * @author haipenge<br>
	 */
	private Integer accessCount = 0;

	public Integer getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(Integer accessCount) {
		this.accessCount = accessCount;
	}

	/**
	 * 说明:分类<br>
	 * 属性名: bookCategory<br>
	 * 类型: com.faceye.component.book.entity.BookCategory<br>
	 * 数据库字段:bookCategory<br>
	 * 
	 * @author haipenge<br>
	 */
	@DBRef
	private BookCategory bookCategory = null;

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	/**
	 * 说明:作者<br>
	 * 属性名: author<br>
	 * 类型: com.faceye.component.book.entity.Author<br>
	 * 数据库字段:author<br>
	 * 
	 * @author haipenge<br>
	 */
	@DBRef
	private Author author = null;

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * 说明:名称<br>
	 * 属性名: name<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:name<br>
	 * 
	 * @author haipenge<br>
	 */
	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 说明:创建日期<br>
	 * 属性名: createDate<br>
	 * 类型: Date<br>
	 * 数据库字段:createDate<br>
	 * 
	 * @author haipenge<br>
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
	private Date createDate = new Date();

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 说明:封面<br>
	 * 属性名: imgUrl<br>
	 * 类型: String<br>
	 * 数据库字段:img_url<br>
	 * 
	 * @author haipenge<br>
	 */

	private String imgUrl;

	public String getImgUrl() {
		if (!StringUtils.startsWithIgnoreCase(imgUrl, "http")) {
			imgUrl = BeanContextUtil.getBean(PropertyService.class).get("image.server") + imgUrl;
		}
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@DBRef
	private List<BookTag> bookTags = new ArrayList<BookTag>(0);

	public List<BookTag> getBookTags() {
		return bookTags;
	}

	public void setBookTags(List<BookTag> bookTags) {
		this.bookTags = bookTags;
	}

}/** @generate-entity-source@ **/
