<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/book/downloadResource/downloadResource.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/book/downloadResource/downloadResource.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="book.downloadResource.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.downloadResource.url"></fmt:message></td>
					<td>${downloadResource.url}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.downloadResource.format"></fmt:message></td>
					<td>${downloadResource.format}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="book.downloadResource.name"></fmt:message></td>
					<td>${downloadResource.name}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/book/downloadResource/edit/${downloadResource.id}"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/book/downloadResource/remove/${downloadResource.id}"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/book/downloadResource/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
