<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageHeader">
	<s:form  id="pagerForm"  onsubmit="return navTabSearch(this);" action="sys/OrgManage" method="post" >
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					广告名称：<input type="text" name="adName" value="${adName}" />
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
			<li><a class="add" href="OrgManage!show.action?operateType=create" target="dialog" mask="true" title="广告信息"><span>新增</span></a></li>
			<li><a class="delete" href="OrgManage!deleteList.action"  target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>删除</span></a></li>
			<li><a class="edit" href="OrgManage!show.action?selectedID={sid_user}&operateType=modify"						
				target="dialog" mask="true" title="机构管理"><span>修改</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>
				<th width="15%" align="center">机构代码</th>
				<th width="15%" align="center">机构名称</th>
				<th width="15%" align="center">管理员用户</th>
				
				<th width="25%" align="center">机构描述</th>
 
			</tr>
		</thead>
		<s:form  method="POST"  >
		<tbody>

 		<c:forEach var="e" items="${table.currentPageData}"> 	
			<tr target="sid_user" rel="${e.org_uuid}">
 			
				<td ><input name="selectedIDList" value="${e.org_uuid}"  type="checkbox" style="margin-top:5px;"></td>
 				<td>${e.org_id}</td>
 				<td>${e.org_name}</td>
 				 <td>${e.admin_name}</td>
 				
  				<td>${e.org_desp}</td>
 
			</tr>
		</c:forEach>  	


		</tbody>
		</s:form>
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


