<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/book/book/book.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/book/book/book.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="book.book.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/book/book/input"/>">
				<fmt:message key="book.book.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
			<div class="content">
				<form action="<c:url value="/book/book/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
								<input type="text" name="EQ|digest" value="${searchParams.digest}"
									placeholder="<fmt:message key="book.book.digest"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|catalog" value="${searchParams.catalog}"
									placeholder="<fmt:message key="book.book.catalog"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|contentValidity" value="${searchParams.contentValidity}"
									placeholder="<fmt:message key="book.book.contentValidity"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|downloadCount" value="${searchParams.downloadCount}"
									placeholder="<fmt:message key="book.book.downloadCount"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|accessCount" value="${searchParams.accessCount}"
									placeholder="<fmt:message key="book.book.accessCount"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
							     <select name="EQ|bookCategory.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="book.bookCategory"/></option>
							       <c:forEach items="${bookCategorys}" var ="bookCategory">
							         <option value="${bookCategory.id}"<c:if test="${bookCategory.id eq entity.bookCategory.id}"> selected</c:if>>${bookCategory.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
							     <select name="EQ|author.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="book.author"/></option>
							       <c:forEach items="${authors}" var ="author">
							         <option value="${author.id}"<c:if test="${author.id eq entity.author.id}"> selected</c:if>>${author.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|name" value="${searchParams.name}"
									placeholder="<fmt:message key="book.book.name"></fmt:message>"
									class="form-control input-sm">
							</div>
							
<div class="col-md-1">
	<input type="text" name="EQ|imgUrl" value="${searchParams.imgUrl}"
		placeholder="<fmt:message key="book.book.imgUrl"></fmt:message>"
		class="form-control input-sm">
</div>
<!--@generate-entity-jsp-query-detail@-->

							<div class="col-md-1">
								<button type="submit" class="btn btn-sm btn-primary">
									<fmt:message key="global.search"></fmt:message>
								</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="content">
				<button class="btn btn-primary btn-sm multi-remove">
					<fmt:message key="global.remove"></fmt:message>
				</button>
				<div classs="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th><input type="checkbox" name="check-all"></th>
								  <th><fmt:message key='book.book.digest'></fmt:message></th>
								  <th><fmt:message key='book.book.catalog'></fmt:message></th>
								  <th><fmt:message key='book.book.contentValidity'></fmt:message></th>
								  <th><fmt:message key='book.book.downloadCount'></fmt:message></th>
								  <th><fmt:message key='book.book.accessCount'></fmt:message></th>
								  <th><fmt:message key='book.book.bookCategory'></fmt:message></th>
								  <th><fmt:message key='book.book.author'></fmt:message></th>
								  <th><fmt:message key='book.book.name'></fmt:message></th>
								<th><fmt:message key='book.book.imgUrl'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="book">
								<tr id="${book.id}">
									<td><input type="checkbox" name="check-single" value="${book.id}"></td>
									  <td>${book.digest}</td>
									  <td>${book.catalog}</td>
									  <td>${book.contentValidity}</td>
									  <td>${book.downloadCount}</td>
									  <td>${book.accessCount}</td>
									  <td>${book.bookCategory.name}</td>
									  <td>${book.author.name}</td>
									  <td>${book.name}</td>
									
									<td>${book.imgUrl}</td>   
 <!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/book/book/detail/${book.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/book/book/edit/${book.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td>
									<a href="#" class="record-remove"><fmt:message key="global.remove"/></a>
									<!--
									<a
										href="<c:url value="/book/book/remove/${book.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a>
									--></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/book/book/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>
