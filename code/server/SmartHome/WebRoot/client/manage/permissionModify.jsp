<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="pageHeader">

	<div class="pageFormContent" style="height:50px;overflow:hidden;border-width:0px ! important;">
		<!--  <s:form  name="addForm" action="device/ConcentratorManage" method="POST" onsubmit="return iframeCallback(this,dialogAjaxDoneFather);"> -->
		<form method="post" action="device/ConcentratorManage!modifySure.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDoneFather)">
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	    <input type="hidden" name="userPermission.concentratorID" value="${userPermission.concentratorID}" /> 
			<dl style="width:100%;" >
				<dt style="width:15%;">用户账号：</dt>
				<dd style="width:80%;">
					<input  name="userPermission.userID" type="text" value="${userPermission.userID}" readonly="readonly"/>

				</dd>
			</dl>
			<dl style="width:50%;" >
				<dt style="width:30%;">用户权限：</dt>
				<dd style="width:65%;">
					<select  name="userPermission.operate"  >
					  <c:forEach var="st" items="${filter.permissionTypeList}">
					  	<c:choose>
					  		<c:when test="${st.intValue==userPermission.operate}">
					  			<option value="${st.intValue}" selected="selected"> ${st.strValue}</option>
					  		</c:when>
					  		<c:otherwise>
					  			<option value="${st.intValue}" > ${st.strValue}</option>
					  		</c:otherwise>
					  	</c:choose>
  			    

				  </c:forEach>
					</select>

				</dd>
			</dl>
			<div style="text-align:right;">
			 <button type="submit" class="mispButton primary" style="margin-right:10px;">确 定</button>
             <button class="mispButton primary close"  >返 回</button>
			</div>
		
        </form>
	<!-- </s:form> -->
	</div>
	
</div>


