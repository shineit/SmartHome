<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="pageHeader">
	<form id="pagerForm" method="post" action="device/SensorTypeManage" onsubmit="return dwzSearch(this, 'dialog');">
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />	
	<div class="searchBar">
		
		<table class="searchContent">
			<tr>
				<td>
					类型名称：<input type="text" name="stFilter.typeName" value="${stFilter.typeName}" />
				</td>
				<td>
					所属系统：
					<select  name="stFilter.typeSys" >
						 <option value="">所有系统</option>

					 <c:forEach var="sl" items="${stFilter.sysList}">
						  		 <c:choose>		       
							   		 <c:when test="${sl== stFilter.typeSys}">  
	                            		       <option value="${sl}" selected='selected'> ${sl}</option>
	                            		 
	                            	  </c:when>
							   		  <c:otherwise>  
							   	   			    <option value="${sl}" > ${sl}</option>
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
			</tr>

		</table> 

	</div>
	</form>
</div>
<div class="pageContent">
<s:form    method="POST"  name="" action="device/SensorTypeManage" >
	<table class="table" layoutH="90" targetType="dialog" width="100%">
		<thead>
				
			<tr>
				<th width="10%" align="center">类型编号</th>
				<th width="30%" align="center">类型名称</th>
				<th width="10%" align="center">预警值</th>
				<th width="10%" align="center">告警值</th>
				<th width="10%" align="center">部件代码</th>
				<th width="20%" align="center">所属系统</th>
				<th width="10%" align="center">操作</th>
			</tr>
		</thead>
		<tbody>

		<c:forEach var="e" items="${table.currentPageData}"> 
			<tr target="sid_user" rel="1">
				<td>${e.typeID}</td>
				<td>${e.typeName}</td>
				<td>${e.defWarnValue}</td>
				<td>${e.defErrorValue}</td>
				<td>${e.typeCode}</td>
				<td>${e.typeSys}</td>
				<td>
					<a class="btnSelect"  href="javascript:$.bringBack({sensorType:'${e.typeID}',sensorTypeName:'${e.typeName}',warnValue:'${e.defWarnValue}',errorValue:'${e.defErrorValue}'})" title="添加传感器类型">选择</a>
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
