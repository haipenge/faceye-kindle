<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<li><a href="#"><i class="fa fa-file"></i><span>电子书</span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "/book/bookCategory")%>"><a href="/book/bookCategory/home"><fmt:message key="book.bookCategory.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/book/downloadResource")%>"><a href="/book/downloadResource/home"><fmt:message key="book.downloadResource.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/book/bookTag")%>"><a href="/book/bookTag/home"><fmt:message key="book.bookTag.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/book/author")%>"><a href="/book/author/home"><fmt:message key="book.author.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/book/book")%>"><a href="/book/book/home"><fmt:message key="book.book.manager" /></a></li>
	</ul></li>