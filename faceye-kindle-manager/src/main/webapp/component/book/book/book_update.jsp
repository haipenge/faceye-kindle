<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/book/book/book.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/book/book/book.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty book.id}">
					<fmt:message key="book.book.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="book.book.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/book/book/save" method="post" role="form" cssClass="form-horizontal" commandName="book">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="book.book.digest"/>
					</label>
					<div class="col-md-6">
					     <form:input path="digest" cssClass="form-control"/>
					   <form:errors path="digest" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="book.book.catalog"/>
					</label>
					<div class="col-md-6">
					     <form:input path="catalog" cssClass="form-control"/>
					   <form:errors path="catalog" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="book.book.contentValidity"/>
					</label>
					<div class="col-md-6">
					     <form:input path="contentValidity" cssClass="form-control"/>
					   <form:errors path="contentValidity" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="book.book.downloadCount"/>
					</label>
					<div class="col-md-6">
					     <form:input path="downloadCount" cssClass="form-control"/>
					   <form:errors path="downloadCount" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="book.book.accessCount"/>
					</label>
					<div class="col-md-6">
					     <form:input path="accessCount" cssClass="form-control"/>
					   <form:errors path="accessCount" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="book.book.bookCategory"/>
					</label>
					<div class="col-md-6">
					      <form:select path="bookCategory.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${bookCategorys}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="bookCategory.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="book.bookCategory"></fmt:message></option>
					        <c:forEach items ="${bookCategorys}" var="bookCategory">
					          <option value="${bookCategory.id}">${bookCategory.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="bookCategory" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="book.book.author"/>
					</label>
					<div class="col-md-6">
					      <form:select path="author.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${authors}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="author.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="book.author"></fmt:message></option>
					        <c:forEach items ="${authors}" var="author">
					          <option value="${author.id}">${author.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="author" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="book.book.name"/>
					</label>
					<div class="col-md-6">
					     <form:input path="name" cssClass="form-control"/>
					   <form:errors path="name" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
	<label class="col-md-2 control-label" for="imgUrl"> <spring:message
			code="book.book.imgUrl"/>
	</label>
	<div class="col-md-6">
		<form:input path="imgUrl" cssClass="form-control"/>
		<form:errors path="imgUrl" cssClass="error"/>
	</div>
</div>
<!--@generate-entity-jsp-property-update@-->

				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
