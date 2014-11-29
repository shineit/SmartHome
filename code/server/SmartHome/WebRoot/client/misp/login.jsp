<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>云安防管理平台</title>
<link href="<%=request.getContextPath()%>/client/lib/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/client/lib/dwz/favicon.ico" rel="shortcut icon"  />
<script src="<%=request.getContextPath()%>/client/lib/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/client/lib/newJS/jQuery.md5.js" type="text/javascript"></script>
 <script type="text/javascript">  

		function changeValidateCode(obj) {
			//获取当前的时间作为参数，无具体意义  
			var timenow = new Date().getTime();
			//每次请求需要一个不同的参数，否则可能会返回同样的验证码  
			//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。  
			obj.src = "login/ValidateImage.action?d=" + timenow;
		}
		//enter 按下提交表单

		$(function() {
			document.onkeydown = function(e) {
				var ev = document.all ? window.event : e;
				if (ev.keyCode == 13) {// 如（ev.ctrlKey && ev.keyCode==13）为ctrl+Center 触发  
					//要处理的事件  
					submitLogin();
				}
			};
		});
		function submitLogin() {
			if ($.trim($("#userName").val()).length <= 0) {
				$("#warn").html("用户名输入不能为空！");
			} else if ($.trim($("#userPwd").val()).length <= 0) {
				$("#warn").html("密码输入不能为空！");
			} else if ($.trim($("#ckey").val()).length <= 0) {
				$("#warn").html("验证码输入不能为空！");
			} else {
				var newCode = $.md5($('#userPwd').val());
				$("#convertPwd").val(newCode);
				$.ajax({
					type : "POST",
					url : "login/login!validateCode.action",
					dataType : "text",
					data : {
						code : $("#ckey").val()
					},
					success : function(json) {
						if (json == 'false') {
							$("#warn").html("验证码输入错误！");
						} else {
							$("form[name='loginForm']").submit();
						}

					}

				});
			}
		}
		$(function() {

			$("#Bt1").click(function() {
				//alert($.trim($("#uaseName").val()).length);

				submitLogin();
			});//Bt1 click function
		}); //function
	</script>



</head>

<body style="overflow-x:hidden;">
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="#"><img src="<%=request.getContextPath()%>/client/lib/dwz/themes/default/images/login_logo.gif" /></a>
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
			    <div  id="warn" style="color:red;text-align:center;font-size:1.2em;">&nbsp${message}</div>
			    
				<s:form action="login/login" method="POST" theme="simple" name="loginForm">
					<p>
						<label>用户名：</label>
						<input id ="userName" type="text" name="user.userName" size="16" class="login_input" style="width:140px !important;"/>
					</p>
					<p>
						<label>密码：</label>
						<input id="userPwd" type="password" size="16" class="login_input" style="width:140px !important;" />
						<input type="hidden" id="convertPwd" name="user.password"  style="width:140px !important;"  />
					</p>
					<p>
						<label>验证码：</label>
						<input id="ckey"  class="code" type="text" size="4" name="user.validateCode" style="margin-left:0px !important;"/>
						<span><img src="login/ValidateImage.action" onclick="changeValidateCode(this)" alt="" width="75" height="24" /></span>
					</p>
					<div class="login_bar">
						<input  id="Bt1" class="sub" type="button" value=" " />
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
				
				
					<p></p>
					<p></p>
					
				</div>
			</div>
	 
		 <jsp:include page="include/footer.jsp" ></jsp:include>
	 </div>
	 </div>
</body>
</html>