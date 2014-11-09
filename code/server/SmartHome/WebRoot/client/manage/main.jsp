<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">

function submitFormAlarm(url){
    var thisForm = document.alarmForm;
	thisForm.action="login/Index!"+url;
	return validateCallback(thisForm,navTabAjax);
}

</script>
<div class="panel " minH="100" defH="200" >
	<h1>今日公告</h1>
	<div align="center">
			<c:choose>
				<c:when test="${newsContent==null || fn:length(newsContent)==0}">
					<div style="padding:5px 50px 10px 5px;">
						<span style="text-align:center;font-size:1.7em;color:orange;">今日暂无公告</span>

					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="n" items="${newsContent}" >
					<div style="padding:5px 50px 10px 5px;">
						<span style="text-align:center;font-size:1.7em;color:orange;">${n.title}</span>
						<span style="float:right;">${n.date}</span>
					</div>
					<br>
					<div
						style="text-align:justify;font-size:1.2em;text-indent: 2em;line-height:25px;">
						${n.content}</div>
					<div class="divider"></div>	
					</c:forEach>
				</c:otherwise>
			</c:choose>
	</div>

</div>
<form id="pagerForm" method="post" action="login/Index">
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>
<div class="pageHeader">
<h1 style="font-size:1.2em;color:red;">告警消息</h1>
</div>
<div class="pageCentent">

	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="Index!deleteList.action" onclick="submitFormAlarm('deleteList')" target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="340">
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
		<s:form  id="alarmForm"  method="POST"  name="alarmForm" >
		
		<tbody>
		<c:forEach var="e" items="${alarmTable.currentPageData}"> 
			
			<tr target="sid_user" rel="${e.id}">
				<td><input name="selectedIDList" value="${e.id}" type="checkbox" style="margin-top:5px;"></td>
				<td>${e.id}</td>
				<td>${e.objID}</td>
				<td>
					<c:forEach var="at" items="${alarmFilter.alarmTypeList}">
						  <c:choose>		       
							   <c:when test="${at.intValue == e.alarmType}">  
	                             ${at.strValue}
							   </c:when>
							   <c:otherwise>  
							   	   
							   </c:otherwise>
							   
						   </c:choose>
						   </c:forEach>
						
 				</td>
				<td>${e.alarmTime}</td>
				<td>

					<c:choose>
					<c:when test="${e.statusColor=='red'}">
					<span class="label label-danger" >请尽快清除</span>
					</c:when>
					<c:otherwise>
					<span class="label label-success" >
					<c:forEach var="cl" items="${alarmFilter.alarmClearList}">
						  <c:choose>		       
							   <c:when test="${cl.intValue == e.clearStatus}">  
	                             ${cl.strValue}
							   </c:when>
							   <c:otherwise></c:otherwise>
							   
						   </c:choose>
				    </c:forEach> 						
					</span>
					</c:otherwise>
					</c:choose>

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
