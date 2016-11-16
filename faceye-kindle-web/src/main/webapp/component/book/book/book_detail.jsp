<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/book/book/book.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/book/book/book.js"/>"></script>
<div class="col-md-8">
	<div class="card card-block">
		<dl class="dl-horizontal">
			<dt class="col-md-4">
				<img src="${book.imgUrl}" class="img-responsive center-block" style="width: 200px;">
			</dt>
			<dd class="col-md-8">
				<h1>${book.name}</h1>
				<p>作者:${book.author.name}</p>
				<p>
					标签:
					<c:forEach items="${book.bookTags}" var="tag" varStatus="status">
                      ${tag.name} <c:if test="${!status.last}">,</c:if>
					</c:forEach>
				</p>
				<p>访问量:${book.accessCount}</p>
				<p>下载量:${book.downloadCount}</p>
			</dd>
		</dl>
		<div>
			<script type="text/javascript">
				/*700*250 book 下*/
				var cpro_id = "u2784773";
			</script>
			<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/c.js"></script>
		</div>
	</div>
	<div class="card card-block">
		<h2>《${book.name}》 Kindle图书下载</h2>
		<ul class="list-group">
			<c:forEach var="resource" items="${downloadResources}">
				<li class="list-group-item">下载 <a href="<c:url value="${host }/book/downloadResource/detail/${resource.id}.html"/>">${book.name}.${resource.format}</a></li>
			</c:forEach>
		</ul>
		<div>
			<script type="text/javascript">
				/*700*250 book底*/
				var cpro_id = "u2784775";
			</script>
			<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/c.js"></script>
		</div>
	</div>
	<div class="card card-block">
		<h3>《${book.name}》简介</h3>
		<p class="card-text">${book.contentValidity}</p>
		<c:if test="${not empty book.author && book.author.profile  !=''}">
			<h3>作者简介</h3>
			<p class="card-text">${book.author.profile }</p>
		</c:if>
		<c:if test="${book.catalog!='' }">
			<h3>目录</h3>
			<p>${book.catalog}</p>
		</c:if>
		<c:if test="${book.digest!='' }">
			<h3>《${book.name }》书摘</h3>
			<p>${book.digest }</p>
		</c:if>
	</div>
</div>
<div class="col-md-4">
	<div class="card card-block">
		<h4 class="card-title">同类好书</h4>
		<div class="list-group">
			<c:forEach var="near" items="${books}" varStatus="status">
				<div class="row" style="margin-top: 10px;">
					<div class="col-md-4">
						<a href="<c:url value="${host}/book/book/detail/${near.id}.html"/>"> <img src="${near.imgUrl}" class="img-responsive img-thumbnail">
						</a>
					</div>
					<div class="col-md-8">
						<h4>
							<a href="<c:url value="${host}/book/book/detail/${near.id}.html"/>"> ${near.name}</a>
						</h4>
						<p>
							作者:<a href="${host }/book/book/home?EQ|author.$id=${near.author.id}">${near.author.name }</a>
						</p>
						<p>
							分类:<a href="${host }/book/book/home?EQ|bookCategory.$id=${near.bookCategory.id }">${near.bookCategory.name}</a>
						</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div>
		<script type="text/javascript">
			/*336*280 书右边栏*/
			var cpro_id = "u2784738";
		</script>
		<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/c.js"></script>
	</div>
</div>