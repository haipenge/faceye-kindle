<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/book/downloadResource/downloadResource.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/book/downloadResource/downloadResource.js"/>"></script>
<div class="col-md-8">
	<div class="card card-block">
		<input type="hidden" name="id" value="${downloadResource.id}">
		<h1>《${book.name }》下载</h1>
		<p class="text-center">
			<a href="#" class="click-show-download-url">点击显示《${book.name}》下载地址</a>
		</p>
		<div class="content">
			<p id="show-download-url"></p>
		</div>
		<div>
			<div>
				<script type="text/javascript">
					/*700*250 book 下*/
					var cpro_id = "u2784773";
				</script>
				<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/c.js"></script>
			</div>
		</div>
		<div class="content">
			<h2>《${book.name }》简介</h2>
			<p>${book.contentValidity}</p>
		</div>
		<div>
			<script type="text/javascript">
				/*700*250 book底*/
				var cpro_id = "u2784775";
			</script>
			<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/c.js"></script>
		</div>
		<c:if test="${fn:length(resources)>1 }">
			<h2>《${book.name}》相关下载</h2>
			<ul class="list-group">
				<c:forEach var="resource" items="${resources}">
					<c:if test="${resource.id ne downloadResource.id }">
						<li class="list-group-item">下载 <a href="<c:url value="${host }/book/downloadResource/detail/${resource.id}.html"/>">${book.name}.${resource.format}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</c:if>
	</div>
</div>
<div class="col-md-4">
	<h4>猜你喜欢</h4>
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
	<div>
		<script type="text/javascript">
			/*336*280 书右边栏*/
			var cpro_id = "u2784738";
		</script>
		<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/c.js"></script>


	</div>
</div>

