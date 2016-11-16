<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/book/book/book.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/book/book/book.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="book.book.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.book.digest"></fmt:message></td>
					<td>${book.digest}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.book.catalog"></fmt:message></td>
					<td>${book.catalog}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.book.contentValidity"></fmt:message></td>
					<td>${book.contentValidity}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.book.downloadCount"></fmt:message></td>
					<td>${book.downloadCount}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.book.accessCount"></fmt:message></td>
					<td>${book.accessCount}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.book.bookCategory"></fmt:message></td>
				    <td>${book.bookCategory.name}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.book.author"></fmt:message></td>
				    <td>${book.author.name}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.book.name"></fmt:message></td>
					<td>${book.name}</td>
				</tr>
				<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="book.book.imgUrl"></fmt:message></td>
	<td>${book.imgUrl}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->

			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/book/book/edit/${book.id}"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/book/book/remove/${book.id}"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/book/book/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
