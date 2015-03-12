<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageHeader" >
	<s:form  id="pagerForm"  onsubmit="return navTabSearch(this);" action="device/HomeAlarmManage" method="post" >
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					集中器编号：<input type="text" name="filter.concentratorID" value="${filter.concentratorID}" />
				</td>	
                <td>
					告警类型：<input type="text" name="filter.alarmType"  value="${filter.alarmType}"/>
				</td>							
				<td>
					<select  name="filter.clearStatus" >
						 <option value="">清除状态</option>

					 		<c:forEach var="ac" items="${filter.alarmClearList}">

 						  		 <c:choose>		       
							   		 <c:when test="${ac.strValue == filter.clearStatus}">  
	                            		<option value="${ac.strValue}" selected='selected'> ${ac.strValue}</option>
	                            		 
	                            	  </c:when>
							   		  <c:otherwise>  
							   	   		<option value="${ac.strValue}" > ${ac.strValue}</option>
							   		   </c:otherwise>
							   
						   		 </c:choose> 
						    </c:forEach>								
				 											

					</select>
				</td>
				<td class="dateRange">
					告警时间:
					<input type="text"  readonly="readonly" class="date" name="filter.startDate" value="${filter.startDate}"/>
					<span class="limit">-</span>
					<input type="text"  readonly="readonly" class="date" name="filter.endDate" value="${filter.endDate}"/>
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

	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				
 				<th width="20%" align="center">集中器编号</th>
				<th width="15%" align="center">传感器类型</th>
				<th width="15%" align="center">传感器描述</th>
				<th width="10%" align="center">告警类型</th>
				<th width="10%" align="center">告警时间</th>
				<th width="10%" align="center">状态</th>
			</tr>
		</thead>
		<tbody>

 		<c:forEach var="e" items="${table.currentPageData}"> 	
			<tr target="sid_user" rel="${e.id}">
				<td>${e.concentratorID}</td>
				<td>${e.sensorTypeName}</td>			 
 				<td>${e.sensorDesp}</td>
				<td>${e.alarmType}</td>
				<td>${e.alarmTime}</td>
				<td>            	
				<c:forEach var="acl" items="${filter.alarmClearList}">
						  <c:choose>		       
							   <c:when test="${acl.intValue == e.clearStatus}">  
	                             ${acl.strValue}
							   </c:when>
							   <c:otherwise></c:otherwise>					   
						   </c:choose>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>  	


		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${table.page}" scope="request"/>
			
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



