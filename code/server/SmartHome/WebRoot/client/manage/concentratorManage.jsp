<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

function submitFormC(url){
    var thisForm = document.concentForm;
	thisForm.action="device/ConcentratorManage!"+url;
	return validateCallback(thisForm,navTabAjax);
}

</script>
<div class="pageHeader">
	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="device/ConcentratorManage" method="POST" >
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	<div class="searchBar">

		<table class="searchContent">
			<tr >
				<td>
					集中器编号：<input type="text" name="filter.concentratorID" value="${filter.concentratorID}"/>
				</td>
 
				<td>
					集中器描述：<input type="text" name="filter.description" value="${filter.description}"/>
				</td>
				<td>
					<select  name="filter.status" >
						 <option value="">集中器状态</option>

					 		<c:forEach var="st" items="${filter.concentStatusList}">

 						  		 <c:choose>		       
							   		 <c:when test="${st.strValue == filter.status}">  
	                            		       <option value="${st.strValue}" selected='selected'> ${st.strValue}</option>
	                            		 
	                            	  </c:when>
							   		  <c:otherwise>  
							   	   			    <option value="${st.strValue}" > ${st.strValue}</option>
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
	</s:form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="device/ConcentratorManage!deleteList.action" onclick="submitFormC('deleteList')" target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>			
				<th width="15%" align="center">编号</th>
				<th width="15%" align="center">IP地址</th>
				<th width="15%" align="center">名称</th>
				<th width="30%" align="center">集中器描述</th>
				<th width="10%" align="center">状态</th>
				<th width="10%" align="center">操作</th>

			</tr>
		</thead>
	<s:form  id="concentForm"  method="POST"  name="concentForm" >			
	<tbody>
	
	    <c:forEach var="e" items="${concentTable.currentPageData}"> 		
	        <tr target="sid_user" rel="${e.concentratorID}" >
				<td><input name="selectedIDList" value="${e.concentratorID}" type="checkbox" style="margin-top:5px;"></td>	        
	            <td>${e.concentratorID}</td>
	            <td>${e.ipAddr}</td>
	            <td>${e.name}</td>
	            <td>${e.description}</td>
	            <td>
	            	<c:forEach var="t" items="${filter.concentStatusList}">
						  <c:choose>		       
							   <c:when test="${t.intValue == e.status}">  
	                             ${t.strValue}
							   </c:when>
							   <c:otherwise>  
							   	   
							   </c:otherwise>
							   
						   </c:choose>
					</c:forEach>
	            </td>
	            <td>
	            <a title="集中器信息修改" target="dialog" href="device/ConcentratorManage!show.action?selectedID=${e.concentratorID}" class="btnEdit" >修改</a>
	            </td> 

			</tr>
		</c:forEach>
			
		</tbody>
		</s:form>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${concentTable.page}" scope="request"/>
			     
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