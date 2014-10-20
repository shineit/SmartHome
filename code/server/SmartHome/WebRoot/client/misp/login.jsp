<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>远程诊断管理平台</title>
<link href="<%=request.getContextPath()%>/client/lib/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/client/lib/dwz/favicon.ico" rel="shortcut icon"  />
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="http://www.szangell.com/"><img src="<%=request.getContextPath()%>/client/lib/dwz/themes/default/images/login_logo.gif" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="#">反馈</a></li>
						<li><a href="#" >帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title"><img src="<%=request.getContextPath()%>/client/lib/dwz/themes/default/images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<s:form action="login/login" method="POST" theme="simple">
					<p>
						<label>用户名：</label>
						<input type="text" name="user.userName" size="16" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="user.password" size="16" class="login_input" />
					</p>
					<p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="<%=request.getContextPath()%>/client/lib/dwz/themes/default/images/header_bg.png" alt="" width="75" height="24" /></span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</s:form>
			</div>
			<div class="login_banner"><img src="<%=request.getContextPath()%>/client/lib/dwz/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#">下载驱动程序</a></li>

					<li><a href="#">忘记密码怎么办？</a></li>

				</ul>
				<div class="login_inner">
				
				
					<p>远程医疗是指通过计算机技术、通信技术与多媒体技术，同医疗技术相结合，旨在提高诊断与医疗水平、降低医疗开支、满足广大人民群众保健需求的一项全新的医疗服务。</p>
					<p>目前，远程医疗技术已经从最初的电视监护、电话远程诊断发展到利用高速网络进行数字、图像、语音的综合传输，并且实现了实时的语音和高清晰图像的交流，为现代医学的应用提供了更广阔的发展空间。</p>
					
				</div>
			</div>
	 
		 <jsp:include page="include/footer.jsp" ></jsp:include>
	 </div>
	 </div>
</body>
</html>