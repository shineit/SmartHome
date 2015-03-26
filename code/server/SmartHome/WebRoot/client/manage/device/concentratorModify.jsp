<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

	$(function() {
		$.pdialog.resizeDialog({
			style : {
				width : 660,
				height : 360
			}
		}, $.pdialog.getCurrent(), "");
		var permission = $("#operatePemission").val();

		switch (permission) {
		case '0'://只读
			$("#modifyLi").css("display", "none");
			$("#deleteLi").css("display", "none");
			break;
		case '1'://修改
			$("#modifyLi").css("display", "block");
			$("#deleteLi").css("display", "none");
			break;
		case '2'://删除
			$("#modifyLi").css("display", "none");
			$("#deleteLi").css("display", "block");
			break;
		case '3'://全部
			$("#modifyLi").css("display", "block");
			$("#deleteLi").css("display", "block");
			break;						
		default:
			break;
		}

	});//dialog 宽度重新定义
</script>
<input type="hidden" name="operatePemission" value="${operatePemission}" id="operatePemission"/>
<div class="pageContent">
	<s:form method="post" action="device/ConcentratorManage" class="pageForm required-validate" name="concentForm" onsubmit="return iframeCallback(this,dialogAjaxDone);"  >
		<input type="hidden" name="selectedID" value="${obj.concentratorID}" />
		<div class="pageFormContent" layoutH="58">
            
			<dl style="width:100%;">
				<dt style="width:15%;">编号：</dt>			
				<dd style="width:80%;"><input type="text" name="obj.concentratorID"  size="30" readonly="readonly" value="${obj.concentratorID}"/></dd>		 
			</dl>
			<dl style="width:100%;">
				<dt style="width:15%;">IP地址：</dt>			
				<dd style="width:80%;"><input type="text" name="obj.ipAddr"  size="30" value="${obj.ipAddr}" readonly="readonly"/></dd>
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

		<div class="formBar"  style="">
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" id="modifyLi" ><s:submit method="modify" value="修  改" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;" id="deleteLi"><s:submit method="delete" value="删  除" cssClass="mispButton danger primary"></s:submit></li>
				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
