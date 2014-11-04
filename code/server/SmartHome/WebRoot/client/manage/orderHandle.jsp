<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
      $(function () {
			$.pdialog.resizeDialog({style: {width: 660,height:360}}, 				
			$.pdialog.getCurrent(), "");
        });//dialog 宽度重新定义
</script>
<div class="pageContent">
	<s:form method="POST" action="info/OrderManage!modify.action" class="pageForm required-validate"   name="handleForm" onsubmit="return iframeCallback(this,dialogAjaxDone);" >
		<input type="text" name="selectedID" value="${selectedID}"  style="display:none;"/>	
		<div class="pageFormContent" layoutH="58">
			<dl style="width:50%;">
				<dt style="width:20%;">服务单号：</dt>			
				<dd style="width:70%;"><input type="text" name="order.orderID"  size="30" readonly="readonly" value="${order.orderID}"/></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:20%;">名称：</dt>			
				<dd style="width:70%;"><input type="text" name="order.orderName"  size="32" readonly="readonly" value="${order.orderName}"/></dd>
			</dl>			
			<dl style="width:50%;">
				<dt style="width:20%;">服务类型：</dt>			
				<dd style="width:70%;">
					<c:forEach var="ot" items="${filter.typeList}">
						  <c:choose>		       
							   <c:when test="${ot.intValue == order.orderType}">  
	                             
	                             <input type="text" name="ot"  size="30" readonly="readonly" value="${ot.strValue}"/>
	                             <input type="hidden" name="order.orderType"  size="30" readonly="readonly" value="${ot.intValue}"/>
							   </c:when>
							   <c:otherwise></c:otherwise>					   
						   </c:choose>
					</c:forEach>
				</dd>
			</dl>

			<dl style="width:50%;">
				<dt style="width:20%;">内容：</dt>			
				<dd style="width:70%;"><textarea name="order.content" cols="34" rows="4" maxlength="200" readonly="readonly">${order.content}</textarea></dd>
			</dl>
			<dl style="width:100%;">
				<dt style="width:10%;">联系人：</dt>			
				<dd style="width:80%;"><input type="text" name="order.contactName"  size="30" readonly="readonly" value="${order.contactName}"/></dd>
			</dl>			
			<dl style="width:50%;">
				<dt style="width:20%;">联系电话：</dt>			
				<dd style="width:70%;"><input type="text" name="order.phoneNum"  size="30" readonly="readonly" value="${order.phoneNum}"/></dd>
			</dl>					
			<dl style="width:100%;">
				<dt style="width:10%;">联系地址：</dt>			
				<dd style="width:80%;"><input type="text" name="order.contactAddr"  size="85" readonly="readonly" value="${order.contactAddr}"/></dd>
			</dl>								
			<dl style="width:100%;">
				<dt style="width:10%;">解决措施：</dt>			
				<dd style="width:80%;"><textarea name="order.handleResult" cols="87" rows="5" maxlength="200">${order.handleResult}</textarea></dd>
			</dl>
		</div>
		<div class="formBar">
			<ul style="margin-right:250px !important;" >
				<li ><div class="buttonActive"><div class="buttonContent"><button type="submit">提 交</button></div></div></li>
				<li style="padding-left:30px;"><div class="button"><div class="buttonContent"><button type="button" class="close">取 消</button></div></div></li>			

			</ul>
		</div>
	</s:form>
</div>
