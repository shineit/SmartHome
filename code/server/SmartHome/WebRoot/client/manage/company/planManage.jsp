<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent" >
	<div class="tabs">

		<div class="tabsContent">
			<div>
	
				<jsp:include page="buildingTree.jsp"/>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">


						<div class="pageHeader" style="border:1px #B8D0D6 solid">
							<s:form  id="pagerForm"  onsubmit="return navTabSearch(this);" action="device/PlanManage" method="post" >
								<input type="hidden" name="pageNum" value="${pageNum}" />
								<input type="hidden" name="numPerPage" value="${numPerPage}" />
							
							<div class="searchBar">
								<table class="searchContent">
									<tr>
									    <td>
					                     <input type="text" name="selectedID" style="display:none" value="${selectedID}"/>
					                    </td>

										<td>
											平面图名称：<input type="text" name="planName" value="${planName}"/>
										</td>
										<td>
											楼层：<input type="text" name="floor" value="${floor}" />
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
									<li><a class="add" href="PlanManage!show.action?selectedID=${selectedID}&operateType=create" target="dialog" mask="true" title="平面图信息"><span>新增</span></a></li>
									<li><a class="delete" href="PlanManage!deleteList.action" target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>删除</span></a></li>
								</ul>
							</div>
							<table class="table" width="100%" layoutH="125">
								<thead>
									<tr>
										<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>
										<th width="25%" align="center">平面图名称</th>
										<th width="15%" align="center">楼层</th>
										<th width="15%" align="center">描述</th>
						 
										
									</tr>
								</thead>
								<s:form  id="newsForm"  method="POST"  name="newsForm" >
								<tbody>

								<c:forEach var="e" items="${table.currentPageData}"> 	
									<tr target="sid_user" rel="${e.planID}">
										<td><input name="selectedIDList" value="${e.planID}" type="checkbox" style="margin-top:5px;"></td>
										<td>${e.name}</td>
										<td>${e.floor}</td>
									 
										<td>${e.desp}</td>
						 
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
								
								<div class="pagination" targetType="navTab" totalCount="${page.count}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.currentPage}"></div>

							</div>
						</div>
				</div>
	
			</div>

		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
