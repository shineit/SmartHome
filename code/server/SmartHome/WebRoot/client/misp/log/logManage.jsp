<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

function submitForm(url){
    var thisForm = document.logForm;
	thisForm.action="log/LogManage!"+url;
	return validateCallback(thisForm,navTabAjax);
}

</script>
<div class="pageHeader">
	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="log/LogManage" method="POST" >
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	<div class="searchBar">

		<table class="searchContent">
			<tr >
				<td>
					日志编号：<input type="text" name="filter.id" value="${filter.id}"/>
				</td>
			 
			 
				<td>
					操作人：<input type="text" name="filter.user" value="${filter.user}"/>
				</td>
	
				
				<td>
					操作：<input type="text" name="filter.name" value="${filter.name}"/>
				</td>
				<td class="dateRange">
					操作时间段:
					<input type="text"  readonly="readonly" class="date" name="filter.startTime" value="${filter.startTime}">
					<span class="limit">-</span>
					<input type="text"  readonly="readonly" class="date" name="filter.endTime" value="${filter.endTime}">
				</td>
				<td>
					<s:submit  value="查 询" cssClass="mispButton primary"></s:submit>
				</td>
				<td>
					<s:submit  value="重 置" cssClass="mispButton primary"  onclick="resetForm(this.form) "></s:submit>
					
				</td>					 
			</tr>

		</table>

	</div>
	</s:form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="log/LogManage!deleteList.action" onclick="submitForm('deleteList')" target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>删除操作日志</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="118">
		<thead>
			<tr>
				<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>			
				<th width="80" align="center">编号</th>
				<th width="100" align="center">操作人</th>
				<th width="80" align="center">操作名称</th>
				<th width="100" align="center">操作对象</th>
				<th width="100" align="center">操作结果</th>
				<th width="150" align="center">操作时间</th>

			</tr>
		</thead>
	<s:form  id="logForm"  method="POST"  name="logForm" >			
	<tbody>
	
	    <c:forEach var="e" items="${logList.currentPageData}"> 		
	        <tr target="sid_user" rel="${e.id}" >
				<td><input name="selectedIDList" value="${e.id}" type="checkbox" style="margin-top:5px;"></td>	        
	            <td>${e.id}</td>
	            <td>${e.user}</td>
	            <td>${e.name}</td>
	            <td>${e.object}</td>
	            <td>${e.result}</td>
	            <td>${e.operTime}</td>

			</tr>
		</c:forEach>
			
		</tbody>
		</s:form>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${logList.page}" scope="request"/>
			     
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