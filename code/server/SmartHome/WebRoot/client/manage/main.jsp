<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="panel collapse" minH="100" defH="200">
	<h1>今日公告</h1>
	<div align="center">

		<c:forEach var="n" items="${newsContent}">

			<div style="padding:5px 50px 10px 5px;">
				<span style="text-align:center;font-size:1.7em;color:orange;">${n.title}</span>
				<span style="float:right;">${n.date}</span>
			</div>
			<br>
			<div style="text-align:justify;font-size:1.2em;text-indent: 2em;line-height:25px;">
				${n.content}</div>
			<div class="divider"></div>
		</c:forEach>

	</div>

</div>
<form id="pagerForm" method="post" action="login/Index">
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>
<div class="pageCentent">

	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="Index!deleteList.action" onclick="submitForm('deleteList')" target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="315">
		<thead>
			<tr>
				<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>
				<th width="20%" align="center">集中器编号</th>
				<th width="20%" align="center">告警用户</th>
				<th width="20%" align="center">告警类型</th>
				<th width="20%" align="center">告警时间</th>
				<th width="15%" align="center">清除状态</th>
				
			</tr>
		</thead>
		<s:form  id="userForm"  method="POST"  name="userForm" >
		
		<tbody>
<!-- 		       <tr>
		       	<td><input name="selectedIDList" value="" type="checkbox" style="margin-top:5px;"></td>
		       	<td>000002</td>
		       	<td>A15_702</td>
		       	<td>火警</td>
		       	<td>2014-11-7 14:30:30.0</td>
		       	<td>已清除</td>
		       </tr> -->
		<c:forEach var="e" items="${alarmTable.currentPageData}"> 
			
			<tr target="sid_user" rel="${e.id}">
				<td><input name="selectedIDList" value="${e.id}" type="checkbox" style="margin-top:5px;"></td>
				<td>${e.id}</td>
				<td>${e.objID}</td>
				<td>${e.alarmType}
<%-- 						<c:forEach var="t" items="${filter.userTypeList}">
						  <c:choose>		       
							   <c:when test="${t.typeValue == e.role}">  
	                             ${t.type}
							   </c:when>
							   <c:otherwise>  
							   	   
							   </c:otherwise>
							   
						   </c:choose>
						   </c:forEach> --%>
						
 				</td>
				<td>${e.alarmTime}</td>
				<td>${e.clearStatus}
<%-- 					<c:forEach var="st" items="${filter.userStatusList}">
						  <c:choose>		       
							   <c:when test="${st.intValue == e.status}">  
	                             ${st.strValue}
							   </c:when>
							   <c:otherwise>  
							   	   
							   </c:otherwise>
							   
						   </c:choose>
				    </c:forEach> --%>
				</td>
			</tr>
		</c:forEach> 
	

		</tbody>
		</s:form>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${alarmTable.page}" scope="request"/>
			
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
