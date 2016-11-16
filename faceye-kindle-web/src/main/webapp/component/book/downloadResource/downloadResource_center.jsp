<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/book/downloadResource/downloadResource.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/book/downloadResource/downloadResource.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="book.downloadResource.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/book/downloadResource/input"/>">
				<fmt:message key="book.downloadResource.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
			<div class="content">
				<form action="<c:url value="/book/downloadResource/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
								<input type="text" name="EQ|url" value="${searchParams.url}"
									placeholder="<fmt:message key="book.downloadResource.url"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|format" value="${searchParams.format}"
									placeholder="<fmt:message key="book.downloadResource.format"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|name" value="${searchParams.name}"
									placeholder="<fmt:message key="book.downloadResource.name"></fmt:message>"
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
								  <th><fmt:message key='book.downloadResource.url'></fmt:message></th>
								  <th><fmt:message key='book.downloadResource.format'></fmt:message></th>
								  <th><fmt:message key='book.downloadResource.name'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="downloadResource">
								<tr id="${downloadResource.id}">
									<td><input type="checkbox" name="check-single" value="${downloadResource.id}"></td>
									  <td>${downloadResource.url}</td>
									  <td>${downloadResource.format}</td>
									  <td>${downloadResource.name}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/book/downloadResource/detail/${downloadResource.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/book/downloadResource/edit/${downloadResource.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td>
									<a href="#" class="record-remove"><fmt:message key="global.remove"/></a>
									<!--
									<a
										href="<c:url value="/book/downloadResource/remove/${downloadResource.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a>
									--></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/book/downloadResource/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>
