<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/book/book/book.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/book/book/book.js"/>"></script>
<div class="row">
	<div class="col-md-9">
		<h1>
			<c:choose>
				<c:when test="${not empty bookCategory}">${bookCategory.name } kindle电子书</c:when>
				<c:otherwise>kindle电子书</c:otherwise>
			</c:choose>
		</h1>
		<c:if test="${empty page.content }">
			<b>I'm so sorry baby! I have not 找到 U要的书:(</b>
		</c:if>
		<div classs="table-responsive">
			<table class="table table-bordered">
				<tbody>
					<c:forEach items="${page.content}" var="book" varStatus="status">
						<c:if test="${status.index==0}">
							<tr>
						</c:if>
						<c:if test="${status.index!=0 && status.index mod 4 ==0 }">
							</tr>
							<tr>
						</c:if>
						<td><a href="<c:url value="${host }/book/book/detail/${book.id}.html"/>"><img src="${book.imgUrl}" class="img-thumbnail center-block"></a>
							<p>
								<a href="<c:url value="${host}/book/book/detail/${book.id}.html"/>">${book.name}</a>
							</p></td>
						<c:if test="${status.last && status.index mod 4 !=0 }">
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<c:choose>
				<c:when test="${empty bookCategory}">
					<f:page page="${page}" url="${host}/book/book/home" params="<%=request.getParameterMap()%>" />
				</c:when>
				<c:otherwise>
					<f:page page="${page}" url="${host}/book/book/home/${bookCategory.id}.html" params="<%=request.getParameterMap()%>" />
				</c:otherwise>
			</c:choose>

		</div>
	</div>
	<div class="col-md-3">
		<h4>热门图书</h4>
		<div class="list-group">
			<c:forEach var="hot" items="${hotBooks.content}" varStatus="status">
				<div class="row" style="margin-top: 10px;">
					<div class="col-md-4">
						<a href="<c:url value="${host}/book/book/detail/${hot.id}.html"/>"> <img src="${hot.imgUrl}" class="img-responsive img-thumbnail">
						</a>
					</div>
					<div class="col-md-8">
						<h6>
							<a href="<c:url value="${host}/book/book/detail/${hot.id}.html"/>"> ${hot.name}</a>
						</h6>
						<p>
							作者:<a href="${host }/book/book/home?EQ|author.$id=${hot.author.id}">${hot.author.name }</a>
						</p>
						<p>
							分类:<a href="${host }/book/book/home?EQ|bookCategory.$id=${hot.bookCategory.id }">${hot.bookCategory.name}</a>
						</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

