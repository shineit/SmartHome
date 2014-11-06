<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageHeader">
	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="device/PlanManage" method="POST" >
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	<div class="searchBar">

		<table class="searchContent">
			<tr >
				<td>
					平面图编号：<input type="text" name="" value=""/>
				</td>
			 
			 
				<td>
					用户名称：<input type="text" name="" value=""/>
				</td>
			
				<td>
					<select  name="" >
						 <option value="">用户类型</option>
<%-- 						 <c:forEach var="st" items="${filter.statusList}">
						  		 <c:choose>		       
							   		 <c:when test="${st.strValue == filter.orderStatus}">  
	                            		       <option value="${st.strValue}" selected='selected'> ${st.strValue}</option>
	                            		 
	                            	  </c:when>
							   		  <c:otherwise>  
							   	   			    <option value="${st.strValue}" > ${st.strValue}</option>
							   		   </c:otherwise>
							   
						   		 </c:choose>
						  </c:forEach>	 --%>							
				 		<option value="1">家庭</option>
				 		<option value="2">企业</option>									

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
	</s:form>
</div>
<div class="pageContent">

	<table class="table" width="100%" layoutH="86">
		<thead>
			<tr>
			
				<th width="150" align="center">编号</th>
				<th width="150" align="center">用户名称</th>
				<th width="200" align="center">描述</th>
				<th width="150" align="center">用户类型</th>
				<th width="80" align="center">操作</th>

			</tr>
		</thead>
	<s:form  id="order"  method="POST"  name="order" >			
	<tbody>
			<tr>
            <td>P00021H</td>
            <td>test1</td>
            <td>一楼客厅报警器安装示意图</td>
            <td>家庭</td>
            <td><a title="查看" target="dialog" href="#" class="btnEdit" >查看</a></td>
            </tr>
<%-- 	    <c:forEach var="e" items="${serviceOrderTable.currentPageData}"> 		
	        <tr target="sid_user" rel="${e.orderID}" > 
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
	        	<td><a title="处理申请" target="dialog" href="info/OrderManage!show.action?selectedID=${e.orderID}" class="btnEdit" >处理</a>
	        	</td>
			</tr>
		</c:forEach>  --%>
			
		</tbody>
		</s:form>
	</table>
		<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
<%-- 	<div class="panelBar">
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

	</div> --%>
</div>