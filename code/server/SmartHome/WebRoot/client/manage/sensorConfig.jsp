<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent">
	<s:form method="post" action="device/ConcentratorManage" class="pageForm required-validate" name="concentForm" onsubmit="return iframeCallback(this,dialogAjaxDone);"  >
		<div class="pageFormContent" layoutH="58">
            <input type="hidden" name="homeSensor.sensorType" value="${homeSensor.sensorType}"/>
			<dl style="width:100%;">
				<dt style="width:15%;">传感器编号：</dt>			
				<dd style="width:80%;"><input type="text" name="homeSensor.id"  size="25" readonly="readonly" value="${homeSensor.id}"/></dd>		 
			</dl>

			<dl style="width:100%;">
				<dt style="width:15%;">传感器类型：</dt>			
				<dd style="width:80%;">
					<input  name="homeSensor.sensorTypeName" type="text" size="25" value="${homeSensor.sensorTypeName}" readonly="readonly"/>
					<a class="btnLook" href="device/ConcentratorManage!showSensorTypeList.action" lookupGroup="homeSensor" rel="" >类型列表</a>	
					<span class="info">(点击图标查找传感器类型)</span>
				
				</dd>
			</dl>
						
			<dl style="width:100%;">
				<dt style="width:15%;">预警值：</dt>			
				<dd style="width:80%;">
				<input type="text" name="homeSensor.warnValue"  size="25"  value="${homeSensor.warnValue}"/>
				</dd>
			</dl>	
			<dl style="width:100%;">
				<dt style="width:15%;">告警值：</dt>			
				<dd style="width:80%;">
				<input type="text" name="homeSensor.errorValue"  size="25"  value="${homeSensor.errorValue}"/>
				</dd>
			</dl>						
		</div>		

		<div class="formBar"  >
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" ><s:submit method="configSensorSure" value="修  改" cssClass="mispButton primary"></s:submit></li>

				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
