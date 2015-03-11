<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent" style="padding:1px">
	<div class="tabs">

		<div class="tabsContent">
			<div>
	
				<jsp:include page="buildingTree.jsp"/>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
				   <c:set var="table" value="${table}" scope="request"/>
					<c:set var="company" value="${company}" scope="request"/>
			 
					<jsp:include page="buildingList.jsp"/>
				</div>
	
			</div>

		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
</div>


	




	

