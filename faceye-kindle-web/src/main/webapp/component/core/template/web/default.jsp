<%@ include file="/component/core/taglib/taglib.jsp"%>
<html>
<head>
<title>${global.title }</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="${global.keywords }" />
<meta http-equiv="description" content="${global.desc}" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/bootstrap-4.0.0-alpha.3/dist/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/bootstrap-4.0.0-alpha.3/bootstrap-package.css"/>" />
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/core/Core.css"/>" />
<script type="text/javascript" src="<c:url value="/js/lib/jquery/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/bootstrap-4.0.0-alpha.3/dist/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/core/Window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/security/web/user/login.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/core/Core.js"/>"></script>
</head>
<body>
<%
String webHost=HostUtil.getProperty("web.host");
request.setAttribute("host", webHost);
%>
	<tiles:insertAttribute name="default-header"></tiles:insertAttribute>
	<div class="container" style="margin-top: 85px;">
		<div class="row" style="margin-bottom: 45px;">
			<tiles:insertAttribute name="default-center"></tiles:insertAttribute>
		</div>
	</div>
	<footer class="footer">
		<div class="container">
			<tiles:insertAttribute name="default-footer"></tiles:insertAttribute>
		</div>
	</footer>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?9c2001cc5ed57c94931ee9de5b7c5a4a";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</body>
</html>
