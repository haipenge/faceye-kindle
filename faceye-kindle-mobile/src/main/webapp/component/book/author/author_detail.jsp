<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/book/author/author.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/book/author/author.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="book.author.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.author.profile"></fmt:message></td>
					<td>${author.profile}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.author.name"></fmt:message></td>
					<td>${author.name}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/book/author/edit/${author.id}"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/book/author/remove/${author.id}"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/book/author/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
