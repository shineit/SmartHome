<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<form id="pagerForm" method="post" action="expert/ReportManage">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="3" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="logList.page.pageSize" value="20" />
	<input type="hidden" name="logList.page.currentPage" value="${logList.page.currentPage}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="log/LogManage" method="post">
	<div class="searchBar">

		<table class="searchContent">
			<tr >
				<td>
					日志编号：<input type="text" name="filter.id" />
				</td>
			 
			 
				<td>
					操作人：<input type="text" name="filter.user" />
				</td>
	
				
				<td>
					操作：<input type="text" name="filter.name" />
				</td>
 
			</tr>
			
			<tr >
				<td class="dateRange">
					操作时间段:
					<input type="text" value="" readonly="readonly" class="date" name="filter.startTime">
					<span class="limit">-</span>
					<input type="text" value="" readonly="readonly" class="date" name="filter.endTime">
				</td>
				 

				<td><div class="buttonActive"><div class="buttonContent"><button type="submit" >查 询</button></div></div></td>
			</tr>
		</table>

	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">

	</div> 
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80" align="center">编号</th>
				<th width="120" align="center">操作人</th>
				<th width="80" align="center">操作名称</th>
				<th width="100" align="center">操作结果</th>
				<th width="150" align="center">操作时间</th>
				<th width="150" align="center">操作</th>
			</tr>
		</thead>
	<tbody>
	    <c:forEach var="e" items="${logList.currentPageData}"> 		
	        <tr target="sid_user" rel="1" >
	            <td>${e.id}</td>
	            <td>${e.user}</td>
	            <td>${e.name}</td>
	            <td>${e.result}</td>
	            <td>${e.operTime}</td>
	            <td>
 					<a title="诊断报告" target="navTab" rel="report" href="LogManage!delete.action?selectedID=${e.id}&operateType=delete" class="btnEdit">删除</a>
				</td>
			</tr>
		</c:forEach>
 

		
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${logList.page}" scope="request"/>
			
			<select class="combox" name="logList.page.currentPage"  onchange="navTabPageBreak({numPerPage:this.value})">
				<c:forEach var="e" items="${page.pageSizeList}"> 	
			       <c:if test="${e==page.currentPage}">
			         <option value="${e}" selected>${e}</option>
				  </c:if>
                  <c:if test="${e!=page.currentPage}">
			         <option value="${e}">${e}</option>
				  </c:if>
				</c:forEach>
 
			</select>
			<span>条，共${page.count}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.count}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.currentPage}"></div>

	</div>
</div>