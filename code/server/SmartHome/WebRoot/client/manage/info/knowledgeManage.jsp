<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageHeader">
	<s:form  id="pagerForm"  onsubmit="return navTabSearch(this);" action="info/KnowledgeManage" method="post" name="KnowledgeSearch">
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					标题：<input type="text" name="filter.title" value="${filter.title}" />
				</td>	
				<td>
					<select  name="filter.knowledgeType" >
						 <option value="">知识类型</option>

					 		<c:forEach var="t" items="${filter.typeList}">

 						  		 <c:choose>		       
							   		 <c:when test="${t.strValue == filter.knowledgeType}">  
	                            		<option value="${t.strValue}" selected='selected'> ${t.strValue}</option>
	                            		 
	                            	  </c:when>
							   		  <c:otherwise>  
							   	   		<option value="${t.strValue}" > ${t.strValue}</option>
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
			<li><a class="add" href="KnowledgeManage!show.action?operateType=create" target="dialog" mask="true" title="新增知识"><span>新增</span></a></li>
			<li><a class="delete" href="KnowledgeManage!deleteList.action"  target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>
				<th width="15%" align="center">公告编号</th>
				<th width="25%" align="center">标题</th>
 				<th width="15%" align="center">内容</th>
 				<th width="15%" align="center">知识种类</th>
 				<th width="15%" align="center">适用领域</th>
 				
			</tr>
		</thead>
		<s:form  id="KnowledgeForm"  method="POST"  name="KnowledgeForm" >
		<tbody>

 		<c:forEach var="e" items="${table.currentPageData}"> 	
			<tr target="sid_user" rel="${e.knowledgeID}">
				<td><input name="selectedIDList" value="${e.knowledgeID}" type="checkbox" style="margin-top:5px;"></td>
				<td>${e.knowledgeID}</td>
				<td>${e.title}</td>
				<td>
				<a style="color:blue !important;text-decoration:underline;" title="知识内容" target="dialog" href="<%=request.getContextPath()%>/client/manage/info/infoContent.jsp?infoContent=${e.content}"  rel="Menu${selectedMenuID}" mask="true" >查看内容详情</a>
				</td> 
				<td>            	
				<c:forEach var="tl" items="${filter.typeList}">
						  <c:choose>		       
							   <c:when test="${tl.intValue == e.knowledgeType}">  
	                             ${tl.strValue}
							   </c:when>
							   <c:otherwise></c:otherwise>					   
						   </c:choose>
					</c:forEach>
				</td>
				<td>            	
				<c:forEach var="kl" items="${filter.kindList}">
						  <c:choose>		       
							   <c:when test="${kl.intValue == e.knowledgeKind}">  
	                             ${kl.strValue}
							   </c:when>
							   <c:otherwise></c:otherwise>					   
						   </c:choose>
					</c:forEach>
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


