<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

 
				<div layoutH="12" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">

						<li><a href="device/BuildingManage?companyID=${company.companyID}&selectedID=${company.companyID}" target="navTab" rel="buildingManage">${company.companyName}</a>
							<ul>
							<li style="display:none"><a href="" target="ajax" >aaa</a></li>
							  <c:forEach var="b" items="${buildingList}"> 	
							    
								<li><a href="device/PlanManage?companyID=${company.companyID}&selectedID=${b.building.buildingID}" target="navTab" rel="planManage">${b.building.name}</a>
									<ul>
									    <li style="display:none"><a href="" target="ajax" >aaa</a></li>
 
									   <c:forEach var="e" items="${b.planList}"> 	
									
										  <li><a href="device/FireSensorManage?companyID=${company.companyID}&selectedID=${e.planID}" target="navTab" rel="sensorManage">${e.name}</a></li>
 										  
 									   </c:forEach>	
										
									</ul>
								</li>
 
							 </c:forEach>	
							</ul>
						</li>
						
				     </ul>
				</div>
				
				 

	

