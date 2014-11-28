<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
      $(function () {
			$.pdialog.resizeDialog({style: {width: 660,height:360}}, 				
			$.pdialog.getCurrent(), "");
        });//dialog 宽度重新定义
</script>
<input type="hidden" value="" id=""/> 
<div class="pageContent">
	<s:form method="post" action="device/ConcentratorManage!modify.action" class="pageForm required-validate" name="concentForm" onsubmit="return iframeCallback(this,dialogAjaxDone);"  >
		<div class="pageFormContent" layoutH="58">

			<dl style="width:100%;">
				<dt style="width:15%;">编号：</dt>			
				<dd style="width:80%;"><input type="text" name="obj.concentratorID"  size="30" readonly="readonly" value="${obj.concentratorID}"/></dd>
			</dl>
			<dl style="width:100%;">
				<dt style="width:15%;">IP地址：</dt>			
				<dd style="width:80%;"><input type="text" name="obj.ipAddr"  size="30" value="${obj.ipAddr}"/></dd>
			</dl>
			<dl style="width:100%;">
				<dt style="width:15%;">名称：</dt>			
				<dd style="width:80%;"><input type="text" name="obj.name"  size="30" value="${obj.name}"/></dd>
			</dl>
						
			<dl style="width:100%;">
				<dt style="width:15%;">描述：</dt>			
				<dd style="width:80%;"><textarea name="obj.description" cols="80" rows="8" maxlength="500">${obj.description}</textarea></dd>
			</dl>						
		</div>		

		<div class="formBar">
			<ul style="margin-right:250px !important;" >
				<li ><div class="buttonActive"><div class="buttonContent"><button type="submit">修 改</button></div></div></li>
				<li style="padding-left:30px;"><div class="button"><div class="buttonContent"><button type="button" class="close">取 消</button></div></div></li>			

			</ul>
		</div>
	</s:form>
</div>
