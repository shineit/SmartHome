<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="pageHeader">
	<form id="pagerForm" method="post" action="device/ConcentratorManage!showSensor.action?selectedID=${concentratorID}" onsubmit="return navTabSearch(this);" >
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />	
	<div class="searchBar">
		
		<table class="searchContent">
			<tr>
<td>
					传感器编号：<input type="text" name="hsFilter.sensorID" value="${hsFilter.sensorID}" />
				</td>				
				<td>
					传感器种类：
					<select  name="hsFilter.sensorKind" >
						 <option value="">所有种类</option>
					<c:forEach var="sk" items="${hsFilter.sensorKindList}">

 						  		 <c:choose>		       
							   		 <c:when test="${sk.strValue == hsFilter.sensorKind}">  
	                            		       <option value="${sk.strValue}" selected='selected'> ${sk.strValue}</option>
	                            		 
	                            	  </c:when>
							   		  <c:otherwise>  
							   	   			    <option value="${sk.strValue}" > ${sk.strValue}</option>
							   		   </c:otherwise>
							   
						   		 </c:choose> 
					</c:forEach>
				 											

					</select>
				</td>
				<td>
					<s:submit  value="查 询" cssClass="mispButton primary"></s:submit>
				</td>
				<td>
					<s:submit  value="重 置" cssClass="mispButton primary"  onclick="resetForm(this.form) "></s:submit>
					
				</td>
			    <td>
			    	<button class="mispButton primary close" type="button">返 回</button>
			    </td>
			</tr>

		</table> 

	</div>
	</form>
</div>
<div class="pageContent">
<s:form    method="POST"  name="" action="device/ConcentratorManage!showSensor.action" >
	<table class="table" layoutH="85" targetType="dialog" width="100%">
		<thead>
				
			<tr>
				<th width="15%" align="center">集中器编号</th>
				<th width="15%" align="center">终端编号</th>
				<th width="10%" align="center">通道编号</th>
				<th width="10%" align="center">传感器种类</th>
				<th width="20%" align="center">传感器类型</th>
				<th width="10%" align="center">预警值</th>
				<th width="10%" align="center">告警值</th>
				<th width="10%" align="center">操作</th>
			</tr>
		</thead>
		<tbody>

		<c:forEach var="e" items="${homeSensorTable.currentPageData}"> 
			<tr target="sid_user" rel="1">
				<td>${e.concentratorID}</td>
				<td>${e.sensorID}</td>
				<td>${e.channelID}</td>
				<td>	            	
					<c:forEach var="sk2" items="${hsFilter.sensorKindList}">
						  <c:choose>		       
							   <c:when test="${sk2.intValue == e.sensorKind}">  
	                             ${sk2.strValue}
							   </c:when>
							   <c:otherwise>		   	   
							   </c:otherwise>
							   
						   </c:choose>
					</c:forEach>
				</td>
				<td>${e.sensorTypeName}</td>
				<td>${e.warnValue}</td>
				<td>${e.errorValue}</td>
				<td>
					<a class="btnEdit"  rel="slDialog" target="dialog" mask="true" href="device/ConcentratorManage!configSensor.action?selectedID=${e.id}" title="配置传感器信息">编辑</a>
				</td>
			</tr>
		</c:forEach>

		</tbody>
	</table>
</s:form>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${homeSensorTable.page}" scope="request"/>
			
			<select class="combox"  onchange="navTabPageBreak({numPerPage:this.value})">
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


