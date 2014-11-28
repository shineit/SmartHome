<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageHeader">
	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="info/OrderManage" method="POST" >
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	<div class="searchBar">

		<table class="searchContent">
			<tr >
				<td>
					服务单号：<input type="text" name="filter.orderID" value="${filter.orderID}"/>
				</td>
			 
			 
				<td>
					名称：<input type="text" name="filter.orderName" value="${filter.orderName}"/>
				</td>
			
				<td>
					<select  name="filter.orderStatus" >
						 <option value="">处理状态</option>
						 <c:forEach var="st" items="${filter.statusList}">
						  		 <c:choose>		       
							   		 <c:when test="${st.strValue == filter.orderStatus}">  
	                            		       <option value="${st.strValue}" selected='selected'> ${st.strValue}</option>
	                            		 
	                            	  </c:when>
							   		  <c:otherwise>  
							   	   			    <option value="${st.strValue}" > ${st.strValue}</option>
							   		   </c:otherwise>
							   
						   		 </c:choose>
						  </c:forEach>								
				 											

					</select>					
				</td>
				<td class="dateRange">
					提交时间:
					<input type="text"  readonly="readonly" class="date" name="filter.startDate" value="${filter.startDate}"/>
					<span class="limit">-</span>
					<input type="text"  readonly="readonly" class="date" name="filter.endDate" value="${filter.endDate}"/>
				</td>
				<td>
					<s:submit  value="查 询" cssClass="mispButton "></s:submit>
				</td>
				<td>
					<s:submit  value="重 置" cssClass="mispButton "  onclick="resetForm(this.form) "></s:submit>
					
				</td>					 
			</tr>

		</table>

	</div>
	</s:form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="OrderManage!deleteList.action" onclick="submitFormN('deleteList')" target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
			<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>
				<th width="100" align="center">服务单号</th>
				<th width="150" align="center">名称</th>
				<th width="80" align="center">服务类型</th>
				<th width="150" align="center">提交时间</th>
				<th width="100" align="center">提交人</th>
				<th width="80" align="center">处理状态</th>	
				<th width="100" align="center">处理人</th>
				<th width="150" align="center">处理时间</th>
				<th width="80" align="center">操作</th>

			</tr>
		</thead>
	<s:form  id="order"  method="POST"  name="order" >			
	<tbody>

	    <c:forEach var="e" items="${table.currentPageData}"> 		
	        <tr target="sid_user" rel="${e.orderID}" > 
	           	<td><input name="selectedIDList" value="${e.orderID}" type="checkbox" style="margin-top:5px;"></td>
	            <td>${e.orderID}</td>
	            <td>${e.orderName}</td>
	            <td>
	            	<c:forEach var="ot" items="${filter.typeList}">
						  <c:choose>		       
							   <c:when test="${ot.intValue == e.orderType}">  
	                             ${ot.strValue}
							   </c:when>
							   <c:otherwise></c:otherwise>					   
						   </c:choose>
					</c:forEach>
	            </td>
	            <td>${e.createTime}</td>
	            <td>${e.creator}</td>
	            <td>
	            	<c:forEach var="t" items="${filter.statusList}">
						  <c:choose>		       
							   <c:when test="${t.intValue == e.orderStatus}">  
	                             ${t.strValue}
							   </c:when>
							   <c:otherwise></c:otherwise>					   
						   </c:choose>
					</c:forEach>
	            	
	            </td>
	            <td>${e.handler}</td>
	            <td>${e.handleTime}</td>
	        	<td><a title="处理申请" target="dialog" href="info/OrderManage!show.action?selectedID=${e.orderID}&operateType=modify" class="btnEdit" >处理</a>
	        	</td>
			</tr>
		</c:forEach> 
			
		</tbody>
		</s:form>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${serviceOrderTable.page}" scope="request"/>
			     
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