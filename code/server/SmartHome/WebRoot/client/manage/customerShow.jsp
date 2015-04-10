<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
    $(function () 
    {
 
    var operate = $("#cShowType").val();
	if(operate=="create")
	{
		$("#csCreate").css("display", "block");
		$("#csModify").css("display", "none");
	}
	if(operate=="modify")
	{
		$("#csCreate").css("display", "none");
		$("#csModify").css("display", "block");	
	}     
     });
</script>
<div class="pageContent">
	<s:form method="post" action="sys/CustomerManage" class="pageForm required-validate"  onsubmit="return iframeCallback(this,dialogAjaxDone);"  >
		<div class="pageFormContent" layoutH="58">

			<input type="hidden" name="obj.userID" value="${obj.userID}"/>
			<input type="hidden" name="obj.userName" value="${obj.userName}"/>
			
			<input type="hidden" name="operateType" value="${operateType}" id="cShowType"/>
			
			
			<dl class="nowrap">
				<dt style="width:20%;">用户姓名：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.customerName"  size="30" value="${obj.customerName}" class="required"/></dd>
			</dl>
			<dl class="nowrap">
				<dt style="width:20%;">联系电话：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.phone"  size="30" value="${obj.phone}" /></dd>
			</dl> 
		
		</div>
		<div class="formBar"  >
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" id="csCreate"><s:submit method="create" value="创 建" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"  id="csModify"><s:submit method="modify" value="修 改" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
