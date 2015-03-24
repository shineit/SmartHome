<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="pageHeader">

	<div class="pageFormContent" style="height:50px;overflow:hidden;border-width:0px ! important;">
	<form method="post" action="device/CompanyManage!addPermission.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDoneFather)">
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	    <input type="hidden" name="userPermission.companyID" value="${selectedID}" /> 
			<dl style="width:100%;" >
				<dt style="width:15%;">用户账号：</dt>
				<dd style="width:70%;">
					<input  name="userPermission.userID" type="text"  />
					<a class="btnLook" href="sys/UserManage!addUser.action" lookupGroup="userPermission" rel="addUser">用户列表</a>	
					<span class="info">(点击图标查找用户)</span>
				</dd>
			</dl>
			<dl style="width:50%;" >
				<dt style="width:30%;">用户名称：</dt>
				<dd style="width:60%;">
					<input  name="userPermission.userName" type="text"  />
				</dd>
			</dl>

			<div style="text-align:right;">
			 <button type="submit" class="mispButton primary" style="margin-right:10px;">添  加</button>
             <button type="button" class="mispButton primary close" >关 闭</button>
			</div>		
        </form>
	</div>
	
</div>

<div class="pageContent">

	<table class="table" width="100%" layoutH="132" >
		<thead>
			<tr>		
				<th width="100" align="center">用户编号</th>
				<th width="120" align="center">用户名</th>
				<th width="120" align="center">账户类型</th>
				<th width="100" align="center">操作</th>
			</tr>
		</thead>
		<tbody>

		<c:forEach var="e" items="${permissionTable.currentPageData}"> 
			<tr target="sid_user" rel="${e.userID}">
	
				<td>${e.userID}</td>
				<td>${e.userName}</td>
				<td>
	            	<c:forEach var="t" items="${filter.userTypeList}">
						  <c:choose>		       
							   <c:when test="${t.typeValue == e.role}">  
	                             ${t.type}
							   </c:when>
							   <c:otherwise> 	   	   
							   </c:otherwise>
							   
						   </c:choose>
					</c:forEach>
				</td>
				<td>	
	<a title="确定要删除该用户的权限？" target="ajaxTodo" href="device/CompanyManage!deletePermission.action?userPermission.userID=${e.userID}&userPermission.companyID=${selectedID}" 
	class="btnDel"  callback="dialogAjaxDoneFather" >删除</a>

				</td>
			</tr>
		</c:forEach>	
	
		
			
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${permissionTable.page}" scope="request"/>
			
			<select class="combox" onchange="navTabPageBreak({numPerPage:this.value})">
				<c:forEach var="e" items="${page.pageSizeList}"> 	
			       <c:if test="${e==page.pageSize}">
			         <option value="${e}" selected>${e}</option>
				  </c:if>
                  <c:if test="${e!=page.pageSize}">
			         <option value="${e}">${e}</option>
				  </c:if>
				</c:forEach>
 
			</select>
			<span>条，共${page.count}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.count}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.currentPage}"></div>

	</div>
</div>
