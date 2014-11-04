<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<script type="text/javascript">
	function convert()
	{   
		$("#convertPwd1").val($.md5($("input[name='oldPwd']").val()));
		$("#convertPwd2").val($.md5($("input[name='newPwd']").val()));
	}
</script>   
<div class="pageContent">
	
	<s:form method="post" action="login/login!modifyPwd.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">

			<div class="unit">
				<label>输入旧密码：</label>
				<input type="password" name="oldPwd" size="30" minlength="4" maxlength="12" class="required" />
				<input type="hidden" id="convertPwd1" name="pwdModel.oldPassword"  style="width:140px !important;"  />
			</div>
			<div class="unit">
				<label>输入新密码：</label>
				<input type="password" id="cp_newPassword" name="newPwd" size="30" minlength="4" maxlength="12" class="required alphanumeric"/>
				<input type="hidden" id="convertPwd2" name="pwdModel.newPassword"  style="width:140px !important;"  />
			</div>
			<div class="unit">
				<label>再次确认新密码：</label>
				<input type="password" name="rnewPassword" size="30" equalTo="#cp_newPassword" minlength="4" maxlength="12" class="required alphanumeric"/>
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="convert()">提 交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取 消</button></div></div></li>
			</ul>
		</div>
	</s:form>
	
</div>