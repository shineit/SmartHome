<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="pageHeader">

	<div class="pageFormContent" style="height:50px;overflow:hidden;border-width:0px ! important;">
		<!--  <s:form  name="addForm" action="device/ConcentratorManage" method="POST" onsubmit="return iframeCallback(this,dialogAjaxDoneFather);"> -->
		<form method="post" action="device/ConcentratorManage!addPermission.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDoneFather)">
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	    <input type="hidden" name="userPermission.concentratorID" value="${concentratorID}" id="selID"/> 
			<dl style="width:100%;" >
				<dt style="width:15%;">用户账号：</dt>
				<dd style="width:80%;">
					<input  name="userPermission.userID" type="text"  />
					<a class="btnLook" href="sys/UserManage!addUser.action" lookupGroup="userPermission" rel="Menu1004">用户列表</a>	
					<span class="info">(点击图标查找用户)</span>
				</dd>
			</dl>
			<dl style="width:50%;" >
				<dt style="width:30%;">用户权限：</dt>
				<dd style="width:65%;">
					<select  name="userPermission.operate" >
					  <c:forEach var="st" items="${filter.permissionTypeList}">
		   	   			    <option value="${st.intValue}" > ${st.strValue}</option>

				  </c:forEach>
					</select>

				</dd>
			</dl>
			<div style="text-align:right;">
			 <button type="submit" class="mispButton primary" style="margin-right:10px;">添  加</button>
             <button type="button" class="mispButton primary close" >取  消</button>
			</div>

<!-- 			<s:submit  value="添加用户" cssClass="mispButton primary" method="addPermission"  ></s:submit> 
			<s:submit  value="取 消" cssClass="mispButton primary"  onclick="resetForm(this.form)"></s:submit> 	 -->		
        </form>
	<!-- </s:form> -->
	</div>
	
</div>

<div class="pageContent">

	<table class="table" width="100%" layoutH="132" >
		<thead>
			<tr>
			    <th width="100" align="center">集中器编号</th>		
				<th width="100" align="center">用户编号</th>
				<th width="120" align="center">用户权限</th>
				<th width="100" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
<%-- 		    <tr>
		    	<td>C00001</td>
		    	<td>U00001</td>
		    	<td>只读，修改，删除</td>
		    	<td>
		    	<a title="集中器信息修改" target="dialog" href="device/ConcentratorManage!show.action?selectedID=${e.concentratorID}" class="btnEdit" mask="true">修改</a>
		    	<a title="专家管理" target="navTab" href="ExpertManage!show.action?selectedID=${e.id}" class="btnDel" rel="Menu${selectedMenuID}" style="margin-left:10px;">删除</a>
		    	</td>
		    </tr> --%>
		<c:forEach var="e" items="${permissionTable.currentPageData}"> 
			<tr target="sid_user" rel="${e.concentratorID}">
				<td>${e.concentratorID}</td>
				<td>${e.userID}</td>
				<td>
	            	<c:forEach var="t" items="${filter.permissionTypeList}">
						  <c:choose>		       
							   <c:when test="${t.intValue == e.operate}">  
	                             ${t.strValue}
							   </c:when>
							   <c:otherwise> 	   	   
							   </c:otherwise>
							   
						   </c:choose>
					</c:forEach>
				</td>
				<td>
				<a title="用户权限修改" target="dialog" href="device/ConcentratorManage!modifyPermission.action?selectedID=${e.userID}&concentratorID=${e.concentratorID}" class="btnEdit" mask="true"  >修改</a>	
				<a title="确定要删除该用户的权限？" target="ajaxTodo" href="device/ConcentratorManage!deletePermission.action?selectedID=${e.userID}&concentratorID=${e.concentratorID}" class="btnDel"  callback="dialogAjaxDoneFather" style="margin-left:10px;">删除</a>

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
