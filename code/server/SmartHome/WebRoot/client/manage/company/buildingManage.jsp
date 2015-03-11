<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent" style="padding:1px">
	<div class="tabs">

		<div class="tabsContent">
			<div>
	
				<div layoutH="12" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">

						<li><a href="device/BuildingManage?selectedID=${company.companyID}" target="ajax" rel="jbsxBox">${company.companyName}</a>
							<ul>
							  <c:forEach var="b" items="${buildingList}"> 	
							    
								<li><a href="device/PlanManage?selectedID=${b.building.buildingID}" target="ajax" rel="jbsxBox">${b.building.name}</a>
									<ul>
									   <c:forEach var="e" items="${b.planList}"> 	
									
										  <li><a href="device/FireSensorManage?selectedID=${e.planID}" target="ajax" rel="jbsxBox">${e.name}</a></li>
 										  
 									   </c:forEach>	
										
									</ul>
								</li>
							 </c:forEach>	
							</ul>
						</li>
				     </ul>
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
				   <c:set var="table" value="${table}" scope="request"/>
					
					<jsp:include page="buildingList.jsp"/>
				</div>
	
			</div>

		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
</div>


	




	

