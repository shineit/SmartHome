<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageHeader" >
	<s:form  id="pagerForm"   onsubmit="return navTabSearch(this);"  action="device/BuildingManage" method="post" >
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
                <td>
					 <input type="text" name="companyID" style="display:none" value="${company.companyID}"/>
				</td>
				<td>
					楼宇名称：<input type="text" name="buildingName" value="${buildingName}"/> 
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

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="BuildingManage!show.action?selectedID=${company.companyID}&operateType=create" target="dialog" mask="true" title="新增单位"><span>新增</span></a></li>
			<li><a class="delete" href="BuildingManage!deleteList.action" target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>删除</span></a></li>
  	
  		</ul>
	</div>
	<table class="table" width="100%" layoutH="123">
		<thead>
			<tr>
				<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>
 				<th width="15%" align="center">楼宇名称</th>
				<th width="20%" align="center">描述</th>
				<th width="20%" align="center">楼宇地址</th>
				<th width="10%" align="center">操作</th>
				
			</tr>
		</thead>
		<s:form   method="POST"   rel="jbsxBox">
		<tbody>

 		<c:forEach var="e" items="${table.currentPageData}"> 	
			<tr target="sid_user" rel="${e.buildingID}">
				<td><input name="selectedIDList" value="${e.buildingID}" type="checkbox" style="margin-top:5px;"></td>
				<td>${e.name}</td>
				<td>${e.desp}</td>			 
 				<td>${e.addr}</td>
 	            <td style="text-align: center;">
             	<a title="楼层信息修改" target="dialog" href="device/BuildingManage!show.action?selectedID=${e.buildingID}&operateType=modify" 
             		class="btnEdit" mask="true" rel="configBuilding"></a>
	            </td> 
			</tr>
		</c:forEach>  	

		</tbody>
		</s:form>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${table.page}" scope="request"/>
			
			<select class="combox" onchange="navTabPageBreak({numPerPage:this.value}, 'jbsxBox')">
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
		
		<div class="pagination" rel="jbsxBox" totalCount="${page.count}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.currentPage}"></div>

	</div>
</div>