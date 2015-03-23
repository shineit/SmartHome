<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

	$(function() {
		$.pdialog.resizeDialog({
			style : {
				width : 560,
				height : 250
			}
		}, $.pdialog.getCurrent(), "");
		});//dialog 宽度重新定义

	var operate = $("#buildShowType").val();
	//alert(operate);
	if(operate=="create")
	{
		//alert("create");
		$("#bsCreate").css("display", "block");
		$("#bsModify").css("display", "none");
	}
	if(operate=="modify")
	{
		//alert("modify");
		$("#bsCreate").css("display", "none");
		$("#bsModify").css("display", "block");	
	}
</script>
<div class="pageContent">
	<s:form method="post" action="device/BuildingManage" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)"  >
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="obj.companyID" value="${obj.companyID}"/>
			<input type="hidden" name="obj.buildingID" value="${obj.buildingID}"/>
			<input type="hidden" name="operateType" value="${operateType}" id="buildShowType"/>
			<dl style="width:50%;">
				<dt style="width:30%;">名称：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.name"  size="25" value="${obj.name}" /></dd>		 
			</dl>
			<dl style="width:100%;">
				<dt style="width:15%;">地址：</dt>			
				<dd style="width:80%;"><input type="text" name="obj.addr"  size="25" value="${obj.addr}"/></dd>		 
			</dl>
		 	<dl style="width:100%;">
				<dt style="width:15%;">描述：</dt>			
				<dd style="width:80%;"><textarea name="obj.desp" cols="60" rows="4" maxlength="200">${obj.desp}</textarea></dd>
			</dl>
				
		</div>		

		<div class="formBar"  >
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" id="bsCreate"><s:submit method="create" value="创 建" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"  id="bsModify"><s:submit method="modify" value="修 改" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
