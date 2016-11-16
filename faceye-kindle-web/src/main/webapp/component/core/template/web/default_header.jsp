<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<nav class="navbar navbar-dark navbar-fixed-top bg-inverse">
	<a class="navbar-brand" href="#">Kindle电子书吧</a>
	<ul class="nav navbar-nav">
		<li class="nav-item"><a class="nav-link" href="<c:url value="${host }/book/book/home"/>">首页</a></li>
		<c:forEach items="${bookCategories}" var="category">
			<li class="nav-item"><a class="nav-link" href="<c:url value="${host }/book/book/home/${category.id}.html"/>">${category.name}</a></li>
		</c:forEach>
	</ul>
	<form class="form-inline navbar-form pull-right" style="margin-left:15px;"  action="<c:url value="${host }/book/book/home"/>">
	  <input class="form-control" type="text" name="like|name" placeholder="输入书名...">
	  <button class="btn btn-success-outline" type="submit">搜索</button>
	</form>
</nav>
