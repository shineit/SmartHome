<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
      $(function () {
			$.pdialog.resizeDialog({style: {width: 660,height:420}}, 				
			$.pdialog.getCurrent(), "");
        });//dialog 宽度重新定义
        
    $(function () 
    {
 
   var operate = $("#adShowType").val();
	if(operate=="create")
	{
		$("#adCreate").css("display", "block");
		$("#adModify").css("display", "none");
	}
	if(operate=="modify")
	{
		$("#adCreate").css("display", "none");
		$("#adModify").css("display", "block");	
	}     
     });
</script>
<div class="pageContent">
	<s:form method="post" action="sys/OrgManage" class="pageForm required-validate"  onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data" >
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="obj.org_uuid" value="${obj.org_uuid}"/>
			<input type="hidden" name="obj.org_id" value="${obj.org_id}"/>
			
			<input type="hidden" name="operateType" value="${operateType}" id="adShowType"/>
 
 			<dl style="width:100%;">
				<dt style="width:15%;">管理员用户：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.admin_name"  size="30" value="${obj.admin_name}" class="required"/></dd>
			</dl> 
			<dl style="width:100%;">
				<dt style="width:15%;">机构名称：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.org_name"  size="30" value="${obj.org_name}" class="required"/></dd>
			</dl>
 
			<dl class="nowrap">
				<dt style="width:15%;">机构描述：</dt>			
				<dd style="width:80%;"><textarea name="obj.org_desp" cols="80" rows="4" maxlength="200">${obj.org_desp}</textarea></dd>
			</dl>
 	
		
		</div>
		<div class="formBar"  >
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" id="adCreate"><s:submit method="create" value="创 建" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"  id="adModify"><s:submit method="modify" value="修 改" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
