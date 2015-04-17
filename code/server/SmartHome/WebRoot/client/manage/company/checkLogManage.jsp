<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageHeader" >
	<s:form  id="pagerForm"  onsubmit="return navTabSearch(this);" action="device/CheckLogManage" method="post" >
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					项目名称：<input type="text" name="filter.checkItem" value="${filter.checkItem}" />
				</td>	
				<td>
					公司名称：<input type="text" name="filter.companyName" value="${filter.companyName}" />
				</td>				
				<td>
					<select  name="filter.checkResult" >
						 <option value="">巡检结果</option>

					 		<c:forEach var="rl" items="${filter.resultList}">

 						  		 <c:choose>		       
							   		 <c:when test="${rl.strValue == filter.checkResult}">  
	                            		<option value="${rl.strValue}" selected='selected'> ${rl.strValue}</option>
	                            		 
	                            	  </c:when>
							   		  <c:otherwise>  
							   	   		<option value="${rl.strValue}" > ${rl.strValue}</option>
							   		   </c:otherwise>
							   
						   		 </c:choose> 
						    </c:forEach>								
				 											

					</select>
				</td>
				<td class="dateRange">
					巡检时间:
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
		<ul class="toolBar">
			<li><a class="delete" href="CheckLogManage!deleteList.action" target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>
 				<th width="10%" align="center">公司编号</th>
 				<th width="20%" align="center">公司名称</th>
				<th width="15%" align="center">项目名称</th>
				<th width="15%" align="center">项目所属系统</th>
				<th width="10%" align="center">巡检结果</th>
				<th width="10%" align="center">巡检员</th>
				<th width="10%" align="center">巡检时间</th>

			</tr>
		</thead>
		<tbody>

 		<c:forEach var="e" items="${table.currentPageData}"> 	
			<tr target="sid_user" rel="${e.logID}">
			     <td><input name="selectedIDList" value="${e.logID}" type="checkbox" style="margin-top:5px;"></td>
				<td>${e.companyID}</td>
				<td>${e.companyName}</td>
				<td>${e.checkItem}</td>			 
 				<td>${e.checkSys}</td>
				<td>            	
				<c:forEach var="r" items="${filter.resultList}">
						  <c:choose>		       
							   <c:when test="${r.intValue == e.checkResult}"> 
							   ${r.strValue}
							   <c:if test="${e.checkResult==2}">
							   				
				               <a style="color:blue !important;text-decoration:underline;" title="异常信息" target="dialog" 
				               href="<%=request.getContextPath()%>/client/manage/company/checkLogAbdesp.jsp?infoContent=${e.abnormalDesp}&picUrl=${e.abnormalPic}" 
				                rel="Menu${selectedMenuID}" mask="true" >点击查看详情</a>

							   </c:if> 
							   </c:when>
							   <c:otherwise></c:otherwise>					   
						   </c:choose>
					</c:forEach>
				</td>
		
				<td>${e.checker}</td>
				<td>${e.checkTime}</td>
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




