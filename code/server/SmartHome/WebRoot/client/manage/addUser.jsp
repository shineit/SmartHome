<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="pageHeader">
	<form id="pagerForm" method="post" action="sys/UserManage!addUser.action" onsubmit="return dwzSearch(this, 'dialog');">
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />	
	<div class="searchBar">
		
		<table class="searchContent">
			<tr>
<td>
					用户名称：<input type="text" name="filter.userName" value="${filter.userName}" />
				</td>				
				<td>
					<select  name="filter.accountType" >
						 <option value="">账户类型</option>

					 <c:forEach var="ut2" items="${filter.userTypeList}">
						  		 <c:choose>		       
							   		 <c:when test="${ut2.type == filter.accountType}">  
	                            		       <option value="${ut2.type}" selected='selected'> ${ut2.type}</option>
	                            		 
	                            	  </c:when>
							   		  <c:otherwise>  
							   	   			    <option value="${ut2.type}" > ${ut2.type}</option>
							   		   </c:otherwise>
							   
						   		 </c:choose>
						  </c:forEach>								
				 											

					</select>
				</td>

				<td class="dateRange">
					注册时间:
					<input type="text"  readonly="readonly" class="date" name="filter.startDate" value="${filter.startDate}"/>
					<span class="limit">-</span>
					<input type="text"  readonly="readonly" class="date" name="filter.endDate" value="${filter.endDate}"/>
				</td>				
			</tr>

		</table> 
		<div class="subBar">
			<ul>
			    <li><button type="submit" class="mispButton primary" style="margin-right:15px;">查 询</button></li>
            	<li><button class="mispButton primary" onclick="resetForm(this.form)">重 置</button></li>
<!-- 				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查 询</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="resetForm(this.form);">重 置</button></div></div></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
<s:form    method="POST"  name="userAddForm" action="sys/UserManage!addUser.action" >
	<table class="table" layoutH="110" targetType="dialog" width="100%">
		<thead>
				
			<tr>
				<th width="20%" align="center">用户编号</th>
				<th width="20%" align="center">用户名称</th>
				<th width="15%" align="center">账号类型</th>
				<th width="25%" align="center">注册时间</th>
				<th width="10%" align="center">用户状态</th>
				<th width="10%" align="center">添加</th>
			</tr>
		</thead>
		<tbody>

		<c:forEach var="e" items="${table.currentPageData}"> 
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
				<td>${e.regDate}</td>
				<td>
					<c:forEach var="st" items="${filter.userStatusList}">
						  <c:choose>		       
							   <c:when test="${st.intValue == e.status}">  
	                             ${st.strValue}
							   </c:when>
							   <c:otherwise>  
							   	   
							   </c:otherwise>
							   
						   </c:choose>
				    </c:forEach>
				</td>
				<td>
					<a class="btnSelect"  href="javascript:$.bringBack({userID:'${e.userID}'})" title="添加用户">选择</a>
				</td>
			</tr>
		</c:forEach>

		</tbody>
	</table>
</s:form>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${table.page}" scope="request"/>
			
			<select class="combox"  onchange="dwzPageBreak({targetType:'dialog',rel:'',data:{numPerPage:this.value}})">
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
		<div class="pagination" targetType="dialog" totalCount="${page.count}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.currentPage}"></div>
	</div>
</div>
